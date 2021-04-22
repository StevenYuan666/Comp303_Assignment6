import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestCompact {
    //Check if the constructor is private
    @Test
    public void testConstructor() throws NoSuchMethodException {
        Constructor<Compact> c = Compact.class.getDeclaredConstructor();
        if(!Modifier.isPrivate(c.getModifiers())){
            fail();
        }
    }

    //Check if the factory method worked correctly
    @Test
    public void testGetInstance() throws NoSuchFieldException, IllegalAccessException {
        Compact a = Compact.getInstance();
        Field field = Compact.class.getDeclaredField("aInstance");
        field.setAccessible(true);
        Object value = field.get(a);
        value = (Compact) value;
        assertEquals(value, a);
    }

    //Check the condition when we don't meet the pre-condition
    @Test
    public void testExecute1(){
        Compact a = Compact.getInstance();
        WallE robot = new WallE();
        try{
            a.execute(robot);
            fail();
        }
        catch(RobotDamagedException e){
        }
    }

    //Check the condition when we meet the pre-condition
    @Test
    public void testExecute2(){
        Compact a = Compact.getInstance();
        Grab b = Grab.getInstance();
        WallE robot = new WallE();
        try{
            b.execute(robot);
            a.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check the condition when we don't meet the pre-condition
    @Test
    public void testExecute3(){
        Compact a = Compact.getInstance();
        Grab b = Grab.getInstance();
        WallE robot = new WallE();
        try{
            for(int i = 0; i < 20; i ++){
                b.execute(robot);
                a.execute(robot);
            }
            fail();
        }
        catch(RobotDamagedException e){
        }
    }

    //Check toString is set correctly
    @Test
    public void testToString(){
        Compact a = Compact.getInstance();
        assertEquals("Compact", a.toString());
    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1(){
        Compact a = Compact.getInstance();
        GetDistance visitor = new GetDistance();
        Object value = a.accept(visitor);
        if(!value.equals(0.0)){
            fail();
        }
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2(){
        Compact a = Compact.getInstance();
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Object value = a.accept(visitor);
        if(!value.equals(1)){
            fail();
        }
    }

}
