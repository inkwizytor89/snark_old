package org.enoch.snark.command;

import org.enoch.snark.model.FleetBuilder;
import org.enoch.snark.module.AbstractModule;

import java.util.HashMap;

public class SendFleetComand extends AbstractCommand {

    private final FleetBuilder fleetBuilder;

    SendFleetComand(AbstractModule module, FleetBuilder fleetBuilder) {
        super(module);
        this.fleetBuilder = fleetBuilder;
        type = CommandType.FLEET_REQUIERED;
    }

    @Override
    public void execute() {

    }
}
