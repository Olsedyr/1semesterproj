package worldOfZuul;

import java.util.Set;
import java.util.HashMap;


public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    public HashMap<String, Room> objs;

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        objs = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public void setObjs(String objName,Room current)
    {
        objs.put(objName,current);
    }


    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "Du er " + description + ".\n" + getExitString();
    }

    public String getRoomObjsDescription() { return "Du ser:" + getObjString(); }


    private String getExitString()
    {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public String getObjString()
    {
        String returnObjString = "";
        Set<String> keys = objs.keySet();
        for(String obj : keys) {
            returnObjString += "\n" + obj;
        }
        return returnObjString;
    }


    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

}

