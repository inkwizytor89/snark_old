package org.enoch.snark.gi;

import org.enoch.snark.instance.Universe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GISessionTest {

    private GISession session;
    private Universe universe;

    @Before
    public void setUp() {
        universe = AbstractSeleniumTest.creatTestInstance();
    }

    @Test
    public void login_when_openSession() {
        session = universe.session;

        session.open();

        assertEquals(true, session.isLoggedIn());
        session.close();
    }

    @Test
    public void logout_when_closeSession() {
        session = universe.session;
        session.open();

        session.close();
        assertEquals(false, session.isLoggedIn());
    }
}