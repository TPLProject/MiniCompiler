import javax.swing.*;
import java.awt.*;

public class ResultTextArea {
    private JTextArea textArea;

    public ResultTextArea() {
        textArea = new JTextArea();
        textArea.setBackground(new Color(255, 248, 240));
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 130), 2, true));
    }

    public void addToFrame(JFrame frame) {
        JLabel label = new JLabel("Result Text Area:");
        label.setBounds(40, 320, 150, 20);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(128, 96, 85));
        frame.add(label);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(40, 350, 540, 80);
        frame.add(scrollPane);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void clear() {
        textArea.setText("");
    }
}