package input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputApp extends JFrame implements KeyListener {
    private static final InputCanvas inputCanvas = new InputCanvas();

    public static void main(String[] args) {
        new InputApp(inputCanvas);
    }

    public InputApp(InputCanvas canvas) {
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);

        this.addKeyListener(this);

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

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.printf("Key Typed: %c%n", e.getKeyChar());

        inputCanvas.setBgColor(switch (e.getKeyChar()) {
            case 'r' -> Color.RED;
            case 'a' -> Color.ORANGE;
            case 'i' -> Color.YELLOW;
            case 'n' -> Color.GREEN;
            case 'b' -> Color.CYAN;
            case 'o' -> Color.BLUE;
            case 'w' -> Color.MAGENTA;

            default -> Color.BLACK;
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
