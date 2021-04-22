import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import Code.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestEmptyCompactor {
    //Check if the constructor is private
    @Test
    public void testConstructor() throws NoSuchMethodException {
        Constructor<EmptyCompactor> c = EmptyCompactor.class.getDeclaredConstructor();
        if(!Modifier.isPrivate(c.getModifiers())){
            fail();
        }
    }

    //Check if the factory method worked correctly
    @Test
    public void testGetInstance() throws NoSuchFieldException, IllegalAccessException {
        EmptyCompactor a = EmptyCompactor.getInstance();
        Field field = EmptyCompactor.class.getDeclaredField("aInstance");
        field.setAccessible(true);
        Object value = field.get(a);
        value = (EmptyCompactor) value;
        assertEquals(value, a);
    }

    //Check the condition when we don't meet the pre-condition
    @Test
    public void testExecute1(){
        EmptyCompactor a = EmptyCompactor.getInstance();
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
        EmptyCompactor a = EmptyCompactor.getInstance();
        Grab b = Grab.getInstance();
        Compact c = Compact.getInstance();
        WallE robot = new WallE();
        try{
            b.execute(robot);
            c.execute(robot);
            a.execute(robot);
        }
        catch(RobotDamagedException e){
            fail();
        }
    }

    //Check toString is set correctly
    @Test
    public void testToString(){
        EmptyCompactor a = EmptyCompactor.getInstance();
        assertEquals("EmptyCompactor", a.toString());

    }

    //Check accept for GetDistance visitor
    @Test
    public void testAccept1(){
        EmptyCompactor a = EmptyCompactor.getInstance();
        GetDistance visitor = new GetDistance();
        Object value = a.accept(visitor);
        if(!value.equals(0.0)) {
            fail();
        }
    }

    //Check accept for GetNumOfCompacted visitor
    @Test
    public void testAccept2(){
        EmptyCompactor a = EmptyCompactor.getInstance();
        GetNumOfCompacted visitor = new GetNumOfCompacted();
        Object value = a.accept(visitor);
        if(!value.equals(0)) {
            fail();
        }
    }
}
