package worldOfZuul;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class RoomObjs {
    private String name;
    private String gameObjDescription;
    private Boolean state;
    private Boolean pickable;

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

    ///This is just a simple version, needs update
    ///public static ArrayList<String> objsArrayList = new ArrayList<>(Arrays.asList());
}