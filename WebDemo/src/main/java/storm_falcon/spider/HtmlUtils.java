package storm_falcon.spider;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

class HtmlUtils {

    static int getDepth(DomNode element) {
        if (element.getNodeName().equalsIgnoreCase("html")) {
            return 0;
        }

        String nodeName;
        int depth = 0;
        do {
            DomNode parentNode = element.getParentNode();
            nodeName = parentNode.getNodeName();

            element = parentNode;
            ++depth;
        } while (!nodeName.equalsIgnoreCase("html"));

        return depth;
    }
}
