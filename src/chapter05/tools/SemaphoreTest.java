package chapter05.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	private final static Semaphore MAX_SEMA_PHORE = new Semaphore(5);
	
	private final static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getDateTime() {
        Date datetime = Calendar.getInstance().getTime();
        return DEFAULT_DATE_FORMAT.format(datetime);
    }
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			final int num = i;
			final Random radom = new Random();
			new Thread() {
				public void run() {
					boolean acquired = false;
					try {
						MAX_SEMA_PHORE.acquire();
						acquired = true;
						System.out.println("我是线程：" + num + " 我获得了使用权！" + getDateTime());
						Thread.sleep(1000 + (radom.nextInt() & 5000));
						System.out.println("我是线程：" + num + " 我执行完了！" + getDateTime());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (acquired) {
							MAX_SEMA_PHORE.release();
						}
					}
				}
			}.start();
		}
	}
}
