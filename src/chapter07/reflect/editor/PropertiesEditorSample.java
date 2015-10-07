package chapter07.reflect.editor;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PropertiesEditorSample {
	
	private final static Field[] TEST_FILEDS = TestDO.class.getDeclaredFields();
	
	static {//注册Editor
		PropertyEditorManager.registerEditor(NodeDO.class, NodeDOEditor.class);
	}

	public static void main(String []args) 
				throws IllegalAccessException, IllegalArgumentException,
				InvocationTargetException, IntrospectionException {
		Map<String , String> parameter = new HashMap<String , String>() {
			private static final long serialVersionUID = 1L;
			{
				put("nodeName" , "小胖测试");
				put("nodeDO" , "xieyuooo|pangpang@qq.com|2012-12-21 12:21:12");
			}
		};//模拟参数信息，就像requeset的ParameterMap类似
		TestDO testDO = convertParameterToDO(parameter);
		System.out.println(testDO.getNodeName());
		System.out.println(testDO.getNodeDO());
	}
	
	private static TestDO convertParameterToDO(Map<String , String> parameter) 
			throws IntrospectionException, IllegalAccessException, 
				   IllegalArgumentException, InvocationTargetException {
		TestDO testDO = new TestDO();
		for(Field field : TEST_FILEDS) {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName() , TestDO.class);
			Method method = propertyDescriptor.getWriteMethod();
			if(field.getType() == String.class) {
				method.invoke(testDO, parameter.get(field.getName()));
			}else {
				PropertyEditor propertyEditor = PropertyEditorManager.findEditor(field.getType());//查找Editor
				if(propertyEditor != null) {
					propertyEditor.setAsText(parameter.get(field.getName()));
					method.invoke(testDO, propertyEditor.getValue());
				}else {
					System.out.println("properties : " + field.getName() + " , can not find editor");
				}
			}
		}
		return testDO;
	}
}
