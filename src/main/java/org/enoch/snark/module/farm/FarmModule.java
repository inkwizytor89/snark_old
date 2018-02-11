package org.enoch.snark.module.farm;

import org.enoch.snark.common.PlanetFromFileReader;
import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SpyInfo;
import org.enoch.snark.module.AbstractModule;
import org.enoch.snark.module.ModuleStatus;
import org.enoch.snark.gi.command.request.SpyReportWaiter;
import org.enoch.snark.gi.command.request.SpyRequest;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class FarmModule extends AbstractModule implements SpyReportWaiter {

    private final File targetsFile;
    private Universe universe;

    public FarmModule(Universe universe) {
        this.universe = universe;
        this.priority = universe.appProperties.farmModule;
        targetsFile = new File(universe.pathToMainDir + "\\farm\\targets.txt");
    }

    @Override
    public void run() {
        setStatus(ModuleStatus.IN_PROGRESS);
        List<Planet> targets = PlanetFromFileReader.get(targetsFile.getAbsolutePath());
        System.err.println("Do szukania:");
        for (Planet planet : targets) {
            System.err.println(planet);
        }
        new SpyRequest(universe, targets, this, 300);
    }

    @Override
    public void saveSpyReport(Collection<SpyInfo> values) {
        for (SpyInfo info : values) {
            System.err.println(info);
        }
        setStatus(ModuleStatus.WAITING);
    }
}
