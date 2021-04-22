import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestGrab {
    //Check if the constructor is private
    @Test
    public void testConstructor() throws NoSuchMethodException {
        Constructor<Grab> c = Grab.class.getDeclaredConstructor();
        if(!Modifier.isPrivate(c.getModifiers())){
            fail();
        }
    }

    //Check if the factory method worked correctly
    @Test
    public void testGetInstance() throws NoSuchFieldException, IllegalAccessException {
        Grab a = Grab.getInstance();
        Field field = Grab.class.getDeclaredField("aInstance");
        field.setAccessible(true);
        Object value = field.get(a);
        value = (Grab) value;
        assertEquals(value, a);
    }

    //Check the condition when we meet the pre-condition
    @Test
    public void testExecute1(){
        Grab a = Grab.getInstance();
        WallE robot = new WallE();
        try{
            a.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check the condition when we don't meet the pre-condition
    @Test
    public void testExecute2(){
        Grab a = Grab.getInstance();
        WallE robot = new WallE();
        robot.extendArm();
        try{
            a.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check toString is set correctly
    @Test
    public void testToString(){
        Grab a = Grab.getInstance();
        assertEquals("Grab", a.toString());
    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1(){
        GetDistance visitor = new GetDistance();
        Grab a = Grab.getInstance();
        Double value = (Double) a.accept(visitor);
        if(!value.equals(0.0)){
            fail();
        }
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2(){
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Grab a = Grab.getInstance();
        Object value = a.accept(visitor);
        if(!value.equals(0)){
            fail();
        }
    }
}
