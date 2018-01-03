package storm_falcon.util.file.encode;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * @author gewp
 */
public class UnicodeInputStream extends InputStream implements Closeable {

    private PushbackInputStream inputStream;
    boolean isInitialized = false;
    private String defaultEncoding;
    private String encoding;

    private static final int BOM_SIZE = 4;

    public UnicodeInputStream(InputStream in, String defaultEncoding) {
        inputStream = new PushbackInputStream(in, BOM_SIZE);
        this.defaultEncoding = defaultEncoding;
    }

    public String getEncoding() {
        if (!isInitialized) {
            try {
                init();
            } catch (IOException e) {
                IllegalStateException ex = new IllegalStateException("Init failed");
                e.initCause(ex);
                throw ex;
            }
        }
        return encoding;
    }

    protected void init() throws IOException {
        if (isInitialized) {
            return;
        }

        byte[] bom = new byte[BOM_SIZE];
        int n, unread;
        n = inputStream.read(bom, 0, bom.length);

        if ((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00)
                && (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF)) {
            encoding = "UTF-32BE";
            unread = n - 4;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)
                && (bom[2] == (byte) 0x00) && (bom[3] == (byte) 0x00)) {
            encoding = "UTF-32LE";
            unread = n - 4;
        } else if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB)
                && (bom[2] == (byte) 0xBF)) {
            encoding = "UTF-8";
            unread = n - 3;
        } else if ((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF)) {
            encoding = "UTF-16BE";
            unread = n - 2;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)) {
            encoding = "UTF-16LE";
            unread = n - 2;
        } else {
            // Unicode BOM mark not found, unread all bytes
            encoding = defaultEncoding;
            unread = n;
        }
        // System.out.println("read=" + n + ", unread=" + unread);

        if (unread > 0)
            inputStream.unread(bom, (n - unread), unread);

        isInitialized = true;
    }

    public void close() {
        isInitialized = true;
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int read() throws IOException {
        isInitialized = true;
        return inputStream.read();
    }
}
