package org.enoch.snark.common;

import java.time.LocalTime;

public class DateUtil {

    // TODO: 2018-02-06 extends to days in input
    public static LocalTime getDateFromTimeSreing(String timeText) {
        final String[] split = timeText.split("\\s+");
        return LocalTime.parse(split[0]);

    }
}
