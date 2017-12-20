package storm_falcon.util.myxml;

import java.util.Map;

/**
 * Created by Storm_Falcon on 2015/10/30.
 * Super class of node
 */
abstract class BaseNode {

    protected static XmlProperty property;

    /**
     * 节点名，key
     */
    protected String name;

    /**
     * 层次，用于格式化等
     */
    protected int mLevel;

    /**
     * 属性列表
     */
    protected Map<String, String> attrMap = null;

    public void setProperty(XmlProperty property) { BaseNode.property = property; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回节点值
     * @return
     */
    public abstract Object getValue();

    /**
     * 返回节点值数量
     * @return
     */
    public abstract int getValueSize();

    public abstract void setValue(Object value);

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        this.mLevel = level;
    }

    public Map<String, String> getAttribute() {
        return attrMap;
    }

    public void setAttribute(Map<String, String> attrMap) {
        this.attrMap = attrMap;
    }

    /**
     * 将属性列表转成string
     * @return
     */
    protected String getAttributeString() {
        StringBuilder sb = new StringBuilder();
        if (attrMap != null && attrMap.size() != 0) {
            for (String attrKey : attrMap.keySet()) {
                String attrValue = attrMap.get(attrKey);
                sb.append(" ").append(attrKey).append("=\"").append(attrValue).append("\"");
            }
        }
        return sb.toString();
    }

    /**
     * 生成开头制表符
     * @param isStart true:开始标签；false:结束标签
     * @return
     */
    protected String getTableString(boolean isStart) {
        if (!property.isFormat) {
            return "";
        }
        if (!isStart && getValueSize() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mLevel; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }
}
