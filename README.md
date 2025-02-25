# Student Grading Software

## Overview
A Java-based application designed to help teachers grade student assignments efficiently. The software supports user account creation, automated grading functionality, and file management using object-oriented programming and recursion principles.

## Features
- User Account Management: Create and manage teacher accounts.
- Grading System: Grade student assignments based on pre-defined criteria.
- File Handling: Program reads and writes user data to .txt files using FileWriter and PrintWriter.
- Object-Oriented Programming: Built using OOP principles with classes like TeacherClass, Files, and Main.
- Recursion: Utilizes recursion for certain grading functionalities to enhance efficiency and modularity.

## Structure
- Main.java: The entry point of the application that connects TeacherClass.java and Files.java.
- TeacherClass.java: Contains methods related to teacher accounts and grading functionality.
- Files.java: Handles file reading and writing for storing user account and grading data.

## Installation
1. Clone the repository to your local machine:

```sh
git clone https://github.com/yourusername/Tank-Wars-Video-Game.git
```

2. Navigate to the project directory:

```sh
cd student-grading-software
```

3. Compile and run the project using your preferred Java IDE or the command line.

```sh
javac Main.java
java Main
```

## Usage
- Upon running the program, you will be prompted to create a teacher account.
- After account creation, you can begin grading student assignments by entering the student's details and grades.
- The grading data is saved in a .txt file for future reference.

## Future Improvements
- Graphical User Interface (GUI): Develop a user-friendly GUI for easier navigation and interaction with the system.
- Database Integration: Implement a database (e.g., MySQL or SQLite) for more robust and scalable data storage instead of relying on text files.
- Automated Grading: Introduce the ability to auto-grade assignments based on predefined criteria or answer keys.
- Recursion Optimization: Further optimize the recursive methods to handle more complex grading tasks and larger datasets efficiently.
- Multi-user Support: Enable support for multiple teachers to manage different classes and students in the same system.
