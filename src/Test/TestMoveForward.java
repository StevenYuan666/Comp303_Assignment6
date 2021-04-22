import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class TestMoveForward {

    //Check if the constructor is private and throw exception correctly
    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException{
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        try{
            if(Modifier.isPrivate(c.getModifiers())){
                c.setAccessible(true);
                m = (MoveForward) c.newInstance(-20);
            }
            fail();
        }
        catch(Exception e){
        }
    }

    //Check if the factory method worked correctly
    @Test
    public void testGetInstance1() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            m = (MoveForward) c.newInstance(20);
        }
        Field hashMap = m.getClass().getDeclaredField("aInstances");
        hashMap.setAccessible(true);
        Object value = hashMap.get(m);
        value = (HashMap<Double, MoveForward>) value;
        ((HashMap<Double, MoveForward>) value).put(20.0, m);
        assertEquals(m, MoveForward.getInstance(20.0));
    }

    //Check if the factory method worked correctly
    @Test
    public void testGetInstance2() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            m = (MoveForward) c.newInstance(20);
        }
        Field hashMap = m.getClass().getDeclaredField("aInstances");
        hashMap.setAccessible(true);
        Object value = hashMap.get(m);
        value = (HashMap<Double, MoveForward>) value;
        ((HashMap<Double, MoveForward>) value).put(20.0, m);
        if(MoveForward.getInstance(30.0).getDistance() == 30.0){
        }
        else{
            fail();
        }
    }

    //Check the getter return the correct value
    @Test
    public void testGetDistance() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            m = (MoveForward) c.newInstance(20);
        }
        if(m.getDistance() == 20.0){
        }
        else{
            fail();
        }
    }

    //Check the condition when we meet the pre-condition
    @Test
    public void testExecute1() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, RobotDamagedException {
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            m = (MoveForward) c.newInstance(20);
        }
        WallE robot = new WallE();
        try{
            m.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check the condition when we don't meet the pre-condition
    @Test
    public void testExecute2() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, RobotDamagedException {
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            m = (MoveForward) c.newInstance(20);
        }
        WallE robot = new WallE();
        robot.extendArm();
        try{
            m.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check toString is set correctly
    @Test
    public void testToString() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            m = (MoveForward) c.newInstance(30);
        }
        assertEquals("MoveForward", m.toString());
    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            m = (MoveForward) c.newInstance(20);
        }
        GetDistance visitor = new GetDistance();
        Object value = m.accept(visitor);
        assertEquals(20.0, value);
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<MoveForward> c = MoveForward.class.getDeclaredConstructor(double.class);
        MoveForward m = null;
        if(Modifier.isPrivate(c.getModifiers())){
            c.setAccessible(true);
            m = (MoveForward) c.newInstance(20);
        }
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Object value = m.accept(visitor);
        assertEquals(0, value);
    }
}
