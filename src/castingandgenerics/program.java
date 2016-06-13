package castingandgenerics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class program {
	
	public static void main(String [ ] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		objectAndLongComparison();
		objectAndClassComparison();
		
	}
	
    private static void objectAndLongComparison()
    {
        long longCounter = 0;
        Object objectCounter = longCounter;
        Date timeLongStart, timeLongStop, timeObjectStart, timeObjectStop;
        System.out.println("Object and Long comparison:");
        timeLongStart = new Date();
        for (int i = 0; i < Integer.MAX_VALUE; i++)
        {
            longCounter = longCounter + 1;
        }
        timeLongStop = new Date();
        System.out.println(timeLongStop.getTime() - timeLongStart.getTime());

        timeObjectStart = new Date();
        for (int i = 0; i < Integer.MAX_VALUE; i++)
        {
            objectCounter = ((long)objectCounter) + 1;
        }
        timeObjectStop = new Date();
        
        System.out.println(timeObjectStop.getTime() - timeObjectStart.getTime());
    }
    
    private static void objectAndClassComparison() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {

        Date[] timeStart = new Date[3];
        Date[] timeStop = new Date[3];
        Class1 myClass = new Class1();
        Object myObject = myClass;
      //  dynamic myDynamic = myClass;

        System.out.println("Object and Class comparison:");
        timeStart[0] = new Date();

        for (int i = 0; i < Integer.MAX_VALUE; i++)
        {
            myClass.setMyProperty1(i);
            //myClass.MyProperty2 = string.Empty;
        }
        timeStop[0] = new Date();
        System.out.println(timeStop[0].getTime() - timeStart[0].getTime());

        timeStart[1] = new Date();
        for (int i = 0; i < Integer.MAX_VALUE; i++)
        {
            ((Class1)myObject).setMyProperty1(i);

        }
        timeStop[1] = new Date();
        System.out.println(timeStop[1].getTime() - timeStart[1].getTime());

        timeStart[2] = new Date();
        Class[] cArg = new Class[1];
        cArg[0] = int.class;
        for (int i = 0; i < 1024 ; i++)
        {
        	Method method = myObject.getClass().getMethod("setMyProperty1",cArg[0]);
            method.invoke(myObject, i);

        }
        timeStop[2] = new Date();
        System.out.println((timeStop[2].getTime() - timeStart[2].getTime())*(Integer.MAX_VALUE/1024));

    }
}
