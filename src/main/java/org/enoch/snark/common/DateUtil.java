package org.enoch.snark.common;

import java.time.LocalTime;

public class DateUtil {

    // TODO: 2018-02-06 extends to days in input
    public static LocalTime parse(String input) {
        final String[] split = input.split("\\s+");
        String time = split[0];
        if(time.startsWith("0")) {
            time = "0"+time;
        }
        return LocalTime.parse(time);

    }
}
