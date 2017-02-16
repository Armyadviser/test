package storm_falcon.util.xls;

import jxl.Sheet;
import jxl.Workbook;

import java.io.Closeable;
import java.io.FileInputStream;

/**
 * Created by Storm_Falcon on 2016/12/5.
 *
 */
public class ExcelReader implements Closeable {

    private Workbook workbook;

    private Sheet sheet;

    private int lineNumber = 0;

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

    public boolean hasNext() {
        return ++lineNumber < sheet.getRows();
    }

    public String getContent(int columnIndex) {
        try {
            return sheet.getCell(columnIndex, lineNumber - 1).getContents();
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

    public void close() {
        if (workbook == null) {
            return;
        }
        workbook.close();
        workbook = null;
    }

}
