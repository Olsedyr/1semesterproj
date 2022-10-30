package worldOfZuul;

import java.util.List;

public class Game {

    private Room currentRoom;
    private Item currentItem;
    private CommandWords commands;

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
        ///Create Rooms
        Room soveværelse, køkken, badeværelse, by, strand;

        soveværelse = new Room("i dit soveværelse, dette er dit hjem.");
        køkken = new Room("i køkkenet, der er masser at spise.");
        badeværelse = new Room("i badeværelset, du kan renser dig selv her.");
        by = new Room("i byen, travl som altid.");
        strand = new Room("på stranden, sandet er blødt under din fødder.");

        ///Set Exit
        soveværelse.setExit("køkken", køkken);

        køkken.setExit("soveværelse", soveværelse);
        køkken.setExit("badeværelse", badeværelse);
        køkken.setExit("by", by);

        badeværelse.setExit("køkken", køkken);

        by.setExit("køkken", køkken);
        by.setExit("strand", strand);

        strand.setExit("by", by);

        currentRoom = soveværelse;

        ///Create items
        Item lys, radiator, vindue, computer;

        lys = new Item("Et lys",true,false);
        radiator = new Item("Et radiator",true,false);
        vindue = new Item("et vindue",true,false);
        computer = new Item("et computer",true,false);

        ///Set Room item
        soveværelse.setRoomItems("lys", lys);
        soveværelse.setRoomItems("radiator", radiator);
        soveværelse.setRoomItems("vindue", vindue);
        soveværelse.setRoomItems("computer", computer);

        køkken.setRoomItems("Køkken lys", lys);

    }

    public boolean goRoom(Command command) {

        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            return false;
        }

        String direction = command.getCommandValue();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            return true;
        }
    }

    public boolean lookRoom(Command command) {
        String Item = currentRoom.getRoomItemList();
        return Item != null;
    }

    public boolean useItem(Command command) {
        if (!command.hasCommandValue()) { return false;}

        String useing = command.getCommandValue();
        Item useingItem = currentRoom.getItem(useing);

        if (useingItem == null) {
            return false;
        } else {
            currentItem = useingItem;
            return true;
        }
    }

    public boolean quit(Command command) {
        return !command.hasCommandValue();
    }

    //region getCommands Implementation
    //---------------------------------------------------------------------------------------
    public String getRoomDescription() {
        return currentRoom.getLongDescription();
    }

    public String getItemDescription() {
        return currentItem.getItemLongDescription();
    }

    public CommandWords getCommands() {
        return commands;
    }

    public List<String> getCommandDescriptions() {
        return commands.getCommandWords();
    }

    public String getItemList() {
        return currentRoom.getRoomItemList();
    }

    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getCommand(word1), word2);
    }
    //---------------------------------------------------------------------------------------
    //endregion
}
