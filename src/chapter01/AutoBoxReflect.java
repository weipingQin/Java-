package chapter01;

import java.lang.reflect.Field;

public class AutoBoxReflect {

	public int a;

	public Integer b;

	public void setA(int a) {
		this.a = a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	public static void main(String[] args) throws Exception {
		AutoBoxReflect test = new AutoBoxReflect();
		Field field1 = AutoBoxReflect.class.getDeclaredField("a");
		Field field2 = AutoBoxReflect.class.getDeclaredField("b");

		field1.set(test, 100);
		System.out.println(field1.get(test));
		System.out.println(field1.getInt(test));

		field2.set(test, 100);
		// field2.setInt(integerTest, 100);//这条代码会报错

		/*
		 * IntegerPass.class.getDeclaredMethod("setA", Integer.class)
		 * .invoke(integerTest, 100);
		 * 
		 * IntegerPass.class.getDeclaredMethod("setA", Integer.class)
		 * .invoke(integerTest, new Integer(100));
		 * 
		 * IntegerPass.class.getDeclaredMethod("setA", int.class)
		 * .invoke(integerTest, 100);
		 * 
		 * IntegerPass.class.getDeclaredMethod("setA", int.class)
		 * .invoke(integerTest, new Integer(100));
		 */
	}
}
