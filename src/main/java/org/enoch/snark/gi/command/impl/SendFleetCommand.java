package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.model.FleetBuilder;
import org.enoch.snark.module.AbstractModule;

import static org.enoch.snark.gi.command.CommandType.FLEET_REQUIERED;

public class SendFleetCommand extends AbstractCommand {

    private final FleetBuilder fleetBuilder;

    SendFleetCommand(FleetBuilder fleetBuilder) {
        super(FLEET_REQUIERED);
        this.fleetBuilder = fleetBuilder;
    }

    @Override
    public void execute() {
    }
}