/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldOfZuul.textUI;

import worldOfZuul.Domain.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author ancla
 */
public class CommandLineClient {

    private final Parser parser;
    private final Game game;
    public Scanner valg;
    public static int choice;


    public CommandLineClient() {
        game = new Game();
        parser = new Parser(game);
        valg = new Scanner(System.in);
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
        System.out.println("Du starter med 0 point, og dit mål er at få så mange point som muligt, ud fra dine klimabevidste handlinger.");
        System.out.println();
        System.out.println("Du kan skrive '" + Commands.GO + " + [udgange]' at gå til forskellige steder, '"
                + Commands.LOOK + "' eller '" + Commands.LOOK + " + [objekt]' at kigge rundt eller på et objekt, \n'"
                + Commands.USE + " + [objekt]' at at interagere med et objekt og '" + Commands.Inventory + "' for at se hvad du har samlet op.");
        System.out.println("Skrive '" + Commands.HELP + "' hvis du har brug for hjælp.");
        System.out.println("Når du ønkser at lukke programmet skal du skrive 'afslut'.");


        //Læser score.txt filen
        try {
            Scanner reader = new Scanner(game.scoreFile);
            System.out.println("Din sidste score var: "+reader.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("Kan ikke finde scorefil");
            throw new RuntimeException(e);
        }


        System.out.println("Held og lykke! :P");
        System.out.println();
        System.out.println("Du vågner op fra din middagslur, det er eftermiddag.");
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
                    game.switchItemState(command);///Moved down here so the ChoiceItem only active switchItemState when chosen an answer
                    System.out.println("Du samlede op: ");
                    System.out.println(game.getItemDescription());

                }else if (game.currentItem instanceof Item.ToggleItem) {
                    game.switchItemState(command);
                    System.out.println(game.getItemDescription());

                }else if (game.currentItem instanceof Item.MultipleChoice) {
                    System.out.println(game.getItemDescription());
                    System.out.println(game.getChoice());
                    System.out.print("> ");
                    if(!valg.hasNextInt()){
                        System.out.println("Det er ikke et af de fire valg! (Skriv et tal fra 1 til 4)");
                    }else {
                        choice = valg.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println(game.currentItem.choice1Text);
                                break;
                            case 2:
                                System.out.println(game.currentItem.choice2Text);
                                break;
                            case 3:
                                System.out.println(game.currentItem.choice3Text);
                                break;
                            case 4:
                                System.out.println(game.currentItem.choice4Text);
                                break;
                            default:
                                System.out.println("Det er ikke et af de fire valg! (Skriv et tal fra 1 til 4)");
                        }
                    }

                }else if(game.currentItem instanceof Item.ChoiceItem) {     //There must be a way to not copy this...
                    System.out.println(game.getItemDescription());
                    System.out.println(game.getChoice());
                    System.out.print("> ");
                    if (!valg.hasNextInt()) {
                        System.out.println("Det er ikke et af de fire valg! (Skriv et tal fra 1 til 2)");
                    } else {
                        choice = valg.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println(game.currentItem.choice1Text);
                                game.switchItemState(command);
                                break;
                            case 2:
                                System.out.println(game.currentItem.choice2Text);
                                game.switchItemState(command);
                                break;
                            default:
                                System.out.println("Det er ikke et af de fire valg! (Skriv et tal fra 1 til 2)");

                        }
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
