package org.enoch.snark.command;

import org.enoch.snark.module.AbstractModule;

public abstract class AbstractCommand {

    private AbstractModule sourceModule;

    AbstractCommand(AbstractModule module) {

        sourceModule = module;
    }

    public abstract void execute();
}
