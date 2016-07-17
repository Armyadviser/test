package storm_falcon.util.myxml;

/**
 * Created by Storm_Falcon on 2015/10/30.
 * Child node
 */
public class TextNode extends BaseNode {

    private String value;

    public TextNode(String name, String value) {
        super.name = name;
        this.value = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (property.isFormat) {
            for (int i = 0; i < mLevel; i++) {
                sb.append("\t");
            }
        }

        sb.append("<").append(name)
                .append(getAttributeString()).append(">")
                .append(value)
                .append("</").append(name).append(">");

        if (property.isFormat) {
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int getValueSize() {
        return 1;
    }

    public void setValue(Object value) {
        this.value = (String) value;
    }
}
