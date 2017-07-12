package xspider;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * HtmlUnit – Getting Started with HtmlUnit
 * http://htmlunit.sourceforge.net/gettingStarted.html
 * 
 * @author xuefeng
 *
 */
public class HtmlUnitTest {
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		final WebClient webClient=new WebClient();
		final HtmlPage page=webClient.getPage("http://www.yanyulin.info");
		System.out.println(page.asText());
		webClient.closeAllWindows();
	}

	public static void homePage() throws Exception {

		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient
					.getPage("http://htmlunit.sourceforge.net");
			// Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit",
			// page.getTitleText());

			final String pageAsXml = page.asXml();
			// Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

			final String pageAsText = page.asText();
			System.out.println(pageAsText);
			// Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		}
	}

}
