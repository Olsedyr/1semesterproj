package worldOfZuul;

public class Item{

    protected String itemDescription;
    private final int points;
    public boolean toggleState;
    public boolean used;
    protected String choiceDescription; //Text shown for all the choices
    public String choice1Text; //Text shown when choosing one of the options
    public String choice2Text;
    public String choice3Text;
    public String choice4Text;
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
                returnString = " ";         // Before: Den/det er tændt/åben.
            } else {
                returnString = " ";         // Before: Den/det er slukket/lukket.
            }
        return returnString;
    }

    public String getItemDescription(){
        if(this instanceof ToggleItem) {
            return ((ToggleItem) this).changeItemDescription();
        }
            return itemDescription;
    }
    public  String getChoiceDescription() {return choiceDescription;}

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
        private final String itemDescriptionTrue;
        private final String itemDescriptionFalse;
        public ToggleItem(String itemDescription, String itemDescriptionTrue, String itemDescriptionFalse, int points, boolean toggleState) {
            super(itemDescription, points);
            this.itemDescriptionTrue = itemDescriptionTrue;
            this.itemDescriptionFalse = itemDescriptionFalse;
            this.toggleState = toggleState;
        }

        public String changeItemDescription() {             //Changes item description based on toggle state
            if (this.getItemState()) {
                this.itemDescription = itemDescriptionTrue;
                return itemDescription;
            } else {
                this.itemDescription = itemDescriptionFalse;
                return itemDescription;
            }
        }
    }

    public static class ChoiceItem extends Item{
        public ChoiceItem(String itemDescirption, int points, String choiceDescription, String choice1Text, String choice2Text, boolean used) {
            super(itemDescirption, points);
            this.choiceDescription = choiceDescription;
            this.choice1Text= choice1Text;
            this.choice2Text = choice2Text;
            this.used = used;
        }
    }

    public static class MultipleChoice extends Item{
        public MultipleChoice (String itemDescirption, int points, String choiceDescription, String choice1Text, String choice2Text, String choice3Text, String choice4Text) {
            super(itemDescirption, points);
            this.choiceDescription = choiceDescription;
            this.choice1Text = choice1Text;
            this.choice2Text = choice2Text;
            this.choice3Text = choice3Text;
            this.choice4Text = choice4Text;
        }
    }

    public static class TrashItem extends Item{
        public TrashItem(String itemDescirption, int points, boolean pickedUp) {
            super(itemDescirption, points);
            this.pickedUp = pickedUp;
        }
    }
}