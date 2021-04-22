import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
public class TestRelease {
    //Check if the constructor is private
    @Test
    public void testConstructor() throws NoSuchMethodException {
        Constructor<Release> c = Release.class.getDeclaredConstructor();
        if(!Modifier.isPrivate(c.getModifiers())){
            fail();
        }
    }

    //Check if the factory method worked correctly
    @Test
    public void testGetInstance() throws NoSuchFieldException, IllegalAccessException {
        Release a = Release.getInstance();
        Field field = Release.class.getDeclaredField("aInstance");
        field.setAccessible(true);
        Object value = field.get(a);
        value = (Release) value;
        assertEquals(value, a);
    }

    //Check the condition when we meet the pre-condition
    @Test
    public void testExecute1(){
        Release a = Release.getInstance();
        WallE robot = new WallE();
        robot.extendArm();
        try{
            a.execute(robot);
        }
        catch (RobotDamagedException e){
            fail();
        }
    }

    //Check the condition when we don't meet the pre-condition
    @Test
    public void testExecute2(){
        Release a = Release.getInstance();
        WallE robot = new WallE();
        robot.openGripper();
        try{
            a.execute(robot);
        }
        catch (RobotDamagedException e){
            fail();
        }
    }

    //Check toString is set correctly
    @Test
    public void testToString(){
        Release a = Release.getInstance();
        assertEquals("Release", a.toString());
    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1(){
        Release a = Release.getInstance();
        GetDistance visitor = new GetDistance();
        Double value = (Double) a.accept(visitor);
        if(!value.equals(0.0)){
            fail();
        }
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2(){
        Release a = Release.getInstance();
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Object value = a.accept(visitor);
        if(!value.equals(0)){
            fail();
        }
    }

}
