package au.edu.uq.itee.comp3506.assn2.entities;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CallRecordExampleUsage {
	/**
	 * Tests search 1 from the assignment specification.
	 * Run time efficiency constant O(n); it go through a loop of List<CallRecord>
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @return List of all the phone numbers called by dialler.
	 *         The list will contain duplicates of the receiver if the dialler called the receiver multiple times.
	 */
	
	public static List<Long> called(long dialler) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Long> result = new ArrayList<>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
			if (ca.getDialler() == dialler)
			result.add(ca.getReceiver());
		}

		return result;
	}
	/**
	 * Tests search 1 from the assignment specification.
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of all the phone numbers called by dialler between start and end time.
	 *         The list will contain duplicates of the receiver if the dialler called the receiver multiple times.
	 */

	
	public static List<Long> called(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
	
		List<CallRecord> records = loadRecords.loadRecords();
		List<Long> result = new ArrayList<Long>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			if (ca.getDialler() == dialler) 
			{
				if (ca.getTimeStamp().isAfter(startTime)
				&& ca.getTimeStamp().isBefore(endTime)
				||ca.getTimeStamp().isEqual(startTime)
			    ||ca.getTimeStamp().isEqual(endTime))
					
				result.add(ca.getReceiver());
			}
		}
		return result;
	}
	/**
	 * Tests search 2 from the assignment specification.
	 * 
	 * @param receiver The phone number that received the calls.
	 * @return List of all the phone numbers that called the receiver.
	 *         The list will contain duplicates of the caller if they called the receiver multiple times.
	 */
	
	public static List<Long> callers(long receiver) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Long> result = new ArrayList<Long>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
			if (ca.getReceiver() == receiver)
			result.add(ca.getDialler());
		}

		return result;
	}
	/**
	 * Tests search 2 from the assignment specification.
	 * 
	 * @param receiver The phone number that received the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of all the phone numbers that called the receiver between start and end time.
	 *         The list will contain duplicates of the caller if they called the receiver multiple times.
	 */
	
	public static List<Long> callers(long receiver, LocalDateTime startTime, LocalDateTime endTime) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Long> result = new ArrayList<Long>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
			if (ca.getReceiver() == receiver) 
			{
				if (ca.getTimeStamp().isAfter(startTime) && ca.getTimeStamp().isBefore(endTime)
				  ||ca.getTimeStamp().isEqual(startTime)||ca.getTimeStamp().isEqual(endTime))
				
					result.add(ca.getDialler());
			}
		}
		return result;
	}
	
	/**
	 * Tests search 3 from the assignment specification.
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @return The list of identifiers of the faulty switches or an empty list if no fault was found.
	 */
	
	public static List<Integer> findConnectionFault(long dialler) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
		if (ca.getDialler() == dialler) 
		{
			if(ca.getConnectionPath().isEmpty()) 
				{
					result.add(ca.getDiallerSwitch());
					
				}
			if(!ca.getConnectionPath().isEmpty()) 
			{
				int firstSwitch = ca.getConnectionPath().get(0);
				int lastSwitch = ca.getConnectionPath().get(ca.getConnectionPath().size() - 1);				
				
				if (firstSwitch != ca.getDiallerSwitch()) 
				  {
					result.add(firstSwitch);			
				  }
				if (lastSwitch != ca.getReceiverSwitch())
				  {
					result.add(lastSwitch);
				
			      }
			}
	    }		
	}
		return result;		
}

	/**
	 * Tests search 3 from the assignment specification.
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The list of identifiers of the faulty switches;
	 *         or an empty list if no fault was found between start and end time.
	 */
	
	public static List<Integer> findConnectionFault(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
			if (ca.getDialler() == dialler&& (ca.getTimeStamp().isAfter(startTime) && ca.getTimeStamp().isBefore(endTime)
					||ca.getTimeStamp().isEqual(startTime)||ca.getTimeStamp().isEqual(endTime))) 
			{
				if(ca.getConnectionPath()==null) 
				{
					result.add(ca.getDiallerSwitch());
					
				}
				
				if(ca.getConnectionPath()!=null) 
				{
				
					int firstSwitch = ca.getConnectionPath().get(0);
				    int lastSwitch = ca.getConnectionPath().get(ca.getConnectionPath().size() - 1);				
				
				if (firstSwitch != ca.getDiallerSwitch()) 
				 {
					result.add(firstSwitch);
				 }
				if (lastSwitch != ca.getReceiverSwitch())
				 {
					result.add(lastSwitch);				
				
				 }
			     }
	       }		
	  }
		         return result;		
}

	/**
	 * Tests search 3 from the assignment specification.
	 * 
	 * @param reciever The phone number that should have received the calls.
	 * @return The list of identifiers of the faulty switches or an empty list if no fault was found.
	 */
	
	public static List<Integer> findReceivingFault(long receiver) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
			if (ca.getReceiver() == receiver ) 
			{
				if(ca.getConnectionPath().isEmpty()) 
				{
					result.add(ca.getDiallerSwitch());
					
				}
			
				if(!ca.getConnectionPath().isEmpty()) 
				{
				    int firstSwitch = ca.getConnectionPath().get(0);
				    int lastSwitch = ca.getConnectionPath().get(ca.getConnectionPath().size() - 1);				
			
				    if (firstSwitch != ca.getDiallerSwitch()) 
				 {
					result.add(firstSwitch);				 
				 }
		
				if (lastSwitch != ca.getReceiverSwitch())
				{
					result.add(lastSwitch);				
				}
			   }
	       }		
	  }
		return result;		
}
	/**
	 * Tests search 3 from the assignment specification.
	 * 
	 * @param reciever The phone number that should have received the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The list of identifiers of the faulty switches;
	 *         or an empty list if no fault was found between start and end time.
	 */
	
	public static List<Integer> findReceivingFault(long receiver, LocalDateTime startTime, LocalDateTime endTime) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
			if (ca.getReceiver() == receiver&& (ca.getTimeStamp().isAfter(startTime) && ca.getTimeStamp().isBefore(endTime)
					||ca.getTimeStamp().isEqual(startTime)||ca.getTimeStamp().isEqual(endTime))) {
				if(ca.getConnectionPath()==null) 
				{
					result.add(ca.getDiallerSwitch());
					
				}
			
				if(ca.getConnectionPath()!=null) 
				{
				
					int firstSwitch = ca.getConnectionPath().get(0);
				    int lastSwitch = ca.getConnectionPath().get(ca.getConnectionPath().size() - 1);				
				
				if (firstSwitch != ca.getDiallerSwitch()) 
				{
					result.add(firstSwitch);
				  System.out.println(firstSwitch);
				 }
				
				if (lastSwitch != ca.getReceiverSwitch()){
					result.add(lastSwitch);
				 System.out.println("last: " +lastSwitch);
				 System.out.println("recev " +ca.getReceiverSwitch());
			 }
			}
	     }		
	}
		return result;		
}
	/**
	 * Tests search 4 from the assignment specification.
	 * 
	 * @return The identifier of the switch that had the most connections.
	 *         If multiple switches have the most connections, the smallest switch identifier is returned.
	 */
	
	public static int maxConnections() {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> s = new ArrayList<>();
		for(int i=0;i<records.size();i++)
		{
			CallRecord ca = records.get(i);
			if(ca.getConnectionPath()!=null)
			{
				for(int e=0;e<ca.getConnectionPath().size();e++)
				{
		         s.add(ca.getConnectionPath().get(e));
				}
			}
		}
		
		if(!s.isEmpty()) {
		Collections.sort(s);
		int previous = s.get(0);
		int popular = s.get(0);
		int count = 1;
		int maxCount = 1;

		for (int i = 1; i < s.size(); i++) {
			if (s.get(i) == previous)
				count++;
			else {
				if (count > maxCount) {
					popular = s.get(i-1);
					maxCount = count;
				}
				previous = s.get(i);
				count = 1;
			}
		}
		return count > maxCount ? s.get(s.size()-1) : popular;
	}
		return 0;
}

	/**
	 * Tests search 4 from the assignment specification.
	 * 
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The identifier of the switch that had the most connections between start and end time.
	 *         If multiple switches have the most connections, the smallest switch identifier is returned.
	 */
	
	public static int maxConnections(LocalDateTime startTime, LocalDateTime endTime) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> s = new ArrayList<>();
		
		for(int i=0;i<records.size();i++)
		{
			CallRecord ca = records.get(i);
			if(ca.getConnectionPath()!=null&&ca.getTimeStamp().isBefore(endTime)&&ca.getTimeStamp().isAfter(startTime)
					||ca.getTimeStamp().isEqual(startTime)||ca.getTimeStamp().isEqual(endTime))
			{
				for(int e=0;e<ca.getConnectionPath().size();e++)
				{
		         s.add(ca.getConnectionPath().get(e));
				}
			}
		}
				
		if(!s.isEmpty()) {
		Collections.sort(s);
		int previous = s.get(0);
		int popular = s.get(0);
		int count = 1;
		int maxCount = 1;

		for (int i = 1; i < s.size(); i++) {
			if (s.get(i) == previous)
				count++;
			else {
				if (count > maxCount) {
					popular = s.get(i-1);
					maxCount = count;
				}
				previous = s.get(i);
				count = 1;
			}
		}
		return count > maxCount ? s.get(s.size()-1) : popular;
	}
		return 0;
}
	/**
	 * Tests search 5 from the assignment specification.
	 * 
	 * @return The identifier of the switch that had the fewest connections.
	 *         If multiple switches have the fewest connections, the smallest switch identifier is returned.
	 */
	
	public static int minConnections() {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> s = new ArrayList<>();
		int result=0;
		
		for(int i=0;i<records.size();i++)
		{
			CallRecord ca = records.get(i);
			if(ca.getConnectionPath()!=null)
			{
				for(int e=0;e<ca.getConnectionPath().size();e++)
				{
		         s.add(ca.getConnectionPath().get(e));
				}
			}
		}
		
		if(!s.isEmpty()) {
		Collections.sort(s);		
	      int[] results = new int[s.size()];
	      int minCount = Integer.MAX_VALUE;
	      for (int i = 0; i < s.size(); i++) {
	          for (int j = 0; j < s.size(); j++) {
	              if (s.get(i).equals(s.get(j))) {
	                  results[i]++;	                 
	              }
	          }	       
	          if (results[i] <= minCount) {
	              minCount = results[i];
	          }
	      }
	      
	      for (int i = 0; i < results.length; i++) {
	          if (results[i] == minCount) {
	        	      result =i;
	          }      
	  }	
	      return s.get(result);
	}
		else return 0;
}
	/**
	 * Tests search 5 from the assignment specification.
	 * 
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The identifier of the switch that had the fewest connections between start and end time.
	 *         If multiple switches have the fewest connections, the smallest switch identifier is returned.
	 */
	public static int minConnections(LocalDateTime startTime, LocalDateTime endTime) {
	
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> s = new ArrayList<>();
		
		for(int i=0;i<records.size();i++)
		{
			CallRecord ca = records.get(i);
			if(ca.getConnectionPath()!=null&&ca.getTimeStamp().isBefore(endTime)&&ca.getTimeStamp().isAfter(startTime)
					||ca.getTimeStamp().isEqual(startTime)||ca.getTimeStamp().isEqual(endTime))
			{
				for(int e=0;e<ca.getConnectionPath().size();e++)
				{
		         s.add(ca.getConnectionPath().get(e));
				}
			}
		}		
	
		if(!s.isEmpty()) {
		Collections.sort(s);		
	      int[] results = new int[s.size()];
	      int minCount = Integer.MAX_VALUE;
	      List<Integer> index= new ArrayList<Integer>();
	      int min=99999;
	      for (int i = 0; i < s.size(); i++) {
	          for (int j = 0; j < s.size(); j++) {
	              if (s.get(i).equals(s.get(j))) {
	                  results[i]++;	                 
	              }
	          }	       
	          if (results[i] <= minCount) {
	              minCount = results[i];
	          }
	      }
	      for (int i = 0; i < results.length; i++) {
	          if (results[i] == minCount) {
	        	      index.add(i);
	          }      
	   }		
	     for(int i=0;i<index.size();i++) {
	    	 if(s.get(index.get(i))<min) 
	    	 
	    		 min=s.get(index.get(i));	    	
	     }
	      return min;		
	}
		return 0;
}
	/**
	 * Tests search 6 from the assignment specification.
	 * 
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of details of all calls made between start and end time.
	 */
	public static List<CallRecord> callsMade(LocalDateTime startTime, LocalDateTime endTime) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<CallRecord> result = new ArrayList<CallRecord>();
	
		for (int i = 0; i < records.size(); i++) {
			CallRecord ca = records.get(i);
			if (ca.getTimeStamp().isAfter(startTime) && ca.getTimeStamp().isBefore(endTime)
					||ca.getTimeStamp().isEqual(startTime)||ca.getTimeStamp().isEqual(endTime))
				result.add(ca);
		}
		return result;
	}
	public static void main(String[] args) throws IOException, NumberFormatException {

		
		LocalDateTime startTime = LocalDateTime.parse("2017-09-15T16:43:36.481");
        LocalDateTime endTime = LocalDateTime.parse("2017-09-20T08:39:27.257");  
        
       
	     
		//System.out.println(findReceivingFault(7917226377L,startTime,endTime));
		System.out.println(minConnections());	
		//System.out.println("this number called these:" + called(5801555149l) + "\n Count:" + called(5801555149l).size());
		//System.out.println("this number been called by these" +callers(5801555149l)+"\n Count"+callers(5801555149l).size());	
		System.out.println("Used Memory   :  " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1020 * 1024) + " MB");

	}

}
		
