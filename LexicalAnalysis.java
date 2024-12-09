public class LexicalAnalysis {
    public static void perform(CodeTextArea codeTextArea, ResultTextArea resultTextArea, ButtonPanel buttonPanel) {
        String code = codeTextArea.getText();
        if (code.isEmpty()) {
            resultTextArea.setText("Lexical Analysis: Failed (No code provided)");
            return;
        }

        // Define valid patterns for tokens
        String keywordPattern = "\\b(int|String|float|char|double)\\b";
        String identifierPattern = "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b";
        String numberPattern = "\\b\\d+\\b";
        String stringLiteralPattern = "\"[^\"]*\"";
        String symbolPattern = "[=;]";
        String validTokenPattern = keywordPattern + "|" + identifierPattern + "|" +
                numberPattern + "|" + stringLiteralPattern + "|" + symbolPattern;

        // Tokenize the code
        String[] tokens = code.split("(?<=[=;])|(?=[=;])|\\s+");
        StringBuilder result = new StringBuilder("Tokens:\n");
        boolean allTokensValid = true;

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty())
                continue;
            if (token.matches(validTokenPattern)) {
                result.append(token).append(" (Valid)\n");
            } else {
                result.append(token).append(" (Invalid)\n");
                allTokensValid = false;
            }
        }

        if (allTokensValid) {
            result.insert(0, "Lexical Analysis: Passed\n");
            buttonPanel.disableButton("lexical");
            buttonPanel.enableButton("syntax");
        } else {
            result.insert(0, "Lexical Analysis: Failed (Invalid tokens found)\n");
        }

        resultTextArea.setText(result.toString());
    }
}