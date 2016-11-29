package storm_falcon.swing.colorconvert;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by Storm_Falcon on 2016/4/28.
 *
 */
public class ColorConverter extends JFrame {

    private final JTextField mRValue = new JTextField(3);
    private final JTextField mGValue = new JTextField(3);
    private final JTextField mBValue = new JTextField(3);

    private final JTextField mRGB = new JTextField(7);

    private final JButton mOct2Hex = new JButton("Convert");
    private final JButton mHex2Oct = new JButton("Convert");

    private final JPanel mHexShow = new JPanel();
    private final JPanel mOctShow = new JPanel();

    private final JTextField mHexRes = new JTextField();
    private final JTextField mOctRes = new JTextField();

    public ColorConverter() {
        super("Color Converter");
        initFrame();
        setListener();

    }

    private void setListener() {
        mOct2Hex.addActionListener(e -> {
            int r = Integer.parseInt(mRValue.getText());
            int g = Integer.parseInt(mGValue.getText());
            int b = Integer.parseInt(mBValue.getText());
            String value = convert(r, g, b);
            mHexRes.setText(value);
            mHexShow.setBackground(new Color(r, g, b));
        });
        mHex2Oct.addActionListener(e -> {
            String rgb = mRGB.getText();
            int[] value = convert(rgb);
            mOctRes.setText(Arrays.toString(value));
            mOctShow.setBackground(new Color(value[0], value[1], value[2]));
        });
    }

    private String convert(int r, int g, int b) {
        return "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }

    private int[] convert(String rgb) {
        if (rgb.startsWith("#")) {
            rgb = rgb.substring(1);
        }
        int r = Integer.parseInt(rgb.substring(0, 2), 16);
        int g = Integer.parseInt(rgb.substring(2, 4), 16);
        int b = Integer.parseInt(rgb.substring(4, 6), 16);
        return new int[]{r, g, b};
    }

    private void initFrame() {
        GridLayout gl = new GridLayout(2, 4);
        gl.setHgap(10);
        gl.setVgap(10);
        setLayout(gl);
        JPanel textPanel = new JPanel();
        textPanel.add(mRValue);
        textPanel.add(mGValue);
        textPanel.add(mBValue);
        mHexShow.setSize(50, 30);
        add(textPanel);
        add(mOct2Hex);
        add(mHexShow);
        mHexRes.setEditable(false);
        add(mHexRes);

        Font font = mRGB.getFont();
        add(mRGB);
        add(mHex2Oct);
        add(mOctShow);
        mOctRes.setEditable(false);
        mOctRes.setFont(font);
        add(mOctRes);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(600, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ColorConverter();
    }
}
