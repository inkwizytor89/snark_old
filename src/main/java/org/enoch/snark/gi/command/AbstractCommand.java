package org.enoch.snark.gi.command;

import org.enoch.snark.gi.Commander;

import java.util.concurrent.TimeUnit;

public abstract class AbstractCommand {

    private AbstractCommand afterCommand;
    private int secoundToDelay;
    private CommandType type = CommandType.CALCULATION;

    protected AbstractCommand(CommandType type) {
        this.type = type;
    }

    public abstract void execute();

    public void doAfter() {
        if(afterCommand == null) {
            return;
        }
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(secoundToDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Commander.push(afterCommand);
        };

        new Thread(task).start();
    }

    public void setSecoundToDelay(int secoundToDelay) {
        this.secoundToDelay = secoundToDelay;
    }

    public void setAfterCommand(AbstractCommand afterCommand) {
        this.afterCommand = afterCommand;
    }

    public CommandType getType() {
        return type;
    }

    public boolean requiredGI() {
        return type == CommandType.FLEET_REQUIERED || type ==CommandType.INTERFACE_REQUIERED;
    }
}
