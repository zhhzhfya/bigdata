package cn.crxy.spider.job3.download;

import cn.crxy.spider.job3.Page;

public interface Downloadable {

	/**
	 * 爬虫下载数据
	 * @param url
	 * @return 
	 */
	public abstract Page download(String url);

}