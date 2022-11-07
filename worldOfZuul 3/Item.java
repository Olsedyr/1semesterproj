package worldOfZuul;

public class Item{

    private String itemDescription;
    private int points;
    public boolean toggleState;
    public boolean used;
    public String itemEndDescription;
    public boolean pickedUp;

    public Item(String itemDescirption,int points){
        this.itemDescription = itemDescirption;
        this.points = points;

    }

    public String getItemLongDescription(){
        String returnString = getItemDescription();
        if(this instanceof ToggleItem){
            returnString += toggleStateString();
        }
        returnString += ".";
        return returnString;
    }

    private String toggleStateString(){
        String returnString = "";
            if (getItemState()) {
                returnString = "Den/det er tændt/åben";
            } else {
                returnString = "Den/det er slukket/lukket";
            }
        return returnString;
    }

    public String getItemDescription(){
        return itemDescription;
    }
    public int getItemPoints(){
        return points;
    }
    public boolean getItemState(){
        return toggleState;
    }
    public boolean getItemUsed(){
        return used;
    }
    public boolean getPickedUp(){
        return pickedUp;
    }


    public static class ToggleItem extends Item{
        public ToggleItem(String itemDescirption, int points, boolean toggleState) {
            super(itemDescirption, points);
            this.toggleState = toggleState;
        }
    }

    public static class ChoiceItem extends Item{
        public ChoiceItem(String itemDescirption, int points, String itemEndDescription, boolean used) {
            super(itemDescirption, points);
            this.itemEndDescription = itemEndDescription;
            this.used = used;
        }
    }

    public static class TrashItem extends Item{
        public TrashItem(String itemDescirption, int points, boolean pickedUp) {
            super(itemDescirption, points);
            this.pickedUp = pickedUp;
        }
    }
}