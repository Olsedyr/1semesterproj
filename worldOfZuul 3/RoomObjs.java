package worldOfZuul;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class RoomObjs{

    private Room Room;

    private String name;
    private String gameObjDescription;
    private Boolean state;
    private Boolean pickable;

    public RoomObjs(String gameObjDescription){
        this.gameObjDescription = gameObjDescription;
    }


    public RoomObjs(String name, String gameObjDescription, boolean state, boolean pickable){
        this.name = name;
        this.gameObjDescription = gameObjDescription;
        this.state = state;
        this.pickable = pickable;
    }

    public String getGameObjName(){
        return name;
    }
    public String getGameObjDescription(){
        return gameObjDescription;
    }
    public boolean getGameObjState(){
        return state;
    }
    public boolean isPickable(){
        return pickable;
    }


    public String getObjShortDescription()
    {
        return gameObjDescription;
    }
    public String getObjLongDescription()
    {
        return gameObjDescription + ".\n" + getBackString();
    }

    private String getBackString()
    {
        return "Tast 'Back' for at gå tilbage til værelsesvisningen";
    }

    ///public static ArrayList<String> objsArrayList = new ArrayList<>(Arrays.asList());
}