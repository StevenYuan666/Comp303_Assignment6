import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TestRechargeBeforeAction {
    //Check the condition when we meet the pre-condition
    @Test
    public void testExecute(){
        MoveForward m = MoveForward.getInstance(20.0);
        RechargeBeforeAction a = new RechargeBeforeAction(m);
        WallE robot = new WallE();
        try{
            a.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check if the getter return correctly
    @Test
    public void testGetAction1(){
        MoveForward m = MoveForward.getInstance(20.0);
        RechargeBeforeAction a = new RechargeBeforeAction(m);
        assertEquals(m, a.getAction());
    }

    //Check if the getter return correctly
    @Test
    public void testGetAction2() throws NoSuchFieldException, IllegalAccessException {
        CompositeAction c = new CompositeAction("C1");
        BasicAction a = MoveForward.getInstance(20.0);
        BasicAction b = MoveForward.getInstance(30.0);
        c.addAction(a);
        c.insertAction(0,b);
        RechargeBeforeAction r = new RechargeBeforeAction(c);
        Field field = CompositeAction.class.getDeclaredField("allAction");
        field.setAccessible(true);
        Object value = field.get(c);
        ArrayList<BasicAction> v = (ArrayList<BasicAction>) value;
        Object valueCopy = field.get(r.getAction());
        ArrayList<BasicAction> vCopy = (ArrayList<BasicAction>) valueCopy;
        for(int i = 0; i < v.size(); i ++){
            assertEquals(v.get(i), vCopy.get(i));
        }
    }

    //Check toString is set correctly
    @Test
    public void testToString(){
        MoveForward m = MoveForward.getInstance(20.0);
        RechargeBeforeAction a = new RechargeBeforeAction(m);
        assertEquals("Forcing Recharge First: " + a.getAction().toString(), a.toString());
    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1(){
        MoveForward m = MoveForward.getInstance(20.0);
        RechargeBeforeAction a = new RechargeBeforeAction(m);
        GetDistance visitor = new GetDistance();
        Object value = a.accept(visitor);
        if(!value.equals(20.0)){
            fail();
        }
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2(){
        MoveForward m = MoveForward.getInstance(20.0);
        RechargeBeforeAction a = new RechargeBeforeAction(m);
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Object value = a.accept(visitor);
        if(!value.equals(0)){
            fail();
        }
    }
}
