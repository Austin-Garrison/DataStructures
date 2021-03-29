import java.util.*;
 
public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{
 private Node<E> head = null;   // points to the head of the list
 private Node<E> tail = null;   //points to the tail of the list
 private int size = 0;    // the number of items in the list
  public void add(int index, E obj)
 {
   listIterator(index).add(obj);
 }
 public void addFirst(E obj) {
   add(0, obj);
   
 }
 public void addLast(E obj) {
   add(size(), obj);
 }
 
 public E get(int index)
 {   
   ListIterator<E> iter = listIterator(index);
   return iter.next();
 } 
 
 public E getFirst() { return head.data;  }
 public E getLast() { return tail.data;  }
 
 public int size() { 
   if (head == null)
     return 0;
 
   int count = 1;
   Node<E> current = head;
   while(current.next != null)
   {
     count++;
     current = current.next;
   }
   return count;
   }
 
 public E remove(int index)
 {     E returnValue = null;
       ListIterator<E> iter = listIterator(index);
       if (iter.hasNext())
       {   returnValue = iter.next();
           iter.remove();
       }
       else {   throw new IndexOutOfBoundsException();  }
       return returnValue;
 }
 
 public Iterator iterator() { return new ListIter(0);  }
 public ListIterator listIterator() { return new ListIter(0);  }
 public ListIterator<E> listIterator(int index){return new ListIter(index);}
 public ListIterator<E> listIterator(ListIterator<E> iter)
 {     return new ListIter( (ListIter) iter);  }
 
 private static class Node<E>
 {    
   private E data;
   private Node<E> next = null;
   private Node<E> prev = null;
 
   private Node(E dataItem)
   {  
     data = dataItem; 
   }
 }
 
 public class ListIter implements ListIterator<E>
 {
   private Node<E> nextItem;
   private Node<E> lastItemReturned;
   private int index = 0;
 
   public ListIter(int i)
   {  
     if (i < 0 || i > size)
     {    
       throw new IndexOutOfBoundsException("Invalid index " + i);
     }
    
     lastItemReturned = null;
       if (i == size)
       {    
         index = size;    
         nextItem = null;     
       }
       else
       {  
         nextItem = head;
         for (index = 0; index < i; index++) 
           nextItem = nextItem.next;  
       }
   }
 
   public ListIter(ListIter other)
   {   nextItem = other.nextItem;
       index = other.index;   
   }
 
   public boolean hasNext() {  
     return (nextItem != null);  
   }
 
   public boolean hasPrevious()
   {  
     return (lastItemReturned != null);  
   }
 
   public int previousIndex() { 
     return this.index;   
   }
  
   public int nextIndex() { 
     return index + 1;   
   }
  
   public void set(E o)  { }  // not implemented
   public void remove(){}      // not implemented
 
   public E next()
   { 
     if (hasNext())
     {
       lastItemReturned = nextItem;
       nextItem = nextItem.next;
       index++;
       return lastItemReturned.data;
     }
     throw new NoSuchElementException();
   }
 
   public E previous()
   { 
     if (hasPrevious())
     {
       nextItem = lastItemReturned;
       if(nextItem == head)
         lastItemReturned = null;
      
       else {
         Node<E> temp = head;
       for (int i = 0; i < index - 2; i++)
         temp = temp.next;
 
       lastItemReturned = temp;
       }
       index--;
       return nextItem.data;
     }
     throw new NoSuchElementException();
   }
 
   public void add(E obj) {
     Node<E> newNode = new Node<E>(obj);
 
       if (head == null) {
           head = newNode;
           tail = head; 
       } 
       
       else if (nextItem == head) {
           nextItem.prev = newNode;
           newNode.next = nextItem;
           head = newNode;    
       } 
       
       else if (nextItem == null) {
           tail.next = newNode;
           newNode.prev = tail;
           tail = newNode;
       } 
       
       else {
           newNode.next = nextItem;
           newNode.prev = nextItem.prev;
           nextItem.prev.next = newNode;
           nextItem.prev = newNode;
       }
 
       size++;
       index++;
       lastItemReturned = null;
   }
 }// end of inner class ListIter
}// end of class DoubleLinkedList
