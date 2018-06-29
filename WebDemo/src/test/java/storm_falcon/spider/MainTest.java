package storm_falcon.spider;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private HtmlPage page;

    @BeforeEach
    void prepare() throws IOException {
        String url = "http://jmjh.miit.gov.cn/loadModuleWebMessage.action?moduleId=1066";

        WebClient webClient = new WebClient();
        WebClientOptions options = webClient.getOptions();
        options.setJavaScriptEnabled(false);
        options.setCssEnabled(false);
        options.setRedirectEnabled(true);
        options.setThrowExceptionOnScriptError(false);

        webClient.setCookieManager(new CookieManager());
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(1000);

        webClient.close();
    }

    @Test
    void xPathTest() {
        List<Object> byXPath = page.getByXPath("//a");
        byXPath.forEach(System.out::println);
    }
}