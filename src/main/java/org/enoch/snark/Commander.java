package org.enoch.snark;

import org.enoch.snark.command.AbstractCommand;
import org.enoch.snark.command.CommandType;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Commander {

    private static final Logger log = Logger.getLogger( Commander.class.getName() );

    private static final int SLEEP_PAUSE = 10;

    private static Queue<AbstractCommand> fleetActionQueue = new LinkedList<>();
    private static Queue<AbstractCommand> interfaceActionQueue = new LinkedList<>();
    private static Queue<AbstractCommand> calculationActionQueue = new LinkedList<>();

    private Commander() {
        startInterfaceQueue();
        startCalculationQueue();
    }

    public static void createInstance() {
        new Commander();
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
        command.execute();
        AbstractCommand after = command.doAfter();
        if(after != null) {
            Commander.push(after);
        }
        log.info("Resolved "+command.toString()+" and prepared "+after);
    }

    private boolean isFreeFleetSlot() {
        // TODO: 2018-02-03 Set logic to the method
        return true;
    }

    public static void push(AbstractCommand command) {
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
            throw new RuntimeException("Invalid type of command");
        }
    }
}
