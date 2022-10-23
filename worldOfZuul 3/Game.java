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
        Room bedroom, kitchen, bathroom, town, beach;

        bedroom = new Room("in your bedroom, this is your house");
        kitchen = new Room("in the kitchen, there's lots to eat");
        bathroom = new Room("in the bathroom, you clean yourself up here");
        town = new Room("in the town, bustling as always");
        beach = new Room("on the beach, the sand is soft under your feet");

        bedroom.setExit("kitchen", kitchen);

        kitchen.setExit("bedroom", bedroom);
        kitchen.setExit("bathroom", bathroom);
        kitchen.setExit("town", town);

        bathroom.setExit("kitchen", kitchen);

        town.setExit("kitchen", kitchen);
        town.setExit("beach", beach);

        beach.setExit("town", town);

        currentRoom = bedroom;
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
