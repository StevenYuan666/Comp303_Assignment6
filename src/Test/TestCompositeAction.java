import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TestCompositeAction {
    @Test
    public void testGetTitle(){
        CompositeAction c = new CompositeAction("C1");
        assertEquals("C1", c.getTitle());
    }

    @Test
    public void testSetTitle(){
        CompositeAction c = new CompositeAction("C1");
        c.setTitle("C2");
        assertEquals("C2", c.getTitle());
    }

    //Check if we can add an action to the end of Composite Action
    @Test
    public void testAddAction() throws NoSuchFieldException, IllegalAccessException {
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        c.addAction(a);
        Field field = CompositeAction.class.getDeclaredField("allAction");
        field.setAccessible(true);
        Object value = field.get(c);
        ArrayList<BasicAction> v = (ArrayList<BasicAction>) value;
        assertEquals(a, v.get(0));
    }

    //Check if we can insert an action to the specified position
    @Test
    public void testInsertAction() throws NoSuchFieldException, IllegalAccessException {
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        Field field = CompositeAction.class.getDeclaredField("allAction");
        field.setAccessible(true);
        Object value = field.get(c);
        ArrayList<BasicAction> v = (ArrayList<BasicAction>) value;
        assertEquals(b, v.get(0));
    }

    //Check if we can remove the input action
    @Test
    public void testRemoveAction1() throws NoSuchFieldException, IllegalAccessException {
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        c.removeAction(b);
        Field field = CompositeAction.class.getDeclaredField("allAction");
        field.setAccessible(true);
        Object value = field.get(c);
        ArrayList<BasicAction> v = (ArrayList<BasicAction>) value;
        assertEquals(a, v.get(0));
    }

    //Check if we can remove the action in the specified position
    @Test
    public void testRemoveAction2() throws NoSuchFieldException, IllegalAccessException {
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        c.removeAction(0);
        Field field = CompositeAction.class.getDeclaredField("allAction");
        field.setAccessible(true);
        Object value = field.get(c);
        ArrayList<BasicAction> v = (ArrayList<BasicAction>) value;
        assertEquals(a, v.get(0));
    }

    //Check if the shallow copy is correct
    @Test
    public void testGetAllActions(){
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        ArrayList<BasicAction> all = c.getAllActions();
        assertEquals(b, all.get(0));
        assertEquals(a, all.get(1));
    }

    //Check the condition when all pre-condition are met
    @Test
    public void testExecute(){
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        WallE robot = new WallE();
        try{
            c.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check if toString is set correctly
    @Test
    public void testToString(){
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        String s = "Composite Action: " + "C1" + " including { " + "MoveForward MoveForward " + "}";
        assertEquals(s, c.toString());
    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1(){
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        GetDistance visitor = new GetDistance();
        Object value = c.accept(visitor);
        if(!value.equals(50.0)){
            fail();
        }
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2(){
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Object value = c.accept(visitor);
        if(!value.equals(0)){
            fail();
        }
    }

    //Check if copy constructor copy the input object correctly
    @Test
    public void testCopyConstructor() throws NoSuchFieldException, IllegalAccessException {
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        CompositeAction cCopy = new CompositeAction(c);
        Field field = CompositeAction.class.getDeclaredField("allAction");
        field.setAccessible(true);
        Object value = field.get(c);
        Object valueCopy = field.get(cCopy);
        ArrayList<BasicAction> v = (ArrayList<BasicAction>) value;
        ArrayList<BasicAction> vCopy = (ArrayList<BasicAction>) valueCopy;
        for(int i = 0; i < v.size(); i ++){
            assertEquals(v.get(i), vCopy.get(i));
        }

    }
}
