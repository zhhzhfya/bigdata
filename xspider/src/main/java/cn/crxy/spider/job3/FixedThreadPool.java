package cn.crxy.spider.job3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool implements ThreadPool{
	private  ExecutorService newFixedThreadPool;

	public FixedThreadPool(int nThreads) {
		this.newFixedThreadPool = Executors.newFixedThreadPool(nThreads);
	}
	
	
	@Override
	public void run(Runnable command) {
		newFixedThreadPool.execute(command);
	}

}
