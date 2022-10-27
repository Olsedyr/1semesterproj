package worldOfZuul;

import java.util.List;

public class Game {

    private Room currentRoom;
    private CommandWords commands;

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
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

        ///Set Room Obj, simple, needs update
        soveværelse.setObjs("Lys",soveværelse);
        soveværelse.setObjs("Radiator",soveværelse);
        soveværelse.setObjs("Vindue",soveværelse);
        soveværelse.setObjs("Computer",soveværelse);

        køkken.setObjs("køkken lys",køkken);


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

    public String getRoomDescription() {
        return currentRoom.getLongDescription();
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

}
