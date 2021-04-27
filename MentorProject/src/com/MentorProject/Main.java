package com.MentorProject;


public class Main extends ReusableMethods  {

	
	public static void main(String[] args) {
		
		try {
			// Invoking browser
			//HELLUU HERE IS THE CHANGE
			
			ReusableMethods.setDriver();
			test();
			//closing drivers
			tearDownAfterClass();
			
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
