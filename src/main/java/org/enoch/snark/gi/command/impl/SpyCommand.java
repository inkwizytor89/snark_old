package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.TargetPlanet;
import org.enoch.snark.model.Universum;
import org.enoch.snark.module.farm.FarmModule;

import static org.enoch.snark.gi.command.CommandType.FLEET_REQUIERED;

public class SpyCommand extends AbstractCommand {

    private Planet target;
    private Planet source;

    public SpyCommand(Planet target) {
        super(FLEET_REQUIERED);

        this.target = target;
//        Universum.findNearestSource(target);
    }

    @Override
    public void execute() {

    }
}
