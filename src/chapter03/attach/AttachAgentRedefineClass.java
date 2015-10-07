package chapter03.attach;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AttachAgentRedefineClass {

	private static Instrumentation inst;

	public static void agentmain(String agentArgs, Instrumentation instP) {
		inst = instP;
	}

	public static void redefineClass(Class<?> theClass, byte[] theClassFile)
			throws ClassNotFoundException, UnmodifiableClassException {
		inst.redefineClasses(new ClassDefinition(theClass, theClassFile));
	}
}
