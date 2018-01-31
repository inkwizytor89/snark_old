package org.enoch.snark;

public class Commander {

    private static Commander instance  = null;

    private Commander() {
    }

    public static Commander getInstance() {
        if(instance == null) {
            instance = new Commander();
        }
        return instance;
    }
}
