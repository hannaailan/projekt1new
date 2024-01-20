package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ProjectTest {

    private Project pro;

    @Before
    public void setUp(){
        pro=new Project("n","d", LocalDate.now().plusDays(10));
    }

    @Test
    public void testKonstruktor() {
        String name = "name";
        String besch = "beschreibung";
        LocalDate daedline= LocalDate.now().plusDays(3);

        pro = new Project(name,besch,daedline);

        assertEquals(name,pro.getName());
        assertEquals(besch,pro.getDescribtion());
        assertEquals(daedline,pro.getDeadline());
    }

    @Test
    public void testRemoveTask(){

        Task task = new Task(pro);

        pro.addTask(task);

        assertTrue(pro.removeTask(task));

        assertEquals(-1,pro.findTask(task));
    }

    @Test
    public void testAddTask(){

        Task task = new Task(pro);

        assertTrue(pro.addTask(task));

        assertEquals(0,pro.findTask(task));

    }

    @Test
    public void testFindTask(){
        Task task = new Task(pro);

        assertEquals(-1,pro.findTask(task));

        pro.addTask(task);

        for(int index=0;index<8;index++){
            assertEquals(index,pro.findTask(task));
            pro.shiftTaskFwd(task);
        }

    }

    @Test
    public void testShiftTaskFwd(){

        Task task = new Task(pro);

        assertFalse(pro.shiftTaskFwd(task));

        pro.addTask(task);

        assertEquals(0,pro.findTask(task));

        assertTrue(pro.shiftTaskFwd(task));

        assertEquals(1,pro.findTask(task));
    }

    @Test
    public void testShiftTaskBck(){
        Task task = new Task(pro);

        assertFalse(pro.shiftTaskBck(task));

        pro.addTask(task);

        assertEquals(0,pro.findTask(task));

        assertFalse(pro.shiftTaskBck(task));

        pro.shiftTaskFwd(task);

        assertTrue(pro.shiftTaskBck(task));

        assertEquals(0,pro.findTask(task));
    }
}
