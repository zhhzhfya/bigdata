package cn.crxy.spider.job3.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.spider.job3.Page;

public class ConsoleStore implements Storable {
	final Logger logger = LoggerFactory.getLogger(getClass());
	public void store(Page page) {
		System.out.println("url="+page.getUrl()+" price="+page.getValues().get("price"));
	}

}
