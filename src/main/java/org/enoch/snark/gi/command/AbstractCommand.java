package org.enoch.snark.gi.command;

import org.enoch.snark.gi.Commander;
import org.enoch.snark.module.AbstractModule;

import java.util.concurrent.TimeUnit;

public abstract class AbstractCommand {

    private AbstractModule sourceModule;

    protected CommandType type = CommandType.CALCULATION;

    AbstractCommand(AbstractModule module) {

        sourceModule = module;
    }

    public abstract void execute();

    public void doAfter(AbstractCommand command, int secoundToDelay) {
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(secoundToDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Commander.push(command);
        };

        new Thread(task).start();
    }

    public CommandType getType() {
        return type;
    }

    public boolean requiredGI() {
        return type == CommandType.FLEET_REQUIERED || type ==CommandType.INTERFACE_REQUIERED;
    }
}
