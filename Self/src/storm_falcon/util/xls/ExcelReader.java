package storm_falcon.util.xls;

import jxl.Sheet;
import jxl.Workbook;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Storm_Falcon on 2016/12/5.
 *
 */
public class ExcelReader implements Closeable {

    private Workbook workbook;

    private Sheet sheet;

    private int rowNumber = 0;

    public void open(String filePath) {
        open(filePath, 0);
    }

    public void open(String filePath, int sheetIndex) {
        try {
            workbook = Workbook.getWorkbook(new FileInputStream(filePath));
            sheet = workbook.getSheet(sheetIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open(String filePath, String sheetName) {
        try {
            workbook = Workbook.getWorkbook(new FileInputStream(filePath));
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasNext() {
        return ++rowNumber < sheet.getRows();
    }

    public String getContent(int columnIndex) {
        try {
            return sheet.getCell(columnIndex, rowNumber - 1).getContents();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public <T> T getObject(int[] columnIndexes, CellMapper<T> mapper) {
        String[] items = new String[columnIndexes.length];
        for (int i = 0; i < columnIndexes.length; i++) {
            items[i] = getContent(columnIndexes[i]);
        }
        return mapper.convert(items);
    }

    private static int[] convertIndexes(String[] sIndexes) {
        return Arrays.stream(sIndexes)
            .map(String::toUpperCase)
            .flatMapToInt(s -> {
                char[] chars = s.toCharArray();
                int result = 0;
                for (char aChar : chars) {
                    result += (int) aChar - 65;
                }
                return IntStream.of(result);
            }).toArray();
    }

    public static <T> Stream<T> stream(String filePath, int sheet,
                                       int rowStart, int rowEnd, String[] columnIndexes, CellMapper<T> mapper) {
        int[] nColumnIndexes = convertIndexes(columnIndexes);

        ExcelReader reader = new ExcelReader();
        reader.open(filePath, sheet);
        for (int i = 1; reader.hasNext() && i < rowStart; i++);

        Iterator<T> iterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return reader.hasNext() && reader.rowNumber <= rowEnd + 1;
            }

            @Override
            public T next() {
                return reader.getObject(nColumnIndexes, mapper);
            }
        };

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
            iterator, Spliterator.ORDERED | Spliterator.NONNULL),
            false).onClose(asUncheckedRunnable(reader));
    }

    public static <T> Stream<T> stream(String filePath, String[] columnIndexes,
                                       int rowStart, int rowEnd, CellMapper<T> mapper) {
        return stream(filePath, 0, rowStart, rowEnd, columnIndexes, mapper);
    }

    private static Runnable asUncheckedRunnable(Closeable c) {
        return () -> {
            try {
                c.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    public void close() {
        if (workbook == null) {
            return;
        }
        workbook.close();
        workbook = null;
    }
}
