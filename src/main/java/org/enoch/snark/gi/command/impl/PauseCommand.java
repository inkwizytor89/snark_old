package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.gi.command.CommandType;
import org.enoch.snark.instance.Universe;

import java.util.concurrent.TimeUnit;

public class PauseCommand extends AbstractCommand {

    private final TimeUnit timeUnit;
    private final long value;

    public PauseCommand(Universe universe, long numberOfSecounds) {
        this(universe, TimeUnit.SECONDS, numberOfSecounds);

    }

    public PauseCommand(Universe universe, TimeUnit timeUnit, long value) {
        super(universe, CommandType.CALCULATION);

        this.timeUnit = timeUnit;
        this.value = value;
    }

    @Override
    public void execute() {
        Runnable task = () -> {
                try {
                    timeUnit.sleep(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        };

        new Thread(task).start();
    }
}
