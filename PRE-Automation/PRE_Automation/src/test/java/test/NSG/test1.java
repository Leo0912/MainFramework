package test.NSG;

import java.lang.reflect.Method;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class test1 {
	
	public void test6() throws Exception {
		//throw new SkipException("");
		// TODO Auto-generated method stub
		//launchProcess("test.NewsGroupSmokeTesting", "globalHeaderValidation");
	}
	@Test
	public void testClinton() {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		
	}
	public void launchProcess(String className, String methodName) throws Exception {

	    Class<?> processClass = Class.forName(className); // convert string classname to class
	    Object process = processClass.newInstance(); // invoke empty constructor

	    Method aMethod = process.getClass().getMethod(methodName);
	    Object res = aMethod.invoke(process); 
	}

}
