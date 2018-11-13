package au.edu.uq.itee.comp3506.assn2.tests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import au.edu.uq.itee.comp3506.assn2.api.TestAPI;
import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;
import au.edu.uq.itee.comp3506.assn2.entities.loadRecords;

/**
 * Hook class used by automated testing tool.
 * The testing tool will instantiate an object of this class to test the functionality of your assignment.
 * You must implement the method and constructor stubs below so that they call the necessary code in your application.
 * 
 * @author xu guo
 */
public final class AutoTester implements TestAPI {
	// TODO Provide any data members required for the methods below to work correctly with your application.

	public AutoTester() {
		// TODO Create and initialise any objects required by the methods below.
		
	}
	/**
	 * Tests search 1 from the assignment specification.
	 * Run time efficiency constant O(n); it go through a loop of List<CallRecord>
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @return List of all the phone numbers called by dialler.
	 *         The list will contain duplicates of the receiver if the dialler called the receiver multiple times.
	 */
	@Override
	public List<Long> called(long dialler) {
		
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
	 * Run time efficiency worst case O(n) if it go through the end of loop of List<CallRecord>
	 * @param dialler The phone number that initiated the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of all the phone numbers called by dialler between start and end time.
	 *         The list will contain duplicates of the receiver if the dialler called the receiver multiple times.
	 */

	@Override
	public List<Long> called(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
	
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
	 * Run time efficiency worst case O(n) if it go through the end of loop of List<CallRecord>
	 * @param receiver The phone number that received the calls.
	 * @return List of all the phone numbers that called the receiver.
	 *         The list will contain duplicates of the caller if they called the receiver multiple times.
	 */
	@Override
	public List<Long> callers(long receiver) {
		
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
	 * Run time efficiency worst case O(n) if it go through the end of loop of List<CallRecord>
	 * @param receiver The phone number that received the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of all the phone numbers that called the receiver between start and end time.
	 *         The list will contain duplicates of the caller if they called the receiver multiple times.
	 */
	@Override
	public List<Long> callers(long receiver, LocalDateTime startTime, LocalDateTime endTime) {
		
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
	 * Run time efficiency constant O(n). Memory usage is 29mb
	 * it has to go through the end of loop 
	 * @param dialler The phone number that initiated the calls.
	 * @return The list of identifiers of the faulty switches or an empty list if no fault was found.
	 */
	@Override
	public List<Integer> findConnectionFault(long dialler) {
		
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
	 * Run time efficiency constant O(n). Memory usage is 29mb
	 * it has to go through the end of loop
	 * @param dialler The phone number that initiated the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The list of identifiers of the faulty switches;
	 *         or an empty list if no fault was found between start and end time.
	 */
	@Override
	public List<Integer> findConnectionFault(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
			if (ca.getDialler() == dialler&& (ca.getTimeStamp().isAfter(startTime) && ca.getTimeStamp().isBefore(endTime)
					||ca.getTimeStamp().isEqual(startTime)||ca.getTimeStamp().isEqual(endTime))) 
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
	 * Run time efficiency constant O(n). it has to go through the end of loop
	 * @param reciever The phone number that should have received the calls.
	 * @return The list of identifiers of the faulty switches or an empty list if no fault was found.
	 */
	@Override
	public List<Integer> findReceivingFault(long receiver) {
		
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
					//System.out.println(ca);	
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
	 * Run time efficiency constant O(n). it has to go through the end of loop
	 * @param reciever The phone number that should have received the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The list of identifiers of the faulty switches;
	 *         or an empty list if no fault was found between start and end time.
	 */
	@Override
	public List<Integer> findReceivingFault(long receiver, LocalDateTime startTime, LocalDateTime endTime) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < records.size(); i++) 
		{
			CallRecord ca = records.get(i);
			
			if (ca.getReceiver() == receiver&& (ca.getTimeStamp().isAfter(startTime) && ca.getTimeStamp().isBefore(endTime)
					||ca.getTimeStamp().isEqual(startTime)||ca.getTimeStamp().isEqual(endTime))) {
				if(ca.getConnectionPath().isEmpty()) 
				{
					result.add(ca.getDiallerSwitch());
					//System.out.println(ca);						
				}
			
				if(!ca.getConnectionPath().isEmpty()) 
				{
				
					int firstSwitch = ca.getConnectionPath().get(0);
				    int lastSwitch = ca.getConnectionPath().get(ca.getConnectionPath().size() - 1);				
				
				if (firstSwitch != ca.getDiallerSwitch()) 
				{
					result.add(firstSwitch);
				  //System.out.println(firstSwitch);
				 }
				
				if (lastSwitch != ca.getReceiverSwitch()){
					result.add(lastSwitch);
				// System.out.println("last: " +lastSwitch);
				// System.out.println("recev " +ca.getReceiverSwitch());
			 }
			}
	     }		
	}
		return result;		
}
	/**
	 * Tests search 4 from the assignment specification.
	 * Run time efficiency constant O(n)+ O(NlogN) = O(NlogN); Memory usage is 29mb
	 * it go through the end of loop of List<CallRecord> and sorting and a loop of List of ConnectionPath
	 * @return The identifier of the switch that had the most connections.
	 *         If multiple switches have the most connections, the smallest switch identifier is returned.
	 */
	@Override
	public int maxConnections() {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> s = new ArrayList<>();
		for(int i=0;i<records.size();i++)
		{
			CallRecord ca = records.get(i);
			if(!ca.getConnectionPath().isEmpty())
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
	 * Run time efficiency constant O(n)+ O(NlogN) = O(NlogN) ;Memory usage is 29mb
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The identifier of the switch that had the most connections between start and end time.
	 *         If multiple switches have the most connections, the smallest switch identifier is returned.
	 */
	@Override
	public int maxConnections(LocalDateTime startTime, LocalDateTime endTime) {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> s = new ArrayList<>();
		
		for(int i=0;i<records.size();i++)
		{
			CallRecord ca = records.get(i);
			if(!ca.getConnectionPath().isEmpty()&&ca.getTimeStamp().isBefore(endTime)&&ca.getTimeStamp().isAfter(startTime)
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
	 * Run time efficiency worst case O(n2) if it go through the end of Nested Loop
	 * Memory usage is 31mb
	 * @return The identifier of the switch that had the fewest connections.
	 *         If multiple switches have the fewest connections, the smallest switch identifier is returned.
	 */
	@Override
	public int minConnections() {
		
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> s = new ArrayList<>();
		int result=0;
		
		for(int i=0;i<records.size();i++)
		{
			CallRecord ca = records.get(i);
			if(!ca.getConnectionPath().isEmpty())
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
	 *Run time efficiency worst case O(n2) if it go through the end of Nested Loop
	 *Memory usage is 29mb
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The identifier of the switch that had the fewest connections between start and end time.
	 *         If multiple switches have the fewest connections, the smallest switch identifier is returned.
	 */
	@Override
	public int minConnections(LocalDateTime startTime, LocalDateTime endTime) {
	
		List<CallRecord> records = loadRecords.loadRecords();
		List<Integer> s = new ArrayList<>();
		
		for(int i=0;i<records.size();i++)
		{
			CallRecord ca = records.get(i);
			if(!ca.getConnectionPath().isEmpty()&&ca.getTimeStamp().isBefore(endTime)&&ca.getTimeStamp().isAfter(startTime)
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
	 * Run time efficiency worst case O(n). Memory usage is 11mb
	 * it has to go through entire loop.
	 * As List is not sorted by time
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of details of all calls made between start and end time.
	 */
	public List<CallRecord> callsMade(LocalDateTime startTime, LocalDateTime endTime) {
		
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
	
	public static void main(String[] args) {
		AutoTester test = new AutoTester();
		
	       
		System.out.println("AutoTester Stub");
	}
}