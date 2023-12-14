/**
This is a template for a Java file.
@author Alba, Martin Darius L. (230179)
@version November 29,2023
**/
/*
I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.
I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.
*/
import java.io.FileNotFoundException;
import java.util.*;
public class GameConsole {

    // makes sure that it handles an error given by the 'newDeck.txt' file
    public static void main(String[]args) throws FileNotFoundException{

        // declares a scanner to get player input.
        Scanner input = new Scanner(System.in);

        // it then welcomes the 2 players given by the command prompt line.
        System.out.println("Welcome, " + args[0] + " and " + args[1]);
        System.out.println("The game begins.\n");

        // we set these 2 players with their own Player type.
        Player dude1 = new Player(args[0]);
        Player dude2 = new Player(args[1]);
        boolean r, k, g;

        // in the case that the user inputs the key word "random", the random deck is then set to true, else it is false.
        if(args[2].equalsIgnoreCase("random")){
            r = true;
        }
        else{
            r = false;
        }

        // in the case that the user inputs the key word "new", the new deck is then assembled instead of the default deck. Else, it assembles the default deck.
        if(args[3].equalsIgnoreCase("new")){
            k = true;
        }
        else{
            k = false;
        }

        // in the case that the user inputs the key word "spell", the spell gamemode is activated, else it does not activate.
        if(args[4].equalsIgnoreCase("spell")){
            g = true;
        }
        else{
            g = false;
        }

        // we then set these variables as the arguments for our GameMaster named cardMaster.
        GameMaster cardMaster = new GameMaster(dude1, dude2, r, k, g);
        System.out.print(cardMaster.dealCard());

        // if the new gamemode is not triggerd, we prompt the player to either perform an attack or a swap.
        if(g == false){
        System.out.print("Attack or Swap? ");
        }

        // if the new gamemode is triggerd, then it adds two new moves the player can use.
        else{
            System.out.print("Attack, Swap, Spell, or DrawSpell? ");
        }

        // while a winner is not declared, it will keep asking for inputs.
        while (cardMaster.hasWinner() == false){

            // the action is then set as a player input.
            String action = input.nextLine();

            // we then print out the message with the player input.
            System.out.print(cardMaster.play(action));

            // we double check in the loop since this will trigger even after a player has won after the turn.
            if(cardMaster.hasWinner() == false){

                // same with the code above, where it edits the message to be printed out accordingly.
                if(g == false){
                System.out.print("\n \nAttack or Swap? ");
                }
                else{
                    System.out.print("\n \nAttack, Swap, Spell, or DrawSpell? ");
                }
            }
        }
        
        // after the game ends, the game report is then shown.
        System.out.print(cardMaster.gameReport());
        input.close();
        
    }
}
