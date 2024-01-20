package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DeveloperTest {

    private Developer dev;
    @Before
    public void setUp(){
        dev = new Developer("n");
    }

    @Test
    public void testKonstruktor() {
        String name = "name";
        dev = new Developer(name);
        assertEquals(name,dev.getName());
    }


    @Test
    public void testStartWorking(){
        dev.startWorking();
        assertTrue(dev.isWorking());

    }


    @Test
    public void testEndWorking(){
        dev.startWorking();
        dev.endWorking();
        assertFalse(dev.isWorking());

        assertFalse(dev.getProcessingTime().isEmpty());
    }



}
