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
        Room soveværelse, badeværelse, by, strand, køkken;

        soveværelse = new Room("i dit soveværelse");
        køkken = new Room("i dit køkken");
        by = new Room("i byen");
        strand = new Room("på stranden");
        badeværelse = new Room("i badeværelset");

        soveværelse.setExit("east", badeværelse);
        soveværelse.setExit("south", køkken);

        badeværelse.setExit("west", soveværelse);

        køkken.setExit("north", soveværelse);
        køkken.setExit("south", by);

        by.setExit("north", køkken);
        by.setExit("west", strand);

        strand.setExit("east", by);

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

    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getCommand(word1), word2);
    }

}
