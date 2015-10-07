package chapter05.tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	
	private final static int GROUP_SIZE = 5;
	
	private final static Random random = new Random();
	
	public static void main(String []args) throws InterruptedException {
		processOneGroup("分组1");
		processOneGroup("分组2");
	}
	
	private static void processOneGroup(final String groupName) throws InterruptedException {
		final CountDownLatch preCountDown = new CountDownLatch(GROUP_SIZE);//等待所有队员准备就绪
		final CountDownLatch startCountDown = new CountDownLatch(1);
		final CountDownLatch endCountDown = new CountDownLatch(GROUP_SIZE);
		System.out.println("==========================>\n分组：" + groupName + "比赛开始：");
		for(int i = 0 ; i < GROUP_SIZE ; i++) {
			new Thread(String.valueOf(i)) {
				public void run() {
					preCountDown.countDown();//代表准备就绪
					System.out.println("我是线程组：【" + groupName + "】,第：" + this.getName() + " 号线程,我已经准备就绪！");
					try {
						startCountDown.await();//等待裁判发出开始指令：start_count_down.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(Math.abs(random.nextInt()) % 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("我是线程组：【" + groupName + "】,第：" + this.getName() + " 号线程,我已执行完成！");
					endCountDown.countDown();
				}
			}.start();
		}
		preCountDown.await();//等待所有队员就位
		System.out.println("各就各位，预备！");
		startCountDown.countDown();//开始赛跑
		try {
			endCountDown.await();//等待多个赛跑者逐个结束
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(groupName + "比赛结束！");
	}
}
