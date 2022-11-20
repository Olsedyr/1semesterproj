/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldOfZuul.textUI;

import worldOfZuul.*;

/**
 *
 * @author ancla
 */
public class CommandLineClient {

    private Parser parser;
    private Game game;
    private Inventory inventory;

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
        System.out.println("Tak fordi du spillede med. Vi ses!.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("###### Velkommen til Klimaspillet! ######");
        System.out.println("Her i Klimaspillet, påvirker dine valg klimaet, så prøv dit bedste for at hjælpe klimaet!");
        System.out.println();
        System.out.println("Du kan skrive '" + Commands.GO + " + [udgange]' at gå til forskellige steder, '"
                + Commands.LOOK + "' eller '" + Commands.LOOK + " + [objekt]' at kigge rundt eller på noget objekt, \n'"
                + Commands.USE + " + [objekt]' at at interagere med objekt og '" + Commands.Inventory + "' at se hvad du har samlede op.");
        System.out.println("Skrive '" + Commands.HELP + "' hvis du har brug for hjælp.");
        System.out.println("Når du ønkser at lukke programmet skal du skrive 'afslut'.");
        System.out.println("Held og lykke! :P");
        System.out.println();
        System.out.println("Du vågner op fra din søvn, det er eftermiddagen.");
        System.out.println(game.getRoomDescription());
    }

    private void printHelp() {
        for(String str : game.getCommandDescriptions())
        {
            System.out.println("- " + str);
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
            System.out.println("Du er inde i en verden hvor du skal træffe de rigtige valg for klimaet");
            System.out.println("Prøv at undersøge dine omgivelser og se om du kan gøre en forskel!");
            System.out.println();
            System.out.println("Kommandoerne du kan gøre brug af er:");
            printHelp();

        } else if (commandWord == Commands.GO) {
            if (game.goRoom(command)) {
                System.out.println(game.getRoomDescription());
            } else {
                System.out.println("Kan ikke gå i den retning.");
            }

        } else if (commandWord == Commands.LOOK) {
            if (game.lookRoom(command)) {
                System.out.println(game.getItemList());
            }else if(game.lookItem(command)) {
                System.out.println(game.getItemDescription());
            }else{
                System.out.println("Kan ikke se noget.");
            }

        } else if (commandWord == Commands.USE) {
            if (game.useItem(command)) {
                if(game.currentItem instanceof Item.TrashItem){
                    game.switchItemState();
                    System.out.println("Du samlede op...");
                    System.out.println(game.getItemDescription());
                }
                if(game.currentItem instanceof Item.MultipleChoice){
                    switch(Item.MultipleChoice) {
                        case 'choice1' : System.out.println(game.currentItem.getChoice1());
                        case 'choice2' : System.out.println(game.currentItem.getChoice2());
                        case 'choice3' : System.out.println(game.currentItem.getChoice3());
                        case 'choice4' : System.out.println(game.currentItem.getChoice4());
                    }

                }

            } else {
                System.out.println("Jeg kan ikke gøre noget ved det.");
            }
        } else if (commandWord == Commands.Inventory) {
            if(game.inventory(command)){
                System.out.println(game.getInventoryDescription());
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


