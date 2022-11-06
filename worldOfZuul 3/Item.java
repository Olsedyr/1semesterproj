package worldOfZuul;

public class Item{

    private String itemDescription;
    private int points;
    public boolean toggleState;
    public boolean used;
    public String otherItem;
    public String itemEndDescription;
    public boolean pickedUp;

    public Item(String itemDescirption,int points){
        this.itemDescription = itemDescirption;
        this.points = points;

    }

    public String getItemLongDescription(){
        return itemDescription + toggleStateString() + ".";
        ///The Choice Item also show toggleStateString, which it shouldn't.
    }

    private String toggleStateString(){
        String returnString = "";
            if (getItemState() && !getItemUsed() && !getPickedUp()) {
                returnString = ", den er tændt/åben";
            } else {
                returnString = ", den er slukket/lukket";
            }
        return returnString;
    }

    ///Need method that remove Choice Item when boolean used is true

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
        public ChoiceItem(String itemDescirption, int points, String itemEndDescription, boolean used, String otherItem) {
            super(itemDescirption, points);
            this.used = used;
            this.otherItem = otherItem;
            this.itemEndDescription = itemEndDescription;
        }
    }

    public static class TrashItem extends Item{
        public TrashItem(String itemDescirption, int points, boolean pickedUp) {
            super(itemDescirption, points);
            this.pickedUp = pickedUp;
        }
    }
}