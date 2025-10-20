package input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InputCanvas extends JPanel {
    private Color bgColor;

    public InputCanvas() {
        bgColor = Color.WHITE;

        InputMap inputMap = this.getInputMap();
        ActionMap actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("R"), "red");
        actionMap.put("red", new ColorAction(Color.RED));

        inputMap.put(KeyStroke.getKeyStroke("A"), "orange");
        actionMap.put("orange", new ColorAction(Color.ORANGE));

        inputMap.put(KeyStroke.getKeyStroke("I"), "yellow");
        actionMap.put("yellow", new ColorAction(Color.YELLOW));

        inputMap.put(KeyStroke.getKeyStroke("N"), "green");
        actionMap.put("green", new ColorAction(Color.GREEN));

        inputMap.put(KeyStroke.getKeyStroke("B"), "cyan");
        actionMap.put("cyan", new ColorAction(Color.CYAN));

        inputMap.put(KeyStroke.getKeyStroke("O"), "blue");
        actionMap.put("blue", new ColorAction(Color.BLUE));

        inputMap.put(KeyStroke.getKeyStroke("W"), "pink");
        actionMap.put("pink", new ColorAction(Color.PINK));
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Dimension windowSize = getBounds().getSize();
        g2.setColor(bgColor);
        g2.fillRect(0, 0, windowSize.width, windowSize.height);

        repaint();
    }


    class ColorAction extends AbstractAction {
        private final Color color;

        public ColorAction(Color color) {
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent ignored) {
            bgColor = color;
        }
    }
}