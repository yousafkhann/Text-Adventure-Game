import java.util.ArrayList;
import java.util.Iterator;

public class ContainerItem extends Item 
{
    //additional member variables
    private ArrayList<Item> items;
    

    public ContainerItem(String name, String type, String description)
    {
        super(name, type, description);
        items = new ArrayList<>();
        
    }

    public void addItem(Item addObject)
    {
        items.add(addObject);
       
    }

    public boolean hasItem(String hasName)
    {
        Iterator<Item> myIterator = items.iterator();
        while (myIterator.hasNext())
        {
            Item thisItem = myIterator.next();
            if(thisItem.getName().equalsIgnoreCase(hasName))
            {
                return true;
            }
        }
        return false;
    }

    public Item removeItem(String removeName)
    {
        Iterator<Item> myIterator = items.iterator();
        while (myIterator.hasNext())
        {
            Item thisItem = myIterator.next();
            if(thisItem.getName().equalsIgnoreCase(removeName))
            {
                Item temp = thisItem;
                items.remove(thisItem);
                
                return temp;
            }
        }
        System.out.println("Cannot find the item.");
        return null;
    }

    //helper method for toString override:
    public String printItems()
    {
        Iterator<Item> myIterator = items.iterator();
        String x = "";
        while (myIterator.hasNext())
        {
            Item thisItem = myIterator.next();
            x= x + "+" +thisItem.getName() + "\n";
        }
        return x;

    }

    @Override
    public String toString()
    {
        String s = super.getName() + " [" + super.getType() + "] :" + super.getDescription() + " that contains: " + "\n" + printItems();
        return s;
    }
        
    //method to print items in container class
    public String itemsToString()
    {
        String myString = "";
        for(int i = 0; i < items.size(); i++)
        {
          myString = myString+ "+"+items.get(i).getName() + "\n";
        }
        
        return myString;
    }

    //helper method that tells us how many items in the array
    public int totalItems()
    {
        return items.size();
    }
}
