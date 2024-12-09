import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ButtonPanel {
    private JButton openFileButton, clearButton, lexicalButton, syntaxButton, semanticButton;

    public ButtonPanel(MiniCompiler compiler, CodeTextArea codeTextArea, ResultTextArea resultTextArea) {
        openFileButton = createStyledButton("Open File");
        openFileButton.setBounds(40, 260, 120, 40);
        openFileButton.addActionListener(e -> OpenFile.open(codeTextArea, resultTextArea, this));

        clearButton = createStyledButton("Clear");
        clearButton.setBounds(180, 260, 120, 40);
        clearButton.setEnabled(false);
        clearButton.addActionListener(e -> {
            codeTextArea.clear();
            resultTextArea.clear();
            disableAllButtons();
        });

        lexicalButton = createStyledButton("Lexical Analysis");
        lexicalButton.setBounds(380, 40, 200, 40);
        lexicalButton.setEnabled(false);
        lexicalButton.addActionListener(e -> LexicalAnalysis.perform(codeTextArea, resultTextArea, this));

        syntaxButton = createStyledButton("Syntax Analysis");
        syntaxButton.setBounds(380, 100, 200, 40);
        syntaxButton.setEnabled(false);
        syntaxButton.addActionListener(e -> SyntaxAnalysis.perform(codeTextArea, resultTextArea, this));

        semanticButton = createStyledButton("Semantic Analysis");
        semanticButton.setBounds(380, 160, 200, 40);
        semanticButton.setEnabled(false);
        semanticButton.addActionListener(e -> SemanticAnalysis.perform(codeTextArea, resultTextArea, this));
    }

    public void addToFrame(JFrame frame) {
        frame.add(openFileButton);
        frame.add(clearButton);
        frame.add(lexicalButton);
        frame.add(syntaxButton);
        frame.add(semanticButton);
    }

    public void enableButton(String buttonName) {
        switch (buttonName) {
            case "clear":
                clearButton.setEnabled(true);
                break;
            case "lexical":
                lexicalButton.setEnabled(true);
                break;
            case "syntax":
                syntaxButton.setEnabled(true);
                break;
            case "semantic":
                semanticButton.setEnabled(true);
                break;
        }
    }

    public void disableButton(String buttonName) {
        switch (buttonName) {
            case "lexical":
                lexicalButton.setEnabled(false);
                break;
            case "syntax":
                syntaxButton.setEnabled(false);
                break;
            case "semantic":
                semanticButton.setEnabled(false);
                break;
        }
    }

    private void disableAllButtons() {
        lexicalButton.setEnabled(false);
        syntaxButton.setEnabled(false);
        semanticButton.setEnabled(false);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(255, 224, 200));
        button.setForeground(new Color(128, 96, 85));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 130), 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);
        return button;
    }
}
