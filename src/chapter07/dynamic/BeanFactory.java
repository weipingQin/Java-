package chapter07.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class BeanFactory {
	
	public static Object getBean(String className) 
		throws InstantiationException, IllegalAccessException,
			   ClassNotFoundException {
		Object obj = Class.forName(className).newInstance();
		InvocationHandler handler = new AOPHandler(obj);//¶¨Òå¹ýÂËÆ÷
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(), handler);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String className , Class<T> clazz) 
		throws InstantiationException, IllegalAccessException,
				ClassNotFoundException {
		return (T)getBean(className);
	}
}
