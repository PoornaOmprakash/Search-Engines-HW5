import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class run {

	public static String train;
	public static String test;
	public static String c;
	public static PrintWriter writer;
	public static PrintWriter w;
	public static PrintWriter pw;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
   
		int numTrain = 0;
		int numTest = 0;
		
		String usage = "Please supply a parameter file.";
		
		if (args.length < 1) {
		      System.err.println(usage);
		      System.exit(1);
		    }

		    // read in the parameter file; one parameter per line in format of key=value
		    Map<String, String> params = new HashMap<String, String>();
		    Scanner scan = new Scanner(new File(args[0]));
		    String line = null;
		    
		    
		   do {
		      line = scan.nextLine();
		      String [] pair = line.split("=");
		      params.put(pair[0].trim(), pair[1].trim());
		    } while (scan.hasNext());
		    
		   FileInputStream fr = new FileInputStream(params.get("train"));
		   BufferedReader br = new BufferedReader(new InputStreamReader(fr)); 
		   c=params.get("c");
		   
//		   System.out.println(c);
		   
		   
		   
		   PrintWriter writer1 = new PrintWriter("c.txt", "UTF-8");
		   writer1.println(""+c);
		   writer1.close();
		   
		   
		   
		   
		   
		   String temp = null;
		   while((br.readLine())!=null)              //Find number of training instances
		   {
			   numTrain++;
//			   System.out.println(temp);
		   }
		   
//		   System.out.println(numTrain);
		   
		   FileInputStream f = new FileInputStream(params.get("test"));
		   BufferedReader b = new BufferedReader(new InputStreamReader(f)); 
		   
		   while((b.readLine())!=null)             //Find number of testing instances
		   {
			   numTest++;
//			   System.out.println(temp);
		   }
	
		   
//		   System.out.println(numTest);
		   
		   int YTrain[] = new int[numTrain];
		   int YTest[] = new int[numTest];
		   
		   
		   
		   int i=0;
		   
		   
           List <Integer> features = new ArrayList<Integer>();
		   
		   
		   fr = new FileInputStream(params.get("train"));
		   br = new BufferedReader(new InputStreamReader(fr)); 
		   
		   
		   
		   
		   
		   int test = 0;
		   while(((temp=br.readLine())!=null))               //Find out number of features (From train)
		   {
//			   System.out.println(temp);
			   Map<Integer,Double> XTrain = new HashMap<Integer,Double>();
			   i = 0;
			   int index = temp.indexOf(" ");
			   int YLabelTrain=Integer.parseInt(temp.substring(0,index));
			   YTrain[i] = YLabelTrain;
			   String t1 = null;
			   temp = temp.substring(index);
//			   System.out.println(temp);
			   temp = temp.trim();
			   while(temp!=null)     //Process each line from the data
			   {
				  temp = temp.trim();
				  index = temp.indexOf(" ");
				  if(index == -1)
					  break;
				  t1 = temp.substring(0,index);
				  t1 = t1.trim();
			      String [] pair = t1.split(":");
			      features.add(Integer.parseInt(pair[0]));
//			      System.out.println(pair[0]+" "+pair[1]);
			      XTrain.put(Integer.parseInt(pair[0].trim()), Double.parseDouble(pair[1].trim()));
			      temp = temp.substring(index);
			   }      
			   test++;
			   i++;
		   }
		   
		   
		   
		   
		   
		   
           i=0;
		   

           f = new FileInputStream(params.get("test"));
		   b = new BufferedReader(new InputStreamReader(f)); 
		
		   

		   test = 0;
		   while(((temp=b.readLine())!=null))               //Find number of features (From test)
		   {
			   Map<Integer,Double> XTest = new HashMap<Integer,Double>();
			   i = 0;
			   int index = temp.indexOf(" ");
			   int YLabelTest=Integer.parseInt(temp.substring(0,index));
			   YTest[i] = YLabelTest;
			   String t1 = null;
			   temp = temp.substring(index);
			   temp = temp.trim();
			   while(temp!=null)     //Process each line from the data
			   {
				  temp = temp.trim();
				  index = temp.indexOf(" ");
				  if(index == -1)
					  break;
				  t1 = temp.substring(0,index);
				  t1 = t1.trim();
			      String [] pair = t1.split(":");
			      features.add(Integer.parseInt(pair[0]));
//			      System.out.println(pair[0]);
//			      System.out.println(Integer.parseInt(pair[0].trim()));
			      XTest.put(Integer.parseInt(pair[0].trim()), Double.parseDouble(pair[1].trim()));
			      temp = temp.substring(index);
			   }  
			   test++;
			   i++;
//			   w.write("\n");
		   }
		   
		   
		   
		   int max = 0;                                  //This gets the total number of features.
		   for(int y=0;y<features.size();y++)
			   {
			     if(features.get(y) > max)
			     {
			    	 max = features.get(y);
			     }
			   }
		   
//		   System.out.println(max);
		   
		   
		   
		   
		   
		   
		   
		   fr = new FileInputStream(params.get("train"));
		   br = new BufferedReader(new InputStreamReader(fr)); 
		   FileOutputStream stream = new FileOutputStream("train.txt");
		   OutputStreamWriter sw = new OutputStreamWriter(stream, "utf-8");
		   writer = new PrintWriter(sw);
		   
		   
		   
		   test = 0;
		   while(((temp=br.readLine())!=null))               //Process XTrain and YTrain
		   {
			   if(test!=0)
			     {
//				    System.out.println("Hello");
				    writer.write("\n");
			     }
//			   System.out.println(temp);
			   Map<Integer,Double> XTrain = new HashMap<Integer,Double>();
			   i = 0;
			   int index = temp.indexOf(" ");
			   int YLabelTrain=Integer.parseInt(temp.substring(0,index));
			   YTrain[i] = YLabelTrain;
			   String t1 = null;
			   temp = temp.substring(index);
//			   System.out.println(temp);
			   temp = temp.trim();
			   while(temp!=null)     //Process each line from the data
			   {
				  temp = temp.trim();
				  index = temp.indexOf(" ");
				  if(index == -1)
					  break;
				  t1 = temp.substring(0,index);
				  t1 = t1.trim();
			      String [] pair = t1.split(":");
			      features.add(Integer.parseInt(pair[0]));
//			      System.out.println(pair[0]+" "+pair[1]);
			      XTrain.put(Integer.parseInt(pair[0].trim()), Double.parseDouble(pair[1].trim()));
			      temp = temp.substring(index);
			   }  
			   
			      writer.write(""+YTrain[i]);
			      
			      for(int g = 1; g<=max+1; g++)
			      {
			    	  if(XTrain.get(g)!=null)
			    		  writer.write(" "+XTrain.get(g));
			    	  else
			    		  writer.write(" 0");
			      }
			   test++;
			   i++;
		   }
		   
		   
		   
		   
		   
		   
           i=0;

           f = new FileInputStream(params.get("test"));
		   b = new BufferedReader(new InputStreamReader(f)); 
		   FileOutputStream st = new FileOutputStream("test.txt");
		   OutputStreamWriter s = new OutputStreamWriter(st, "utf-8");
		   w = new PrintWriter(s);
		   

		   test = 0;
		   while(((temp=b.readLine())!=null))               //Process XTest and YTest
		   {
			   if(test!=0)
			     {
				    w.write("\n");
			     }
			   Map<Integer,Double> XTest = new HashMap<Integer,Double>();
			   i = 0;
			   int index = temp.indexOf(" ");
			   int YLabelTest=Integer.parseInt(temp.substring(0,index));
			   YTest[i] = YLabelTest;
			   String t1 = null;
			   temp = temp.substring(index);
			   temp = temp.trim();
			   while(temp!=null)     //Process each line from the data
			   {
				  temp = temp.trim();
				  index = temp.indexOf(" ");
				  if(index == -1)
					  break;
				  t1 = temp.substring(0,index);
				  t1 = t1.trim();
			      String [] pair = t1.split(":");
			      features.add(Integer.parseInt(pair[0]));
//			      System.out.println(Integer.parseInt(pair[0].trim()));
			      XTest.put(Integer.parseInt(pair[0].trim()), Double.parseDouble(pair[1].trim()));
			      temp = temp.substring(index);
			   }  
			   
			      w.write(""+YTest[i]);
			      
			      for(int g = 1; g<=max+1; g++)
			      {
//			    	  System.out.println(g);
			    	  if(XTest.get(g)!=null)
			    		  w.write(" "+XTest.get(g));
			    	  else
			    		  w.write(" 0");
			      }
			   test++;
			   i++;
//			   w.write("\n");
		   }
		   	   
		   
		   
		   scan.close();
		   fr.close();
		   br.close();   
	}

}
