package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.macro.ShipEnum;
import org.enoch.snark.gi.macro.Mission;
import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.Fleet;
import org.enoch.snark.model.Planet;

public class SpyCommand extends SendFleetCommand {

    public SpyCommand(Universe universe, Planet target) {
        this(universe, target, 1);
    }

    public SpyCommand(Universe universe, Planet target, Integer count) {
        super(universe, target, Mission.SPY, new Fleet().put(ShipEnum.SON, count));
    }

}
