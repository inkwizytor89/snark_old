package org.enoch.snark.instance;

import org.enoch.snark.gi.command.AbstractCommand;

public interface Commander {


    void setFleeFreeSlots(int i);

    void setExpeditionFreeSlots(int i);

    void push(AbstractCommand command);
}
