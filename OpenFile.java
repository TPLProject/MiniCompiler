import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFile {
    public static void open(CodeTextArea codeTextArea, ResultTextArea resultTextArea, ButtonPanel buttonPanel) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                codeTextArea.setText("");
                codeTextArea.setText(reader.lines().reduce("", (a, b) -> a + "\n" + b));
                buttonPanel.enableButton("lexical");
                buttonPanel.enableButton("clear");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
            }
        }
    }
}