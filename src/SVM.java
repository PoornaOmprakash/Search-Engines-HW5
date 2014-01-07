import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SVM {
	
	public static PrintWriter writer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        String l;
        List <String> trueLabel = new ArrayList<String>();
        List <String> predLabel = new ArrayList<String>();
        FileInputStream fr = new FileInputStream("citeseer.test.ltc.svm");
		BufferedReader br = new BufferedReader(new InputStreamReader(fr)); 
		String temp;
		while(((temp=br.readLine())!=null))            
		   {
               int index = temp.indexOf(" ");
			   String YLabelTrain=temp.substring(0,index);
			   trueLabel.add(YLabelTrain);
	       }
		
		FileInputStream f = new FileInputStream("predictions");
		BufferedReader b = new BufferedReader(new InputStreamReader(f)); 
		
		
		while(((temp=b.readLine())!=null))      
		   {
               int index = temp.indexOf(" ");
			   String YLabelPred=temp.substring(0,index);
			   predLabel.add(YLabelPred);
	       }
		
		
		PrintWriter writer = new PrintWriter("predictions.txt", "UTF-8");
		for(int i=0;i<predLabel.size();i++)
		{
			writer.write(predLabel.get(i)+" "+trueLabel.get(i)+"\n");
		}
	    writer.close();
}
}
