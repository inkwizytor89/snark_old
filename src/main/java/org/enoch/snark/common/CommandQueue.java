package org.enoch.snark.common;

import org.enoch.snark.Commander;
import org.enoch.snark.command.AbstractCommand;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class CommandQueue {

    private int sleepPause;
    private static Queue<AbstractCommand> queue;

    public CommandQueue(int sleepPause) {
        this.sleepPause = sleepPause;
        queue = new LinkedList<>();
        startQueue();
    }

    private void startQueue() {
        Runnable task = () -> {
            while(true) {
                while(!queue.isEmpty()) {
                    AbstractCommand command = queue.remove();
                    command.execute();
                    AbstractCommand after = command.doAfter();
                    if(after != null) {
                        Commander.push(after);
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(sleepPause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    public void add(AbstractCommand command) {
        queue.add(command);
    }
}
