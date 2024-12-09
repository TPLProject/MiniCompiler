# Mini Compiler
Mini Compiler is a simple, educational Java-based compiler that performs lexical, syntax, and semantic analysis on a given code input. The tool is designed to help students and learners understand the basic processes of compiling code and how compilers parse and validate programming languages.

# Feautures
**Lexical Analysis**: Breaks down the source code into tokens and validates if the tokens match expected patterns.

**Syntax Analysis**: Ensures that the code structure adheres to basic Java syntax rules, such as the presence of semicolons at the end of statements.

**Semantic Analysis**: Checks for basic semantic correctness like variable declarations and type consistency

# Language Used
Java

# How It Works
**1. Lexical Analysis**: This step uses regular expressions to tokenize the input. It identifies keywords, variables, numbers, strings, and symbols.

**2. Syntax Analysis**: The syntax analyzer checks if the code follows basic Java rules like ending statements with semicolons.

**3. Semantic Analysis**: The semantic analyzer ensures that variables are declared with types and that assignments and operations are type-consistent.

# Limitations
This is a simple prototype designed for educational purposes. It does not support all Java syntax or advanced features like method calls, loops, or complex data structures.

The syntax analysis only checks for the presence of semicolons and does not cover more complex syntax rules.

The semantic analyzer only checks for variable declarations and type consistency for simple data types.

