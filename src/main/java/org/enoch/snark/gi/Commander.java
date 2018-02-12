package org.enoch.snark.gi;

import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.gi.command.CommandType;
import org.enoch.snark.gi.command.impl.PauseCommand;
import org.enoch.snark.instance.Universe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Commander {

    private static final Logger log = Logger.getLogger( Commander.class.getName() );

    private static final int SLEEP_PAUSE = 10;

    private GISession session;

    private Queue<AbstractCommand> fleetActionQueue = new LinkedList<>();
    private Queue<AbstractCommand> interfaceActionQueue = new LinkedList<>();
    private Queue<AbstractCommand> calculationActionQueue = new LinkedList<>();
    private Universe universe;

    public Commander(Universe universe) {
        this.universe = universe;
        this.session = universe.session;
        startInterfaceQueue();
        startCalculationQueue();
    }

    private void startInterfaceQueue() {
        Runnable task = () -> {
            while(true) {

                if(!fleetActionQueue.isEmpty() && isFreeFleetSlot()) {
                    resolve(fleetActionQueue.remove());
                    continue;
                } else if(!interfaceActionQueue.isEmpty()) {
                    resolve(interfaceActionQueue.remove());
                    continue;
                }

//                if(session.isLoggedIn())    session.close();
                try {
                    TimeUnit.SECONDS.sleep(SLEEP_PAUSE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(task).start();
    }

    private void startCalculationQueue() {
        Runnable task = () -> {
            while(true) {
                while(!calculationActionQueue.isEmpty()) {
                    resolve(fleetActionQueue.remove());
                }
                try {
                    TimeUnit.SECONDS.sleep(SLEEP_PAUSE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(task).start();
    }

    private void resolve(AbstractCommand command) {
        if(command.requiredGI() && !session.isLoggedIn()) {
            session.open();
        }
        try {
            command.execute();
        }catch (Exception e) {
            e.printStackTrace();
            push(new PauseCommand(universe, command, 120));
        }
        command.doAfter();
        log.info("Executed "+command+ " prepare "+ command.getAfterCommand());
    }

    private boolean isFreeFleetSlot() {
        // TODO: 2018-02-03 Set logic to the method
        return true;
    }

    public void push(AbstractCommand command) {
        if (CommandType.FLEET_REQUIERED.equals(command.getType())) {
            fleetActionQueue.add(command);
            log.info("Inserted "+command.toString()+" into queue fleetActionQueue size "+fleetActionQueue.size());
        } else if (CommandType.INTERFACE_REQUIERED.equals(command.getType())) {
            interfaceActionQueue.add(command);
            log.info("Inserted "+command.toString()+" into queue interfaceActionQueue size "+interfaceActionQueue.size());
        } else if (CommandType.CALCULATION.equals(command.getType())) {
            calculationActionQueue.add(command);
            log.info("Inserted "+command.toString()+" into queue calculationActionQueue size "+calculationActionQueue.size());
        }else {
            throw new RuntimeException("Invalid typeShip of command");
        }
    }
}
