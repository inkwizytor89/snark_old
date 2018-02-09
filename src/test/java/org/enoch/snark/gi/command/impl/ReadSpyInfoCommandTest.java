package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.AbstractSeleniumTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReadSpyInfoCommandTest extends AbstractSeleniumTest {

    @Test
    public void execute() {
        final ReadSpyInfoCommand command = new ReadSpyInfoCommand(universe, sampleTarget, null);

        command.execute();
    }
}