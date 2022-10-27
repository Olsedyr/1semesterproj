/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldOfZuul.textUI;

import worldOfZuul.Command;
import worldOfZuul.Commands;
import worldOfZuul.Game;

/**
 *
 * @author ancla
 */
public class CommandLineClient {

    private Parser parser;
    private Game game;

    public CommandLineClient() {
        game = new Game();
        parser = new Parser(game);
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Tak fordi du spillede med. Vi ses!");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Velkommen til klimaspillet!");
        System.out.println("Her i klimaspillet, der påvirker dine valg klimaet, så prøv at tage de rigtige valg!");
        System.out.println("Skriv '" + Commands.HELP + "' hvis du har brug for hjælp.");
        System.out.println();
        System.out.println(game.getRoomDescription());
    }

    private void printHelp() {
        for(String str : game.getCommandDescriptions())
        {
            System.out.println(str + " ");
        }
    }

    //Controller
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        Commands commandWord = command.getCommandName();

        if (commandWord == Commands.UNKNOWN) {
            System.out.println("Jeg ved ikke hvad du mener...");
            return false;
        }

        if (commandWord == Commands.HELP) {
            System.out.println("Du er inde i en verden hvor du skal træffe de rigtige valg for klimaet.");
            System.out.println("Prøv at undersøge verdenen og se om du kan gøre en forskel!");
            System.out.println();
            System.out.println("Kommandoerne du kan gøre brug af er:");
            printHelp();
        } else if (commandWord == Commands.GO) {
            if (game.goRoom(command)) {
                System.out.println(game.getRoomDescription());
            } else {
                System.out.println("Du kan ikke gå i den retning!");
            }
        } else if (commandWord == Commands.QUIT) {
            if (game.quit(command)) {
                wantToQuit = true;
            } else {
                System.out.println("Afslut hvad?");
            }

        }
        return wantToQuit;
    }
}
