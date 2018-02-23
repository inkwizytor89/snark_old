package org.enoch.snark.instance;

import org.enoch.snark.gi.GISession;
import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.gi.command.CommandType;
import org.enoch.snark.gi.command.impl.PauseCommand;
import org.enoch.snark.gi.macro.GIUrlBuilder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CommanderImpl implements Commander {

    private static final Logger log = Logger.getLogger( CommanderImpl.class.getName() );

    private static final int SLEEP_PAUSE = 10;

    private Universe universe;
    private GISession session;
    private int fleeFreeSlots = 0;
    private int expeditionFreeSlots = 0;

    private Queue<AbstractCommand> fleetActionQueue = new LinkedList<>();
    private Queue<AbstractCommand> interfaceActionQueue = new LinkedList<>();
    private Queue<AbstractCommand> calculationActionQueue = new LinkedList<>();

    public CommanderImpl(Universe universe) {
        this.universe = universe;
        this.session = universe.session;
        startInterfaceQueue();
        startCalculationQueue();
    }

    private void startInterfaceQueue() {
        Runnable task = () -> {
            while(true) {

                if(!fleetActionQueue.isEmpty() && isFleetFreeSlot()) {
                    resolve(fleetActionQueue.poll());
                    fleeFreeSlots--;
                    continue;
                } else if(!interfaceActionQueue.isEmpty()) {
                    resolve(interfaceActionQueue.poll());
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
                    resolve(calculationActionQueue.poll());
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
        boolean success;
        if(command.requiredGI() && !session.isLoggedIn()) {
            session.open();
        }
        try {
            success = command.execute();
        }catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        if(success) {
            command.doAfter();
            log.info("Executed "+command+ " prepare "+ command.getAfterCommand());
        } else {
            command.failed++;
            if (command.failed < 5) {
                push(new PauseCommand(universe, command, 10));
            } else {
                System.err.println("\n\nTOTAL CRASH: " + command + "\n");
            }
        }
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
            fleetActionQueue.offer(command);
            log.info("Inserted "+command+" into queue fleetActionQueue size "+fleetActionQueue.size());
        } else if (CommandType.INTERFACE_REQUIERED.equals(command.getType())) {
            interfaceActionQueue.offer(command);
            log.info("Inserted "+command+" into queue interfaceActionQueue size "+interfaceActionQueue.size());
        } else if (CommandType.CALCULATION.equals(command.getType())) {
            calculationActionQueue.offer(command);
            log.info("Inserted "+command+" into queue calculationActionQueue size "+calculationActionQueue.size());
        }else {
            throw new RuntimeException("Invalid typeShip of command");
        }
    }
}
