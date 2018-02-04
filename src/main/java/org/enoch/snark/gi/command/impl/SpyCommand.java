package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.GIUrlBuilder;
import org.enoch.snark.gi.command.GICommand;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;
import org.enoch.snark.model.Universum;

import static org.enoch.snark.gi.GIUrlBuilder.PAGE_BASE_FLEET;
import static org.enoch.snark.gi.command.CommandType.FLEET_REQUIERED;

public class SpyCommand extends GICommand {

    private Planet target;
    private SourcePlanet source;

    public SpyCommand(Planet target) {
        super(FLEET_REQUIERED);

        this.target = target;
        this.source = Universum.findNearestSource(target);
    }

    @Override
    public void execute() {
        final GIUrlBuilder builder = new GIUrlBuilder(source)
                .setTarget(target)
                .setPage(PAGE_BASE_FLEET);
        selenium.open(builder.build());
        System.out.println();
    }
}
