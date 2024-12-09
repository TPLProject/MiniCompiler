import javax.swing.*;
import java.awt.*;

public class CodeTextArea {
    private JTextArea textArea;

    public CodeTextArea() {
        textArea = new JTextArea();
        textArea.setBackground(new Color(255, 248, 240));
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 130), 2, true));
    }

    public void addToFrame(JFrame frame) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(40, 40, 300, 200);
        frame.add(scrollPane);
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void clear() {
        textArea.setText("");
    }
}