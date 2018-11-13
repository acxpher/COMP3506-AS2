package au.edu.uq.itee.comp3506.assn2.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class loadSwitch {
	
	public static List<Integer> loadSwitches() {
		int switch_count = 0;
		int invalid_switch = 0;
		
		List<Integer> switches = new ArrayList<Integer>();
		String invalid = "";
		
		try (BufferedReader br = new BufferedReader(new FileReader("data/switches.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				int s = Integer.parseInt(line);
				if (s > 9999) 
				{
					switches.add(s);	
					switch_count++;
				} 
				else 
				{
					invalid += line + ",";
					invalid_switch++;
					//System.out.println(invalid);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(switches);
		return switches;
	}
	
	public static boolean Contains(int s) throws IOException {
		
		for(int i=0;i<loadSwitch.loadSwitches().size()-1;i++)
		{
			if(loadSwitch.loadSwitches().get(i)==s)
				return true;
		}
		return false;
	}
	
	public static boolean Search(int s) throws IOException {
		int low= 0;
		int high= loadSwitches().size()-1;
		
		while (high >= low) {
			int middle = (high+low)/2;
			if (loadSwitches().get(middle)==s) {
				return true;
			}
			if(loadSwitches().get(middle)<s) {
				low =middle+1;
			}
			if(loadSwitches().get(middle)>s) {
				high=middle-1;
			}
		}
		return false;
	}
}
