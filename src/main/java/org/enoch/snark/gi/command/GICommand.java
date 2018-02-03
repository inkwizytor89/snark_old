package org.enoch.snark.gi.command;

import org.enoch.snark.module.AbstractModule;

public abstract class GICommand extends AbstractCommand {

    GICommand(AbstractModule module) {
        super(module);
    }
}
