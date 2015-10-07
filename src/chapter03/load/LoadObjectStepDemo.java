package chapter03.load;

class Parent {
	
	public Parent() {
	   System.out.println("parent constructor init....");	
	}
	   
	static {
	    System.out.println("parent static block init....");	
	}
	
	{
		System.out.println("parent normal block call....");	
	}
}

class Child extends Parent {
	
	static {
		System.out.println("child static block call....");
	}
	
	{
		System.out.println("child block call....");
	}
	
	public Child() {
	    System.out.println("child constructor call....");	
	}
}
public class LoadObjectStepDemo {

	public static void main(String []args) {
		new Child();
	}
}
