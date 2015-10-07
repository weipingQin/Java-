package chapter01;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigNumberTest {

	public static void main(String []args) {
		//这个数字long是放不下的
		BigDecimal bigDecimal = new BigDecimal("1233243243243243243243243243243243241432423432");
		System.out.println("数字的原始值是：" + bigDecimal);
		
		//bigDecimal = bigDecimal.add(BigDecimal.TEN);
		//System.out.println("添加10以后：" + bigDecimal);
		
		//二进制数字
		byte[] bytes = bigDecimal.toBigInteger().toByteArray();
		for(byte b : bytes) {
			String bitString = lpad(Integer.toBinaryString(b & 0xff) , '0' , 8);
			System.out.println(bitString);
		}
		//还原结果
		BigInteger bigInteger = new BigInteger(bytes);
		System.out.println("还原结果为：" + bigInteger);
	}
	
	private static String lpad(String end , char c , int paddingLength) {
		if(end == null) {
			end = "";
		}
		if(end.length() >= paddingLength) {
			return end;
		}
		StringBuilder buf = new StringBuilder(paddingLength);
		for(int i = 0 ,size = paddingLength - end.length() ; i < size ; i++) {
			buf.append(c);
		}
		return buf.append(end).toString();
	}
}
