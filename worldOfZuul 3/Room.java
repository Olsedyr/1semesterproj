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
        return "You are " + description + ".\n" + getExitString();
    }

    public String getRoomObjsDescription()
    {
        return getObjString();
    }


    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    private String getObjString()
    {
        String returnObjString = "You see:";
        Set<String> keys = objs.keySet();
        for(String obj : keys) {
            returnObjString += "  " + obj;
        }
        return returnObjString;
    }


    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
}

