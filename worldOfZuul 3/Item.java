package worldOfZuul;

public class Item{


    private String itemDescription;
    private int points;
    public boolean toggleState;
    private boolean pickable;

    public Item(String itemDescirption,int points,boolean toggleState, boolean pickable){
        this.itemDescription = itemDescirption;
        this.points = points;
        this.toggleState = toggleState;
        this.pickable = pickable;
    }

    public String getItemLongDescription(){
        return itemDescription + ", " + toggleStateString() + pickableString() + ".";
    }


    private String toggleStateString(){
        String returnString = "";
        if(getItemState()){
            returnString = "den er tændt/åben";
        } else {
            returnString = "den er slukket/lukket";
        }
        return returnString;
    }

    private String pickableString(){
        String returnString = "";
        if(getPickable()){
            returnString = ", du kan samle den op";
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