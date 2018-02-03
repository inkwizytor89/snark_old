package org.enoch.snark.gi.command;

import org.enoch.snark.model.FleetBuilder;
import org.enoch.snark.module.AbstractModule;

public class SendFleetCommand extends AbstractCommand {

    private final FleetBuilder fleetBuilder;

    SendFleetCommand(AbstractModule module, FleetBuilder fleetBuilder) {
        super(module);
        this.fleetBuilder = fleetBuilder;
        type = CommandType.FLEET_REQUIERED;
    }

    @Override
    public void execute() {
    }
}
