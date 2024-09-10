import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Files {
  public static void saveFile(String filename, ArrayList<String> array) throws IOException {
    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter file = null;
    
    try {
      fw = new FileWriter(filename, true);
      bw = new BufferedWriter(fw);
      file = new PrintWriter(bw);
      // Writes teacherLoginInfo array into Database/TeacherData.
      for(int i = array.size()-1; i < array.size(); i++){
        file.write(array.get(i));
        file.write("\n");
      }
      // Refreshes the writer.
      file.flush();
    } finally { 
      try { 
        file.close();
        bw.close();
        fw.close();
      // Prints error message if an exception occurs.
      } catch (IOException e) {
        System.out.println(e);
      } // end catch()
    }
  } // end saveFile()
  
  public static String[] loadStringArr(String filename) {
    String addLines="";
    try {
      BufferedReader file = new BufferedReader(new FileReader(filename));
        while (file.ready()) {
          addLines += file.readLine()+",";
        } // end while()
      file.close();
    } catch (IOException e) {
      System.out.println(e);
    } // end catch()
    return addLines.split(",");
  } // end loadStringArr()
} // end Files()
