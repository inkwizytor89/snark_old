package org.enoch.snark.module;

import java.util.Date;

public abstract class AbstractModule implements Comparable<AbstractModule> {

    protected Date readyOn = new Date();
    protected Double priority = 0.0;

    @Override
    public int compareTo(AbstractModule o) {
        return priority.compareTo(o.priority);
    }

    public boolean isReady() {
        return new Date().after(readyOn);
    }

    public abstract void run();
}
