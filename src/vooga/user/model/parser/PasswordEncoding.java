package vooga.user.model.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PasswordEncoding {
	
	public PasswordEncoding(){	
	}
	
	 public void writeToFile(String information) throws IOException{
	Writer output = null;
	    File file = new File("resources/passwordBankResource.properties");
	    output = new BufferedWriter(new FileWriter(file));
	    output.write(information);
	    output.close();
	    System.out.println("Your file has been written");  
	 }
	 
	 public List<String> readFile(File file){
		 // Open the file that is the first 
		 List<String> fileLines = new ArrayList<String>();
		    // command line parameter
		    FileInputStream inputStream = null;
			try {
				inputStream = new FileInputStream("doc/resources/PasswordResource.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(inputStream);
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		    String line = "";
		    //Read File Line By Line
		    try {
				while ((line = reader.readLine()) != null)   {
					fileLines.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		    return fileLines;
		    }
	
}
