package cn.crxy.spider.job3.repository;

public interface Repository {

	String poll();

	void add(String nextUrl);

	void addHigh(String nextUrl);

}
