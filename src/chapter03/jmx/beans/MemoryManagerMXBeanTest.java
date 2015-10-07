package chapter03.jmx.beans;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.List;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ReflectionException;

public class MemoryManagerMXBeanTest {

	public static void main(String []args) throws IntrospectionException, InstanceNotFoundException, ReflectionException, AttributeNotFoundException, MBeanException {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		System.gc();
		
		List<MemoryManagerMXBean> list = ManagementFactory.getMemoryManagerMXBeans();
		for(MemoryManagerMXBean memoryManagerMXBean : list) {
			System.out.println(memoryManagerMXBean.getName());
			System.out.println(memoryManagerMXBean.getObjectName());
			MBeanInfo mBeanInfo = mBeanServer.getMBeanInfo(memoryManagerMXBean.getObjectName());
			
			MBeanAttributeInfo[] mBeanAttributes = mBeanInfo.getAttributes();
			for(MBeanAttributeInfo mBeanAttribute : mBeanAttributes) {
				System.out.println("=============>" + mBeanAttribute.getName() + "\t" + mBeanAttribute.getType());
				System.out.println("=============value = >" + mBeanServer.getAttribute(memoryManagerMXBean.getObjectName(), mBeanAttribute.getName()));
			}
			
			/*String []poolNames = memoryManagerMXBean.getMemoryPoolNames();
			for(String poolName : poolNames) {
				System.out.println("\t" + poolName);
			}*/
		}
	}
}
