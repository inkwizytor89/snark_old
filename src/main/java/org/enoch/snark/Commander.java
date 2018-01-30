package org.enoch.snark;

import org.enoch.snark.module.AbstractModule;
import org.enoch.snark.module.explore.ExploreModule;
import org.enoch.snark.module.farm.FarmModule;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Commander {
    Set<AbstractModule> modules = new TreeSet<>();

    Commander() {
        modules.add(new FarmModule());
        modules.add(new ExploreModule());
    }

    public void run() {

        while(true) {
            for (AbstractModule module : modules) {
                if (module.isReady()) {
                    module.run();
                }
            }
            sleep( 1);
        }
    }

    private void sleep(long time) {
        System.out.println("Wait to "+time );
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
