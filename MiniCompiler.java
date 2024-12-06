import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MiniCompiler extends JFrame {
    private JTextArea codeTextArea, resultTextArea;
    private JButton openFileButton, clearButton, lexicalButton, syntaxButton, semanticButton;
    private boolean isLexicalDone = false, isSyntaxDone = false;
    private Map<String, String> symbolTable = new HashMap<>(); // Symbol table to store variable types

    public MiniCompiler() {
        setTitle("Java Mini Compiler");
        setSize(650, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false); // Disable window resizing

        // Background color
        getContentPane().setBackground(new Color(245, 245, 235)); // Light cream background

        // Code Text Area
        JLabel codeLabel = new JLabel("");
        codeLabel.setBounds(40, 10, 150, 20);
        codeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        codeLabel.setForeground(new Color(128, 96, 85));
        add(codeLabel);

        codeTextArea = new JTextArea();
        codeTextArea.setBackground(new Color(255, 248, 240));
        codeTextArea.setForeground(Color.BLACK);
        codeTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        codeTextArea.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 130), 2, true));
        JScrollPane codeScrollPane = new JScrollPane(codeTextArea);
        codeScrollPane.setBounds(40, 40, 300, 200);
        add(codeScrollPane);

        // Buttons
        openFileButton = createStyledButton("Open File");
        openFileButton.setBounds(40, 260, 120, 40);
        openFileButton.addActionListener(e -> openFile());
        add(openFileButton);

        clearButton = createStyledButton("Clear");
        clearButton.setBounds(180, 260, 120, 40);
        clearButton.addActionListener(e -> clearText());
        clearButton.setEnabled(false);
        add(clearButton);

        lexicalButton = createStyledButton("Lexical Analysis");
        lexicalButton.setBounds(380, 40, 200, 40);
        lexicalButton.addActionListener(e -> performLexicalAnalysis());
        lexicalButton.setEnabled(false);
        add(lexicalButton);

        syntaxButton = createStyledButton("Syntax Analysis");
        syntaxButton.setBounds(380, 100, 200, 40);
        syntaxButton.addActionListener(e -> performSyntaxAnalysis());
        syntaxButton.setEnabled(false);
        add(syntaxButton);

        semanticButton = createStyledButton("Semantic Analysis");
        semanticButton.setBounds(380, 160, 200, 40);
        semanticButton.addActionListener(e -> performSemanticAnalysis());
        semanticButton.setEnabled(false);
        add(semanticButton);

        // Result Text Area
        JLabel resultLabel = new JLabel("Result Text Area:");
        resultLabel.setBounds(40, 320, 150, 20);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 12));
        resultLabel.setForeground(new Color(128, 96, 85));
        add(resultLabel);

        resultTextArea = new JTextArea();
        resultTextArea.setBackground(new Color(255, 248, 240));
        resultTextArea.setForeground(Color.BLACK);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTextArea.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 130), 2, true));
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
        resultScrollPane.setBounds(40, 350, 540, 80);
        add(resultScrollPane);
    }

    // Create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(255, 224, 200)); // Light peach background
        button.setForeground(new Color(128, 96, 85)); // Brown text
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 130), 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);

        // Add rounded corners and shadow effect
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                c.setOpaque(false);
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = c.getWidth();
                int height = c.getHeight();

                // Shadow
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRoundRect(3, 3, width - 6, height - 6, 20, 20);

                // Button fill
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, width, height, 20, 20);

                // Border
                g2.setColor(((JButton) c).getForeground());
                g2.drawRoundRect(0, 0, width - 1, height - 1, 20, 20);

                g2.dispose();

                super.paint(g, c);
            }
        });
        return button;
    }

    // Open File
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                codeTextArea.read(reader, null);
                lexicalButton.setEnabled(true);
                clearButton.setEnabled(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
            }
        }
    }

    // Clear Text Areas
    private void clearText() {
        codeTextArea.setText("");
        resultTextArea.setText("");
        lexicalButton.setEnabled(false);
        syntaxButton.setEnabled(false);
        semanticButton.setEnabled(false);
        clearButton.setEnabled(false);
        isLexicalDone = false;
        isSyntaxDone = false;
        symbolTable.clear(); // Clear the symbol table
    }

    // Lexical Analysis
    private void performLexicalAnalysis() {
        String code = codeTextArea.getText();
        if (code.isEmpty()) {
            resultTextArea.setText("Lexical Analysis: Failed (No code provided)");
            return;
        }

        // Define valid patterns for tokens
        String keywordPattern = "\\b(int|String|float|char|double)\\b"; // Valid keywords
        String identifierPattern = "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b"; // Variable names
        String numberPattern = "\\b\\d+\\b"; // Numbers
        String stringLiteralPattern = "\"[^\"]*\""; // String literals
        String symbolPattern = "[=;]"; // Symbols = and ;
        String validTokenPattern = keywordPattern + "|" + identifierPattern + "|" +
                numberPattern + "|" + stringLiteralPattern + "|" + symbolPattern;

        // Tokenize the code using a regex pattern
        String[] tokens = code.split("(?<=[=;])|(?=[=;])|\\s+"); // Split by spaces and symbols

        StringBuilder result = new StringBuilder("Lexical Analysis: Tokens:\n");
        boolean allTokensValid = true;

        for (String token : tokens) {
            token = token.trim(); // Remove leading/trailing spaces
            if (token.isEmpty())
                continue; // Skip empty tokens
            if (token.matches(validTokenPattern)) {
                result.append(token).append(" (Valid)\n");
            } else {
                result.append(token).append(" (Invalid)\n");
                allTokensValid = false;
            }
        }

        if (allTokensValid) {
            result.insert(0, "Lexical Analysis: Passed\n");
            lexicalButton.setEnabled(false);
            syntaxButton.setEnabled(true);
            isLexicalDone = true;
        } else {
            result.insert(0, "Lexical Analysis: Failed (Invalid tokens found)\n");
        }

        resultTextArea.setText(result.toString());
    }

    // Syntax Analysis
    private void performSyntaxAnalysis() {
        if (!isLexicalDone) {
            JOptionPane.showMessageDialog(this, "Perform lexical analysis first!");
            return;
        }
        String code = codeTextArea.getText();
        if (code.contains(";")) {
            resultTextArea.setText("Syntax Analysis: Passed");
            syntaxButton.setEnabled(false);
            semanticButton.setEnabled(true);
            isSyntaxDone = true;
        } else {
            resultTextArea.setText("Syntax Analysis: Failed (Syntax error: Missing semicolons)");
        }
    }

    private void performSemanticAnalysis() {
        if (!isSyntaxDone) {
            JOptionPane.showMessageDialog(this, "Perform syntax analysis first!");
            return;
        }

        String code = codeTextArea.getText();
        String[] lines = code.split("\\n");
        symbolTable.clear();
        boolean semanticValid = true;
        StringBuilder result = new StringBuilder("Semantic Analysis: ");

        for (String line : lines) {
            line = line.trim();
            if (line.contains("=")) {
                String[] parts = line.split("=");
                String lhs = parts[0].trim();
                String rhs = parts[1].trim().replace(";", "");

                if (symbolTable.containsKey(lhs)) {
                    // Check type consistency for assignments
                    String type = symbolTable.get(lhs);
                    if (!isTypeConsistent(type, rhs)) {
                        result.append("Type mismatch error: ").append(lhs).append(" cannot be assigned to ").append(rhs)
                                .append("\n");
                        semanticValid = false;
                    }
                } else {
                    // Parse variable declaration and add to symbol table
                    String[] declarationParts = lhs.split("\\s+");
                    if (declarationParts.length == 2) {
                        String type = declarationParts[0].trim();
                        String varName = declarationParts[1].trim();
                        symbolTable.put(varName, type);

                        // Check type consistency for initialization
                        if (!isTypeConsistent(type, rhs)) {
                            result.append("Type mismatch error: ").append(varName).append(" cannot be initialized to ")
                                    .append(rhs).append("\n");
                            semanticValid = false;
                        }
                    }
                }
            }
        }

        if (semanticValid) {
            result.append("Passed\n");
        } else {
            result.insert(0, "Failed\n");
        }

        resultTextArea.setText(result.toString());
        semanticButton.setEnabled(false);
    }

    private boolean isTypeConsistent(String type, String value) {
        switch (type) {
            case "int":
                return value.matches("\\d+");
            case "String":
                return value.matches("\"[^\"]*\"");
            case "float":
                return value.matches("\\d+\\.\\d+");
            case "char":
                return value.matches("'.'");
            case "double":
                return value.matches("\\d+\\.\\d+");
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MiniCompiler().setVisible(true));
    }
}