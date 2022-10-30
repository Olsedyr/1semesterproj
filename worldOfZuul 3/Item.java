package worldOfZuul;

import java.util.Set;

public class Item{

    private String itemDescription;
    private int points;
    private boolean toggleState;
    private boolean pickable;

    public Item(String itemDescirption,int points,boolean toggleState, boolean pickable){
        this.itemDescription = itemDescirption;
        this.points = points;
        this.toggleState = toggleState;
        this.pickable = pickable;
    }

    public String getItemLongDescription(){
        return itemDescription + ", " + toggleStateString() + ", " + pickableString() + ".";
    }

    private String toggleStateString(){
        String returnString = "";
        if(getItemState() == true){
            returnString = "den er tændt/åben";
        } else {
            returnString = "den er slukket/lukket";
        }
        return returnString;
    }

    private String pickableString(){
        String returnString = "";
        if(getPickable() == true){
            returnString = "du kan samle den op";
        } else {
            returnString = "du kan ikke samle den op";
        }
        return returnString;
    }

    public int getItemPoints(){
        return points;
    }
    public boolean getItemState(){
        return toggleState;
    }
    public boolean getPickable(){
        return pickable;
    }


}