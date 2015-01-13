/**
*Noah Abdelguerfi
*9/22/14
*Fall 2014 : CSCI 2125
*Homework 2 singlyLinkedList
**/
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<DataType> implements Iterable {
    
    private Node<DataType> head = null;
    private Node<DataType> tail = null;
      //create a node
    public static class Node<DataType>{

        private DataType data;              
        private Node<DataType> next;

      public Node(DataType data){
         this.data = data;
      }// end of node 
   }//end of class node
    

    //add a element to the end of the linked list
    public void add(DataType data){
        Node<DataType> n = new Node(data);
        if (this.head == null && this.tail == null){
            this.head = n;
            tail = head;
        }// end of if 
        else{
            this.tail.next = n;
        }// end of else
        this.tail = n;
    }// end of add
    
    // remove the node after the one passed as a parameter
    public void remove(Node<DataType> prev){
         
        if(this.tail == null || prev.next == null){
           throw new IllegalStateException("Cannot remove the tail."); 
        }// end of if
        else{
            prev.next = prev.next.next;
            tail = prev;          
        }//end of else
            
    }// end of remove
    
    //This method returns the nth to last element of a singly linked list.
    //If n is larger than the list size, the method returns null.   
    // gets the nth to last value
    public DataType getNthToLast(int n){
        Node<DataType> ahead = head;
        Node<DataType> NthToLast = head;
        //ahead is moved forward by n positions
        for( int i = 1; i < n; i++){
            
            if( head.next != null ){
               ahead = ahead.next; 
            }//end if
            else{
                return null;            //value of n is larger than list size
            }//end else
            
        }// end getNthToLast    
        //Both the ahead and the NthToLast are moved forward until ahead reaches the tail.     
        while(ahead.next != null ){
            
             ahead = ahead.next;
             NthToLast = NthToLast.next;
        }// end while
        //When ahead reaches the tail, the method returns NthToLast
        return NthToLast.data;
        
    }// end getNthToLast
   
    //clear the list
    public void clear(){   
        this.head = null;
        this.tail = null;        
    }// end of clear
    
    // returns SinglyLinkedListIterator
    @Override
    public SinglyLinkedListIterator iterator() {
        return new SinglyLinkedListIterator();
    }// end of SinglyLinkedLitIterator

    public class SinglyLinkedListIterator implements Iterator<DataType> {
        
        Node<DataType> last = null;
        Node<DataType> prev;
        Node<DataType> current = SinglyLinkedList.this.head;
              
        //returns false if list does not have next
        @Override
        public boolean hasNext() {
            
           if(head != null && last != null && last.next != null){
               return true;
           }else{
               return false;
           }
                  
        }// end of hasNext
        
        
        //returns the next element in the list
        @Override
        public DataType next()throws NoSuchElementException {
           
             if (this.current == null){
               throw new java.util.NoSuchElementException();
           }// end of if
           else {
                       
               Node<DataType> old = this.current;              
               prev = current;
               this.current = this.current.next;              
                return old.data; 
           }// end of else 
           
        }// end of next
        
        //calls remove method from SinglyLinkedList
        @Override
        public void remove(){
            SinglyLinkedList.this.remove(prev);
           
        }//end of remove
        // adds data to the list using the iterator
        public void add(DataType type) throws NoSuchElementException{
           
            if (this.last == null && SinglyLinkedList.this.head == null ){
                SinglyLinkedList.this.add(type);
                last = SinglyLinkedList.this.head;
            }//end of if
            else if(last != null && SinglyLinkedList.this.head != null){
                 Node<DataType> n = new Node(type);
              
                 tail.next = n;
                 tail = tail.next;
                 
            }//end of else
            else{
                   throw new NoSuchElementException("No such element");
            }
            
           
        }//end of add

    }// end of inner class SinglyLinkedListIterator 
    
   public static void main(String [] args){
  
        SinglyLinkedList<Integer> list = new SinglyLinkedList();
        
     SinglyLinkedList.SinglyLinkedListIterator it = list.iterator();
         //tests iterator add 
          it.add(3); 
          it.add(4);             
          it.add(6);
          it.add(1);
          it.add(2);
          it.add(8);
          it.add(9);
          it.add(10);
                 
      System.out.println("////////////////////////////////////////////////////////////////////////////////");
      System.out.println("SinglyLinkedList add:");
       //add to list
       //tests list add
        list.add(2);
        list.add(4);      
        list.add(6);
        list.add(1); // list 2,4,6,1,8,9,3,5
        list.add(8);
        list.add(9);
        list.add(3);
        list.add(5);
      
        int number = 0;
        Node<Integer> temp = list.head;
        while(temp != null){           
            if(number == 8 ){
                System.out.println("////////////////////////////////////////////////////////////////////////////////");
                System.out.println("SinglyLinkedListIterator add:");
            }
            System.out.println(temp.data);
            temp = temp.next;
            number ++;
        }  
        // tests getNthToLast
        System.out.println("The 3rd to last is: " + list.getNthToLast(3) );
	System.out.println("The 4th to last is: " + list.getNthToLast(4) );
        System.out.println("////////////////////////////////////////////////////////////////////////////////");
    
     
    }// end of main
    
}// end of SinglyLinkedList class
