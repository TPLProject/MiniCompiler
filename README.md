# Overview
The MiniCompiler is a desktop application designed to perform lexical, syntax, and semantic analysis on source code files. This tool provides a user-friendly interface for developers to analyze their code and identify potential issues at different stages of the compilation process.

## Features

Open File: Allows users to open a source code file. The contents of the file will be displayed in the Code Text Area. If no file is selected, all buttons are disabled.

Lexical Analysis: Performs lexical analysis on the source code. The result will be displayed in the Result Text Area. This button will be enabled once a file is opened and will be disabled again after displaying results.

Syntax Analysis: Performs syntax analysis on the source code. The result will be displayed in the Result Text Area. This button will be enabled once lexical analysis produces a successful result and will be disabled again after displaying results.

Semantic Analysis: Performs semantic analysis on the source code. The result will be displayed in the Result Text Area. This button will be enabled once syntax analysis produces a successful result and will be disabled again after displaying results.

Clear: Clears both the Result Text Area and Code Text Area, effectively resetting the application.


## User Interface

Open File Button: Opens a file containing the source code.

Lexical Analysis Button: Initiates lexical analysis on the opened source code.

Syntax Analysis Button: Initiates syntax analysis on the source code after successful lexical analysis.

Semantic Analysis Button: Initiates semantic analysis on the source code after successful syntax analysis.

Clear Button: Resets the application by clearing both the Result Text Area and Code Text Area.

Result Text Area: Displays the results of the analysis.

Code Text Area: Displays the contents of the opened source code file.


## How to Use

1. Click the "Open File" button to select and open a source code file.
2. Once the file is opened, the "Lexical Analysis" button will be enabled. Click it to perform lexical analysis.
3. If the lexical analysis is successful, the "Syntax Analysis" button will be enabled. Click it to perform syntax analysis.
4. If the syntax analysis is successful, the "Semantic Analysis" button will be enabled. Click it to perform semantic analysis.
5. The results of each analysis will be displayed in the Result Text Area.
6. Use the "Clear" button to reset the application or just simply click the "Open File" button again to start a new analysis.
