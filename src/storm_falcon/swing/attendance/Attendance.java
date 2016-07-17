package storm_falcon.swing.attendance;

import org.apache.poi.hssf.usermodel.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Storm_Falcon on 2015/10/12.
 * 生成一天的考勤内容
 */
public class Attendance extends JFrame {

    private JTextField mYear;
    private JTextField mMonth;
    private JTextField mDay;
    private JTextArea mGozenn;
    private JTextArea mGogo;
    private JButton mGenerate;
    private JTextField mPath;

    private final static int TIME_GOZENN_BEGIN_HOUR = 8;
    private final static int TIME_GOZENN_END_HOUR = 12;
    private final static int TIME_GOGO_BEGIN_HOUR = 13;
    private final static int TIME_GOGO_END_HOUR = 17;
    private final static int TIME_MINUTE_FULL = 0;
    private final static int TIME_MINUTE_HALF = 30;

    public Attendance() {
        super("考勤");
        this.setLayout(new BorderLayout());

        initDate();
        initContent();
        initChoices();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(950, 450);
        this.pack();
        this.setVisible(true);

        setListener();
    }

    private void createExcel(String filePath, String date, String gozennContent, String gogoContent) throws IOException {
        //create workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("gewanpeng");

        //create cell and font style
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        style.setFont(font);

        //create row 0
        HSSFRow row = sheet.createRow(0);

        //日期
        HSSFCell cell = row.createCell(0);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue(date);

        //上午开始时间：时
        cell = row.createCell(1);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style);
        cell.setCellValue(TIME_GOZENN_BEGIN_HOUR);

        //上午开始时间：分
        cell = row.createCell(2);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style);
        cell.setCellValue(TIME_MINUTE_HALF);

        //上午结束时间：时
        cell = row.createCell(3);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style);
        cell.setCellValue(TIME_GOZENN_END_HOUR);

        //上午结束时间：分
        cell = row.createCell(4);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style);
        cell.setCellValue(TIME_MINUTE_FULL);

        //上午内容
        cell = row.createCell(5);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue(gozennContent);

        //create row 1
        row = sheet.createRow(1);

        //日期
        cell = row.createCell(0);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue(date);

        //下午开始时间：时
        cell = row.createCell(1);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style);
        cell.setCellValue(TIME_GOGO_BEGIN_HOUR);

        //下午开始时间：分
        cell = row.createCell(2);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style);
        cell.setCellValue(TIME_MINUTE_FULL);

        //下午结束时间：时
        cell = row.createCell(3);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style);
        cell.setCellValue(TIME_GOGO_END_HOUR);

        //下午结束时间：分
        cell = row.createCell(4);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(style);
        cell.setCellValue(TIME_MINUTE_HALF);

        //下午内容
        cell = row.createCell(5);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(style);
        cell.setCellValue(gogoContent);

        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);
    }

    private void setListener() {
        mGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String filePath = mPath.getText();
                    String date = mYear.getText() + "/" + mMonth.getText() + "/" + mDay.getText();
                    String gozennContent = mGozenn.getText();
                    String gogoContent = mGogo.getText();

                    createExcel(filePath, date, gozennContent, gogoContent);

                    finish();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * 日期
     */
    private void initDate() {
        JLabel l1 = new JLabel("日期：");
        JLabel l2 = new JLabel("年");
        JLabel l3 = new JLabel("月");
        JLabel l4 = new JLabel("日");
        Calendar c = Calendar.getInstance();
        mYear = new JTextField(c.get(Calendar.YEAR) + "");
        mMonth = new JTextField(c.get(Calendar.MONTH) + 1 + "");
        mDay = new JTextField(c.get(Calendar.DATE) + "");
        JPanel p1 = new JPanel();
        p1.add(l1);
        p1.add(mYear); p1.add(l2);
        p1.add(mMonth); p1.add(l3);
        p1.add(mDay); p1.add(l4);
        this.add(p1, BorderLayout.NORTH);
    }

    /**
     * 内容
     */
    private void initContent() {
        JLabel l5 = new JLabel("上午：", JLabel.CENTER);
        JLabel l6 = new JLabel("下午：", JLabel.CENTER);
        mGozenn = new JTextArea(3, 10);
        mGogo = new JTextArea(3, 10);
        JPanel p2 = new JPanel();
        GridLayout g1 = new GridLayout(2, 2);
        g1.setVgap(5);
        p2.setLayout(g1);
        p2.add(l5); p2.add(mGozenn);
        p2.add(l6); p2.add(mGogo);
        this.add(p2, BorderLayout.CENTER);
    }

    /**
     * 生成选项
     */
    private void initChoices() {
        JLabel l7 = new JLabel("路径：");
        mPath = new JTextField("E:/Document/考勤/temp.xls");
        mGenerate = new JButton("生成");
        JPanel p3 = new JPanel();
        p3.add(l7); p3.add(mPath); p3.add(mGenerate);
        this.add(p3, BorderLayout.SOUTH);
    }

    public void finish() {
        this.dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Attendance();
    }
}
