package chapter07.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestMain {

	public static void main(String []args)
			throws InstantiationException, IllegalAccessException {
		@SuppressWarnings({ "unchecked", "serial" })
		List<HashMap<String , String>>list = Arrays.asList(
				new HashMap<String , String>() {
					{
						put("name" , "xieyuooo");
						put("title" , "小胖");
					}
				},
				new HashMap<String , String>() {
					{
						put("name" , "ffff");
						put("title" , "标题2");
					}
				}
		);
		List<UserDO>users = new ArrayList<UserDO>(list.size());
		for(HashMap<String , String> row : list) {
			users.add(ConvertionService.convertMapToBean(row, UserDO.class));
		}
		System.out.println();
		//这里大家可以将users的列表进行输出
	}
}
