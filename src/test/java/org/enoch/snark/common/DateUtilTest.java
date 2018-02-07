package org.enoch.snark.common;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    @Test
    public void getDateFromTimeSreing() {
        String timeInput="21:01:19 h";

        final LocalTime time = DateUtil.getDateFromTimeSreing(timeInput);

        assertEquals(21,time.getHour());
        assertEquals(1,time.getMinute());
        assertEquals(19,time.getSecond());
    }
}