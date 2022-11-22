package worldOfZuul;

public class Item{
    private String itemDescription;
    private int points;
    public boolean toggleState;
    public boolean used;
    public String choice1;
    public String choice2;
    public static String choice1Text; //Text shown when choosing one of the options
    public static String choice2Text;
    public static String mchoice1Text; //Text shown when choosing one of the options
    public static String mchoice2Text;
    public static String mchoice3Text;
    public static String mchoice4Text;
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
        return returnString;
    }

    private String toggleStateString(){
        String returnString = "";
            if (getItemState()) {
                returnString = " Den/det er tændt/åben.";
            } else {
                returnString = " Den/det er slukket/lukket.";
            }
        return returnString;
    }

    public String getItemDescription(){
        return itemDescription;
    }
    public static String getChoice1(){
        return choice1Text;
    }
    public static String getChoice2(){
        return choice2Text;
    }
    public static String getChoice1m(){
        return mchoice1Text;
    }
    public static String getChoice2m(){
        return mchoice2Text;
    }
    public static String getChoice3m(){
        return mchoice3Text;
    }
    public static String getChoice4m(){
        return mchoice4Text;
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
        public ChoiceItem(String itemDescirption, int points, String choice1Text, String choice2Text) {
            super(itemDescirption, points);
            this.choice1Text= choice1Text;
            this.choice2Text= choice2Text;
        }
    }

    public static class MultipleChoice extends Item{
        public MultipleChoice (String itemDescirption, int points, String mchoice1Text, String mchoice2Text, String mchoice3Text, String mchoice4Text) {
            super(itemDescirption, points);
            this.mchoice1Text= mchoice1Text;
            this.mchoice2Text= mchoice2Text;
            this.mchoice3Text= mchoice3Text;
            this.mchoice4Text= mchoice4Text;
        }
    }

    public static class TrashItem extends Item{
        public TrashItem(String itemDescirption, int points, boolean pickedUp) {
            super(itemDescirption, points);
            this.pickedUp = pickedUp;
        }
    }
}