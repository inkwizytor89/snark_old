package org.enoch.snark.gi.command.request;

import org.enoch.snark.gi.command.impl.SendFleetCommand;
import org.enoch.snark.gi.macro.Mission;
import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.AttackInfo;
import org.enoch.snark.model.AttackPlan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttackRequest {

    private final AttackReportWaiter waiter;
    private final List<AttackInfo> spyReport = new ArrayList<>();
    private final LocalDateTime startTimestamp = LocalDateTime.now();

    public AttackRequest(Universe universe, List<AttackPlan> attacks, AttackReportWaiter waiter) {
        this.waiter = waiter;
        for(AttackPlan attack : attacks) {

            final SendFleetCommand attackCommand = new SendFleetCommand(universe, attack.target, Mission.ATTACK, attack.fleet);
            universe.commander.push(attackCommand);
        }
    }
}
