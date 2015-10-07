package chapter03.attach;

import java.io.IOException;
import java.util.List;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class AttachLoadAgentMain {

	public static void main(String []args) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
		List<VirtualMachineDescriptor> machineDescriptors = VirtualMachine.list();
		for(VirtualMachineDescriptor machineDescriptor : machineDescriptors) {
			String name = machineDescriptor.displayName();
			if(name != null && name.contains("RedeineClassMain")) {
				System.out.println("attach to machine : " + name);
				VirtualMachine machine =  VirtualMachine.attach(machineDescriptor);
				machine.loadAgent("/ux/xxx/redefineclass2.jar");
				break;
			}
		}
	}
}
