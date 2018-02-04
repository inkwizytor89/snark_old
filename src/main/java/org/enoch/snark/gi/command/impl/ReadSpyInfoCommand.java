package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.gi.command.SpyObserver;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SpyInfo;

import static org.enoch.snark.gi.command.CommandType.INTERFACE_REQUIERED;

public class ReadSpyInfoCommand extends AbstractCommand {

    private Planet planet;
    private SpyObserver observer;

    public ReadSpyInfoCommand(Planet planet, SpyObserver observer) {
        super(INTERFACE_REQUIERED);
        this.planet = planet;
        this.observer = observer;
    }

    @Override
    public void execute() {
        SpyInfo info = new SpyInfo();
        info.planet = planet;

        observer.report(info);
    }
}
