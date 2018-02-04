package org.enoch.snark.gi.command.request;

import org.enoch.snark.gi.Commander;
import org.enoch.snark.gi.command.SpyObserver;
import org.enoch.snark.gi.command.impl.ReadSpyInfoCommand;
import org.enoch.snark.gi.command.impl.SpyCommand;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SpyInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SpyRequest implements SpyObserver{

    private final SpyReportWaiter waiter;
    private final int baseWait;
    private final Map<Planet, SpyInfo> spyReport = new HashMap<>();
    private boolean isNew = true;

    public SpyRequest(List<Planet> targets, SpyReportWaiter waiter, int baseWait) {
        this.waiter = waiter;
        this.baseWait = baseWait;
        for(Planet target : targets) {
            spyReport.put(target, null);

            final SpyCommand spyCommand = new SpyCommand(target);
            spyCommand.setAfterCommand(new ReadSpyInfoCommand(target, this));
            Commander.push(spyCommand);
        }

        returnSpyReport();
    }

    private void returnSpyReport() {
        Runnable task = () -> {
             do {
                isNew = false;
                try {
                    TimeUnit.SECONDS.sleep(baseWait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (isNew && !areAll());

            waiter.saveSpyReport(spyReport.values());
        };

        new Thread(task).start();
    }

    private boolean areAll() {
        return !spyReport.values().contains(null);
    }

    public void report(SpyInfo info) {
        isNew = true;
        spyReport.put(info.planet, info);
    }
}
