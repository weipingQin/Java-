
import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

import java.lang.reflect.Field;
import java.util.Map;

@BTrace
public class BTraceTest {

	@TLS
    static long startTime;

    @OnMethod(
            clazz="chapter3.tools.btrace.TestHello",
            method="test"
    )
    public static void traceExecute() {
            startTime = timeMillis();
    }

    @OnMethod(
            clazz="chapter3.tools.btrace.TestHello",
            method="test",
            location=@Location(Kind.RETURN)
    )
    public static void traceExecute(@Return boolean result ,
                                    @Self chapter3.tools.btrace.TestHello testHello,
                                    @ProbeClassName String className,
                                    @ProbeMethodName String methodName,
                                    int time ,
                                    String name ,
                                    Map<String , String>map) {
         println("====================================>");
         println(strcat("call class = " , className));
         println(strcat("call method = " , methodName));

         Field fieldName = field(className , "name");
         Field fieldFreeMSize = field(className , "THREE_M_SIZE");

         println(strcat("property name = " , str(get(fieldName , testHello))));
         println(strcat("static property THREE_M_SIZE = " , str(get(fieldFreeMSize))));


         println(strcat("map.toString() = " , str(map)));
         println(strcat("map.get() = " , get(map,"fuck")));
         println(strcat(strcat("times:",str(time)), strcat(" , name = " , name)));
         jstack();
         println(strcat("delay:" , str(timeMillis() - startTime)));
         println(strcat("return value is:",str(result)));
         
    }
}
