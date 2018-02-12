package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.gi.command.CommandType;
import org.enoch.snark.instance.Universe;

import java.util.concurrent.TimeUnit;

public class PauseCommand extends AbstractCommand {

    private final AbstractCommand command;
    private final TimeUnit timeUnit;
    private final long value;

    public PauseCommand(Universe universe,AbstractCommand command, long numberOfSecounds) {
        this(universe,command, TimeUnit.SECONDS, numberOfSecounds);

    }

    public PauseCommand(Universe universe, AbstractCommand command, TimeUnit timeUnit, long value) {
        super(universe, CommandType.CALCULATION);
        this.command = command;
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
                universe.commander.push(command);
        };

        new Thread(task).start();
    }

    @Override
    public String toString() {
        return "Sleep "+value+" "+timeUnit+" for: "+command;
    }
}
