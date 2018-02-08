package org.enoch.snark.gi.command;

import org.enoch.snark.instance.Universum;

import java.util.concurrent.TimeUnit;

public abstract class AbstractCommand {

    private AbstractCommand afterCommand;
    private int secoundToDelay;
    protected Universum universum;
    private CommandType type;

    protected AbstractCommand(Universum universum, CommandType type) {
        this.universum = universum;
        this.type = type;
    }

    public abstract void execute();

    public void doAfter() {
        if(afterCommand == null) {
            return;
        }
        Runnable task = () -> {
            universum.session.sleep(TimeUnit.SECONDS, secoundToDelay);
            universum.commander.push(afterCommand);
        };

        new Thread(task).start();
    }

    protected void setSecoundToDelay(int secoundToDelay) {
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
