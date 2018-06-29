package storm_falcon.spider;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "http://jmjh.miit.gov.cn/loadModuleWebMessage.action?moduleId=1066";

        WebClient webClient = new WebClient();
        WebClientOptions options = webClient.getOptions();
        options.setJavaScriptEnabled(false);
        options.setCssEnabled(false);
        options.setRedirectEnabled(true);
        options.setThrowExceptionOnScriptError(false);

        webClient.setCookieManager(new CookieManager());
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(1000);

        List<HtmlAnchor> anchors = page.getAnchors();
        anchors.forEach(anchor -> {
            System.out.println(anchor.getTextContent() + "\t" + HtmlUtils.getDepth(anchor));
        });

        webClient.close();
    }
}
