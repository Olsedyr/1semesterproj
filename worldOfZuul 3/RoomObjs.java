package worldOfZuul;

import java.util.Set;
import java.util.HashMap;

public class RoomObjs{

    private Room Room;
    private String objDescription;
    private HashMap<String, Room> objs = new HashMap<String, Room>();

    private String name;
    private Boolean state;
    private Boolean pickable;


    public RoomObjs(String objDescription){
        this.objDescription = objDescription;
    }

    public HashMap<String, worldOfZuul.Room> getObjsMap() {
        return objs;
    }

    public String getObjDescription(){
        return objDescription;
    }
    public boolean getObjState(){
        return state;
    }
    public boolean isPickable(){
        return pickable;
    }


    public String getObjShortDescription() {
        return objDescription;
    }
    public String getObjLongDescription() {
        return objDescription + ".\n" + getBackString();
    }

    public String getRoomObjsList() { return "Du ser:" + getObjString(); }



    public String getObjString()
    {
        String returnObjString = "";
        Set<String> keys = objs.keySet();
        for(String obj : keys) {
            returnObjString += "\n" + obj;
        }
        return returnObjString;
    }

    private String getBackString() {
        return "Tast 'Back' for at gå tilbage til værelsesvisningen"; ///missing
    }

    public Object getObj(String objName) { return objs.get(objName);
    }



}