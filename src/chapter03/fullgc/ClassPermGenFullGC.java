package chapter03.fullgc;

import java.lang.reflect.Method;
import java.net.URLClassLoader;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ClassPermGenFullGC {
	
	private static URLClassLoader parentClassLoader;
	
	static {
		parentClassLoader = (URLClassLoader)ClassPermGenFullGC.class.getClassLoader();
	}

	public static Object createProxy(Class <?>targetClass) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetClass);
		enhancer.setClassLoader(new URLClassLoader(parentClassLoader.getURLs() , parentClassLoader));
		enhancer.setUseCache(false);
		enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				return methodProxy.invokeSuper(object, args);
			}
		});
		return enhancer.create();
	}
	
	public static void main(String []args) {
		while(true) {
			createProxy(ClassPermGenFullGC.class);
		}
	}
}
