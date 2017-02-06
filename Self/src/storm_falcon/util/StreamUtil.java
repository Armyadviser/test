package storm_falcon.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by falcon on 17-1-27.
 *
 */
public class StreamUtil {
    public static <E> Stream<E> stream(Enumeration<E> enumeration) {
        Iterator<E> iterator = new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            @Override
            public E next() {
                return enumeration.nextElement();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator, Spliterator.NONNULL), false);
    }

    public static Stream<Byte> stream(final byte[] data) {
        Iterator<Byte> iterator = new Iterator<Byte>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < data.length;
            }

            @Override
            public Byte next() {
                return data[index++];
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator, Spliterator.NONNULL), false);
    }
}
