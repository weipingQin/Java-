package chapter03.inst.redefineclass;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class InstForRedefineClass {

	private static Instrumentation inst;

	public static void premain(String agentArgs, Instrumentation instP) {
		inst = instP;
	}

	public static void redefineClass(Class<?> theClass, byte[] theClassFile)
			throws ClassNotFoundException, UnmodifiableClassException {
		inst.redefineClasses(new ClassDefinition(theClass, theClassFile));
	}
}
