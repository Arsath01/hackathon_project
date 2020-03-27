package batteryjson;
import java.io.*;
import java.util.Scanner;
import org.json.JSONObject;

public class batteryjson {

	public static void main(String[] args) throws Exception {
		JSONObject obj = new JSONObject();//json object is created
		float Battery_percentage=0;
		float Battery_drain=0;
		 String[] words=null;
		String input1="Uid u0a202";
		String input2="Foreground activities";
		try {
		 File myObj = new File("C:\\Users\\Arsath\\eclipse-workspace\\batteryjson\\Battery.txt");//reading the input file
	      Scanner myReader = new Scanner(myObj);
	      String temp,Foreground_time="";
	      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        words = data.split(":");
		        if(words.length>=1)
		        {
		        words[0]=words[0].trim();
		                 if (words[0].equals(input1))   //Search for the given word
		                 {
		                	 temp=words[1];
		                	 String[] array1 = temp.split("\\(");
					         Battery_drain=Float.parseFloat(array1[0]);
					         Battery_percentage=(Battery_drain/1000);
		                 }
		                 if(words[0].equals(input2))
		 					{
		 	                 temp=words[1];
		 			         String[] array1 = temp .split("\\(r");
		 			         Foreground_time=array1[0].trim();
		 					}
		        }
		        else
		        {
		        	System.out.println("error");
		        }
//		        System.out.println(data);
	      }
	      
	      obj.put("Foreground_time",Foreground_time);
			obj.put("Battery_drain" ,Battery_drain);
			obj.put("Battery_percentage" ,Battery_percentage);
			FileWriter file1=new FileWriter("C:\\Users\\Arsath\\eclipse-workspace\\batteryjson\\output.json");
			file1.write(obj.toString());
			file1.flush();
			System.out.println(obj);
		}
	      catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
	      }
	}
}



