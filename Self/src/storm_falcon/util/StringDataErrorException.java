package storm_falcon.util;

/**
 * Created by Storm_Falcon on 2015/10/10.
 * This will happen when a string type is error.
 */
public class StringDataErrorException extends RuntimeException {
    public StringDataErrorException(String s) {
        super(s);
    }
}
