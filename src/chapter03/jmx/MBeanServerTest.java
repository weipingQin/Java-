package chapter03.jmx;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;

public class MBeanServerTest {

	public static void main(String[] args) throws MalformedObjectNameException,
			IntrospectionException, InstanceNotFoundException,
			AttributeNotFoundException, ReflectionException, MBeanException,
			IOException {
		// System.gc();
		// System.out.println(JMXUtils.getYongGC());
		// System.out.println(JMXUtils.getFullGC());

		JMXUtils.traceAll(ManagementFactory.getPlatformMBeanServer());

	}
}
