package org.enoch.snark.module.farm;

import org.enoch.snark.common.PlanetFromFileReader;
import org.enoch.snark.gi.command.request.AttackReportWaiter;
import org.enoch.snark.gi.command.request.AttackRequest;
import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.AttackInfo;
import org.enoch.snark.model.AttackPlan;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SpyInfo;
import org.enoch.snark.module.AbstractModule;
import org.enoch.snark.module.ModuleStatus;
import org.enoch.snark.gi.command.request.SpyReportWaiter;
import org.enoch.snark.gi.command.request.SpyRequest;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FarmModule extends AbstractModule implements SpyReportWaiter, AttackReportWaiter {

    private final File targetsFile;

    public FarmModule(Universe universe) {
        super(universe);
        this.priority = universe.appProperties.farmModule;
        targetsFile = new File(universe.pathToMainDir + "\\farm\\targets.txt");
    }

    @Override
    public void run() {
        setStatus(ModuleStatus.IN_PROGRESS);
        List<Planet> targets = PlanetFromFileReader.get(targetsFile.getAbsolutePath());
        if(targets == null ) throw new RuntimeException("Error when load targets from file");
        System.err.println("Do szukania:");
        for (Planet planet : targets) {
            System.err.println(planet);
        }
        new SpyRequest(universe, targets, this);
    }

    @Override
    public void saveSpyReport(Collection<SpyInfo> values) {
        Set<SpyInfo> spyInfoSet = new TreeSet<>(values);
        final int fleetCount = calculateAvailableFleetCount();
        List<SpyInfo> toAttack = new ArrayList<>();
        int index = 0;
        for (SpyInfo info : spyInfoSet) {
            System.err.println("Entry: "+info);
            if( info.getSumResourceCount() > 10000) {
                toAttack.add(info);
            }
            if(index>=fleetCount) break;
        }

        final List<AttackPlan> attackPlans = toAttack.stream().map(AttackPlan::new).collect(Collectors.toList());
        new AttackRequest(universe, attackPlans, this);
        setStatus(ModuleStatus.WAITING);
    }

    private int calculateAvailableFleetCount() {
        return 10;
    }

    @Override
    public void saveAttackReport(Collection<AttackInfo> values) {

    }
}
