package org.enoch.snark.gi;

import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.gi.command.CommandType;
import org.enoch.snark.gi.command.impl.PauseCommand;
import org.enoch.snark.gi.macro.GIUrlBuilder;
import org.enoch.snark.instance.Universe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Commander {

    private static final Logger log = Logger.getLogger( Commander.class.getName() );

    private static final int SLEEP_PAUSE = 10;

    private Universe universe;
    private GISession session;
    private int fleeFreeSlots = 0;
    private int expeditionFreeSlots = 0;

    private Queue<AbstractCommand> fleetActionQueue = new LinkedList<>();
    private Queue<AbstractCommand> interfaceActionQueue = new LinkedList<>();
    private Queue<AbstractCommand> calculationActionQueue = new LinkedList<>();

    public Commander(Universe universe) {
        this.universe = universe;
        this.session = universe.session;
        startInterfaceQueue();
        startCalculationQueue();
    }

    private void startInterfaceQueue() {
        Runnable task = () -> {
            while(true) {

                if(!fleetActionQueue.isEmpty() && isFleetFreeSlot()) {
                    resolve(fleetActionQueue.remove());
                    fleeFreeSlots--;
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
            command.failed++;
            if(command.failed < 5) {
                push(new PauseCommand(universe, command, 120));
            } else {
                System.err.println("\n\nTOTAL CRASH: "+command+"\n");
            }
        }
        command.doAfter();
        log.info("Executed "+command+ " prepare "+ command.getAfterCommand());
    }

    private boolean isFleetFreeSlot() {
        if(!session.isLoggedIn()) return false;
        if(fleeFreeSlots > 0)   return true;
        new GIUrlBuilder(universe).updateFleetStatus();
        if(fleeFreeSlots > 0)   return true;
        return false;
    }

    public void setFleeFreeSlots(int fleeFreeSlots) {
        this.fleeFreeSlots = fleeFreeSlots;
    }

    public void setExpeditionFreeSlots(int expeditionFreeSlots) {
        this.expeditionFreeSlots = expeditionFreeSlots;
    }

    public void push(AbstractCommand command) {
        if (CommandType.FLEET_REQUIERED.equals(command.getType())) {
            fleetActionQueue.add(command);
            log.info("Inserted "+command+" into queue fleetActionQueue size "+fleetActionQueue.size());
        } else if (CommandType.INTERFACE_REQUIERED.equals(command.getType())) {
            interfaceActionQueue.add(command);
            log.info("Inserted "+command+" into queue interfaceActionQueue size "+interfaceActionQueue.size());
        } else if (CommandType.CALCULATION.equals(command.getType())) {
            calculationActionQueue.add(command);
            log.info("Inserted "+command+" into queue calculationActionQueue size "+calculationActionQueue.size());
        }else {
            throw new RuntimeException("Invalid typeShip of command");
        }
    }
}
