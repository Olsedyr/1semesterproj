package worldOfZuul.Domain;

import java.util.Set;
import java.util.HashMap;

public class Room{

    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> roomItems;


    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        roomItems = new HashMap<String, Item>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public void setRoomItems(String itemName, Item item){
        roomItems.put(itemName,item);
    }


    public String getShortDescription() {
        return description;
    }
    public String getLongDescription() {
        return "Du er " + description + ".\n" + getExitString();
    }

    public String getRoomItemList() {
        return "Du ser " + roomItems.size() + " objekter af interesse:" + getItemString();
    }
    private String getItemString(){
        String returnString = "";
        Set<String> keys = roomItems.keySet();
        for(String roomItem : keys) {
            returnString += "\n" + "- " + roomItem;
        }
        return returnString;
    }

    public void removeItem(Item item)
    {
        roomItems.remove(item);

        ///don't know why doesn't work
    }

    private String getExitString() {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) { return exits.get(direction);}
    public Item getItem(String itemName) { return roomItems.get(itemName);}


}

