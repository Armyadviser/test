package storm_falcon.util.myxml;

/**
 * Created by Storm_Falcon on 2015/10/30.
 * A series of myxml attribute.
 */
public class XmlProperty {

    public static final boolean FORMAT_ON = true;
    public static final boolean FORMAT_OFF = false;
    public boolean isFormat = FORMAT_ON;

    public static final String ENCODE_GBK = "GBK";
    public static final String ENCODE_UTF8 = "UTF-8";
    public String encode = ENCODE_GBK;

    public static final String NODE_KEY_DEFAULT = "DefaultKey";

    public static final String NODE_VALUE_DEFAULT = "DefaultValue";
}
