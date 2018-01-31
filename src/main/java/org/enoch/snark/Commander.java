package org.enoch.snark;

import org.enoch.snark.command.AbstractCommand;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Commander {

    private static final Logger log = Logger.getLogger( Commander.class.getName() );

    private static final int SLEEP_PAUSE = 10;
    private static Commander instance  = null;
    private static Queue<AbstractCommand> queue;

    private Commander() {
        queue = new LinkedList<>();
        startQueue();
    }

    private void startQueue() {
        Runnable task = () -> {
            while(true) {
                while(!queue.isEmpty()) {
                    AbstractCommand command = queue.remove();
                    command.execute();
                }
                log.info("Comander queue is empty.");
                try {
                    TimeUnit.SECONDS.sleep(SLEEP_PAUSE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    public static Commander getInstance() {
        if(instance == null) {
            instance = new Commander();
        }
        return instance;
    }

    public static void push(AbstractCommand command) {
        queue.add(command);
    }
}
