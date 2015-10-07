package chapter03.jmx;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Set;
import javax.management.AttributeNotFoundException;
import javax.management.BadAttributeValueExpException;
import javax.management.BadBinaryOpValueExpException;
import javax.management.BadStringOperationException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.InvalidApplicationException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.QueryExp;
import javax.management.ReflectionException;
import javax.management.RuntimeMBeanException;

public class JMXUtils {

	private final static MBeanServer DEFAULT_MBEAN_SERVER = ManagementFactory
			.getPlatformMBeanServer();

	public static long getYongGC() {
		return getYoungGC(DEFAULT_MBEAN_SERVER);
	}

	public static long getFullGC() {
		return getFullGC(DEFAULT_MBEAN_SERVER);
	}

	public static long findLoadedClass() {
		return findLoadedClass(DEFAULT_MBEAN_SERVER);
	}

	public static long getYoungGC(MBeanServerConnection mbeanServer) {
		try {
			ObjectName objectName;
			if (mbeanServer.isRegistered(new ObjectName("java.lang:type=GarbageCollector,name=ParNew"))) {
				objectName = new ObjectName("java.lang:type=GarbageCollector,name=ParNew");
			} else if (mbeanServer.isRegistered(new ObjectName("java.lang:type=GarbageCollector,name=Copy"))) {
				objectName = new ObjectName("java.lang:type=GarbageCollector,name=Copy");
			} else {
				objectName = new ObjectName("java.lang:type=GarbageCollector,name=PS Scavenge");
			}
			return (Long) mbeanServer.getAttribute(objectName , "CollectionCount");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static long getFullGC(MBeanServerConnection mbeanServer) {
		try {
			ObjectName objectName;
			if (mbeanServer.isRegistered(new ObjectName("java.lang:type=GarbageCollector,name=ConcurrentMarkSweep"))) {
				objectName = new ObjectName("java.lang:type=GarbageCollector,name=ConcurrentMarkSweep");
			} else if (mbeanServer.isRegistered(new ObjectName("java.lang:type=GarbageCollector,name=MarkSweepCompact"))) {
				objectName = new ObjectName("java.lang:type=GarbageCollector,name=MarkSweepCompact");
			} else {
				objectName = new ObjectName("java.lang:type=GarbageCollector,name=PS MarkSweep");
			}
			return (Long) mbeanServer.getAttribute(objectName , "CollectionCount");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static long findLoadedClass(MBeanServerConnection mBeanServer) {
		try {
			return (Long) (mBeanServer.getAttribute(new ObjectName(
					"java.lang:type=ClassLoading"), "TotalLoadedClassCount"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void traceOneDomain(String doMain,
			MBeanServerConnection mBeanServer)
			throws MalformedObjectNameException, IntrospectionException,
			InstanceNotFoundException, AttributeNotFoundException,
			ReflectionException, MBeanException, IOException {
		
		Set<ObjectInstance> set = mBeanServer.queryMBeans(new ObjectName(doMain + ":*"), new QueryExp() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean apply(ObjectName name)
					throws BadStringOperationException,
					BadBinaryOpValueExpException,
					BadAttributeValueExpException, InvalidApplicationException {
				return true;
			}

			@Override
			public void setMBeanServer(MBeanServer s) {}
		});
		for (ObjectInstance objectInstance : set) {
			System.out.println("\t\t\t" + objectInstance.getObjectName() + "\t"
					+ objectInstance.getClassName());
			traceMebeanInfo(mBeanServer, objectInstance.getObjectName());
		}
	}

	public static void traceMebeanInfo(MBeanServerConnection mBeanServer,
			ObjectName objectName) throws IntrospectionException,
			InstanceNotFoundException, MalformedObjectNameException,
			ReflectionException, AttributeNotFoundException, MBeanException,
			IOException {
		MBeanInfo mBeanInfo = mBeanServer.getMBeanInfo(objectName);
		MBeanAttributeInfo[] mBeanAttributes = mBeanInfo.getAttributes();

		System.out.println("\t\t\tMBeanInfos : ");
		for (MBeanAttributeInfo mBeanAttribute : mBeanAttributes) {
			try {
				System.out.println("\t\t\t\t\t"
						+ mBeanAttribute.getName()
						+ "\t"
						+ mBeanAttribute.getType()
						+ "\tvalue = >"
						+ mBeanServer.getAttribute(objectName,
								mBeanAttribute.getName()));
			} catch (RuntimeMBeanException e) {
				if (e.getCause() instanceof UnsupportedOperationException) {
					System.out.println("\t\t\t\t\t" + mBeanAttribute.getName()
							+ "\t" + mBeanAttribute.getType()
							+ "\tvalue = > value not supported");
				}
			}

		}
	}

	public static void traceAll(MBeanServerConnection mBeanServer)
			throws MalformedObjectNameException, IntrospectionException,
			InstanceNotFoundException, AttributeNotFoundException,
			ReflectionException, MBeanException, IOException {
		System.out.println("MBean count = " + mBeanServer.getMBeanCount());
		String[] domains = mBeanServer.getDomains();
		for (String domain : domains) {
			System.out.println("\tbegin trace domain -> " + domain);
			traceOneDomain(domain, mBeanServer);
		}
	}
}
