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

import java.lang.CloneNotSupportedException;
//import java.util.Scanner;

public class CellPhone implements PubliclyCloneable{
    //attributes    
        private long serialNum;
        private String brand;
        private int year;
        private double price;

    
    /** 
     * @param serialNum
     */
    //setters
        public void setSerialNum(long serialNum){
            this.serialNum = serialNum;
        }
        
        /** 
         * @param brand
         */
        public void setBrand(String brand){
            this.brand = brand;
        }
        
        /** 
         * @param year
         */
        public void setYear(int year){
            this.year = year;
        }
        
        /** 
         * @param price
         */
        public void setPrice(double price){
            this.price = price;
        }

    
    /** 
     * @return long
     */
    //getters
        public long getSerialNum(){
            return serialNum;
        }
        
        /** 
         * @return String
         */
        public String getBrand(){
            return brand;
        }
        
        /** 
         * @return int
         */
        public int getYear(){
            return year;
        }
        
        /** 
         * @return double
         */
        public double getPrice(){
            return price;
        }

    //constructors
        public CellPhone(){
            //this constructor should never be used because of the serial number rule
            serialNum = 0;
            brand = "";
            year = 0;
            price = 0.0;
        }
        public CellPhone(long serialNum, String brand, int year, double price){
            //it is assumed that user will input unique serial number
            this.serialNum = serialNum;
            //it is assumed that brand will only be one word
            this.brand = brand;
            this.year = year;
            this.price = price;
        }
        //deep copy
        public CellPhone(CellPhone object, long serialNum){
            //user will have to put in new serial number to make it unique
            this(serialNum, object.getBrand(),object.getYear(),object.getPrice());
        }
        
        /** 
         * @return CellPhone
         */
        //user has to change serial number of object after creating a clone.
        public CellPhone clone()
        {
          //  Scanner keyboard = new Scanner(System.in);
          CellPhone copy = null;
            try
            {
                copy = (CellPhone)super.clone();
              //  long temp = keyboard.nextLong();
              //  copy.setSerialNum(temp);
              //keyboard.close();
                return copy;
            } catch(CloneNotSupportedException e){
                return new CellPhone(this.getSerialNum(), this.getBrand(), this.getYear(),this.getPrice());
            }
        }
        
        /** 
         * @return String
         */
        //clone method with serial number included
        /*
        public Object clone(long serialNum) throws CloneNotSupportedException{
            CellPhone cloned = (CellPhone)super.clone();
            cloned.setSerialNum(serialNum);
            return cloned;
        }
        */
        public String toString(){
            return this.getSerialNum() + ": " + this.getBrand() + " " + this.getPrice() + "$ " + this.getYear();
        }
        
        /** 
         * @param object
         * @return boolean
         */
        public boolean equals(CellPhone object){
            return (this.getBrand() == object.getBrand()) && (this.getPrice() == object.getPrice()) && (this.getYear() == object.getYear());
        }

}   
