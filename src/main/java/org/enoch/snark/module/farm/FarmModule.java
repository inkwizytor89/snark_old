package org.enoch.snark.module.farm;

import org.enoch.snark.common.PlanetFromFileReader;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SpyInfo;
import org.enoch.snark.module.AbstractModule;
import org.enoch.snark.module.ModuleStatus;
import org.enoch.snark.gi.command.request.SpyReportWaiter;
import org.enoch.snark.gi.command.request.SpyRequest;

import java.util.Collection;
import java.util.List;

public class FarmModule extends AbstractModule implements SpyReportWaiter {

    public FarmModule() {
        this.priority = 2.0;
    }

    @Override
    public void run() {

        setStatus(ModuleStatus.IN_PROGRESS);

        System.out.println("cos sie dzieje");
        List<Planet> targets = PlanetFromFileReader.get("src/main/resources/FarmModule/targets.txt");
        new SpyRequest(targets, this, 300);
    }

    @Override
    public void saveSpyReport(Collection<SpyInfo> values) {

    }
}
