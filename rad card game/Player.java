/**
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
import java.util.ArrayList;
import java.util.Random;
public class Player {

    // declares all variables to be used in the player class.
    private String name;
    private int tokens;
    private boolean fullhand;
    private ArrayList<Card> myDeck;
    private ArrayList<Spell> mySpellDeck;
    private String message;

    // instantiates all the values to be used by the player type.
    // takes in an argument, and makes the name of the player the argument given.
    public Player(String n){
        name = n;
        tokens = 0;
        fullhand = false;
        myDeck = new ArrayList<>();
        mySpellDeck = new ArrayList<>();
    }

    // returns the players name
    public String getPlayerName(){
        return name;
    }

    // return the deck of the player as an arratlist of type cards.
    public ArrayList<Card> returnDeck(){
        return myDeck;
    }

    // draws a card for the player
    public void drawCard(Card c){

        // if the current hand of the player is less than or equal to 5, then keep drawing and full hand = false
        if (myDeck.size() <= 5) {
            myDeck.add(c);
        }

        // if the player currently has more than 5 cards in hand, it sets the boolean full hand = true.
        else{
            fullhand = true;
        }
    }

    // discards the active card of the player.
    public void discard(){
        myDeck.remove(0);
    }

    // this swaps the players current active card with the strongest determine power (health * power) in the players deck and draws that card.
    public String swap(){

        // declares an array list of type integer.
        ArrayList<Integer> products = new ArrayList<>();

        // sets the message to be sent to the console to an empty string.
        message = "";

        // declares the biggest index to 1 by default.
        int biggestindex = 1;

        // this for loop reiterates through every single card in the deck and calculates their determined power, excluding the active card.
        for(int i = 1; i<5 ;i++){
            int product = myDeck.get(i).getHealth() * myDeck.get(i).getPower();

            // it then adds it to the array list product to be then evaluated later.
            products.add(product);  
        }

        // the current biggest number by default is then declared by the variables, biggest.
        int biggest = products.get(0);

        // this for loop iterates through every single integer in the array list to then declare which card has the biggest determined power.
        for(int i = 1; i<4 ;i++){

            // compares the previous biggest with the current integer in the for array list, if it is bigger then set that integer to the new biggest.
            if(biggest < products.get(i)){
                biggest = products.get(i);

                // it then gets the index of that biggest number plus 1, since it does not include the active card.
                biggestindex = i+1;
            }
        }

        // takes a variable swapped card and declares its contents to be the active card. After that it replaces the current active card with the index of the best card in the deck.
        Card swappedcard = myDeck.get(0);
        myDeck.set(0, myDeck.get(biggestindex));
        
        // it then sets the index of the previous biggest card with the swapped card, effectively swapping the active card to the current biggest card in the deck.
        myDeck.set(biggestindex,swappedcard);

        // reports the swap in a message.
        message += "   " + myDeck.get(0).getName() + " is now active with " + myDeck.get(0).getHealth() + " health.";
        return message;
    }

    // claims a token for the player
    public void claimToken(){
        tokens += 1;
    }

    // returns the token count for the player
    public int getTokens(){
        return tokens;
    }

    // returns the current active card of the player.
    public Card getActiveCard(){
        return myDeck.get(0);
    }

    // returns a boolean value and determines if the deck of the player is full.
    public boolean handIsFull(){
        return fullhand;
    }

    // returns the status report of the player.
    public String statusReport(){

        // declares 3 different array lists.
        ArrayList<Integer> spaces = new ArrayList<>();
        ArrayList<String> diffspace = new ArrayList<>();
        ArrayList<String> diffnumspace = new ArrayList<>();

        // sets message to an empty string and adds the players name.
        message = "";
        message += getPlayerName() + "\n";

        // gets each length of the cards name in the deck and adds them to a list named spaces.
        for(int i = 0;i<5;i++){
            spaces.add(myDeck.get(i).getName().length());
        }

        // by default, declares the longest name to be the first card, or the active card.
        int biggest = spaces.get(0);

        // it then evaluates which card name has the longest length by comparing it to the other cards.
        for(int i = 0;i<5;i++){
            if(biggest < spaces.get(i)){
                biggest = spaces.get(i);
            }
        }

        // after determining which card has the longest name,
        // it then adds 'diffspaces' amount of spaces with (the length of the longest card name - the length of the current card's name) using a for loop.
        String spacing = "";
        for(int i = 0;i<5;i++){
            int diffspaces = biggest - myDeck.get(i).getName().length();

            // it then adds the spaces accordingly per card to make the format as intended.
            for(int j = 0;j<diffspaces;j++){
                spacing = spacing + " ";
            }

            // adds the spaces to an array list 'diffspace'.
            diffspace.add(spacing);
            spacing = "";
        }

        // clears the spaces array list, since now it will be used for the numbers
        spaces.clear();

        // uses a for loop to convert the int type health to a string for every card in the deck. It then adds it to the 'spaces' array list.
        for(int i = 0;i<5;i++){
            String health = Integer.toString(myDeck.get(i).getHealth());
            spaces.add(health.length());
        }

        // uses the same principle as used in the spaces for the length of the card name.
        biggest = spaces.get(0);
        for(int i = 0;i<5;i++){
            if(biggest < spaces.get(i)){
                biggest = spaces.get(i);
            }
        }

        String numberspacing = "";
        for(int i = 0;i<5;i++){
            String health = Integer.toString(myDeck.get(i).getHealth());
            int diffspaces = biggest - health.length();
            for(int j = 0;j<diffspaces;j++){
                numberspacing = numberspacing + " ";
            }
            diffnumspace.add(numberspacing);
            numberspacing = "";   
        }

        // it then goes through a for loop iterating it for every card, with the index of the spaces.
        for(int i = 0;i<5;i++){
            message += "    " +  diffspace.get(i) + myDeck.get(i).getName() + " : " + diffnumspace.get(i) + myDeck.get(i).getHealth() + "\n"; 
        }

        return message;
    }

// spells
// these function are for the new gamemode Spell Madness.
/* How spell madness works:
 *   Each player gets only 1 spell, if they evidently try to draw an additional spell, the game master will not let them.
 *   Each spell has their own unique attributes and some may change the flow of the game if casted in the right time.
 *   If a player draws or casts a spell, their turn will be consumed. Meaning, their turn is used to draw or cast a spell.
 *   At the start of the game, each player is given 1 spell, spells are randomized even if the randomize selection is false.
 */

    // draws a spell for the player.
    public void drawSpell(Spell spell){
        mySpellDeck.add(spell);
    }

    // discards the spell of the player
    public void discardSpell(){
        mySpellDeck.remove(0);
    }

    // returns the spell deck of the player
    public ArrayList<Spell> returnSpellDeck(){
        return mySpellDeck;
    }

    // return the active spell of the player
    public Spell activeSpell(){
        return mySpellDeck.get(0);
    }

    // the features of the spells and what it does is coded here, along with the arguments required for the spell to function.
    public String castSpell(String spellName, Player target, Player player, Boolean weakness, Boolean resistance){

        // declares message as a blank string, and also declares damage as a type integer.
        message = "";
        int damage;
        
        // makes a switch case statement for the spells.
        switch (spellName) {

            // in the case that the player casts a lightning spell, it takes 0.5x the current health of the target card.
            case "Lightning":
                damage = (int) ((int) target.getActiveCard().getHealth() * 0.5);
                target.getActiveCard().takeDamage(damage);
                message += "\n   Lightning spell deals " + damage + " damage to " + target.getActiveCard().getName() + "!!! ";
                break;

            // in the case that the player casts a rage spell, it multiplies the players active cards attack by 2 and also takes into consideration
            // if the target is weak or resistant to the current players card. Stacks multiplicatively.
            case "Rage":
                damage = player.getActiveCard().getPower() * 2;

                if(weakness == true){
                    damage *= 2;
                }
                else if(resistance == true){
                    damage /= 2;
                }

                target.getActiveCard().takeDamage(damage);
                message += "\n   Rage spell enrages " + player.getActiveCard().getName() + " and deals \n   " + damage + " damage to " + target.getActiveCard().getName() + "!!!";
                break;

            // in the case that the player casts a heal spell, the players active card gets 50% of their hp back, e.g. (2hp to 3hp, 100hp to 150hp). Can also result in an overheal.
            case "Heal":
                int heal = (int) ((int)player.getActiveCard().getHealth() * 0.5);
                player.getActiveCard().updateHealth(heal);
                message += "\n   Heal spell rejuvenates " + player.getActiveCard().getName() + " and heals it for " + heal +"HP!!! ";
                message += "\n   " + player.getActiveCard().getName() + " now has " + player.getActiveCard().getHealth() + "HP.";
                break;

            // in the case that the player casts a smite spell, the target card is obliterated instantly, taking the health of the target card and also dealing that much damage.
            // Effectively killing it instantly.
            case "Smite":
                damage = target.getActiveCard().getHealth();
                target.getActiveCard().takeDamage(damage);
                message += "\n   Smite spell obliterates " + target.getActiveCard().getName() +"!!!";
                break;

            // in the case that the player casts a duel spell, a random number generator is created named 'coinflip' if the coin is 0 or heads
            // then the player's active card is killed instantly, if it is tails, or a 1, then the player who casted the spell wins instantly killing the active card of the target player.
            // just a worse version of the smite spell, rng based.
            case "Duel":
                Random coinflip = new Random();
                int decide = coinflip.nextInt(2);
                message += "\n   " + player.getPlayerName() + " tests their chances...\n";

                if(decide == 1){
                    damage = target.getActiveCard().getHealth();
                    target.getActiveCard().takeDamage(damage);
                    message += "   " + player.getPlayerName() + " wins the coin toss and kills " + target.getPlayerName() + "'s " + target.getActiveCard().getName() + "!!!";
                }
                else{
                    damage = player.getActiveCard().getHealth();
                    player.getActiveCard().takeDamage(damage);
                    message += "   " + player.getPlayerName() + " loses the coin toss and kills their own card!!!";
                }
                break;

            // in the case that the player casts a blood exchange spell, the health of the players active card is swapped with the health of the target players active card.
            case "Blood Exchange":
                int exchange1 = player.getActiveCard().getHealth();
                int exchange2 = target.getActiveCard().getHealth();
                player.getActiveCard().replaceHealth(exchange2);
                target.getActiveCard().replaceHealth(exchange1);
                message += "\n   " + player.getActiveCard().getName() + " swaps health with " + target.getActiveCard().getName() + "!!!\n   " + player.getActiveCard().getName()
                + " now has " + player.getActiveCard().getHealth() + "HP.\n   " + target.getActiveCard().getName() + " now has " + target.getActiveCard().getHealth()+"HP.";
                break;

            // in the case that the player casts a heroic stand spell, it has 2 prompts. The first one being if the players active cards health is not less than or equal to 60
            // if that is the case, then the spell does not activate and the turn is skipped (the spell does not get used.)
            // The second one is when the players active card does have less than or equal 60 health
            // in that case, the players card is blessed and grants it 3x attack for the whole game until it is killed (stackable).
            case "Heroic Stand":
                if(player.getActiveCard().getHealth() <= 60){
                    player.getActiveCard().updateAttack(player.getActiveCard().getPower() * 3);
                    message += "\n   " + player.getActiveCard().getName() + " has been blessed by the heroes of old and now has 3x attack."; 
                }

                else{
                    player.drawSpell(new Spell("Heroic Stand"));
                    message += "\n   This spell does not seem to affect " + player.getActiveCard().getName() + ". (must be 60 health and below)";
                }
                break;
            
            // in the case that the player casts a swap spell, it basically does the opposite of the swap function for the player.
            // a copy paste of the swap function, except it gets the smallest determined product instead, by swapping the < to a > to get the smallest number.
            // evidently either skipping the turn of the target player, or having them attack with their weakest card.
            case "Swap":
                ArrayList<Integer> products = new ArrayList<>();
                message += "\n   Swap Spell confuses " + target.getPlayerName() + "...\n";
                int smallestindex = 1;
                for(int i = 1; i<5 ;i++){
                    int product = target.myDeck.get(i).getHealth() * target.myDeck.get(i).getPower();
                    products.add(product);  
                }
                int smallest = products.get(0);
                for(int i = 1; i<4 ;i++){
                    if(smallest > products.get(i)){
                        smallest = products.get(i);
                        smallestindex = i+1;
                    }
                }
                Card swappedcard = target.myDeck.get(0);
                target.myDeck.set(0, target.myDeck.get(smallestindex));
                target.myDeck.set(smallestindex,swappedcard);
                message += "   "+ target.getPlayerName() + " swaps " + target.myDeck.get(smallestindex).getName() + " with " + target.myDeck.get(0).getName() + ".";

            default:
                break;
            }
            return message;
        }
    }
