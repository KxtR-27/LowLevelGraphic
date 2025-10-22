package fractal;

import javax.swing.*;
import java.awt.*;

public class FractalApp extends JFrame {
    public static void main(String[] args) {
        new FractalApp();
    }

    public FractalApp() {
        this.setLayout(new BorderLayout());
        this.add(new FractalCanvas(), BorderLayout.CENTER);

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