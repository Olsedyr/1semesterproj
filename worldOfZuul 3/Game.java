package worldOfZuul;

import java.util.List;

public class Game {

    private Room currentRoom;
    private RoomObjs currentRoomObjs;
    private CommandWords commands;

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
        //region Rooms Implementation
        //---------------------------------------------------------------------------------------
        Room soveværelse, køkken, badeværelse, by, strand;

        ///Create Room
        soveværelse = new Room("i dit soveværelse, dette er dit hjem");
        køkken = new Room("i køkkenet, der er masser at spise.");
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
        //---------------------------------------------------------------------------------------
        //endregion


        //region Room Objs Implementation
        //---------------------------------------------------------------------------------------
        RoomObjs lys, radiator, vindue, computer;

        ///Create Room Obj
        lys = new RoomObjs("Det er et lys");
        radiator = new RoomObjs("Det er et radiator");
        vindue = new RoomObjs("Det er vinduet i din rum");
        computer = new RoomObjs("Det er dit computer");

        ///Set Room Obj
        soveværelse.setObjs("lys",soveværelse);
        soveværelse.setObjs("radiator",soveværelse);
        soveværelse.setObjs("vindue",soveværelse);
        soveværelse.setObjs("computer",soveværelse);

        køkken.setObjs("Køkken lys",køkken);
        //---------------------------------------------------------------------------------------
        //endregion

        currentRoom = soveværelse;
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
        String roomObj = currentRoom.getObjString();
        if(roomObj == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean quit(Command command) {
        if (command.hasCommandValue()) {
            return false;
        } else {
            return true;
        }
    }

    //region getCommands Implementation
    //---------------------------------------------------------------------------------------
    public String getRoomDescription() {
        return currentRoom.getLongDescription();
    }

    public String getObjDescription() {
        return currentRoomObjs.getObjLongDescription();
    }

    public CommandWords getCommands() {
        return commands;
    }

    public List<String> getCommandDescriptions() {
        return commands.getCommandWords();
    }

    public String getRoomObjList()
    {
        return currentRoom.getRoomObjsDescription();
    }

    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getCommand(word1), word2);
    }
    //---------------------------------------------------------------------------------------
    //endregion
}
