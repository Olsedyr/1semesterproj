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
        soveværelse = new Room(" i dit soveværelse i dit hjem");
        køkken = new Room(" i køkkenet. Der var gæster på besøg i går og det kan ses");
        badeværelse = new Room(" på badeværelset. Du mærker de kolde klinker under dine fødder");
        by = new Room(" i byen, travl som altid. Du kan vælge enten at tage en bil eller en cykel hjem");
        strand = new Room(" på stranden. Sandet er blødt under din fødder");

        ///Set Exitshuidou
        soveværelse.setExit("køkken", køkken);

        køkken.setExit("soveværelse", soveværelse);
        køkken.setExit("badeværelse", badeværelse);
        køkken.setExit("byen", by);

        badeværelse.setExit("køkken", køkken);

        by.setExit("køkken", køkken);
        by.setExit("stranden", strand);

        strand.setExit("byen", by);

        currentRoom = soveværelse;
        //endregion

        //region ------------------Items------------------
        Item.ToggleItem loftlampe, radiator, vindue, computer, køkkenlampe, tv, vandhane;
        loftlampe = new Item.ToggleItem("Du kigger på loftlampen i dit soveværelse. Du overvejer hvorvidt det er nødvendigt at det er tændt. " +
                "Gardinet er trukket fra så solen skinner ind i rummet og hjælper med at lyse det op.",1,true);
        radiator = new Item.ToggleItem("Det er en radiator i din soveværelse. Du kan mærke at rummet er dejligt varmt.",5,true);
        vindue = new Item.ToggleItem("Der er et vindue i dit soveværelse. Udenfor kan du se at bladene er faldet af træerne " +
                "og der er rim på noget af græsset under dem.",1,true);
        computer = new Item.ToggleItem("Det er din computer. Den er stationær, har  to skærme og du fik den til din sidste fødselsdag. " +
                "Når den er i brug kan du se på din elmåler at den bruger ret meget strøm.",2,true);
        køkkenlampe = new Item.ToggleItem("Der er en lampe i dit køkken. Du overvejer hvorvidt det er nødvendigt at have den tændt. ",1,true);
        tv = new Item.ToggleItem("Der er et TV i køkkenet. Når du vasker op er det ofte rart at have noget at se på imens, " +
                "men ellers bruger du det ikke så meget.",3,true);
        vandhane = new Item.ToggleItem("Der er et vandhane på badeværelset.",3,true);

        Item.ChoiceItem køleskab, komfur, bruser, badekar, cykel, bil;
        køleskab = new Item.ChoiceItem("Der er et køleskab i dit køkken. Med ingredienserne indeni kan du enten lave en økologisk salat med kylling, " +
                "eller en burger lavet på oksekød med ost og bacon.", 2,false);
        komfur = new Item.ChoiceItem("I dit køkken er der også et komfur. Du kan vælge enten at varme kødet i ovenen " +
                "eller stege det på en stegepande.",0,false);
        bruser = new Item.ChoiceItem("Der er en bruser på dit badeværelse. du kan tage et brusebad her.",3,false);
        badekar = new Item.ChoiceItem("Der er et badekar på dit badeværelse. du kan tage et karbadbad her.",0,false);
        cykel = new Item.ChoiceItem("Du kan tage til stranden ved at cykle.",3,false);
        bil = new Item.ChoiceItem("Du kan tage til stranden ved at køre.",0,false);

        Item.TrashItem silkepapir, sodavandsdåser, pizzabakke, mælkekarton;
        silkepapir = new Item.TrashItem("Brugt silkepapir.",0,false);
        sodavandsdåser = new Item.TrashItem("Tomme sodavandsdåser som du drak i går med dine venner.",0,false);
        pizzabakke = new Item.TrashItem("Tom pizzabakke, olien fra pizzaen pletter pizzaboksen.",0,false);
        mælkekarton = new Item.TrashItem("Tom mælkekarton，du har endda vasket det og foldet det sammen.",0,false);

        ///Set Room item
        soveværelse.setRoomItems("loftlampe", loftlampe);
        soveværelse.setRoomItems("radiator", radiator);
        soveværelse.setRoomItems("vindue", vindue);
        soveværelse.setRoomItems("computer", computer);
        soveværelse.setRoomItems("silkepapir", silkepapir);
        soveværelse.setRoomItems("sodavandsdåser", sodavandsdåser);

        køkken.setRoomItems("køkkenlampe", køkkenlampe);
        køkken.setRoomItems("tv", tv);
        køkken.setRoomItems("køleskab", køleskab);
        køkken.setRoomItems("komfur", komfur);
        køkken.setRoomItems("pizzabakke", pizzabakke);
        køkken.setRoomItems("mælkekarton", mælkekarton);

        badeværelse.setRoomItems("vandhane", vandhane);
        badeværelse.setRoomItems("bruser", bruser);
        badeværelse.setRoomItems("badekar", badekar);

        by.setRoomItems("cykel", cykel);
        by.setRoomItems("bil", bil);
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
