package org.enoch.snark.command;

import org.enoch.snark.module.AbstractModule;

public abstract class AbstractCommand {

    private AbstractModule sourceModule;

    protected CommandType type = CommandType.CALCULATION;

    AbstractCommand(AbstractModule module) {

        sourceModule = module;
    }

    public abstract void execute();

    public AbstractCommand doAfter() {
        return null;
    }

    public CommandType getType() {
        return type;
    }
}
