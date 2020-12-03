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
        /**
         * serial number of cellphone object
         */ 
        private long serialNum;
        /**
         * brand of cellphone
         */
        private String brand;
        /**
         * year of cell phone
         */
        private int year;
        /**
         * price of phone
         */
        private double price;

    
    /** 
     * sets serial number
     * @param serialNum long for new serial number
     */
        protected void setSerialNum(long serialNum){
            this.serialNum = serialNum;
        }
        
        /** 
         * sets brand of phone
         * @param brand String new brand name
         */
        public void setBrand(String brand){
            this.brand = brand;
        }
        
        /** 
         * sets year attribute
         * @param year int for new year
         */
        public void setYear(int year){
            this.year = year;
        }
        
        /** 
         * sets price of phone
         * @param price double price
         */
        public void setPrice(double price){
            this.price = price;
        }

    
    /** 
     * returns serial number of cellPhone object
     * @return long
     */
        protected long getSerialNum(){
            return serialNum;
        }
        
        /** 
         * gets brand
         * @return String
         */
        public String getBrand(){
            return brand;
        }
        
        /** 
         * gets year
         * @return int
         */
        public int getYear(){
            return year;
        }
        
        /** 
         * gets price
         * @return double
         */
        public double getPrice(){
            return price;
        }

        /**
         * default constructor sets everything to the 0 value
         */
        public CellPhone()
        {
            //this constructor should never be used because of the serial number rule
            serialNum = 0;
            brand = "";
            year = 0;
            price = 0.0;
        }
        /**
         * parametrized constructor
         * @param serialNum long for serial number
         * @param brand String for brand name
         * @param year int for year
         * @param price double for price
         */
        public CellPhone(long serialNum, String brand, int year, double price){
            //it is assumed that user will input unique serial number
            this.serialNum = serialNum;
            //it is assumed that brand will only be one word
            this.brand = brand;
            this.year = year;
            this.price = price;
        }
        //deep copy
        /**
         * copy constructor that makes a true deep copy
         * @param object CellPhone object being copied
         * @param serialNum long new serial number
         */
        public CellPhone(CellPhone object, long serialNum){
            //user will have to put in new serial number to make it unique
            this(serialNum, object.getBrand(),object.getYear(),object.getPrice());
        }
        
        /** 
         * clone method returns a deep copy
         * user has to change serial number of object after creating a clone.
         * @return CellPhone
         */
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
        
        
        //clone method with serial number included
        /*
        public Object clone(long serialNum) throws CloneNotSupportedException{
            CellPhone cloned = (CellPhone)super.clone();
            cloned.setSerialNum(serialNum);
            return cloned;
        }
        */
        /**
         * to string method 
         * @return String
         */
        public String toString(){
            return this.getSerialNum() + ": " + this.getBrand() + " " + this.getPrice() + "$ " + this.getYear();
        }
        
        /** 
         * equals method that compares every attribute except serial number
         * @param object CellPhone object being compared with
         * @return boolean
         */
        public boolean equals(CellPhone object){
            return (this.getBrand() == object.getBrand()) && (this.getPrice() == object.getPrice()) && (this.getYear() == object.getYear());
        }

}   
