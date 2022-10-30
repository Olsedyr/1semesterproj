package worldOfZuul;

import java.util.Set;
import java.util.HashMap;

public class Room{
    private RoomObjs RoomObjs;
    private String description;
    private HashMap<String, Room> exits;



    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    ///RoomObjs listMap to Room
    private void ObjMap () {
        HashMap<String, Room> objsMap = RoomObjs.getObjsMap();
    }
    public void setObjs(String objName,Room current) {
        ObjMap();
    }

    public String getShortDescription() {
        return description;
    }
    public String getLongDescription() {
        return "Du er " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) { return exits.get(direction);}

}

