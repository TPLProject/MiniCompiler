public class SyntaxAnalysis {
    public static void perform(CodeTextArea codeTextArea, ResultTextArea resultTextArea, ButtonPanel buttonPanel) {
        String code = codeTextArea.getText();
        if (code.isEmpty()) {
            resultTextArea.setText("Syntax Analysis: Failed (No code provided)");
            return;
        }

        // Simple syntax validation: Check for semicolons
        if (code.contains(";")) {
            resultTextArea.setText("Syntax Analysis: Passed");
            buttonPanel.disableButton("syntax");
            buttonPanel.enableButton("semantic");
        } else {
            resultTextArea.setText("Syntax Analysis: Failed (Syntax error: Missing semicolons)");
        }
    }
}