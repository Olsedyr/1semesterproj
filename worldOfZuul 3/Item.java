package worldOfZuul;

public class Item{

    private String itemDescription;
    private int points;
    public boolean toggleState;
    public boolean used = false;

    public Item(String itemDescirption,int points){
        this.itemDescription = itemDescirption;
        this.points = points;
    }

    public String getItemLongDescription(){
        return itemDescription + toggleStateString() + ".";
    }

    private String toggleStateString(){
        String returnString = "";
            if (getItemState()) {
                returnString = ", den er tændt/åben";
            } else {
                returnString = ", den er slukket/lukket";
            }
        return returnString;
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


    public static class ToggleItem extends Item{
        public ToggleItem(String itemDescirption, int points, boolean toggleState) {
            super(itemDescirption, points);
            this.toggleState = toggleState;
        }
    }

    public static class ChoiceItem extends Item{
        public ChoiceItem(String itemDescirption, int points, boolean used) {
            super(itemDescirption, points);
            this.used = used;
        }
    }
}