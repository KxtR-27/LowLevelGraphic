package input;

import javax.swing.*;
import java.awt.*;

public class InputApp extends JFrame {
    private static final InputCanvas inputCanvas = new InputCanvas();

    public static void main(String[] args) {
        new InputApp(inputCanvas);
    }

    public InputApp(InputCanvas canvas) {
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);

        this.addKeyListener(canvas);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(720, 480);
        this.centerOnScreen();
        this.setVisible(true);
    }

    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension mySize = this.getSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;
        int adjX = centerX - mySize.width / 2;
        int adjY = centerY - mySize.height / 2;

        this.setLocation(adjX, adjY);
    }
}
