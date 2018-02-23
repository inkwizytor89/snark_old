package org.enoch.snark.gi.command;

import org.enoch.snark.gi.command.impl.PauseCommand;
import org.enoch.snark.instance.Universe;

import java.util.logging.Logger;

public abstract class AbstractCommand {
    protected static final Logger log = Logger.getLogger( AbstractCommand.class.getName() );

    private AbstractCommand afterCommand;
    private int secoundToDelay;
    protected Universe universe;
    private CommandType type;
    public int failed = 0;

    protected AbstractCommand(Universe universe, CommandType type) {
        this.universe = universe;
        this.type = type;
    }

    public abstract boolean execute();

    public void doAfter() {
        if(afterCommand == null) {
            return;
        }
        universe.commander.push(new PauseCommand(universe, afterCommand, secoundToDelay));
    }

    protected void setSecoundToDelayAfterCommand(int secoundToDelay) {
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
