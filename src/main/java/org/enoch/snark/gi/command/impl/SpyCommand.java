package org.enoch.snark.gi.command.impl;

import org.enoch.snark.common.DateUtil;
import org.enoch.snark.gi.macro.GIUrlBuilder;
import org.enoch.snark.gi.command.GICommand;
import org.enoch.snark.gi.macro.Fleet;
import org.enoch.snark.gi.macro.FleetSelector;
import org.enoch.snark.gi.macro.Mission;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;
import org.enoch.snark.instance.Universe;
import org.openqa.selenium.By;

import java.time.LocalTime;

import static org.enoch.snark.gi.command.CommandType.FLEET_REQUIERED;

public class SpyCommand extends GICommand {

    private final FleetSelector fleetSelector;
    private Planet target;
    private SourcePlanet source;
    private GIUrlBuilder giUrlBuilder;

    public SpyCommand(Universe universe, Planet target) {
        super(universe, FLEET_REQUIERED);

        this.target = target;
        this.source = universe.findNearestSource(target);

        giUrlBuilder = new GIUrlBuilder(universe);
        fleetSelector = new FleetSelector(universe.session);
    }

    @Override
    public void execute() {
        giUrlBuilder.openFleetView(source, target, Mission.SPY);
        fleetSelector.typeShip(Fleet.SON, 1);
        fleetSelector.next();

        final String duration = chromeDriver.findElement(By.id("duration")).getText();
        final LocalTime time = DateUtil.parse(duration);
        setSecoundToDelay(time.toSecondOfDay());
        fleetSelector.next();

        fleetSelector.start();
    }
}
