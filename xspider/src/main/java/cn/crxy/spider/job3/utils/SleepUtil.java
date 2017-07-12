package cn.crxy.spider.job3.utils;

public class SleepUtil {

		public static void sleep(long milliSeconds) {
			try {
				Thread.currentThread().sleep(milliSeconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
}
