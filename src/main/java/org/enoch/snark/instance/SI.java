package org.enoch.snark.instance;

import org.enoch.snark.module.AbstractModule;
import org.enoch.snark.module.farm.FarmModule;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SI {

    private static final Logger log = Logger.getLogger( SI.class.getName() );

    Set<AbstractModule> modules = new TreeSet<>();
    private Date nearestActionDate;
    private AbstractModule nearestModule;
    private Universum universum;

    public SI(Universum universum) {
        this.universum = universum;
        modules.add(new FarmModule(universum));
    }

    public void run() {
        while(true) {
            nearestActionDate = initTime();
            for (AbstractModule module : modules) {
                if (module.isReady()) {
                    module.run();
                }
                updateNearestActionDate(module.getReadyOn(), module);
            }
            sleep();
        }
    }

    private void updateNearestActionDate(Date readyOn, AbstractModule module) {
        if(nearestActionDate.after(readyOn)) {
            nearestActionDate = readyOn;
            nearestModule = module;
        }
    }

    private Date initTime() {
        return new Date(new Date().getTime() + 24*3600*1000);
    }

    private void sleep() {
        long timeToSleep = nearestActionDate.getTime() - new Date().getTime()+1000;
        log.log(Level.FINE, "Next {0} on {1} that is {2}ms",
                new Object[]{nearestModule.getName(), nearestActionDate.toString(), timeToSleep});
        try {
            TimeUnit.MILLISECONDS.sleep(timeToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
