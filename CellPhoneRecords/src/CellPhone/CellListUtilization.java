//-------------------------------------------------
//Written by: Joshua-James Nantel-Ouimet (#40131733)
//            Samaninder Singh (#40133493)
//COMP 249
//Assignment 4
//Due date: december 4th 2020
//-------------------------------------------------
/**
 * @author Joshua-James Nantel-Ouimet
 * @author Samaninder Singh 
 * @version 1.0
 */
package CellPhone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

import java.util.Scanner;

public class CellListUtilization {
    
    /** 
     * main method
     * @param args[] String main method argument
     */
    public static void main(String args[]){
        CellList one = new CellList();
        CellList two = new CellList();

        //read the file
        BufferedReader inputStream;
        try 
        {
            inputStream = new BufferedReader(new FileReader("Cell_Info.txt"));
            String line;
            boolean valid = true;
            while(valid)
            {
                line = inputStream.readLine();
                if(line == null)
                    break;
                if(line.length() == 0)
                    continue;
                String[] words = line.split("\\s+");
                long serialNum = Long.parseLong(words[0]);
                String brand = words[1];
                int year = Integer.parseInt(words[3]);
                double price = Double.parseDouble(words[2]);
                CellPhone temp = new CellPhone(serialNum, brand, year, price);
                if(one.contains(temp.getSerialNum()) >= 0)
                    continue;
                else 
                    one.addToStart(temp);
            }
        }catch(FileNotFoundException e){
            System.out.println(e.toString());
        }catch(IOException e){
            System.out.println(e.toString());
        }
        //show the contents of the list
        one.showContents();

        //prompt user to enter a few serial numbers and search for them inside the list
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nPlease enter three serial numbers (on three seperate lines):");
        long first = 0;
        long second = 0;
        long third = 0;
        try
        {
             first = keyboard.nextLong();
             second = keyboard.nextLong();
             third = keyboard.nextLong();
        }catch(InputMismatchException e){
            System.out.println(e.toString());
        }
        //search for serial numbers in list
        
        int indexFirst = one.contains(first);
        int indexSecond = one.contains(second);
        int indexThird = one.contains(third);
        if(indexFirst < 0)
            System.out.println("Cell list does not contain first serial number");
        if(indexFirst >= 0) 
            System.out.println("Cell list contains first serial number at position " + (indexFirst));
        if(indexSecond < 0)
            System.out.println("Cell list does not contain second serial number");
        if(indexSecond >= 0) 
            System.out.println("Cell list contains second serial number at position " + (indexSecond));
        if(indexThird < 0)
            System.out.println("Cell list does not contain third serial number");
        if(indexThird >= 0) 
            System.out.println("Cell list contains third serial number at position " + (indexThird));
        
        //test out every method for cellphone
        CellPhone test1 = new CellPhone(112312311,"test", 2000, 214.00);
        CellPhone test2 = new CellPhone(113131314, "alsoTest", 2114, 124124.00);
        CellPhone test3 = new CellPhone(test1, 213412312);
        CellPhone test4 = test2.clone();

        System.out.println(test1.equals(test3)); // expect true
        System.out.println(test2.equals(test4)); // expect true
        System.out.println(test1.equals(test4));//expect false
        System.out.println(test2.equals(test3)); //expect false

        System.out.println(test1.toString());

        //test out every cellList method 
        CellList three = one.clone(); //clone method
        System.out.println(three.equals(one)); // expect true
        System.out.println(three.equals(two)); // expect false

        CellList four = new CellList(two); //copy constructor
        System.out.println(four.equals(two)); //expect true

        //test out insert, delete, replace  methods
        //original
        System.out.println("\nOriginal cell list");
        one.showContents();
        System.out.println();

        //insert
        System.out.println("\nnew cellphone at index 3");
        one.insertAtIndex(3, test1);
        System.out.println();
        one.showContents();

        //delete
        System.out.println("\ndeleted cellphone at index 3");
        one.deleteFromIndex(3);
        one.showContents();

        //replace
        System.out.println("\nSwitched out cellphone at index 2 with new one");
        one.replaceAtIndex(1, test2);
        one.showContents();

        //delete from start
        System.out.println("\nDeleted first cellphone");
        one.deleteFromStart();
        one.showContents();

        
        //close keyboard
        keyboard.close();
    }
}
