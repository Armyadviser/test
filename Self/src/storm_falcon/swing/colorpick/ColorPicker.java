package storm_falcon.swing.colorpick;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

/**
 * Created by Storm_Falcon on 2016/12/2.
 *
 */
public class ColorPicker extends JFrame {

    private PickColorThread thread;

    public ColorPicker() {
        super("Color Picker - <Storm_Falcon>");

        initFont();
        setLocation(400, 200);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(160, 90));
        JTextField hexField = new JTextField(7);
        JTextField octField = new JTextField(18);

        setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(hexField, BorderLayout.CENTER);
        getContentPane().add(octField, BorderLayout.SOUTH);

        thread = new PickColorThread(octField, hexField, panel);
        thread.start();

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(e -> {
            if (e.getID() != KeyEvent.KEY_PRESSED
                    || e.getKeyCode() != KeyEvent.VK_SPACE)
                return true;

            boolean pause = thread.getPause();
            pause = !pause;
            thread.setPause(pause);
            return false;
        });

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void initFont() {
        Font font = new Font("Courier New", Font.PLAIN, 20);
        FontUIResource resource = new FontUIResource(font);
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, resource);
            }
        }
        UIManager.put("TextField.font", font);
    }

    public static void main(String[] args) {
        new ColorPicker();
    }
}
