package org.enoch.snark.module.farm;

import org.enoch.snark.module.AbstractModule;

import java.util.Date;

public class FarmModule extends AbstractModule {

    public FarmModule() {
        this.priority = 2.0;
    }

    @Override
    public void run() {
        long shift = 28000;
        log.info("FarmModule do nothing");
        readyOn = new Date(readyOn.getTime()+shift);
    }
}
