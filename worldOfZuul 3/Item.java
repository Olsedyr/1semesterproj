package worldOfZuul;

public class Item{

    private String itemDescription;
    private String name;
    private Boolean state;
    private Boolean pickable;

    public Item(String itemDescirption,Boolean state, Boolean pickable){
        this.itemDescription = itemDescription;
        this.state = state;
        this.pickable = pickable;
    }

    public String getItemLongDescription(){
        return itemDescription;
    }
    public String getItemName(){
        return name;
    }
    public boolean getItemState(){
        return state;
    }
    public boolean isPickable(){
        return pickable;
    }


}