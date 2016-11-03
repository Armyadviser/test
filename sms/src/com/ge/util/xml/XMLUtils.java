package com.ge.util.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.function.Function;

/**
 * Created by Storm_Falcon on 2016/10/26.
 *
 */
public class XMLUtils {

	private static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

	public static <T> T parse(String text, Function<Document, T> parser) {
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new InputSource(new StringReader(text)));
			return parser.apply(document);
		} catch (Exception e) {
			System.out.println("error text:\n" + text);
			e.printStackTrace();
			return null;
		}
	}

	public static String getFirstValue(Document document, String tagName) {
		NodeList list = document.getElementsByTagName(tagName);
		if (list == null || list.getLength() == 0) {
			return null;
		}

		Node node = list.item(0);
		return node.getTextContent();
	}
}
