package chapter03.inst.transformer;

import java.lang.instrument.UnmodifiableClassException;

import chapter03.asm.ForASMTestClass;

public class TestformerTestMain {
	
	public static void main(String []args) throws UnmodifiableClassException {
		//InstForTransformer.reTransClass(ForASMTestClass.class);
		ForASMTestClass testClass = new ForASMTestClass();
		testClass.display1();
		testClass.display2();
	}
}
