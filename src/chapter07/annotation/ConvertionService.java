package chapter07.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class ConvertionService {

	private static List<Field> findFields(Class <?>clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		Set<String>fieldNames = new HashSet<String>();
        Class <?>clazzTemp = clazz;
        while(clazzTemp != Object.class) {
        	Field []fields = clazzTemp.getDeclaredFields();
        	for(Field field : fields) {
        		if(!fieldNames.contains(field.getName())) {//同名属性子类覆盖
        			fieldList.add(field);
        			fieldNames.add(field.getName());
        		}
        	}
            clazzTemp = clazzTemp.getSuperclass();
        }
        return fieldList;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convertMapToBean(Map<String , String>row , Class<T>clazz) 
				throws InstantiationException, IllegalAccessException {
		Object object = clazz.newInstance();
		List<Field>list = findFields(clazz);
		for(Field field : list) {
			if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
				if(!field.isAccessible()) field.setAccessible(true);
				Column columnAnnotation = field.getAnnotation(Column.class);
				if(columnAnnotation != null) {
					String value = row.get(columnAnnotation.name());//通过annotation注解的名称来获取对应的值
					Class <?>fieldType = field.getType();
					if(fieldType == String.class) {
						field.set(object, value);
					}else if(fieldType == Integer.class) {
						field.set(object, getInteger(value));
					}else if(fieldType == int.class) {
						field.setInt(object, getInt(value));
					}else if(fieldType == Long.class) {
						field.set(object, getLongWrapper(value));
					}else if(fieldType == long.class) {
						field.setLong(object , getLong(value));
					}/*
						继续的数据类型大家可以自己补充
					*/
				}else {
					/*没有annotation的代码交给大家自己去完成了*/
				}
			}
		}
		return (T)object;
	}
	
	public static Integer getInteger(String value) {
        if (StringUtils.isEmpty(value)) return null;
        return Integer.valueOf(value);
    }

    public static int getInt(String value) {
        if (StringUtils.isEmpty(value)) return 0;
        return Integer.valueOf(value);
    }

    public static Long getLongWrapper(String value) {
        if (StringUtils.isEmpty(value)) return null;
        return Long.valueOf(value);
    }

    public static long getLong(String value) {
        if (StringUtils.isEmpty(value)) return 0;
        return Long.valueOf(value);
    }
}
