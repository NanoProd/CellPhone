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
import java.util.NoSuchElementException;


public class CellList implements PubliclyCloneable {
private class CellNode implements PubliclyCloneable
    {
    //inner class attributes
    private CellPhone cell;
    private CellNode link;

    /*
        Avoid creating accessor methods as this could result in privacy leaks if a user could have access to the reference of one of our nodes
    */
    //inner class constructors
    public CellNode(){
        cell = null;
        link = null;
    }
    public CellNode(CellPhone cell, CellNode link){
        this.cell = cell;
        this.link = link;
    }
    /*
        The following code could have resulted in a pricacy leak if we had not used the copyOf() method, the implemented method avoids 
        that the copied item has the same reference as the new item and it is a private method so no outside user will ever have access to 
        the private references to the attributes of our class
    */
    @SuppressWarnings("unused")
	public CellNode(CellNode other){
        try
        {
            if(other == null)
                throw new NullPointerException();
        }catch(NullPointerException e){
            System.out.println(e.toString());
        }
        if(other.cell == null && other.link == null)
        {
            cell = null;
            link = null;
        }
        else 
        {
            cell =(CellPhone)other.clone();
            link = copyOf(other.link);
        }
    }
    //clone method
    /*
             The following code could have resulted in a pricacy leak if we had not used the copyOf() method, the implemented method avoids 
        that the copied item has the same reference as the new item and it is a private method so no outside user will ever have access to 
        the private references to the attributes of our class
    */
    public Object clone()
    {
        try
        {
            CellNode copy = (CellNode)super.clone();
            if(cell == null && link == null)
            {
                copy.cell = null;
                copy.link = null;
                return copy;
            }
            else
                copy = copyOf(copy);
            
            return copy;
        } catch(CloneNotSupportedException e)
        {
            return null;
        }
    }
    }//end of inner class

    //outer class attributes
    private CellNode head;
    @SuppressWarnings("unused")
	private int size;

   
   /** 
    * @return int
    */
   // Avoid creating accessor and mutator methods  as this could result in privacy leaks if a user could have access to the reference of one of our nodes
    //getter methods

    public int getSize()
    {
        return size();
    }

    //constructors
    public CellList()
    {
        head = null;
        size = 0;
    }
    
    /** 
     * @return CellList
     */
    //outer class public clone() method
    /*
             The following code could have resulted in a pricacy leak if we had not used the copyOf() method, the implemented method avoids 
        that the copied item has the same reference as the new item and it is a private method so no outside user will ever have access to 
        the private references to the attributes of our class
    */
    public CellList clone()
    {
        try
        {
            CellList copy = (CellList)super.clone();
            if (head == null)
                copy.head = null;
            else
                copy.head = copyOf(head);
            return copy;
        } catch(CloneNotSupportedException e)
        {
            return null;
        }
    }
    
    /** 
     * @param otherHead
     * @return CellNode
     */
    //copyOf method --> returns a true deep copy of a CellNode --> must implement a publicly cloneable interface with public clone() method
    /*
       this private method avoids any privacy leaks while handling private instance variables of our class 
    */
    private CellNode copyOf(CellNode otherHead)
    {
            if(otherHead == null)
            {
                CellNode empty = new CellNode();
                return empty;
            }

            CellNode position = otherHead; //moves down other's list
            CellNode newHead = null; //will point to head of the copy list
            CellNode end = null; //positioned at end of new growing list
                //create first node
                newHead = new CellNode((CellPhone)(position.cell).clone(), null);
                end = newHead;
                position = position.link;
                while(position != null)
                {
                    //copy node at position to end of new list
                    end.link = new CellNode((CellPhone)(position.cell).clone(), null);
                    end = end.link;
                    position = position.link;
                }
            return newHead;
    }
    //deep copy constructor uses copyOf method
    /*
        The following code could have resulted in a pricacy leak if we had not used the copyOf() method, the implemented method avoids 
        that the copied item has the same reference as the new item and it is a private method so no outside user will ever have access to 
        the private references to the attributes of our class
    */
    public CellList(CellList object)
    {
        try
        {
            if(object == null)
                throw new NullPointerException();
        } catch(NullPointerException e){
            System.out.println(e.toString());
        }
       if(object.head == null)
           head = null;
        else    
            head = copyOf(object.head);
    }

    
    /** 
     * @param data
     */
    //add to start method
    public void addToStart(CellPhone data)
    {
        head = new CellNode(data, head);
    }

    
    /** 
     * @param position
     * @param data
     */
    //insert at index method 
    public void insertAtIndex(int position, CellPhone data)
    {
        //check if position is valid
        try
        {
        if(position < 0)
            throw new NoSuchElementException();
        if(position > size() - 1)
            throw new NoSuchElementException();
        } 
        catch(NoSuchElementException e)
        {
            System.out.println(e.toString());
            System.exit(0);
        }
        
        CellNode node = new CellNode(data, null);
        if(head == null)
        {
            head = node;
            return;
        }
        if( position == 0)
        {
            node.link = head;
            head = node;
            return;
        }
        CellNode previous = null;
        CellNode current = head;
        int count = 0;
        while(current != null && count < position)
        {
            previous = current;
            current = current.link;
            count++;
        }
        node.link = previous.link;
        previous.link = node;
    }

    
    /** 
     * @param position
     */
    //delete from index method
    public void deleteFromIndex(int position)
    {
         //check if position is valid
         try
         {
         if(position < 0)
             throw new NoSuchElementException();
         if(position > size() - 1)
             throw new NoSuchElementException();
        if(head == null)
             throw new NoSuchElementException();
         } 
         catch(NoSuchElementException e)
         {
             System.out.println(e.toString());
             System.exit(0);
         }
        if(position == 0)
        {
           head = head.link;
           return;
        }
        
        //find previous node of the node to be deleted
        CellNode temp = head;
        for(int i = 0; temp!=null && i<position-1; i++)
            temp = temp.link;
        CellNode next = temp.link.link;

        temp.link = next;
    }


    
    /** 
     * @return boolean
     */
    //delete from start
    public boolean deleteFromStart()
    {
        if(head != null)
        {
            head = head.link;
            return true;
        }
        else
        {
            return false;
        }
    }

    
    /** 
     * @param position
     * @param data
     */
    //replace at index method
    public void replaceAtIndex(int position, CellPhone data)
    {
        //verify that index is legit
        if(position < 0)
            return;
        if(position > size() -1)
            return;
        //find previous node of position
        CellNode current = head;
        for(int i = 0; i < position-1; i++)
            current = current.link;
        CellNode temp = new CellNode(data, current.link.link);
        current.link = temp;

    }

    
    /** 
     * @return int
     */
    //get size of cellList 
    public int size()
    {
        int count = 0;
        CellNode position = head;
        while(position != null)
        {
            count++;
            position = position.link;
        }
        return count;
    }

    
    /** 
     * @param serialNumber
     * @return int
     */
    //contains method --> uses find method
    public int contains(long serialNumber)
    {
        return find(serialNumber);
    }

    
    /** 
     * @param serialNumber
     * @return int
     */
    //private find method
    private int find(long serialNumber)
    {
        int count = 0;
        CellNode position = head;
        CellPhone itemAtPosition;
        while(position != null)
        {
            count++;
            itemAtPosition = position.cell;
            if((itemAtPosition.getSerialNum() == serialNumber))
                return count;
            position = position.link;
        }
        return -1;
    }

    //show contents 
    public void showContents()
    {
        System.out.println("The current size of the list is " + this.size() + ". Here are the contents of the list");
        System.out.println("=======================================================================================");
        CellNode position = head;
        int count = 0;
        while(position != null)
        {
            if(count % 3 == 0)
                System.out.println();
            System.out.print("[" + position.cell + "] --->  ");
            position = position.link;
            count++;
        }
        System.out.print(" X\n");
    }

    
    /** 
     * @param other
     * @return boolean
     */
    //equals method
    public boolean equals(Object other)
    {
        if(other == null)
            return false;
        else
        {
            CellList otherList = (CellList)other;
            if(size() != otherList.size())
                return false;
            CellNode position = head;
            CellNode otherPosition = otherList.head;
            while(position != null)
            {
                if(!(position.cell.equals(otherPosition.cell)))
                    return false;
                position = position.link;
                otherPosition = otherPosition.link;
            }
            return true; //no mismatch found
        }
    }


}