package worldOfZuul.Domain;

public class Item{

    protected String itemDescription;       //Text for the item itself
    private final int points;
    public boolean toggleState;             //On or off for the ToggleItem class
    public boolean used;                    //Check if the ChoiceItem class item have been used or not
    public int correctChoice;               //Correct int choice
    protected String choiceDescription;     //Text for the choices
    public String choice1Text;              //Text shown when choosing one of the options
    public String choice2Text;
    public String choice3Text;
    public String choice4Text;
    public boolean pickedUp;                //Check if the TrashItem class item have been picked up or not

    public Item(String itemDescription,int points){
        this.itemDescription = itemDescription;
        this.points = points;
    }

    public String getItemLongDescription(){
        String returnString = getItemDescription();
        if(this instanceof ToggleItem){
            returnString += toggleStateString();
        }
        return returnString;
    }

    public String getItemDescription(){
        if(this instanceof ToggleItem) {
            return ((ToggleItem) this).changeItemDescription();
        }
        return itemDescription;
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

        public ChoiceItem(String itemDescirption, int points, String choiceDescription, String choice1Text, String choice2Text, boolean used, int correctChoice) {
            super(itemDescirption, points);
            this.choiceDescription = choiceDescription;
            this.choice1Text = choice1Text;
            this.choice2Text = choice2Text;
            this.used = used;
            this.correctChoice = correctChoice;
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