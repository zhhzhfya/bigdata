package xfly.web.grab.tool;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class PageCrawer {
	// para:uri
	// ret:网页内容：
	// 是在怕的时候选择获得 还是在获得后再提取
	public static void main(String[] args) {

		NodeList rt = getNodeList("http://tieba.baidu.com/f?kw=%B3%C9%B6%BC%C0%ED%B9%A4%B4%F3%D1%A7");
		System.out.println(rt.toHtml());

	}

	public static NodeList getNodeList(String url) {
		Parser parser = null;
		HtmlPage visitor = null;

		try {
			parser = new Parser(url);
			// parser.setEncoding("GBK"); //html中文乱码
			parser.setEncoding("UTF-8");
			visitor = new HtmlPage(parser);
			parser.visitAllNodesWith(visitor);

		} catch (ParserException e) {
			e.printStackTrace();
		}
		NodeList nodeList = visitor.getBody();
		// visitor.
		return nodeList;

	}

}
