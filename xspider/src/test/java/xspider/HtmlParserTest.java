package xspider;

import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.visitors.NodeVisitor;

public class HtmlParserTest {

	public static void main(String[] args) throws Exception {
		Parser parser = new Parser( (new URL("http://www.jd.com/allSort.aspx")).openConnection() );
		final Node[] topNodeArray = parser.extractAllNodesThatMatch(new CssSelectorNodeFilter("div[id='allsort'] div[class='m']>div[class='mt']>h2>a")).toNodeArray();
		for (int i = 0; i < topNodeArray.length; i++) {
			final Node topNode = topNodeArray[i];
			topNode.accept(new NodeVisitor(true) {
				@Override
				public void visitTag(Tag tag) {
					final String name = tag.toPlainTextString();
					final String url = tag.getAttribute("href");
					System.out.println(name+"\t"+url);
				}
			});
		}


	}

}
