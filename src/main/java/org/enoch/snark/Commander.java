package org.enoch.snark;

import org.enoch.snark.command.AbstractCommand;
import org.enoch.snark.command.CommandType;
import org.enoch.snark.common.CommandQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Commander {

    private static final Logger log = Logger.getLogger( Commander.class.getName() );

    private static final int SLEEP_PAUSE = 10;
    private static Commander instance  = null;

    private static CommandQueue fleetActionQueue = new CommandQueue(SLEEP_PAUSE);
    private static CommandQueue interfaceActionQueue = new CommandQueue(SLEEP_PAUSE);
    private static CommandQueue calculationActionQueue = new CommandQueue(SLEEP_PAUSE);

    private Commander() {
    }

    public static Commander getInstance() {
        if(instance == null) {
            instance = new Commander();
        }
        return instance;
    }

    public static void push(AbstractCommand command) {
        if (CommandType.FLEET_REQUIERED.equals(command.getType())) {
            fleetActionQueue.add(command);
        } else if (CommandType.INTERFACE_REQUIERED.equals(command.getType())) {
            interfaceActionQueue.add(command);
        } else if (CommandType.CALCULATION.equals(command.getType())) {
            calculationActionQueue.add(command);
        }else {
            throw new RuntimeException("Invalid type of command");
        }
    }
}
