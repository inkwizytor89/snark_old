package org.enoch.snark.module.farm;

import org.enoch.snark.Commander;
import org.enoch.snark.command.SpyCommand;
import org.enoch.snark.model.TargetPlanet;
import org.enoch.snark.module.AbstractModule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FarmModule extends AbstractModule {

    public FarmModule() {
        this.priority = 2.0;
    }

    @Override
    public void run() {

        List<TargetPlanet> targets = new ArrayList<>();
        for(TargetPlanet target : targets) {
            Commander.push(new SpyCommand(target));
        }
    }
}
