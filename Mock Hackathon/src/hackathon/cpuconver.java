package hackathon;

//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;
//import com.mysql.jdbc.PreparedStatement;

public class cpuconver {
	static Scanner myReader=null;
	static double max=0;
	static String c;
	static double arr[]=new double[1000];
	static int iteration=0;
	static double total=0;
	static Connection myConn = null;
	static Statement myStmt = null;
	ResultSet myRs = null;
	public static void main(String[] args) throws SQLException  {
		JSONObject obj = new JSONObject();//json object is created
//		obj.put("transaction name", "sample_transaction");
	    try {
	    	myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cpu", "root" , "arsath001");
	    	System.out.println("Database connection is successful!\n");
	    	myStmt = myConn.createStatement();
	    	PreparedStatement pStmt = myConn.prepareStatement("INSERT into cpuvalue(transaction, average, maximum) values(?,?,?)"); 
	      File myObj = new File("C:\\Users\\Arsath\\Desktop\\input.txt");//reading the input file
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
//	        System.out.println(data);
	        StringTokenizer stringtokenizer=new StringTokenizer(data," ");
	        pStmt.setString(1, "Transaction1");
	        while(stringtokenizer.hasMoreElements())
	        {
	        	int a=0;
	        	while(a<8)//8 is used because in the input the value comes in the 9 th position
	        	{
	        		stringtokenizer.nextElement().toString();
					a++;
	        	}
	        	Double val = Double.parseDouble(stringtokenizer.nextElement().toString());//for converting the string to the value
				if (max < val)
					max = val;
//				total+=val;
				while (a < 11) {
					stringtokenizer.nextElement().toString();//skipping the string remaining
					a++;
				}
				iteration++;
				c= iteration + "s";//for writing 1s 2s
				
				obj.put(c,val);
				
				arr[iteration] =val;
				
	        }
	      }
	      for (int i = 0; i < arr.length; i++) {
				total = total + arr[i];//for finding the average the total is find out
			}
			
			double average = (double)total / (double)iteration;
			obj.put("total", max);
			obj.put("average", average);
//	      myReader.close();
			pStmt.setDouble(2, average);
			pStmt.setDouble(3, max);
			pStmt.execute();
			System.out.println(obj);
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }finally {
	    	if(myReader !=null)
			{
				myReader.close();
			}
	    }
	  }
	}

	



	


