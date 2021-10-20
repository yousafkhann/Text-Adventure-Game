import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Location {
    
    //member variables
    private String name;
    private String description;
    private ArrayList<Item> myItem; //stores item objects in the location.
    private HashMap<String, Location> connections;

    //constructors
    public Location (String name, String description)
    {
        this.name=name;
        this.description=description;
        myItem = new ArrayList<>();
        connections = new HashMap<String, Location>();
    }

    //methods

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setDescription( String description)
    {
        this.description=description;
    }

    //moreMethods

    public void addItem(Item object)
    {
        myItem.add(object);
    }

    public boolean hasItem(String findName)
    {
        boolean isBoolean = false;
        for(int i=0; i<myItem.size(); i++)
        {
            if(myItem.get(i).getName().equalsIgnoreCase(findName))
            {
                isBoolean=true;
            }
        }

        return isBoolean;
    }

    public Item getItem(String findName)
    {
       
        for(int i=0; i<myItem.size(); i++)
        {
            if(myItem.get(i).getName().equalsIgnoreCase(findName))
            {
                return myItem.get(i);
            }
        }
        return null;
    }

    public Item getItem(int index)
    {
        if(index>myItem.size() || index<0)
        {
            
            return null;
        }
        else
        {
            return myItem.get(index);
        }
    }

    public int numItems()
    {
        return myItem.size();
    }

    public Item removeItem(String parameter)
    {
        for(int i=0; i<myItem.size(); i++)
        {
            if(myItem.get(i).getName().equalsIgnoreCase(parameter))
            {
                Item temp = myItem.get(i);
                myItem.remove(i);
                return temp;
            }
            
        }
        System.out.println("Item not found");
        return null;
    }

    public void connect (String dirName, Location obj)
    {
        connections.put(dirName, obj);
    }

    public boolean canMove (String dirName)
    {
        Set<String> keys = connections.keySet();
        Iterator<String> myIter = keys.iterator();
        boolean isBoolean = false;

        while(myIter.hasNext())
        {
            
            String myKey = myIter.next();
            Location myValue = connections.get(myKey);

            if(myKey.equalsIgnoreCase(dirName) && myValue!=null)
            {
                isBoolean = true;
            }
    
        }
        return isBoolean;    
    }

    public Location getLocation(String dirName)
    {
        Set<String> keys = connections.keySet();
        Iterator<String> myIter = keys.iterator();
        Location value = null;

        while(myIter.hasNext())
        {
            
            String myKey = myIter.next();
            Location myValue = connections.get(myKey);

            if(myKey.equalsIgnoreCase(dirName))
            {
                value = myValue;
            }
            
        }
        return value;
    }
}


/*
    public boolean canMove (String dirName)
    {
        boolean isBoolean = false;
        if(connections.containsKey(dirName))
        {
            isBoolean = true;
        }   
        return isBoolean;
    }

    public Location getLocation(String dirName)
    {
        return (connections.get(dirName));
    }

public boolean canMove (String dirName)
    {
        Set<String> keys = connections.keySet();
        Iterator<String> myIter = keys.iterator();
        boolean isBoolean = false;

        while(myIter.hasNext())
        {
            
            String myKey = myIter.next();
            Location myValue = connections.get(myKey);

            if(myKey.equalsIgnoreCase(dirName) && myValue!=null)
            {
                isBoolean = true;
            }
    
        }
        return isBoolean;    
    }

    public Location getLocation(String dirName)
    {
        Set<String> keys = connections.keySet();
        Iterator<String> myIter = keys.iterator();
        Location value = null;

        while(myIter.hasNext())
        {
            
            String myKey = myIter.next();
            Location myValue = connections.get(myKey);

            if(myKey.equalsIgnoreCase(dirName))
            {
                value = myValue;
            }
            
        }
        return value;
    }
*/

