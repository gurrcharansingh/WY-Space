package com.wy.space;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Scanner;  

public class WyspaceMain {

	public static void main(String[] args) {
		 
			  int groundBandwidth=0;
			  String bandwidth;
			  //takes user input to provide ground station bandwidth - validations added for input
		        Scanner sc = new Scanner(System.in);
		        while(true) {
		        System.out.print("Please enter ground station bandwidth : ");  
                bandwidth = sc.nextLine().trim(); 
                if (bandwidth.isEmpty()) { 
                    System.out.println("Invalid input");
                    continue;
                }
	    	    boolean isnum = bandwidth.matches("-?\\d+(\\.\\d+)?"); 
                if (isnum) { // if Its a Number then we pass it in
                	groundBandwidth = Integer.parseInt(bandwidth);
                	break;
                } else {
                   // we pass exception message to catch
                    System.out.println("Invalid Input: Numeric values only please");
                    continue;
                }
	          }
		        
                System.out.println();
              
                //import the text file from src containing the satellites schedule
                try {
			    Path rootDir = Paths.get(".").normalize().toAbsolutePath();
			    File file = new File(rootDir.toString() + "/src/"+"pass-schedule.txt");
			    List<Satellite> satList = new ArrayList<Satellite>();
		        BufferedReader in = new BufferedReader(new FileReader(file));
		        
		        //Parse the imported satellite schedules and populate in Satellites list
		        String satDetail;		        
		        while ((satDetail = in.readLine())!= null) {
		           Satellite satellite=new Satellite();
		           String[] param=satDetail.split(",");
		           satellite.setName(param[0]);
		           satellite.setBandwidth(Integer.valueOf(param[1]));		           
		           satellite.setStartTime(LocalTime.parse(param[2]));
		           satellite.setEndTime(LocalTime.parse(param[3]));
		           satList.add(satellite);
		        }
		        in.close();
		        
		        calculate(satList, groundBandwidth);
		         
		    } catch (Exception e) {
		        System.out.println("File Read Error");
		    }

	}
	
	public static void calculate(List<Satellite> list, int groundBandwidth) {
		int totalbandwidth = 0;
		boolean support=false;
		List<LocalTime> periodList = new ArrayList<LocalTime>();
		
		// group the start time of all passes and count the times each start-time is recurring
        Map<LocalTime, Long> countDetailMap= list.stream().collect(Collectors.groupingBy(Satellite::getStartTime,Collectors.counting()));	
        Long maxValue=(Collections.max(countDetailMap.values())); //get max value from the counts
        
        //collect the start time of pass with maximum counts in list
		  for (Entry<LocalTime, Long> entry : countDetailMap.entrySet()) { 
	            if (entry.getValue()==maxValue) {
	            	periodList.add(entry.getKey());
	            }
	        }
		  
		  System.out.println("******************Results********************");
		  System.out.println();
		  
		  //print the time period at which total downlink will be at its maximum
		  System.out.print("Time at which total downlink will be at its maximum is: ");
		  periodList.forEach(k->{
          System.out.print(k+" to "+k.plusMinutes(30)+" ") ;	  
		  });
		  System.out.println();
		  
          //calculate the total bandwidth of passes with max counts contained in our list
		  for (final Satellite item : list) {
			  if(periodList.contains(item.getStartTime()))
				  totalbandwidth += item.getBandwidth();
		  }	  
		 	
		  //check whether Ground station bandwidth supports total bandwidth of satellite passes.
		  if(groundBandwidth==totalbandwidth) {
			  support=true; 
		  }else if(groundBandwidth<totalbandwidth) {
			  support=false;  
		  }else if(groundBandwidth>totalbandwidth) {
			  support=true; 
		  }
			
		  
		  System.out.println("Ground station provided bandwidth: "+ groundBandwidth);
		  System.out.println("Total bandwidth required : "+ totalbandwidth);
		  System.out.println("Ground station has the bandwidth to support this: "+(support?"YES":"NO"));
	}
}
