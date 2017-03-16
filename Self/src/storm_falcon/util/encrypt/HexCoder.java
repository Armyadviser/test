package storm_falcon.util.encrypt;

/**
 * @author gewp
 */
public class HexCoder {
    private static final char[] DIGITS_LOWER;
    private static final String CHARSET_NAME = "UTF-8";

    static {
        DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    private static byte[] decodeHex(final char[] data) throws RuntimeException {
        final int len = data.length;
        if ((len & 0x1) != 0x0) {
            throw new RuntimeException("Odd number of characters.");
        }
        final byte[] out = new byte[len >> 1];
        int f;
        for (int i = 0, j = 0; j < len; ++j, f |= toDigit(data[j], j), ++j, out[i] = (byte) (f & 0xFF), ++i) {
            f = toDigit(data[j], j) << 4;
        }
        return out;
    }

    private static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        int i = 0;
        int j = 0;
        while (i < l) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0xF & data[i]];
            ++i;
        }
        return out;
    }

    private static int toDigit(final char ch, final int index) throws RuntimeException {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public static String decode(final String string) throws RuntimeException {
        try {
            final char[] charArray = string.toCharArray();
            return new String(decodeHex(charArray), CHARSET_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String encode(final String string) throws RuntimeException {
        try {
            final byte[] byteArray = string.getBytes(CHARSET_NAME);
            return new String(encodeHex(byteArray, DIGITS_LOWER));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
