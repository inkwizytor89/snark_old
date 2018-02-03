package org.enoch.snark.gi.command;

import org.enoch.snark.model.TargetPlanet;
import org.enoch.snark.module.farm.FarmModule;

public class SpyCommand extends AbstractCommand {
    public SpyCommand(TargetPlanet target) {
        super(new FarmModule());
    }

    @Override
    public void execute() {

    }
}
