import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Observer;

public class TestProgram {

    //Check if th getter of Title work correctly
    @Test
    public void testGetTitle(){
        Program p = new Program("myProgram");
        assertEquals("myProgram", p.getTitle());
    }

    //Check if th setter of Title work correctly
    @Test
    public void testSetTitle(){
        Program p = new Program("myProgram");
        p.setTitle("myProgram2");
        assertEquals("myProgram2", p.getTitle());
    }

    //Check if we can add an action to the end of Program
    @Test
    public void testAddAction() throws NoSuchFieldException, IllegalAccessException {
        Program p = new Program("myProgram");
        Action a = MoveForward.getInstance(20.0);
        p.addAction(a);
        Field field = Program.class.getDeclaredField("allActions");
        field.setAccessible(true);
        Object value = field.get(p);
        ArrayList<Action> v = (ArrayList<Action>) value;
        assertEquals(a, v.get(0));
    }

    //Check if we can insert an action to the specified position
    @Test
    public void testInsertAction() throws NoSuchFieldException, IllegalAccessException {
        Program p = new Program("myProgram");
        Action a = MoveForward.getInstance(20.0);
        Action b = MoveForward.getInstance(30.0);
        p.addAction(a);
        p.insertAction(0, b);
        Field field = Program.class.getDeclaredField("allActions");
        field.setAccessible(true);
        Object value = field.get(p);
        ArrayList<Action> v = (ArrayList<Action>) value;
        assertEquals(b, v.get(0));
    }

    //Check if we can remove the input action
    @Test
    public void testRemoveAction1() throws NoSuchFieldException, IllegalAccessException {
        Program p = new Program("myProgram");
        Action a = MoveForward.getInstance(20.0);
        Action b = MoveForward.getInstance(30.0);
        p.addAction(a);
        p.insertAction(0, b);
        p.removeAction(b);
        Field field = Program.class.getDeclaredField("allActions");
        field.setAccessible(true);
        Object value = field.get(p);
        ArrayList<Action> v = (ArrayList<Action>) value;
        assertEquals(a, v.get(0));
    }

    //Check if we can remove the action in the specified position
    @Test
    public void testRemoveAction2() throws NoSuchFieldException, IllegalAccessException {
        Program p = new Program("myProgram");
        Action a = MoveForward.getInstance(20.0);
        Action b = MoveForward.getInstance(30.0);
        p.addAction(a);
        p.insertAction(0, b);
        p.removeAction(0);
        Field field = Program.class.getDeclaredField("allActions");
        field.setAccessible(true);
        Object value = field.get(p);
        ArrayList<Action> v = (ArrayList<Action>) value;
        assertEquals(a, v.get(0));
    }

    //Check if we can add an observer correctly
    @Test
    public void testAddObserver() throws NoSuchFieldException, IllegalAccessException,
            RobotDamagedException {
        Program p = new Program("myProgram");
        Action turn = Turn.getInstance(Direction.Left);
        p.addAction(turn);
        LogToConsoloe observer = new LogToConsoloe();
        p.addObserver(observer);
        WallE robot = new WallE();
        p.executeProgram(robot);
        Field field = Program.class.getDeclaredField("allObservers");
        field.setAccessible(true);
        Object value = field.get(p);
        ArrayList<Observer> v = (ArrayList<Observer>) value;
        assertEquals(observer, v.get(0));
        observer.printLog();
        observer.clearLog();
    }

    //Check if we can remove an observer correctly
    @Test
    public void testRemoveObserver() throws NoSuchFieldException, IllegalAccessException {
        Program p = new Program("myProgram");
        LogToConsoloe observer = new LogToConsoloe();
        LogToConsoloe observer2 = new LogToConsoloe();
        p.addObserver(observer);
        p.addObserver(observer2);
        p.removeObserver(observer);
        Field field = Program.class.getDeclaredField("allObservers");
        field.setAccessible(true);
        Object value = field.get(p);
        ArrayList<Observer> v = (ArrayList<Observer>) value;
        assertEquals(observer2, v.get(0));
    }

    //Check the condition when all pre-condition are met
    @Test
    public void testExecuteProgram(){
        Program p = new Program("myProgram");
        Action a = MoveForward.getInstance(20.0);
        Action b = MoveForward.getInstance(30.0);
        p.addAction(a);
        p.insertAction(0, b);
        LogToConsoloe observer = new LogToConsoloe();
        p.addObserver(observer);
        LogToFile observer2 = new LogToFile("/Users/stevenyuan/Documents/McGill/U1/2021winter/" +
                "Comp303/Assignment/Comp303_Assignment6/src/Test");
        p.addObserver(observer2);
        WallE robot = new WallE();
        try{
            p.executeProgram(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check if the shallow copy is correct
    @Test
    public void testGetAllActions() throws NoSuchFieldException, IllegalAccessException {
        Program p = new Program("myProgram");
        Action a = MoveForward.getInstance(20.0);
        Action b = MoveForward.getInstance(30.0);
        p.addAction(a);
        p.insertAction(0, b);
        Field field = Program.class.getDeclaredField("allActions");
        field.setAccessible(true);
        Object value = field.get(p);
        ArrayList<Action> v = (ArrayList<Action>) value;
        ArrayList<Action> vCopy = p.getAllActions();
        for(int i = 0; i < vCopy.size(); i ++){
            assertEquals(v.get(i), vCopy.get(i));
        }
    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1(){
        Program p = new Program("myProgram");
        Action a = MoveForward.getInstance(20.0);
        Action b = MoveForward.getInstance(30.0);
        p.addAction(a);
        p.addAction(b);
        GetDistance visitor = new GetDistance();
        Object value = p.accept(visitor);
        if(!value.equals(50.0)){
            fail();
        }
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2(){
        Program p = new Program("myProgram");
        Action a = MoveForward.getInstance(20.0);
        Action b = Compact.getInstance();
        p.addAction(a);
        p.addAction(b);
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Object value = p.accept(visitor);
        if(!value.equals(1)){
            fail();
        }
    }
}
