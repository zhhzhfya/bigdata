package cn.crxy.spider.job3.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.spider.job3.utils.RedisUtil;

public class RedisRepository implements Repository {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static String highKey = "spider.job3.todo.high";
	public static String lowKey = "spider.job3.todo.low";
	
	final RedisUtil redisUtil = new RedisUtil();
	
	@Override
	public String poll() {
		final String v1 = redisUtil.lpop(highKey);
		if(v1!=null) {
			return v1;
		}
		return redisUtil.lpop(lowKey);
	}

	@Override
	public void add(String nextUrl) {
		redisUtil.rpush(lowKey, nextUrl);
	}

	@Override
	public void addHigh(String nextUrl) {
		redisUtil.rpush(highKey, nextUrl);
	}

}
