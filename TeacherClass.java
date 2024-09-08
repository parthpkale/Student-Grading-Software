import java.util.Scanner;
import java.util.ArrayList;

public class TeacherClass {
  public static Scanner numscan = new Scanner(System.in);
  public static Scanner wordscan = new Scanner(System.in);
  public String className;  
  public int numOfStudents;
  public static int arrNum;
  public static String selClass;

  public TeacherClass(String theName, int theNumStud) {
    className = theName;
    numOfStudents = theNumStud;
  }


  // The following method prints out the list of classes that the user has created.
  public static String getClasses(ArrayList<String> classes) {
    String text = "";
    if (classes.size() == 0) {
      return text;
    } else {
      System.out.println();
      System.out.println("Your classes:");
      System.out.println();
      for (int i = 0; i < classes.size(); i++) {
        text += classes.get(i).toString();
        if (i != classes.size() - 1) {
          text += ", ";
        }
      }
      return text;
    }
  } // end getClasses()

  // The following method allows the teacher to select a class to edit.
  public static void pickClass(ArrayList<String> classes, ArrayList<Integer> numStud) {
    System.out.println("Which class would you like to enter?");
    System.out.println();
    // Method gets user input on which class they want to enter.
    String entClass = wordscan.nextLine();
    System.out.println();
    boolean got = false;
    // Uses for loop to iterate through classes to check if user input matches any of the classes.
    for (int i = 0; i < classes.size(); i++) {
      if (entClass.equals(classes.get(i))) {
        got = true;
        selClass = classes.get(i);
        arrNum = i;
      }
    }
    // If user input does not match any item on the list, then the following program runs.
    if (got == false) {
      System.out.println("You do not have a class called: " + entClass + ", please check your spelling and type the class name again.");
      System.out.println();
      // Runs pickClass() method again to allow user to re-enter class name.
      pickClass(classes, numStud);
    } else if (numStud.get(arrNum) == 0) {
      System.out.println("You currently have no students in your " + selClass + " class."); 
    } else {
      System.out.println("You have " + numStud.get(arrNum) + " students in your class.");
    }
  } // end pickClass()

  // The following method allows the teacher to add students to a class.
  public static void addStudents(ArrayList<Integer> numStud, ArrayList<String> subject, ArrayList<String> studName, ArrayList<Double> studGrade) {
    System.out.println();
    System.out.println("How many students do you want to add to your class?");
    System.out.println();
    System.out.print("Number of additional students: ");
    int numStudAdd = numscan.nextInt();
    System.out.println();
    numStud.set(arrNum, (numStudAdd+numStud.get(arrNum)));
    // Iterates through following for loop 'i' number of times. 'i' being the number of additional students the teacher wants to add.
    for (int i = 0; i < numStudAdd; i++) {
      System.out.println("What is the student #" +  (i+1) + "'s name?");
      System.out.println();
      String currStudName = wordscan.nextLine();
      System.out.println();
      studName.add(currStudName);
      studGrade.add(0.0);
      subject.add(selClass);
    }
  } // end addStudents()

  // The following method allows the user to change the grades of the students.
  public static void changeGrade(ArrayList<Integer> numStud, ArrayList<String> subject, ArrayList<String> studName, ArrayList<Double> studGrade) {
    System.out.println();
    // Prints out the list of students in the class and their corresponding marks.
    for (int g = 0; g < subject.size(); g++) {
      if (subject.get(g).equals(selClass)) {
        System.out.println("Class: " + subject.get(g) + " | Name: " + studName.get(g) + " | Grade: " + studGrade.get(g) + "%");
      }
    }
    System.out.println();
    System.out.println("Type the execution command number:");
    System.out.println();
    System.out.println("1. Mass Grade Edit\n2. Individual Grade Edit");
    System.out.println();
    System.out.print("Execution Command Number: ");
    int editNum = numscan.nextInt();
    System.out.println();
    // If editNum == 1, user can mass edit grades for students.
    if (editNum == 1) {
      System.out.println("How would you like to mass edit grades, type the execution command number:");
      System.out.println();
      System.out.println("1. Add by a specified amount\n2. Decrease by a specific amount\n3. Multiply by a specific factor\n4. Divide by a specific factor");
      System.out.println();
      System.out.print("Execution Command Number: ");
      int massEditType = numscan.nextInt();
      System.out.println();
      double gradeIncrease;
      // Uses switch/case to decide which mass edit type the user wants to use.
      switch(massEditType) {
        case 1: 
          System.out.println("By what amount would you like to increase your student grades in " + selClass + " class?");
          System.out.println();
          System.out.print("Amount Increase: ");
          gradeIncrease = numscan.nextDouble();
          for (int i = 0; i < subject.size(); i++) {
            if (subject.get(i).equals(selClass)) {
              studGrade.set(i, (studGrade.get(i) + gradeIncrease));
            }           
            if (studGrade.get(i) > 100) {
              System.out.println();
              System.out.println("NOTE: " + studName.get(i) + " in class " + selClass + " now has a grade greater than 100%.");
            }
          }
        break;
        case 2:
          System.out.println("By what amount would you like to decrease your student grades in " + selClass + " class?");
          System.out.println();
          System.out.print("Amount Decrease: ");
          gradeIncrease = numscan.nextDouble();
          for (int i = 0; i < subject.size(); i++) {
            if (subject.get(i).equals(selClass)) {
              studGrade.set(i, (studGrade.get(i) - gradeIncrease));
            }           
            if (studGrade.get(i) > 100) {
              System.out.println();
              System.out.println("NOTE: " + studName.get(i) + " in class " + selClass + " now has a grade greater than 100%.");
            }
          }
        break;
        case 3:
          System.out.println("By what factor would you like to multiply your student grades in " + selClass + " class?");
          System.out.println();
          System.out.print("Multiplication Factor: ");
          gradeIncrease = numscan.nextDouble();
          for (int i = 0; i < subject.size(); i++) {
            if (subject.get(i).equals(selClass)) {
              studGrade.set(i, (studGrade.get(i) * gradeIncrease));
            }           
            if (studGrade.get(i) > 100) {
              System.out.println();
              System.out.println("NOTE: " + studName.get(i) + " in class " + selClass + " now has a grade greater than 100%.");
            }
          }
        break;
        case 4:
          System.out.println("By what factor would you like to divide your student grades in " + selClass + " class?");
          System.out.println();
          System.out.print("Division Factor: ");
          gradeIncrease = numscan.nextDouble();
          for (int i = 0; i < subject.size(); i++) {
            if (subject.get(i).equals(selClass)) {
              studGrade.set(i, (studGrade.get(i) / gradeIncrease));
            }           
            if (studGrade.get(i) > 100) {
              System.out.println();
              System.out.println("NOTE: " + studName.get(i) + " in class " + selClass + " now has a grade greater than 100%.");
            }
          }
        break;
      } // end switch()
    // If editNum == 2, user can edit a specific student's grade.
    } else {
      System.out.println("Type the name of the student whose grade you want to change:");
      System.out.println();
      System.out.print("Student Name: ");
      String studGCName = wordscan.nextLine();
      System.out.println();
      boolean got = false;
      // Program checks if the specified student exists within the class.
      for (int c = 0; c < subject.size(); c++) {
        if (subject.get(c).equals(selClass) && studName.get(c).equals(studGCName)) {
          got = true;
          System.out.println(studName.get(c) + " currently has a " + studGrade.get(c) + "% in " + selClass + ".");
          System.out.println();
          System.out.println("What would you like to change their grade to?");
          System.out.println();
          System.out.print("New Grade: ");
          double studNewGrade = numscan.nextDouble();
          studGrade.set(c, studNewGrade);
          System.out.println();
          System.out.println(studName.get(c) + "'s grade in " + selClass + " is now " + studGrade.get(c) + "%.");
          System.out.println();
        } else if (got = false) {
          System.out.println("This student does not exist in the class " + selClass + ".");
          System.out.println("Try typing the student's name again.");
          studGCName = wordscan.nextLine();
        }
      }
    }
  } // end changeGrade()
} // end TeacherClass()
