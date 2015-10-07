package chapter07.reflect;

import java.lang.reflect.Method;

public class MethodInvokeSample {

	public static void main(String []args) 
					throws Exception {
		Method method = MethodInvokeSample.class.getDeclaredMethod("test", String.class , int.class);
		String result = (String)method.invoke(null, "fuck" , 2);
		System.out.println(result);
	}
	
	
	public static String test(String a , int b) {
		return "传入参数1：" + a + ",传入的参数2：" + b;
	}
}
