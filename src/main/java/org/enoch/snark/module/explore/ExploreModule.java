package org.enoch.snark.module.explore;

import org.enoch.snark.module.AbstractModule;

import java.util.Date;

public class ExploreModule extends AbstractModule {

    public ExploreModule() {
        this.priority = 1.0;
    }

    @Override
    public void run() {
        long shift = 12000;
        log.info("ExploreModule do nothing");
        readyOn = new Date(readyOn.getTime()+shift);
    }
}
