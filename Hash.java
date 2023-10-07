package Basic;

import java.util.ArrayList;
import java.util.Scanner;
public class Hash{
	
	//------------------------------Entry Class------------------------------------
	
	private static class Entry 
	{
	    String key;
	    ArrayList<String> values;
	    Entry next;
	    public Entry(String key, ArrayList<String> values)
	    {
	      this.key = key;
	      this.values = values;
	      this.next = null;
	    }
	  }
  private Entry[] table;
  private int size;

  //------------------------------Initializing Size Constructor---------------------
  
  public Hash(int size) 
  {
    this.table = new Entry[size];
    this.size = size;
  }
  
  //------------------------------Hash Code----------------------------------------
  
  private int hash(String key) 
  {
    int hash = key.hashCode() % size;
    if (hash < 0) hash += size;
    return hash;
  }

  //------------------------------Insertion Method--------------------------------
  
  public void insert(String key, ArrayList<String> values) 
  {
    int hash = hash(key);
    Entry current = table[hash];
    while (current != null) 
    {
      if (current.key.equals(key)) 
      {
        current.values.addAll(values);
        return;
      }
      current = current.next;
    }
    Entry newEntry = new Entry(key, values);
    newEntry.next = table[hash];
    table[hash] = newEntry;
  }

  //-------------------------------Deletion--------------------------------------
  
  public void delete(String key) 
  {
    int hash = hash(key);
    Entry current = table[hash];
    Entry previous = null;
    while (current != null) 
    {
      if (current.key.equals(key)) 
      {
        if (previous == null) 
        {
          table[hash] = current.next;
        } 
        else 
        {
          previous.next = current.next;
        }
        System.out.println("The Key is Deleted.");
        return;
      }
      previous = current;
      current = current.next;
    }
    System.out.println("The Key is not Present.");
    return;
  }
  
  //-----------------------------------Search----------------------------------------

  public ArrayList<String> search() 
  {
	ArrayList<String> single = new ArrayList<String>();
	single.add("The Key is not Present.");
	Scanner sc = new Scanner(System.in);
  	System.out.println("Enter key to Search: ");
  	String key = sc.nextLine();
    int hash = hash(key);
    if (table[hash] == null) 
    {
    	return single;
    } 
    else 
    {
        Entry entry = table[hash];
        while (entry != null && !entry.key.equals(key)) 
        {
          entry = entry.next;
        }
        if (entry == null) 
        {
            return null;
        } 
        else 
        {
          return entry.values;
        }
      }
  }
  
  //--------------------------------Print----------------------------------------------
  public void print() {
	    for (int i = 0; i < size; i++) 
	    {
	      if (table[i] != null) 
	      {
	        Entry entry = table[i];
	        while (entry != null) 
	        {
	          System.out.println("(" + entry.key + " : " + entry.values + ") ");
	          entry = entry.next;
	        }
	      }
	    }
	    System.out.println();
	  }
  
  //------------------------------------------- IsEmpty Method --------------------------------------------
  public boolean isEmpty() 
  {
	    for (int i = 0; i < table.length; i++) 
	    {
	        if ( table[i] != null ) 
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
  //-------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter size of hashtable: ");
		int size = sc.nextInt();
		Hash ht = new Hash(size);
		while (true) 
		{
		    System.out.println("Enter 1 for Insertion.");
		    System.out.println("Enter 2 for Deletion.");
		    System.out.println("Enter 3 for Search.");
		    System.out.println("Enter 4 for Print.");
		    System.out.println("Enter 5 for Exit.");
		    int option = sc.nextInt();
		    if (option == 1) 
		    {
		    	ArrayList<String> values = new ArrayList<>();
		        System.out.print("Enter key: ");
		        String key = sc.next();
		        int x;
		        do
		        {
	        	System.out.println("Enter 1 for Adding Meaning. ");
	        	System.out.println("Enter 2 for Exit. ");
	        	x = sc.nextInt();
	        	if ( x == 1 )
	        	{
	        		System.out.print("Enter the Meaning: ");
	        		String mean = sc.next();
		        	values.add(mean);
	        	} 
		        } while ( x != 2 );
		        ht.insert(key, values);
		    } 
		    else if (option == 2) 
		    {
		        System.out.print("Enter key to delete: ");
		        String key = sc.next();
		        ht.delete(key);
		    }
		    else if (option == 3)
		    {
		    	System.out.println( ht.search());
		    }
		    else if (option == 4 )
		    {
		    	ht.print();
		    }
		    else if (option == 5)
		    {
		    	break;
		    }
		}
	}
	}

