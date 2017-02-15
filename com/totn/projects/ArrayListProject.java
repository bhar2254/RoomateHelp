package com.totn.projects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
	This program rotates elements in ArrayList to the left
	Written by Christian Thomas and Blaine Harper
**/

public class ArrayListProject
{
	private static Scanner in;
	private static boolean debug = false, isRunning = true;
	private static ArrayList<Double> array = new ArrayList<Double>();	
	
	public static void main(String[] args)
	{
		ArrayList<Double> values = array;
		
//		Methods that will always run
		inputValues(values);
		checkForMore(values);

//		Methods that the use will select
		while(isRunning)
		{
			menu(values);
		}
		
//		Final text and output
		System.out.println("Thanks for using this program!");		
		System.out.println("Final ArrayList: " + values);
	}

	private static void menu(ArrayList<Double> values) 
	{
		System.out.println("What would you like to do with your array?");
		System.out.println("1. Add more values.");
		System.out.println("2. Rotate to the left.");
		System.out.println("3. Remove negative numbers.");
		System.out.println("4. Sort numerically.");
		System.out.println("5. Check for repitions.");
		System.out.println("6. Quit.");

		in = new Scanner(System.in);
		int userSelection = in.nextInt();
		if(userSelection == 1)
			inputValues(values);
		if(userSelection == 2)
			rotatingLeft(values);
		if(userSelection == 3)
			removeNegatives(values);
		if(userSelection == 4)
			sort(values);
		if(userSelection == 5)
			checkForRepeats(values);
		if(userSelection == 6)
			isRunning = false;
		else
			System.out.println("Current ArrayList: " + values);
	}

	private static void inputValues(ArrayList<Double> values)
	{
//		Waits for the user to input values for the array or to issue the escape command
		System.out.println("Please insert values, insert Q to quit");
		in = new Scanner(System.in);
		
		while(in.hasNextDouble())
			values.add(in.nextDouble());
	}

	private static void checkForMore(ArrayList<Double> values)
	{
//		Setup booleans to use
		boolean responded = false, firstRun = true;
		String response = null;
		
		while(!responded)
		{
//			If the user has already ran this method the output with be different
//			Based on the user's response last time they ran the method
			if(firstRun)
				System.out.println("Would you like to input any more values? Y/N?");
			else
				System.out.println(response + " is not a valid response. Try Y/N?");
			
//			Open a Scanner to check for the user's Y/N (yes or no) response.
			firstRun = false;
			
			response = new Scanner(System.in).nextLine();
			debug("RESPONSE: " + response);
			
//			If the response begins with Y then the user answered truthfully
			if(response.toLowerCase().startsWith("y")
					|| response.toLowerCase().startsWith("n"))
				responded = true;
		}
		
//		If the user says yes then they can input more values into the ArrayList	
		if(response.toLowerCase().startsWith("y"))
			inputValues(values);
	}
	
	private static void checkForRepeats(ArrayList<Double> values) 
	{
		int repeats = 1, highestRepeat = 0;
		Double mode = null;
		
		for(int i = 1; i < values.size(); i++)
		{
			debug("Checking " + values.get(i) + " and " + values.get(i-1));
			if(values.get(i).intValue() == values.get(i-1).intValue())
			{
				debug("Found ONE!");
				repeats++;
				if(repeats > highestRepeat)
				{
					mode = values.get(i);
					highestRepeat = repeats;
				}
			} else {
				debug("Not it");
				repeats = 1;
			}
		}
		if(mode!=null)
			System.out.println("Mode: " + mode + " | Repeats: " + highestRepeat + " times.");
		else
			System.out.println("No Consecutive Repitition");
	}
	
	private static void removeNegatives(ArrayList<Double> values)
	{
//		Deletes all values which are less than zero (negative)
		debug("Removing Negatives...");
		
		for(int i = 0; i < values.size(); i++)
		{
			if(values.get(i) < 0)
			{
				debug("Removing value: " + i + " from VALUES");
				values.remove(i);
				i-=1;
			}
		}
	}
	
	private static void rotatingLeft(ArrayList<Double> values)
	{
//		Moves all of the values to the left once
		debug("Moving VALUES to the left...");
		
		double starting = values.get(0);
		values.add(starting);
		values.remove(0);
	}
	
	private static void sort(ArrayList<Double> values)
	{
//		A very simple method to sort all of the values in values
		debug("Sorting...");
		Collections.sort(values);
	}
	
	private static void debug(String text)
	{
//		My favorite debugging method that only outputs text when the debug
//		boolean is true. 
//		(Note: debug boolean is at the beginning of this Class file
		if(debug)
			System.out.println(text);
	}
}