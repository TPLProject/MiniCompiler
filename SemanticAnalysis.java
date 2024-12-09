import java.util.HashMap;
import java.util.Map;

public class SemanticAnalysis {
    private static Map<String, String> symbolTable = new HashMap<>();

    public static void perform(CodeTextArea codeTextArea, ResultTextArea resultTextArea, ButtonPanel buttonPanel) {
        String code = codeTextArea.getText();
        if (code.isEmpty()) {
            resultTextArea.setText("Semantic Analysis: Failed (No code provided)");
            return;
        }

        String[] lines = code.split("\\n");
        symbolTable.clear();
        boolean semanticValid = true;
        StringBuilder result = new StringBuilder("Semantic Analysis:\n");

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
        buttonPanel.disableButton("semantic");
    }

    private static boolean isTypeConsistent(String type, String value) {
        switch (type) {
            case "int":
                return value.matches("\\d+");
            case "String":
                return value.matches("\"[^\"]*\"");
            case "float":
            case "double":
                return value.matches("\\d+\\.\\d+");
            case "char":
                return value.matches("'.'");
            default:
                return false;
        }
    }
}