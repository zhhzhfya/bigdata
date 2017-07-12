package cn.crxy.spider.job3.store;

import cn.crxy.spider.job3.Page;

public interface Storable {

	/**
	 * 存储下载的数据
	 * @param page
	 */
	public abstract void store(Page page);

}