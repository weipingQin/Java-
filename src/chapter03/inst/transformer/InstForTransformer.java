package chapter03.inst.transformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class InstForTransformer {
	
    private static Instrumentation inst;
	
    /*编译为agent后，系统启动执行main方法前会调用它*/
    public static void premain(String agentArgs, Instrumentation instP) {
       inst = instP;
       inst.addTransformer(new TestTransformer() , true);
       //设置为true后，可以在运行时进行retransformClasses方法，否则调用retransformClasses无效
       //inst.addTransformer(new TestTransformer() , true);
    }
    
    public static void reTransClass(Class <?>clazz) throws UnmodifiableClassException {
    	inst.retransformClasses(clazz);
    }
}
