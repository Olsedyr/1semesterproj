package worldOfZuul;

public abstract class GameObjs {
    private String name;
    private String gameObjDescription;
    private Boolean state;
    private Boolean pickable;

    public GameObjs(String name,String gameObjDescription, boolean state, boolean pickable){
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
}