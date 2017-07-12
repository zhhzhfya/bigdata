package cn.crxy.spider.job3.duplicatable;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashSetDuplicatable implements Duplicatable {
	final Logger logger = LoggerFactory.getLogger(getClass());
	private Set<String> duplicatable = new HashSet<String>();
	
	public void add(String target) {
		duplicatable.add(target);
		logger.debug("{}的当前大小是{}", getClass().getName(), duplicatable.size());
	}

	public boolean is(String target) {
		return duplicatable.contains(target);
	}

}
