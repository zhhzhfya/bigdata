package cn.crxy.spider.job3.repository;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryRepository implements Repository {
	final Logger logger = LoggerFactory.getLogger(getClass());
	private ConcurrentLinkedQueue<String> highPriority = new ConcurrentLinkedQueue<String>(); //高优先级
	private ConcurrentLinkedQueue<String> lowPriority = new ConcurrentLinkedQueue<String>();  //低优先级
	
	public String poll() {
		if(!highPriority.isEmpty()) {
			return highPriority.poll();
		}
		if(!lowPriority.isEmpty()) {
			return lowPriority.poll();
		}
		return null;
	}

	public void add(String nextUrl) {
		lowPriority.add(nextUrl);
	}
	
	public void addHigh(String nextUrl) {
		highPriority.add(nextUrl);
	}

}
