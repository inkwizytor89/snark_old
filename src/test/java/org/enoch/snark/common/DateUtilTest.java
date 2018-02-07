package org.enoch.snark.common;

import org.junit.Test;

import java.text.ParseException;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    @Test
    public void getDateFromTimeSreing() throws ParseException {
        String timeInput="31:01:19 h";

        final LocalTime time = DateUtil.getDateFromTimeSreing(timeInput);

        assertEquals(31,time.getHour());
        assertEquals(1,time.getMinute());
        assertEquals(19,time.getSecond());
    }
}