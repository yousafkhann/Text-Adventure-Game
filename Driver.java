import java.util.Scanner;
public class Driver {

    
    static Location currLocation;
    static ContainerItem myInventory = new ContainerItem("Backpack", "BackPackItems", "Backpack used to carry my books");
    public static void main(String[] args)
    {   
        
        System.out.println("Zork version 1.0 by Taha Ameer and Yousaf Khan ");
        System.out.println("All rights reserved - you may play this game forever ");
        System.out.println("");

    
        createWorld();

        Scanner myObj = new Scanner(System.in);

        
        boolean bool = true;
        while(bool)
        {

            System.out.print("Enter command: ");
            String command = myObj.nextLine();
            command = command.toLowerCase();
            String[] arrCommand = command.split(" ");

   
            bool = !arrCommand[0].equalsIgnoreCase("quit");
            

            switch (arrCommand[0])
            {
                case "quit": 
                {
                    bool = false;
                    System.exit(0);
                    break;
                }
                case "look":
                {
                    System.out.println(currLocation.getName());
                    System.out.println(currLocation.getDescription());
                    
                    for(int i=0; i<currLocation.numItems(); i++)
                    {
                        System.out.println("+ " + currLocation.getItem(i).getName()); 
                    }
                    break;
                }
                case "examine":
                {
                    boolean thisBool = true;
                    if(arrCommand.length<2)
                    {
                        System.out.println("Incomplete command, add the item you want to examine.");
                        break;
                    }
                    if(currLocation.getItem(arrCommand[1])!=null)
                    {
                        System.out.println(currLocation.getItem(arrCommand[1]).toString());
                        thisBool = false;
                        break;
                    }
                    
                    if(thisBool)
                    {
                        System.out.println("Cannot find that item.");
                        break;
                    }
                    break;
                }
                case "go":
                {
                    if(arrCommand.length<2)
                    {
                        System.out.println("Incomplete command, add a valid direction.");
                        break;
                    }
                    
                    if(currLocation.canMove(arrCommand[1]))
                    {
                        currLocation = currLocation.getLocation(arrCommand[1]);
                        break;
                    }
                    else if(arrCommand[1].equalsIgnoreCase("south") || arrCommand[1].equalsIgnoreCase("north") || arrCommand[1].equalsIgnoreCase("east") || arrCommand[1].equalsIgnoreCase("west"))
                    {
                        System.out.println("Valid direction provided, but there is no location to move to in that direction. Try a different direction.");
                    }
                    else
                    {
                        System.out.println("An invalid direction was entered; valid directions: North, South, West, or East.");
                    }
                    break;
                }  
                case "inventory":
                {
                    if(myInventory.totalItems()>0)
                    {
                        System.out.println(myInventory.itemsToString());
                    }
                    else
                    {
                        System.out.println("The inventory is empty.");
                    }
                    break;
                }  
                case "put":
                {
                    if(arrCommand.length>=4)
                    {
                        if(arrCommand[2].equalsIgnoreCase("in"))
                        {
                            if(!myInventory.hasItem(arrCommand[1]))
                            {
                                System.out.println("The item does not exist in your inventory.");
                                break;
                            }
                            else
                            {
                                if(!currLocation.hasItem(arrCommand[3]))
                                {
                                    System.out.println("The specified container does not exist at the current location.");
                                    break;
                                }
                                else
                                {
                                    Item temp = currLocation.getItem(arrCommand[3]);
                                    if(!temp.getClass().toString().equalsIgnoreCase("class ContainerItem"))
                                    {
                                        System.out.println("The specified container item is not actually a container item");
                                    }
                                    else
                                    {
                                        ContainerItem addTo = (ContainerItem) currLocation.getItem(arrCommand[3]);
                                        Item toAdd = myInventory.removeItem(arrCommand[1]);
                                        addTo.addItem(toAdd);
                                        System.out.println("Item removed from inventory.");
                                        break;
                                    }
                                }
                            }

                        }
                    }
                    else
                    {
                        System.out.println("incomplete command.");
                        break;
                    }
                    break;  
                }
                
                case "take":
                {
                    if(arrCommand.length>=4)
                    {
                        if(arrCommand[2].equalsIgnoreCase("from"))
                        {
                            if(!currLocation.hasItem(arrCommand[3]))
                            {
                                System.out.println("The specified container item does not exist in the current location.");
                                break;
                            }
                            else
                            {
                                Item temp = currLocation.getItem(arrCommand[3]);
                                String classType = "class ContainerItem";
                                if(!temp.getClass().toString().equalsIgnoreCase(classType))
                                {
                                    System.out.println("the specified container item is not actually a container item.");
                                    break;
                                }
                                else 
                                {
                                    ContainerItem xTemp = (ContainerItem) currLocation.getItem(arrCommand[3]);
                                    if(!xTemp.hasItem(arrCommand[1]))
                                    {
                                        System.out.println("Valid container item provided, however the container item does not contain the specified item that you wish to take.");
                                        break;
            
                                    }
                                    else
                                    {
            
                                        Item toAdd = xTemp.removeItem(arrCommand[1]);
                                        myInventory.addItem(toAdd);
                                        System.out.println("Item added to inventory.");
                                        break;
                                    }
                                    
                                }
                                
                                
                            }
                        }
                        else
                        {
                            System.out.println("Invalid command.");
                            break;
                        }
                        
                    }
                    else
                    {
                        
                        if(arrCommand.length<2)
                        {
                            System.out.println("Incomplete command, add the object you want to take.");
                            break;
                        }
                        if(currLocation.hasItem(arrCommand[1]))
                        {
                            myInventory.addItem(currLocation.getItem(arrCommand[1]));
                            currLocation.removeItem(arrCommand[1]);
                            System.out.println("Item taken and placed in inventory.");
                        }
                        else
                        {
                            System.out.println("Cannot find that item here.");
                        }
                        break;
                    }
                }            
                case "drop":
                {
                    if(arrCommand.length<2)
                    {
                        System.out.println("Incomplete command, type the object you want to drop.");
                        break;
                    }
                    if(myInventory.hasItem(arrCommand[1]))
                    {
                        currLocation.addItem(myInventory.removeItem(arrCommand[1]));
                        System.out.println("Item dropped.");
                    }
                    else
                    {
                        System.out.println("Cannot find that item in your inventory.");
                    }
                    break;
                }
                case "help":
                {
                    System.out.println("go: The go command moves the characters current location to the location in the DIRECTION that you enter, if it is a legal direction");
                    System.out.println("look: The look command describes and prints out items that are found at the current location");
                    System.out.println("examine: The examine command allows the user to examine the items found in their current location");
                    System.out.println("inventory: The inventory command prints a list of the item names that are currently stored in the character's inventory");
                    System.out.println("take: The take command tries to find the matching item at the character's location, if a matching item is found the item is removed from the current location and added to the character's inventory");
                    System.out.println("drop: The drop command tries to find the matching item in the character's inventory, if a mactching item is found, the item is removed from the character's inventory and added to the current location's items");
                    System.out.println("put _ in _ : this command takes an item from the inventory and places it in a container item in the current location");
                    System.out.println("take _ from _: this command takes an item inside a container item and places it in the inventory.");
                    System.out.println("quit: The quit command is used by the user to exit the game");
                    System.out.println("help: The help command displays all of the commands available");
                    break;    
                }
                /*case "take":
                {
                    if(arrCommand[2].equalsIgnoreCase("from") && arrCommand[3]!=null)
                    {
                        if(!currLocation.hasItem(arrCommand[3]))
                        {
                            System.out.println("The item does not exist in the current location.");
                            break;
                        }
                        else
                        {
                            ContainerItem temp = (ContainerItem) currLocation.getItem(arrCommand[3]);
                            if(!temp.hasItem(arrCommand[1]))
                            {
                                System.out.println("The container does not contain the specified item.");
                                break;
                            }
                            else
                            {
                                Item toAdd = temp.removeItem(arrCommand[1]);
                                myInventory.addItem(toAdd);
                                System.out.println("Item added to inventory.");
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid command.");
                        break;
                    }
                    break;
                }*/
                default:
                {
                    System.out.println("I don't know how to do that.");
                }    
                        
            }

            
        }
    }

    public static void lengthCheck(String[] cmd)
    {
        if(cmd.length<2)
        {
            System.out.println("Incomplete command, add the item you want to examine.");
            
        }
    }
    public static void createWorld()
    {
        Location kitchen = new Location("Kitchen", "The place that has food");
        Location hallway = new Location ("Hallway", "A lobby connecting to other places in the houses");
        Location bedroom = new Location("Bedroom", "The master bedroom of the house");
        Location library = new Location("Library", "The place that holds all of the books");

        hallway.connect("West", bedroom);
        hallway.connect("South", library);
        hallway.connect("North", kitchen);
        library.connect("West", bedroom);
        library.connect("East", hallway);
        library.connect("North", kitchen);
        bedroom.connect("North", kitchen);
        bedroom.connect("East", hallway);
        bedroom.connect("South", library);
        kitchen.connect("West", bedroom);
        kitchen.connect("South", library);
        kitchen.connect("East", hallway);

        hallway.addItem(new Item("shoe", "clothing", "A single shoe sitting in the middle of the hallway"));
        hallway.addItem(new Item("umbrella", "accessory", "Umbrella leaning against the hallway"));
        ContainerItem vase= new ContainerItem("Vase", "ornament", "A very expensive looking vase with persian engravings on it.");
        Item ring = new Item("Ring", "accessory", "A lucky find: a 24 karat diamond ring.");
        vase.addItem(ring);
        hallway.addItem(vase);

        library.addItem(new Item("biography", "book", "An unpublished biography of a mysterious individual named 'Yousaf'"));
        library.addItem(new Item("contacts", "accessory", "a pair of dried up contacts sits on the table"));

        kitchen.addItem(new Item("Turkey", "food", "Turkey covered with mold, suggesting it is old"));
        kitchen.addItem(new Item("Plate", "dishes", "Sparkling clean plate straight out of the dishwasher"));
        ContainerItem fridge = new ContainerItem("fridge", "appliance", "A fridge covered with mold.");
        Item apple = new Item("apple", "food", "a rotten apple ridden with worms");
        fridge.addItem(apple);
        kitchen.addItem(fridge);

        bedroom.addItem(new Item("Knife", "utensil", "Covered with butter"));
        bedroom.addItem(new Item("Pillow", "bedding", "An extremely comfortable cured pillow"));
        ContainerItem cupboard = new ContainerItem("cupboard", "storage", "A rustic cupboard with one of its doors partially broken.");
        Item suit = new Item("suit", "clothing", "A very 1980s looking suit in brand new condition");
        cupboard.addItem(suit);
        bedroom.addItem(cupboard);

        currLocation = hallway;


    }
    
    /* test code
        currLocation = new Location("Kitchen", "the place with the food");
        
        Item knife = new Item("Knife", "utensil", "Covered with butter");
        Item turkey = new Item("Turkey", "food", "Turkey covered with mold, suggesting it is old");
        Item plate = new Item("Plate", "dishes", "Sparkling clean plate straight out of the dishwasher");
        currLocation.addItem(knife);
        currLocation.addItem(turkey);
        currLocation.addItem(plate);
        */

         /*test code

        ContainerItem yo = new ContainerItem("Inventory", "backpack", "things inside backpack");
        yo.addItem(new Item("yahya", "chaprasi", "sarak pe nachnay wala"));
        yo.addItem(new Item("nomi", "man", "a very sexy kind of man"));

        System.out.println(yo);
        System.out.println(yo.totalItems());

        */
}
