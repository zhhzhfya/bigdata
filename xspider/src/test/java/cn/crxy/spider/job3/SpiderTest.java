package cn.crxy.spider.job3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cn.crxy.spider.job3.download.HttpClientDownload;
import cn.crxy.spider.job3.duplicatable.BloomFilterDuplicatable;
import cn.crxy.spider.job3.process.JdProcess;
import cn.crxy.spider.job3.repository.MemoryRepository;
import cn.crxy.spider.job3.store.ConsoleStore;
import cn.crxy.spider.job3.utils.HtmlXParser;

public class SpiderTest {
	String url = "http://www.jd.com";
	Spider spider;
	
	@Before	//每个测试方法执行之前，before都要先执行
	public void before() {
		this.spider = new Spider();
		this.spider.setDownloadable(new HttpClientDownload());
		this.spider.setProcessable(new JdProcess());
		this.spider.setStorable(new ConsoleStore());
		this.spider.setRepository(new MemoryRepository());
		this.spider.setDuplicatable(new BloomFilterDuplicatable());
		this.spider.setSeedUrl(url);
	}
	
	@Test
	public void test() {
		try {
			this.spider.start();
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test @Ignore
	public void testDownload() {
		final Page page = this.spider.download(url);
		Assert.assertNotNull(page);
		
		String rawHtml = page.getRawHtml();
		Assert.assertNotNull(rawHtml);
	}
	
	@Test @Ignore
	public void testProcess() {
		final Page page = this.spider.download(url);
		this.spider.process(page);
		
		final int size = page.getValues().keySet().size();
		Assert.assertTrue(size>0);
	}
	
	@Test  @Ignore
	public void testStore() {
		final Page page = this.spider.download(url);
		this.spider.process(page);
		this.spider.store(page);
		Assert.assertTrue(true);
	}
	
	@Test  @Ignore
	public void testHtmlXParser() {
		final Page page = this.spider.download(url);
		String name = (new HtmlXParser(page.getRawHtml())).select("//div[@id='name']").text();
		System.out.println(name);
		Assert.assertNotNull(name);
	}
	
	@Test  @Ignore
	public void testFindAlla() {
		final Page page = this.spider.download(url);
		String as = (new HtmlXParser(page.getRawHtml())).select("//a").getAttributeByName("href");
		System.out.println(as);
	}
	
}
