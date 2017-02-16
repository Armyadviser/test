package storm_falcon.util.myxml;

import org.junit.Test;

/**
 * Created by falcon on 17-2-16.
 *
 */
public class XmlHelperTest {
    @Test
    public void main() throws Exception {
        XmlHelper myxml = new XmlHelper();
        myxml.insert(XmlProperty.NODE_KEY_DEFAULT, new Node("key1"));
        myxml.insert(XmlProperty.NODE_KEY_DEFAULT, new Node("key2"));
        myxml.insert(XmlProperty.NODE_KEY_DEFAULT, new Node("key3"));
        myxml.insert("key2", new TextNode("k1", "v1"));
        myxml.insert("key2", new TextNode("k2", "v2"));
        myxml.insert("key2", new TextNode("k3", "v3"));
        myxml.insert("key3", new TextNode("k-1", "v-1"));
        myxml.insert("key3", new TextNode("k-2", "v-2"));
        System.out.println(myxml.toString());

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