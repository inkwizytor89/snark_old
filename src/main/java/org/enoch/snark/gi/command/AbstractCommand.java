package org.enoch.snark.gi.command;

import org.enoch.snark.instance.Universe;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class AbstractCommand {
    protected static final Logger log = Logger.getLogger( AbstractCommand.class.getName() );

    private AbstractCommand afterCommand;
    private int secoundToDelay;
    protected Universe universe;
    private CommandType type;

    protected AbstractCommand(Universe universe, CommandType type) {
        this.universe = universe;
        this.type = type;
    }

    public abstract void execute();

    public void doAfter() {
        if(afterCommand == null) {
            return;
        }
        Runnable task = () -> {
            universe.session.sleep(TimeUnit.SECONDS, secoundToDelay);
            universe.commander.push(afterCommand);
        };

        new Thread(task).start();
    }

    protected void setSecoundToDelay(int secoundToDelay) {
        this.secoundToDelay = secoundToDelay;
    }

    public AbstractCommand getAfterCommand() {
        return afterCommand;
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
