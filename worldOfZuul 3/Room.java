package worldOfZuul;

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

    public void setRoomItems(String useing, Item Item){
        roomItems.put(useing,Item);
    }

    public String getShortDescription() {
        return description;
    }
    public String getLongDescription() {
        return "Du er " + description + ".\n" + getExitString();
    }


    public String getRoomItemList() {
        return "Du ser: " + getItemString();
    }
    private String getItemString(){
        String returnString = "";
        Set<String> keys = roomItems.keySet();
        for(String roomItem : keys) {
            returnString += " " + roomItem;
        }
        return returnString;
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
    public Item getItem(String useing) { return roomItems.get(useing);}

}

