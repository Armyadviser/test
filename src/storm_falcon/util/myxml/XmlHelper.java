package storm_falcon.util.myxml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;
import java.util.Map;

/**
 * Created by Storm_Falcon on 2015/10/20.
 * myxml helper
 */
public class XmlHelper {

    private BaseNode root;

    private Document document;

    /**
     * �մ���XmlHelper����ʱΪtrue��
     * ��������һ���ڵ����Ϊfalse��
     */
    private boolean bFirstNode = true;

    public XmlHelper() {
        XmlProperty property = new XmlProperty();
        property.isFormat = XmlProperty.FORMAT_ON;
        setProperties(property);
    }

    public void setProperties(XmlProperty property) {
        if (root == null) {
            root = new TextNode(XmlProperty.NODE_KEY_DEFAULT, XmlProperty.NODE_VALUE_DEFAULT);
        }
        root.setProperty(property);
    }

    public BaseNode createNode(String name) {
        BaseNode temp = new Node(name);
        if (bFirstNode) {
            root = temp;
            bFirstNode = false;
        }
        return temp;
    }

    public BaseNode createTextNode(String name, String value) {
        BaseNode temp = new TextNode(name, value);
        if (bFirstNode) {
            root = temp;
            bFirstNode = false;
        }
        return temp;
    }

    /**
     * ��ָ�����ڵ��²����ӽڵ�
     * @param parentName ���ڵ�����key
     * @param node
     */
    public void insert(String parentName, BaseNode node) throws XmlException {
        //������ڵ���������ڵ�����ͬ��ֱ�ӷ��أ�������
        if (parentName.equals(node.getName())) {
            return;
        }
        if (!insert(root, parentName, node)) {
            throw new XmlException("Parent Node is not exists:" + parentName);
        }
    }

    /**
     * ��ָ�����ڵ��²����ӽڵ�
     * @param parentNode ��ǰ���ڵ�
     * @param name Ŀ�길�ڵ���
     * @param node �ӽڵ�
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean insert(BaseNode parentNode, String name, BaseNode node) {
        String parentName = parentNode.getName();

        //���������Ҷ�ӽڵ㣬��û�ҵ��������ʧ��
        if (!parentName.equals(name) && parentNode instanceof TextNode) {
            return false;
        }

        node.setLevel(parentNode.getLevel() + 1);

        //���������Ҷ�ӽڵ㣬��ԭֵ�滻
        if (parentName.equals(name) && parentNode instanceof TextNode) {
//            Node tempNode = new Node(parentNode.getName());
//            tempNode.add(node);
//            parentNode = tempNode;
            parentNode.setValue(node.getValue());
            return true;
        }

        //�����������Ҷ�ӽڵ㣬����ӵ�β��
        if (parentName.equals(name) && parentNode instanceof Node) {
            ((Node) parentNode).add(node);
            return true;
        }

        //�����δ�ҵ���������ò���ӽڵ㣬����һ�����
        List<BaseNode> children = (List<BaseNode>) parentNode.getValue();
        for (BaseNode child : children) {
            if (insert(child, name, node)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Mapת��Xml
     * @param map
     * @param rootName ���ڵ���
     * @return
     * @throws XmlException
     */
    public static String map2Xml(Map<String, ?> map, String rootName) throws XmlException {
        XmlHelper xml = new XmlHelper();
        if (rootName == null || rootName.length() == 0) {
            rootName = XmlProperty.NODE_KEY_DEFAULT;
        }

        //�������ڵ�
        if (map == null || map.size() == 0) {
            xml.createTextNode(rootName, XmlProperty.NODE_VALUE_DEFAULT);
            return xml.toString();
        } else {
            xml.createNode(rootName);
        }


        return map2Xml(xml, map, rootName);
    }

    /**
     * Mapת��Xml
     * @param map
     * @return
     * @throws XmlException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String map2Xml(Map<String, ?> map) throws XmlException {
        if (map == null || map.size() == 0) {
            throw new XmlException("Map can't be null");
        }

        if (map.size() != 1) {
            throw new XmlException("Map's size should be 1.");
        }

        String key = map.keySet().iterator().next();
        Map value = (Map) map.get(key);
        return map2Xml(value, key);
    }

    @SuppressWarnings("unchecked")
    private static String map2Xml(XmlHelper xml, Map<String, ?> map, String parentName) throws XmlException {
        for (String key : map.keySet()) {
            Object value = map.get(key);

            if (value instanceof Map) {
                xml.insert(parentName, new Node(key));
                map2Xml(xml, (Map<String, Object>) value, key);
            } else {
                xml.insert(parentName, new TextNode(key, value.toString()));
            }
        }

        return xml.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?myxml version='1.0' encoding='")
                .append(BaseNode.property.encode).append("'?>");
        if (BaseNode.property.isFormat) {
            sb.append("\n");
        }
        sb.append(root.toString());
        return sb.toString();
    }

    public boolean parse(String file) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String get(String key) {
        NodeList nodeList = document.getChildNodes();
        for (int i = 0, length = nodeList.getLength(); i < length; i++) {
        	String value = get(key, nodeList.item(i));
			if (value != null) {
				return value;
			}
        }
		return null;
    }

    private String get(String key, org.w3c.dom.Node node) {
        if (key.equals(node.getNodeName())) {
			return node.getTextContent();
		}

		if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
			NodeList nodeList = node.getChildNodes();
			for (int i = 0, length = nodeList.getLength(); i < length; i++) {
				org.w3c.dom.Node childNode = nodeList.item(i);
                String value = get(key, childNode);
                if (value != null) {
                    return value;
                }
            }
        }
		return null;
    }

    public static void main(String[] args) throws XmlException {
//        XmlHelper myxml = new XmlHelper();
//        myxml.insert(XmlProperty.NODE_KEY_DEFAULT, new Node("key1"));
//        myxml.insert(XmlProperty.NODE_KEY_DEFAULT, new Node("key2"));
//        myxml.insert(XmlProperty.NODE_KEY_DEFAULT, new Node("key3"));
//        myxml.insert("key2", new TextNode("k1", "v1"));
//        myxml.insert("key2", new TextNode("k2", "v2"));
//        myxml.insert("key2", new TextNode("k3", "v3"));
//        myxml.insert("key3", new TextNode("k-1", "v-1"));
//        myxml.insert("key3", new TextNode("k-2", "v-2"));
//        System.out.println(myxml.toString());
//
//        Map<String, String> subMap = new LinkedHashMap<String, String>();
//        subMap.put("k1", "v1");
//        subMap.put("k2", "v2");
//        subMap.put("k3", "v3");
//        Map<String, Object> map = new LinkedHashMap<String, Object>();
//        map.put("key1", "value1");
//        map.put("key2", "value2");
//        map.put("key3", "value3");
//        map.put("key4", subMap);
//        System.out.println(XmlHelper.map2Xml(map, XmlProperty.NODE_KEY_DEFAULT));

//        Map<String, String> subMap1 = new LinkedHashMap<String, String>();
//        subMap1.put("key1", "value1");
//        subMap1.put("key2", "value2");
//        Map<String, Object> subMap2 = new LinkedHashMap<String, Object>();
//        subMap2.put("k1", "v1");
//        subMap2.put("k2", "v2");
//        subMap2.put("k3", subMap1);
//        Map<String, Object> map = new LinkedHashMap<String, Object>();
//        map.put("root", subMap2);
//        System.out.println(XmlHelper.map2Xml(map));

    }
}
