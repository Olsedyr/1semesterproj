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

        ///Create Room
        bedroom = new Room("in your bedroom, this is your house");
        kitchen = new Room("in the kitchen, there's lots to eat");
        bathroom = new Room("in the bathroom, you clean yourself up here");
        town = new Room("in the town, bustling as always");
        beach = new Room("on the beach, the sand is soft under your feet");

        ///Set Exit
        bedroom.setExit("kitchen", kitchen);

        kitchen.setExit("bedroom", bedroom);
        kitchen.setExit("bathroom", bathroom);
        kitchen.setExit("town", town);

        bathroom.setExit("kitchen", kitchen);

        town.setExit("kitchen", kitchen);
        town.setExit("beach", beach);

        beach.setExit("town", town);

        ///Set Room Obj, simple, needs update
        bedroom.setObjs("Light",bedroom);
        bedroom.setObjs("Heater",bedroom);
        bedroom.setObjs("Window",bedroom);
        bedroom.setObjs("Computer",bedroom);

        kitchen.setObjs("Kitchen light",kitchen);


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

    public boolean lookRoom(Command command) {
        if (command.hasCommandValue()) {
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
