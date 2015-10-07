package chapter05.atomic.extend;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeTest {
	
	long longV = 2;
	
	int intV1;
	
	int intV2;
	
	String field1 = "123";
	
	String field2;
	
	static String STATIC_FIELD;
	
	static String STATIC_FIELD2;

	public static void main(String []args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Unsafe unsafe = getUnsafe();
		System.out.println("JVM地址宽度：" + unsafe.addressSize());//输出JVM地址宽度
		
		Field field1 = UnsafeTest.class.getDeclaredField("longV");
		Field field2 = UnsafeTest.class.getDeclaredField("intV1");
		Field field3 = UnsafeTest.class.getDeclaredField("intV2");
		Field field4 = UnsafeTest.class.getDeclaredField("field1");
		Field field5 = UnsafeTest.class.getDeclaredField("field2");
		Field staticField = UnsafeTest.class.getDeclaredField("STATIC_FIELD");
		Field staticField2 = UnsafeTest.class.getDeclaredField("STATIC_FIELD2");
		
		System.out.println(unsafe.objectFieldOffset(field1));
		System.out.println(unsafe.objectFieldOffset(field2));
		System.out.println(unsafe.objectFieldOffset(field3));
		System.out.println(unsafe.objectFieldOffset(field4));
		System.out.println(unsafe.objectFieldOffset(field5));
		System.out.println(unsafe.objectFieldOffset(field5));
		
		System.out.println(unsafe.staticFieldOffset(staticField));
		System.out.println(unsafe.staticFieldOffset(staticField2));
	}

	private static Unsafe getUnsafe() throws IllegalAccessException {
		Field filed = Unsafe.class.getDeclaredFields()[0];
		filed.setAccessible(true);
		Unsafe unsafe = (Unsafe)filed.get(null);
		return unsafe;
	}
}
