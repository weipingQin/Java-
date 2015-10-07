package chapter02;

class VolatileInteger {
	volatile int number;
}
public class VolatileArrayTest {

	/**
	 * 本例中，程序仅循环数次，如果要看出实际的效果，需要循环很多次才能看到实际的效果
	 * 包含了运行效率以及CPU使用量的变化
	 * @param args
	 */
	public static void main(String []args) {
		final VolatileInteger[]values = new VolatileInteger[10];
		for(int i = 0 ; i < 10 ; i++) {
			values[i] = new VolatileInteger();
		}
		for(int i = 0 ; i < 10 ; i++) {
			final int value = i;
			new Thread() {
				public void run() {
					for(int j = value ; j < 1000 ; j++) {
						values[value].number = value;
					}
				}
			}.start();
		}
	}
	
}
