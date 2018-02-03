package org.enoch.snark.module.farm;

import org.enoch.snark.gi.Commander;
import org.enoch.snark.gi.command.SpyCommand;
import org.enoch.snark.model.TargetPlanet;
import org.enoch.snark.module.AbstractModule;

import java.util.ArrayList;
import java.util.List;

public class FarmModule extends AbstractModule {

    public FarmModule() {
        this.priority = 2.0;
    }

    @Override
    public void run() {

        System.out.println("cos sie dzieje");
        List<TargetPlanet> targets = new ArrayList<>();
        for(TargetPlanet target : targets) {
            Commander.push(new SpyCommand(target));
        }
    }
}
