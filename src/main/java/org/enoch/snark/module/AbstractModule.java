package org.enoch.snark.module;

import java.util.Date;
import java.util.logging.Logger;

public abstract class AbstractModule implements Comparable<AbstractModule> {

    protected static final Logger log = Logger.getLogger( AbstractModule.class.getName() );

    protected Date readyOn = new Date();
    protected Double priority = 0.0;
    private String name = this.getClass().getName();

    @Override
    public int compareTo(AbstractModule o) {
        return priority.compareTo(o.priority);
    }

    public boolean isReady() {
        return new Date().after(readyOn);
    }

    public abstract void run();

    public Date getReadyOn() {
        return readyOn;
    }

    public String getName() {
        return name;
    }
}
