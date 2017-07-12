package cn.crxy.spider.job3.process;

import cn.crxy.spider.job3.Page;

public interface Processable {

	/**
	 * 解析下载的页面
	 * @param page
	 */
	public abstract void process(Page page);

}