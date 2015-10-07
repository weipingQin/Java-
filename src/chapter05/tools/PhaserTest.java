package chapter05.tools;

import java.util.concurrent.Phaser;

/**
 * 此代码需要在JDK 1.7以上的版本运行
 * @author xieyuooo
 *
 */
public class PhaserTest {

	private final static int COUNTER = 5;
	
	public static void main(String []args) throws InterruptedException {
		final Phaser phaser = new Phaser(COUNTER);
		for(int i = 0 ; i < COUNTER; i++) {
			final int num = i;
			Thread t = new Thread() {
				public void run() {
					System.out.println("当前线程编号:" + num + " 准备开始执行 \t" + System.currentTimeMillis());
					phaser.arriveAndAwaitAdvance();
					System.out.println("执行列表:" + num + " 开始执行 \t" + System.currentTimeMillis());
					try {
						Thread.sleep(this.hashCode() & 2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					phaser.arriveAndDeregister();
					//phaser.arriveAndDeregister();//做多次就会有问题
					System.out.println("线程：" + num + " 执行完成 \t" + System.currentTimeMillis());
				}
			};
			Thread.sleep(1234);
			System.out.println("未达arriveAndAwaitAdvance个数：" + phaser.getUnarrivedParties());
			t.start();
		}
		System.out.println("注册总个数：" + phaser.getRegisteredParties());
		while(!phaser.isTerminated()) {
			System.out.println("达到个数：" + phaser.getRegisteredParties() + "\t" + System.currentTimeMillis());
			Thread.sleep(20);
		}
		System.out.println("end .....");
	}
}
