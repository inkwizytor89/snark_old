package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.GIUrlBuilder;
import org.enoch.snark.gi.command.GICommand;
import org.enoch.snark.gi.macro.Fleet;
import org.enoch.snark.gi.macro.FleetSelector;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;
import org.enoch.snark.instance.Universum;
import org.openqa.selenium.By;

import java.util.Date;

import static org.enoch.snark.gi.GIUrlBuilder.PAGE_BASE_FLEET;
import static org.enoch.snark.gi.command.CommandType.FLEET_REQUIERED;

public class SpyCommand extends GICommand {

    private Planet target;
    private SourcePlanet source;

    public SpyCommand(Universum universum, Planet target) {
        super(universum, FLEET_REQUIERED);

        this.target = target;
        this.source = universum.findNearestSource(target);
    }

    @Override
    public void execute() {
        final GIUrlBuilder builder = new GIUrlBuilder(universum.appProperties, source)
                .setTarget(target)
                .setPage(PAGE_BASE_FLEET);
        selenium.open(builder.build());
        new FleetSelector(universum.session).type(Fleet.SON, 1);
        selenium.click("continue");
//new Date()
        final String duration = chromeDriver.findElement(By.id("duration")).getText(); // format: 0:00:19 h
        System.out.println();
    }
}
