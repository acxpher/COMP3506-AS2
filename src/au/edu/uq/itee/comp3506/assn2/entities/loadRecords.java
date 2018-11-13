package au.edu.uq.itee.comp3506.assn2.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class loadRecords {

	public static List<CallRecord> loadRecords() throws NumberFormatException {
		int count = 0;
		int invalid = 0;
		List<CallRecord> cas = new ArrayList<CallRecord>();
		
try (BufferedReader br = new BufferedReader(new FileReader("data/call-records.txt"))) 
{
			
		String line;			
		while ((line = br.readLine()) != null) 
		{
				String[] token = line.split("  | ");
				List<Integer> path = new ArrayList<>();
				CallRecord ca = new CallRecord(0, 0, 0, 0, null, null);				

         if (token.length >= 5 && token[0].matches("\\d*") 
    	      	&& token[1].matches("\\d*") && token[token.length - 2].matches("\\d*")
    	      	
    		    && token[token.length - 3].matches("\\d*"))
	       {
                     ca.dialler = Long.parseLong(token[0]);
					ca.receiver = Long.parseLong(token[token.length - 2]);
					ca.diallerSwitch = Integer.parseInt(token[1]);
					ca.receiverSwitch = Integer.parseInt(token[token.length - 3]);
					ca.timeStamp = LocalDateTime.parse(token[token.length - 1]);
				
					for (int i = 2; i < token.length - 3; i++) 
					{
						if (token[i].length() == 5)//&&loadSwitch.Search(Integer.parseInt(token[i])))
						path.add(Integer.parseInt(token[i]));									
					}
					ca.connectionPath = path;
					
					if(!ca.getConnectionPath().isEmpty()) 
				{
					if(ca.getDiallerSwitch()==ca.getConnectionPath().get(0))
				    cas.add(ca);
					System.out.println(cas.size()+": "+ca);
					
				}
					else cas.add(ca);
					
						 
	        }  else invalid++;	 								 
		}		
  } catch (IOException e) 
       {
			e.printStackTrace();
		}
		
		return cas;

	}
	public static void main(String[] args) throws IOException, NumberFormatException {
		loadRecords();
		//System.out.println("Used Memory   :  " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1020 * 1024) + " MB");

	}
}
