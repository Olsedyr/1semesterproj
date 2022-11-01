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
        //region ------------------Rooms------------------
        Room soveværelse, køkken, badeværelse, by, strand;
        soveværelse = new Room("i dit soveværelse, dette er dit hjem");
        køkken = new Room("i køkkenet, der er masser at spise");
        badeværelse = new Room("i badeværelset, du kan renser dig selv her");
        by = new Room("i byen, travl som altid");
        strand = new Room("på stranden, sandet er blødt under din fødder");

        ///Set Exitshuidou
        soveværelse.setExit("køkken", køkken);

        køkken.setExit("soveværelse", soveværelse);
        køkken.setExit("badeværelse", badeværelse);
        køkken.setExit("by", by);

        badeværelse.setExit("køkken", køkken);

        by.setExit("køkken", køkken);
        by.setExit("strand", strand);

        strand.setExit("by", by);

        currentRoom = soveværelse;
        //endregion

        //region ------------------Items------------------
        Item.ToggleItem soveværelseLys, radiator, vindue, computer, køkkenLys, tv, vandhane;
        soveværelseLys = new Item.ToggleItem("Det er lyset i din soveværelse",1,true);
        radiator = new Item.ToggleItem("Det er radiatoren i din soveværelse",5,true);
        vindue = new Item.ToggleItem("Det er vinduet i din hus",1,true);
        computer = new Item.ToggleItem("Det er dit computer",2,true);
        køkkenLys = new Item.ToggleItem("Det er et lys i din køkken",1,true);
        tv = new Item.ToggleItem("Det er et TV i din hus",3,true);
        vandhane = new Item.ToggleItem("Det er et vandhane i badeværelse",3,true);

        Item.ChoiceItem køleskab, oven, bruser, badekar, cykel, bill;
        køleskab = new Item.ChoiceItem("Det er køleskabet i dit køkken, med ingredienserne indeni kan du lave en økologisk salat med kylling.", 2,false);
        oven = new Item.ChoiceItem("Det er ovnen i dit køkken, med den kan du lave bagt oksekød.",0,false);
        bruser = new Item.ChoiceItem("Det er bruseren på dit badeværelse, du kan tage et brusebad her.",3,false);
        badekar = new Item.ChoiceItem("Det er badekarret på dit badeværelse, du kan tage et bad her.",0,false);
        cykel = new Item.ChoiceItem("Du kan tage til stranden ved at cykle.",3,false);
        bill = new Item.ChoiceItem("Du kan tage til stranden ved at køre.",0,false);

        Item.TrashItem silkepapir, sodavandsdåser, pizzaæske, mælkekande;
        silkepapir = new Item.TrashItem("Brugt silkepapir.",0,false);
        sodavandsdåser = new Item.TrashItem("Tomme sodavandsdåser som du drak i går.",0,false);
        pizzaæske = new Item.TrashItem("Tom pizzaæske, olien fra pizzaen pletter pizzaboksen.",0,false);
        mælkekande = new Item.TrashItem("Tom mælkekande，du har endda vasket det og foldet det sammen。",0,false);

        ///Set Room item
        soveværelse.setRoomItems("lys", soveværelseLys);
        soveværelse.setRoomItems("radiator", radiator);
        soveværelse.setRoomItems("vindue", vindue);
        soveværelse.setRoomItems("computer", computer);
        soveværelse.setRoomItems("silkepapir", silkepapir);
        soveværelse.setRoomItems("sodavandsdåser", sodavandsdåser);

        køkken.setRoomItems("lys", køkkenLys);
        køkken.setRoomItems("tv", tv);
        køkken.setRoomItems("køleskab", køleskab);
        køkken.setRoomItems("oven", oven);
        køkken.setRoomItems("pizzaæske", pizzaæske);
        køkken.setRoomItems("mælkekande", mælkekande);

        badeværelse.setRoomItems("vandhane", vandhane);
        badeværelse.setRoomItems("bruser", bruser);
        badeværelse.setRoomItems("badekar", badekar);

        by.setRoomItems("cykel", cykel);
        by.setRoomItems("bill", bill);
        //endregion
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
        //}if(currentItem.used()==true) {Inventory.moveItems();}
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
