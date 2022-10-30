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

        soveværelse = new Room("i dit soveværelse, dette er dit hjem");
        køkken = new Room("i køkkenet, der er masser at spise");
        badeværelse = new Room("i badeværelset, du kan renser dig selv her");
        by = new Room("i byen, travl som altid");
        strand = new Room("på stranden, sandet er blødt under din fødder");

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
        Item soveværelseLys, radiator, vindue, computer,køkkenLys;

        soveværelseLys = new Item("Det er lyset i din soveværelse",1,true,false);
        radiator = new Item("Det er radiatoren i din soveværelse",4,true,false);
        vindue = new Item("Det er vinduet i din hus",1,true,false);
        computer = new Item("Det er dit computer",2,true,false);

        køkkenLys = new Item("Det er et lys i din køkken",1,true,false);

        ///Set Room item
        soveværelse.setRoomItems("lys", soveværelseLys);
        soveværelse.setRoomItems("radiator", radiator);
        soveværelse.setRoomItems("vindue", vindue);
        soveværelse.setRoomItems("computer", computer);

        køkken.setRoomItems("lys", køkkenLys);

    }

    public boolean goRoom(Command command) {
        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            System.out.println("Har brug for en retning at gå til.");
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
        if(command.hasCommandValue()) {
            return false;
        }
        String Item = currentRoom.getRoomItemList();
        return Item != null;
    }

    public boolean lookItem(Command command) {
        if (!command.hasCommandValue()) {
            return false;
        }
        String itemName = command.getCommandValue();
        Item useingItem = currentRoom.getItem(itemName);
        if (useingItem == null) {
            return false;
        } else {
            currentItem = useingItem;
            return true;
        }
    }

    public boolean useItem(Command command) {
        if (!command.hasCommandValue()) {
            return false;
        }
        String itemName = command.getCommandValue();
        Item useingItem = currentRoom.getItem(itemName);
        if (useingItem == null) {
            return false;
        } else {
            currentItem = useingItem;
            return true;
        }
    }

    public void switchItemState() {
        currentItem.toggleState^=true;
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
