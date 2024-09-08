import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Main {
  public static Scanner numscan = new Scanner(System.in);
  public static Scanner wordscan = new Scanner(System.in);

  public static void main(String[] args) {
    // Initialize Variables
    ArrayList<String> classes = new ArrayList<String>();
    ArrayList<String> subject = new ArrayList<String>();
    ArrayList<String> studName = new ArrayList<String>();
    ArrayList<Double> studGrade = new ArrayList<Double>();
    ArrayList<Integer> numStud = new ArrayList<Integer>();
    ArrayList<String> teacherLoginInfo = new ArrayList<String>();
    String username = "";
    String password = "";

    // Initial Account Login/Signup 
    System.out.println("Welcome to the Grading Software. Please enter your username and password or sign-up.");
    System.out.println();
    boolean login = false;
    while (login == false) {
      //Loads TeacherDatabase File before input of username and password to ensure that the most recent updates are being used from Database/TeacherData.
      String[] TeacherDatabase = Files.loadStringArr("Database/TeacherData");
      System.out.println("1. Login\n2. Sign Up");
      System.out.println();
      System.out.print("Execution Command Number: ");
      int enterType = numscan.nextInt();
      // Runs enterType == 1 if username and password already stored in database (account has been created).
      if (enterType == 1) {
        System.out.println();
        System.out.print("Username: ");
        username = wordscan.nextLine();
        System.out.print("Password: ");
        password = wordscan.nextLine();
        // After receiving username and password, the program will check if the username and password match any of the stored usernames and passwords through a for loop iteration.
        for (int i = 1; i < (TeacherDatabase.length); i++) {
          if (TeacherDatabase[i-1].equals(username) && TeacherDatabase[i].equals(password)) {
            System.out.println();
            System.out.println("Login Successful");
            System.out.println();
            login = true;
            break;
          }
        }
        // If username and password do not match any of the stored usernames and passwords, the program will ask user to retry until successful.
        if (login == false) {
          System.out.println();
          System.out.println("Login Unsuccessful. Please try again.");
          System.out.println();
          login = false;
        } 
        // Runs enterType == 2 if username and password have not been entered into database (account has not been created).
      } else {
        System.out.println();
        System.out.print("New Username: ");
        // Adds username to teacherLoginInfo Arraylist.
        teacherLoginInfo.add(wordscan.nextLine());
        // Uses try/catch to save the updated teacherLoginInfo Arraylist to the Database/TeacherData.
        try {
          Files.saveFile("Database/TeacherData", teacherLoginInfo);
        } catch (IOException e) {
          System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
        System.out.print("New Password: ");
        // Adds password to teacherLoginInfo Arraylist.
        teacherLoginInfo.add(wordscan.nextLine());
        // Uses try/catch to save the updated teacherLoginInfo Arraylist to the Database/TeacherData.
        try {
          Files.saveFile("Database/TeacherData", teacherLoginInfo);
        } catch (IOException e) {
          System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
        System.out.println();
        // Directs user to login page after creating new account.
        enterType = 1;
      }
    } // end while()
    String cont = "";
    // While loop only runs when login == true.
    while (cont != "exit" && login == true) {
      System.out.println("Type the execution command number:");
      System.out.println();
      System.out.println("1. Enter Class\n2. Add Class\n3. Log Out");
      System.out.println();
      System.out.print("Execution Command Number: ");
      int choice = numscan.nextInt();
      // Uses switch case to decide the program's next course of action.
      switch (choice) {
        case 1:
          enterClass(classes, numStud, subject, studName, studGrade);    
          break;
        case 2:
          addClass(classes, numStud);
          break;
        case 3:
          cont = "exit";
          login = false;
          System.out.println();
          System.out.println("Thanks for using the Grading Software!");
      } // end switch()
    } // end while()
  } // end void()

  // The following method allows the teacher to enter a specific class.
  public static void enterClass(ArrayList<String> classes, ArrayList<Integer> numStud, ArrayList<String> subject, ArrayList<String> studName, ArrayList<Double> studGrade) {
    // Checks class size. If class size is 0, the program will ask the teacher to create a class.
    if (classes.size() == 0) {
      System.out.println();
      System.out.println("You currently have no classes. You must add a class.");
      // Runs addClass() method to create a class.
      addClass(classes, numStud);
      // Runs the following if students are already enrolled in class.
    } else {
      // Uses getClasses() method from TeacherClass.java.
      System.out.println(TeacherClass.getClasses(classes));
      System.out.println();
      // Uses pickClass() method from TeacherClass.java.
      TeacherClass.pickClass(classes, numStud);
      System.out.println();
      boolean cont = true;
      // Provides user with options to edit students or grades.
      while (cont == true) {
        System.out.println("Type the execution command number:");
        System.out.println("1. Add Students\n2. Add/Change Grades\n3. Back");
        System.out.println();
        System.out.print("Execution Command Number: ");
        int choice = numscan.nextInt();
        // Uses if/else selection method to decide the program's next course of action.
        // When choice == 1, the program will enable the user to add students to their class.
        if (choice == 1) {
          TeacherClass.addStudents(numStud, subject, studName, studGrade);
        // When choice == 2, the program will enable the user to add/change grades for students in their class.
        } else if (choice == 2 && numStud.get(TeacherClass.arrNum) > 0) {
          TeacherClass.changeGrade(numStud, subject, studName, studGrade);
        } else if (choice == 3) {
          System.out.println();
          System.out.println("Going back...");
          System.out.println();
          cont = false;
          // If a class has no students, the program will force the user to add students before enabling them to add/change grades.
        } else if (choice == 2 && numStud.get(TeacherClass.arrNum) == 0) {
          System.out.println();
          System.out.println("You currently have no students in this class. You must add students before you can add/change grades.");
          TeacherClass.addStudents(numStud, subject, studName, studGrade);
        }
      } // end while()
    }
  } // end enterClass()

  // The following method allows the teacher to add a class.
  public static void addClass(ArrayList<String> classes, ArrayList<Integer> numStud) {
    System.out.println();
    System.out.println("Type the name of the class you want to add:");
    System.out.println();
    String className = wordscan.nextLine();
    classes.add(className);
    System.out.println();
    System.out.println("You now have class called: " + className);
    numStud.add(0);
    System.out.println();
  } // end addClass()
} // end main()
