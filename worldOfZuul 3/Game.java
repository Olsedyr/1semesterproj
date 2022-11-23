package worldOfZuul;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Room currentRoom;
    public Item currentItem;
    private final Inventory inventory;
    private final CommandWords commands;

    public List<Integer> score_list = new ArrayList<Integer>();


    public File scoreFile = new File("score.txt");


    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
        inventory = new Inventory();
    }

    private void createRooms() {
        //region ------------------------------------Rooms------------------------------------
        Room soveværelse, køkken, badeværelse, byen, strand;
        soveværelse = new Room("i dit soveværelse i dit hjem");
        køkken = new Room("i køkkenet. Der var gæster på besøg i går og det kan ses");
        badeværelse = new Room("på badeværelset. Du mærker de kolde klinker under dine fødder");
        byen = new Room("i byen, travl som altid. Du kan vælge enten at tage en bil eller en cykel hjem");
        strand = new Room("på stranden. Sandet er blødt under din fødder");

        ///Set Exit
        soveværelse.setExit("køkken", køkken);

        køkken.setExit("soveværelse", soveværelse);
        køkken.setExit("badeværelse", badeværelse);
        køkken.setExit("byen", byen);

        badeværelse.setExit("køkken", køkken);

        byen.setExit("køkken", køkken);
        byen.setExit("stranden", strand);

        strand.setExit("byen", byen);

        currentRoom = soveværelse;
        //endregion ------------------------------------------------------------------------

        //region ------------------------------------Items------------------------------------
        ///Toggle Items: ToggleState==True means that the current state of the object is not climate friendly
        Item.ToggleItem loftlampe, radiator, vindue, computer, køkkenlampe, tv, vandhane;
        loftlampe = new Item.ToggleItem("Dette burde du ikke kunne se! pinligt...",
                "Du kigger på loftlampen i dit soveværelse. Den er tændt. Du overvejer hvorvidt det er nødvendigt at det er tændt. " +
                        "Gardinet er trukket fra så solen skinner ind i rummet og hjælper med at lyse det op.",
                "Du kigger på loftlampen i dit soveværelse. Den er slukket" +
                        "Lige nu vil det nok ikke gøre den store forskel om den er tændt eller slukket, da rummet allerede er godt lyst op.",
                1,true);
        radiator = new Item.ToggleItem("Dette burde du ikke kunne se! pinligt...",
                "Det er en radiator i din soveværelse. Den er tændt. Du kan mærke at rummet er ret varmt fordi den har været tændt hele dagen, " +
                        "og solen samtidig har varmet rummet op.",
                "Det er radiatoren i dit soveværelse. Den er slukket lige nu, men du kan mærke at rummet stadig er dejligt varmt fra solen som skinner ind.",
                5,true);
        vindue = new Item.ToggleItem("Dette burde du ikke kunne se! pinligt...",
                "Der er et vindue i dit soveværelse, som står åbent. Udenfor kan du se at bladene er faldet af træerne, og der er rim på græsset under dem." +
                        " I rummet er der dog stadig varmt fra radiatoren og solen der skinner udenfor, men du kan mærke den kulden komme ind gennem vinduet.",
                "Der er et vindue i det soveværelse. Det er lukket. Der er en behagelig temperatur herinde, " +
                        "og luften er stadig lidt frisk fra vinduet sidst stod åbent.",
                1,true);
        computer = new Item.ToggleItem("Dette burde du ikke kunne se! pinligt...",
                "Det er din computer. Den er stationær, har to skærme og du fik den til din sidste fødselsdag. Det hele står lige nu tændt, fra da du spillede tidligere. " +
                        "Når den er i brug kan du se på din elmåler at den bruger ret meget strøm.",
                " Det er din stationære computer. Den er slukket, så der er ikke længere en summen at høre fra den.",
                2,true);
        køkkenlampe = new Item.ToggleItem("Dette burde du ikke kunne se! pinligt...",
                "Du kigger på køkkenlampen. Den er tændt. Du overvejer hvorvidt det er nødvendigt at det er tændt. " +
                        "Der er flere store vinduer rundt omkring i køkkenet. Gardinerne er trukket fra så solen skinner ind i rummet og hjælper med at lyse det op.",
                "Du kigger på loftlampen i dit soveværelse. Den er slukket. " +
                        "Lige nu vil det nok ikke gøre den store forskel om den er tændt eller slukket, da rummet allerede er godt lyst op.",
                1,true);
        tv = new Item.ToggleItem("Dette burde du ikke kunne se! pinligt...",
                "Der er et TV i køkkenet. Det er lige nu tændt, og du kan høre en nyhedsvært tale i baggrunden. Når du vasker op er det " +
                        "ofte rart at have noget at se på imens, men ellers bruger du det ikke så meget. Og lige nu ser det ikke ud til" +
                        " at der er sket noget du ikke har hørt om tidligere.",
                "Der er et TV i dit køkken. Det er lige nu slukket, og bruger dermed ikke unødig strøm. " +
                        "Du har alligevel ikke lyst til at se noget på det lige nu.",
                3,true);
        vandhane = new Item.ToggleItem(" Dette burde du ikke kunne se! pinligt...",
                "Der er et vandhane på badeværelset. Den står og drypper, formentligt fra da du vaskede hænder tidligere på dagen.",
                "Der er en vandhane på badeværelset. Lige nu er den ikke i brug, og bruger dermed ikke unødig rent vand.",
                3,true);


        ///Choice Items, can only choose once
        Item.ChoiceItem køleskab, komfur, bad, transport;
        køleskab = new Item.ChoiceItem("Der er et køleskab i dit køkken. Med ingredienserne indeni kan du enten lave en økologisk salat med kylling, " +
                "eller en burger lavet på oksekød med ost og bacon.", 3,
                "1. salat \n2. burger",
                "du spiste salat",
                "du spiste burger",
                false);
        komfur = new Item.ChoiceItem("I dit køkken er der også et komfur. Du kan vælge enten at varme kødet i ovenen, " +
                "eller stege det på en stegepande.",3,
                "1. ovenen \n2. stegepande",
                "du brugt ovenen",
                "du brugt stegepande",
                false);
        bad = new Item.ChoiceItem("Der er en bruser og et badekar på dit badeværelse." +
                " Du kan tage et brusebad eller karbad bad her.",3,
                "1. bruser \n2. badekar",
                "du brugt bruser, hurtigt, men effektivt",
                "du brugt badekar, dejligt og varmt",
                false);
        transport = new Item.ChoiceItem("Du kan tage til stranden ved at cykle eller at køre.",3,
                "1. cykle \n2. bil",
                "du brugt cykle, ding ding",
                "du brugt bil, beep beep",
                false);

        ///Multiple Choice Items, can be chosen multiple times
        Item.MultipleChoice grete, brete;
        grete = new Item.MultipleChoice("En pige ved navn Grett står der og ser ud til, at hun gerne vil vide, hvordan man hjælper klimakrisen.", 3,
                "Hej du! Er der noget jeg kan gøre for at hjælpe med klimakrisen?" +
                        "\n1. Du kan samle skrald op nede på stranden" +
                        "\n2. Du kan prøve at slukke for computeren" +
                        "\n3. Du kan tage cyklen i stedet for bilen" +
                        "\n4. Aner det ikke",
                "Mange tak for hjælpen!",
                "Nåår computeren, det var en god ide. Tak!",
                "Uha, nej tak, det er der for langt til",
                "Når..");

        brete = new Item.MultipleChoice("En pige ved navn Brett står der og ser ud til, at hun gerne vil stille dig et simpelt matematikspørgsmål.", 3,
                "1+1=?" +
                        "\n1. 3" +
                        "\n2. 2" +
                        "\n3. 7" +
                        "\n4. 42",
                "Det kan måske virke med et par...",
                "Korrekt! Godt klaret!",
                "Forkert, hvordan får du overhovedet det svar?",
                "Det er et svar... men for det forkerte spørgsmål.");


        ///Trash Items
        Item.TrashItem silkepapir, sodavandsdåser, pizzabakke, mælkekarton;
        silkepapir = new Item.TrashItem("Brugt silkepapir.",1,false);
        sodavandsdåser = new Item.TrashItem("Tomme sodavandsdåser som du drak i går med dine venner.",1,false);
        pizzabakke = new Item.TrashItem("Tom pizzabakke, olien fra pizzaen pletter pizzaboksen.",1,false);
        mælkekarton = new Item.TrashItem("Tom mælkekarton，du har allerede foldet det sammen.",1,false);

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
        badeværelse.setRoomItems("bad", bad);

        byen.setRoomItems("transport", transport);

        soveværelse.setRoomItems("Grete", grete);
        soveværelse.setRoomItems("Brete", brete);
        //endregion ------------------------------------------------------------------------
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
        return Item != null;    //return true if Item != null
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

    public int plus_sum_score(){
        int sum=0;
        for (int i = 0; i<score_list.size(); i++)
            sum += Integer.valueOf(score_list.get(i));
        if (currentItem instanceof Item.ToggleItem) {
            if (currentItem.getItemState() == true) {
                System.out.println("Du fik " + currentItem.getItemPoints() + " pointx");
            } else {
                System.out.println("Du mistede " + currentItem.getItemPoints() + " point");
            }
        }else if (currentItem instanceof Item.TrashItem) {
            if (currentItem.getPickedUp()==true){
                System.out.println("Du fik " + currentItem.getItemPoints() + " point");
            }

        }
        System.out.println("Din score er nu: " + sum);

        //Skriver til score.txt filen
        PrintWriter pw;
        try{
        pw = new PrintWriter(scoreFile);
        pw.println(sum);
        pw.close();
        } catch (FileNotFoundException ex){
            System.out.println("Der var en fejl i scoresystemet. ");
        }
        return sum;
    }


    public void switchItemState(Command command) {
        if (currentItem instanceof Item.ToggleItem) {
            //If you do the "right thing" you get points
            if (currentItem.getItemState()==true) {              //If item use is climate friendly, add points and change toggleState
                score_list.add(currentItem.getItemPoints());
            } else {                                             //If item use is not climate friendly, subtract points and change toggleState
                score_list.remove(Integer.valueOf(currentItem.getItemPoints()));
            }
            plus_sum_score();
            currentItem.toggleState ^= true;                    //Toggle switch for toggleState boolean
            //refer to method changing itemDescription based on toggleState?
        } else if(currentItem instanceof Item.ChoiceItem) {
            currentItem.used = true;
            //Missing score implementation
            removeItem(command);
        } else if (currentItem instanceof Item.TrashItem) {
            currentItem.pickedUp = true;
            if (currentItem.getPickedUp()==true) {
                score_list.add(currentItem.getItemPoints());
            }
            plus_sum_score();
            addItemToInventory();
            removeItem(command);
        }
    }

    private void removeItem(Command command) {
       String itemName=command.getCommandValue();
       currentRoom.removeItem(itemName);
    }

    private void addItemToInventory() {
        inventory.addTrash(currentItem.getItemDescription(), currentItem);
    }

    public  boolean inventory(Command command){
        return !command.hasCommandValue();
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

    public String getChoice() {
        return currentItem.getChoiceDescription();
    }

    public String getInventoryDescription(){
        return inventory.getInventoryString();
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
