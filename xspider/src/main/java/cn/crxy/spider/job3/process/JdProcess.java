package cn.crxy.spider.job3.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.spider.job3.Page;
import cn.crxy.spider.job3.utils.HtmlXParser;
import cn.crxy.spider.job3.utils.JSONUtils;
import cn.crxy.spider.job3.utils.RegExp;

public class JdProcess implements Processable {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void process(Page page) {
		final String rawHtml = page.getRawHtml();
		if(rawHtml==null) {
			return;
		}
		final String url = page.getUrl();
		//处理产品明细
		if(url.startsWith("http://item.jd.com/")) {
			parseProduct(page, rawHtml, url);
		}
		
		String as = (new HtmlXParser(page.getRawHtml())).select("//a").getAttributeByName("href");
		if(as!=null) {
			for(String nextUrl : as.split(",")){
				if(nextUrl.startsWith("javascript:")) {
					continue;
				}
				
				page.addTargetUrl(nextUrl);
			}
		}
	}

	public void parseProduct(Page page, final String rawHtml, final String url) {
		//解析分类
		String style = (new HtmlXParser(rawHtml)).select("//div[@class='breadcrumb']//a").text();
		page.addField("style", style);
		//解析名称
		String name = (new HtmlXParser(rawHtml)).select("//div[@id='name']").text();
		page.addField("name", name);
		
		//请求价格
		String skuid = RegExp.parse("\\d+", url);
		final String priceUrl = "http://p.3.cn/prices/get?skuId=J_"+skuid ;
		final String priceJSON = JSONUtils.parseFromUrl(priceUrl);
		final String price = JSONUtils.parseJSONArrayIndex0(priceJSON, "p");
		page.addField("price", price);
	}

}
