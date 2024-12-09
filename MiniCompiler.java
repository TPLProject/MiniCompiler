import javax.swing.*;
import java.awt.*;

public class MiniCompiler extends JFrame {
    private CodeTextArea codeTextArea;
    private ResultTextArea resultTextArea;
    private ButtonPanel buttonPanel;

    public MiniCompiler() {
        setTitle("Mini Compiler");
        setSize(650, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 235)); // Light cream background

        codeTextArea = new CodeTextArea();
        codeTextArea.addToFrame(this);

        resultTextArea = new ResultTextArea();
        resultTextArea.addToFrame(this);

        buttonPanel = new ButtonPanel(this, codeTextArea, resultTextArea);
        buttonPanel.addToFrame(this);
    }

    public void enableButton(String buttonName) {
        buttonPanel.enableButton(buttonName);
    }

    public void disableButton(String buttonName) {
        buttonPanel.disableButton(buttonName);
    }
}