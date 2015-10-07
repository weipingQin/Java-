package chapter07.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AOPHandler implements InvocationHandler {

	private Object target;

	public AOPHandler(Object target) {
		this.target = target;
	}

	public void println(String str, Object... args) {
		System.out.println(str);
		if (args == null) {
			System.out.println("\t未传入任何值.....");
		} else {
			for (Object obj : args) {
				System.out.println(obj);
			}
		}
	}

	public Object invoke(Object proxyed, Method method, Object[] args)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		//System.out.println(method.getDeclaringClass());
		System.out.println("\n\n====>调用方法名：" + method.getName());
		Class<?>[] variables = method.getParameterTypes();
		System.out.println("\n\t参数类型列表：\n");
		for (Class<?> typevariables : variables) {
			System.out.println("\t\t\t" + typevariables.getName());
		}
		println("\n\n\t传入参数值为：\n");
		for(Object arg : args) {
			System.out.println("\t\t\t" + arg);
		}
		
		Object result = method.invoke(target, args);
		println("返回的参数为：", result);
		println("返回值类型为：", method.getReturnType());
		return result;
	}
}
