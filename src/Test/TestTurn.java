import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
public class TestTurn {

    //Check if the constructor is private
    @Test
    public void testConstructor1() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<Turn> c = Turn.class.getDeclaredConstructor(Direction.class);
        Turn turn = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            turn = c.newInstance(Direction.Left);
        }
        Field field = Turn.class.getDeclaredField("degree");
        field.setAccessible(true);
        Object value = field.get(turn);
        value = (double) value;
        if(!value.equals(-90.0)){
            fail();
        }
    }

    //Check if the constructor is private
    @Test
    public void testConstructor2() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<Turn> c = Turn.class.getDeclaredConstructor(Direction.class);
        Turn turn = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            turn = c.newInstance(Direction.Right);
        }
        Field field = Turn.class.getDeclaredField("degree");
        field.setAccessible(true);
        Object value = field.get(turn);
        value = (double) value;
        if(!value.equals(90.0)){
            fail();
        }
    }

    //Check if the factory method worked correctly
    @Test
    public void testGetInstance1() throws NoSuchFieldException, IllegalAccessException {
        Turn turn = Turn.getInstance(Direction.Left);
        Field field = Turn.class.getDeclaredField("degree");
        field.setAccessible(true);
        Object value = field.get(turn);
        value = (double) value;
        if(!value.equals(-90.0)){
            fail();
        }
    }

    //Check if the factory method worked correctly
    @Test
    public void testGetInstance2() throws NoSuchFieldException, IllegalAccessException {
        Turn turn = Turn.getInstance(Direction.Right);
        Field field = Turn.class.getDeclaredField("degree");
        field.setAccessible(true);
        Object value = field.get(turn);
        value = (double) value;
        if(!value.equals(90.0)){
            fail();
        }
    }

    //Check if the factory method worked correctly and throw exception correctly
    @Test
    public void testGetInstance3() throws NoSuchFieldException, IllegalAccessException {
        try{
            Turn turn = Turn.getInstance(null);
            fail();
        }
        catch (IllegalArgumentException e){
        }
    }

    //Check if the getter work correctly
    @Test
    public void testGetDirection1(){
        Turn turn = Turn.getInstance(Direction.Left);
        assertEquals(Direction.Left, turn.getDirection());
    }

    //Check if the getter work correctly
    @Test
    public void testGetDirection2(){
        Turn turn = Turn.getInstance(Direction.Right);
        assertEquals(Direction.Right, turn.getDirection());
    }

    //Check the condition when we meet the pre-condition
    @Test
    public void testExecute1(){
        WallE robot = new WallE();
        Turn turn = Turn.getInstance(Direction.Right);
        try{
            turn.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check the condition when we don't meet the pre-condition
    @Test
    public void testExecute2(){
        WallE robot = new WallE();
        robot.extendArm();
        Turn turn = Turn.getInstance(Direction.Right);
        try{
            turn.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check the condition when we meet the pre-condition but need recharge firstly
    @Test
    public void testExecute3(){
        WallE robot = new WallE();
        robot.extendArm();
        Turn turn = Turn.getInstance(Direction.Right);
        try{
            while(robot.getBatteryCharge() > 5){
                turn.execute(robot);
            }
            turn.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check toString is set correctly
    @Test
    public void testToString(){
        Turn turn = Turn.getInstance(Direction.Left);
        assertEquals("Turn", turn.toString());
    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1(){
        GetDistance visitor = new GetDistance();
        Turn turn = Turn.getInstance(Direction.Left);
        Double value = (Double) turn.accept(visitor);
        if(value != 0.0){
            fail();
        }
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2(){
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Turn turn = Turn.getInstance(Direction.Left);
        Object value = turn.accept(visitor);
        if(!value.equals(0)){
            fail();
        }
    }
}
