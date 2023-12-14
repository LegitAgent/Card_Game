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
import java.util.*;
import java.io.*;
public class GameMaster {

    // declares all variables to be used in this class.
    private int turnCounter;
    private Player firstPlayer, secondPlayer, player, target, deadPlayer, winnerPlayer;
    private boolean randomization, brandnewDeck, newGamemode;
    private String message;
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Spell> spellDeck = new ArrayList<>();
    Random random = new Random();

    // asssembles the deck by adding in new cards and calling the card class's constructor and its arguments.
    private void assembleDeck(){
        deck.add( new Card( "Dragon", "Aquira", 174, 26 ) );
        deck.add( new Card( "Ghost", "Brawn", 130, 48 ) );
        deck.add( new Card( "Fairy", "Cerulea", 162, 29 ) );
        deck.add( new Card( "Dragon", "Demi", 147, 28 ) );
        deck.add( new Card( "Ghost", "Elba", 155, 37 ) );   
        deck.add( new Card( "Fairy", "Fye", 159, 42 ) );
        deck.add( new Card( "Dragon", "Glyede", 129, 26 ) );
        deck.add( new Card( "Ghost", "Hydran", 163, 35 ) );
        deck.add( new Card( "Fairy", "Ivy", 146, 45 ) );
        deck.add( new Card( "Dragon", "Jet", 170, 24 ) );
        deck.add( new Card( "Ghost", "Kineti", 139, 21 ) );
        deck.add( new Card( "Fairy", "Levi", 160, 43 ) );
        deck.add( new Card( "Dragon", "Meadow", 134, 29 ) );
        deck.add( new Card( "Ghost", "Naidem", 165, 26 ) );
        deck.add( new Card( "Fairy", "Omi", 145, 21 ) );
        deck.add( new Card( "Dragon", "Puddles", 170, 34 ) );   
        deck.add( new Card( "Ghost", "Quarrel", 151, 29 ) );
        deck.add( new Card( "Fairy", "Raven", 168, 32 ) );
        deck.add( new Card( "Dragon", "Surge", 128, 27 ) );
        deck.add( new Card( "Ghost", "Takiru", 140, 26 ) );
        deck.add( new Card( "Fairy", "Ustelia", 163, 47 ) );
        deck.add( new Card( "Dragon", "Verwyn", 145, 25 ) );
        deck.add( new Card( "Ghost", "Wyverin", 158, 32 ) );
        deck.add( new Card( "Fairy", "Xios", 155, 27 ) );
        deck.add( new Card( "Dragon", "Yora", 159, 44 ) );
        deck.add( new Card( "Ghost", "Zulu", 125, 46 ) );
    }

    // assembles the spell deck with the spells name. Assembles it 4 times to make sure that the spell deck does not run out.
    private void assembleSpellDeck() {
        for(int i = 0 ; i < 3; i++){
            spellDeck.add(new Spell("Rage"));
            spellDeck.add(new Spell("Lightning"));  
            spellDeck.add(new Spell("Smite"));
            spellDeck.add(new Spell("Swap"));
            spellDeck.add(new Spell("Heal"));
            spellDeck.add(new Spell("Duel"));
            spellDeck.add(new Spell("Blood Exchange"));
            spellDeck.add(new Spell("Heroic Stand"));
        }
    }

    // assembles the deck from a text file called 'newCards.txt'.
	private void newDeck()

    // error handles it by throwing a FileNotFoundException error.
    throws FileNotFoundException
    {
        
        // declares the variables to be used by the cards.
        String[] cardLine;
        String name, type;
        int attack, health;

        // declares a new FileReader with the name 'reader'
        FileReader reader = new FileReader("newCards.txt");

        // the scanner named input then scans through the FileReader, making it print out the lines.
        Scanner input = new Scanner(reader);

        // while the input scans and reads a line, this while statement will keep running, by calling the function, hasNextLine().
        // which will return true as long as there is a line after the input.
        while(input.hasNextLine()){

            // declares all default values of the stats of the card.
            name = "";
            type = "";
            health = 0;
            attack = 0;

            // declares the variable 'lines' to be equal to the line scanned by the input from the FileReader.
            String lines = input.nextLine();

            // it then splits the input with the delimiter that is a space.
            // it then puts it in an array named cardLine.    
            cardLine = lines.split(" ");

            // sets a counter, i, to then set each and every variable to their respective values.
            int i = 0;

            // this for each loop then iterates through the cardLine array for every element, increasing i by 1.
            // effectively declaring each variable with their correct values because of the format of the newDeck.txt file.
            for(String line : cardLine){
                
                if(i == 0){
                    type = line;
                }
                else if(i == 1){
                    name = line;
                }

                // converts the strings of the txt file to their integer counter part.
                else if(i == 2){
                    health = Integer.parseInt(line);
                }
                else if(i == 3){
                    attack = Integer.parseInt(line);
                }
                i++;
            }

            // it then adds a card with all those components and loops through the whole process again until the 'newDeck.txt' has no more lines to read from.
            deck.add(new Card(type, name, health, attack));
        }
        input.close();
    }

    // instantiates the GameMaster constructor, which accepts 2 players and 3 booleans as an argument.
    // also error handles the FileNotFoundException, since the function newDeck is called here.
    public GameMaster(Player a, Player b, boolean r, boolean k, boolean g) throws FileNotFoundException{

        // declares all the values with their corresponding boolean arguments as gamemodes.
        turnCounter = 10;
        brandnewDeck = k;
        newGamemode = g;

        // if the user decides to have a new deck, or a whole new deck instead of the default one, it calls the new deck, else it assembles the normal deck.
        if(brandnewDeck == false){
            assembleDeck();
        }
        else{
            newDeck();
        }

        // if the user decides to play with the spell gamemode on, it assembles the spell deck.
        if(newGamemode == true){
            assembleSpellDeck();
        }

        // declares all other values from the argument with their own respective values.
        firstPlayer = a;
        secondPlayer = b;
        randomization = r;
    }

    // return the turn counter
    public int getTurnCounter(){
        return turnCounter;
    }

    // updates the turn counter with the argument getting added with the turn counter.
    public void updateTurnCounter(int update){
        turnCounter = turnCounter + update;
    }

    // type1 = target, type2 = card in play
    // checks if the card type is resistant to another cards type
    public boolean checkResistance(String type1, String type2){

        boolean checker;

        // if the newDeck was not assembled, then the resistance parameters are the one for default classes.
        if(brandnewDeck == false){
            if (type1.equalsIgnoreCase("Dragon") && type2.equalsIgnoreCase("Ghost")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Ghost") && type2.equalsIgnoreCase("Fairy")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Fairy") && type2.equalsIgnoreCase("Dragon")){
                checker = true;
            }
            else{
                checker = false;
            }   
            return checker;
        }

        // other wise, it checks for the new card types and their resistances.
        else{
            if (type1.equalsIgnoreCase("Human") && type2.equalsIgnoreCase("God")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("God") && type2.equalsIgnoreCase("Alien") || type1.equalsIgnoreCase("God") && type2.equalsIgnoreCase("Robot")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Robot") && type2.equalsIgnoreCase("Human")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Alien") && type2.equalsIgnoreCase("Robot")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Human") && type2.equalsIgnoreCase("Alien")){
                checker = true;
            }
            else{
                checker = false;
            }   
            return checker;
        }

    }

// type1 = target card, type2 = card in hand
// check the current weakness of the card by taking in the type of cards.
    public boolean checkWeakness(String type1, String type2){

        boolean checker;

        // same logic as above, it will only check if the brandNewDeck gamemode is false.
        if(brandnewDeck == false){
            if (type1.equalsIgnoreCase("Dragon") && type2.equalsIgnoreCase("Fairy")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Fairy") && type2.equalsIgnoreCase("Ghost")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Ghost") && type2.equalsIgnoreCase("Dragon")){
                checker = true;
            }
            else{
                checker = false;
            }
            return checker;
        }

        // else it will check the weaknesses of the new deck.
        else{
            if (type1.equalsIgnoreCase("God") && type2.equalsIgnoreCase("Human")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Alien") && type2.equalsIgnoreCase("God") || type1.equalsIgnoreCase("Robot") && type2.equalsIgnoreCase("God")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Human") && type2.equalsIgnoreCase("Robot")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Robot") && type2.equalsIgnoreCase("Alien")){
                checker = true;
            }
            else if(type1.equalsIgnoreCase("Alien") && type2.equalsIgnoreCase("Human")){
                checker = true;
            }
            else{
                checker = false;
            }   
            return checker;
        }

    }

    // deals the cards at the start of the game.
    public String dealCard(){

        // it declares the variables to be used in this function, and sets the message to be sent to an empty string.
        int index;
        message = "";

        // uses a for loop to iterate through the cards to be dealt
        for(int i = 0; i < 10;i++){

            // if the random deck deal is disabled, then it only deals the top most card.
            if(randomization == false){
                index = 0;
            }

            // else it gets a random number from 0 to the size of the deck since the bound is exclusive.
            else{
                index = random.nextInt(deck.size());
            }

            // sees if the player to get the next card is player 1 or player 2, if i is even, then the player who will draw is player 1, if it is odd then it is player 2.
            if(i % 2 == 0){
                player = firstPlayer;
            }
            else{
                player = secondPlayer;
            }

            // that player then draws the card and prints out a message to broadcast what the player got.
                player.drawCard(deck.get(index));
                message += player.getPlayerName() + " draws " + deck.get(index).getName() + ". \n \n";

                // this is exclusive for the new deck where a clone was included.
                // wanted to make a cool feature where the clone would have the players name instead of the usual 'clone', so it would print out '[player name] clone' as its name.
                if(deck.get(index).getName().equalsIgnoreCase("clone")){
                    deck.get(index).changeName(player.getPlayerName() + " clone");
                }

                // we then remove the index to where that card was to shoft the array of cards to the left.
                deck.remove(index);

                // if the players hand is full, then a message will print out saying that the players hand is full.
                if(player.handIsFull() == true){
                    message += player.getPlayerName() + " hand is full.\n \n";
                    break;
                }

            }

        // this is for the spell gamemode, where the player only draws a single spell from the spell deck and broadcasts it similarly to when you draw a card.
        // how the spell draws work will always be randomized, regardless of the random gamemode.
        if(newGamemode = true && spellDeck.size() >= 1){

            index = random.nextInt(spellDeck.size());
            firstPlayer.drawSpell(spellDeck.get(index));
            message += firstPlayer.getPlayerName() + " draws a " + firstPlayer.activeSpell().returnSpellName() + " spell. \n \n";
            spellDeck.remove(index);

            // it also removes the spell by getting the index of that spell and using the .remove function to dispose of the spell.
            index = random.nextInt(spellDeck.size());
            secondPlayer.drawSpell(spellDeck.get(index));
            message += secondPlayer.getPlayerName() + " draws a " + secondPlayer.activeSpell().returnSpellName() + " spell. \n \n";
            spellDeck.remove(index);

        }
        
        return message;
    }

    // it is to determine how much damage a card will take and how much damage it will deal.
    public String dealDamage(Card inPlay, Card target){

        // declares the variables to be used.
        int damage;
        message = "";

        // if the weakness function returns true given the types of the card that are being dealt, then the card in play's damage is multiplied by 2 to the target card.
        if(checkWeakness(target.getType(), inPlay.getType()) == true){
            damage = inPlay.getPower() * 2;
            message += "      " + target.getType() + " is weak to " +inPlay.getType() + ".\n";
        }

        // else, if the target card is resistant to the card in play, then the damage is then halved.
        else if(checkResistance(target.getType(), inPlay.getType())){
            damage = inPlay.getPower() / 2;
            message += "      " + target.getType() + " is resistant to " + inPlay.getType() +".\n";
        }

        // if the card in play has no significant interaction with the target card, then its damage will just be its normal attack.
        else{
            damage = inPlay.getPower();
        }

        // we then use the takeDamage() function to update the health of the target card.
        target.takeDamage(damage);
        message += "   " + inPlay.getName() + " deals " + damage + " damage on " + target.getName() + ".\n";
        message += "   " + target.getName() + " has " + target.getHealth() + " health left.";
        return message;

    }
    
    // is true if any player reaches 3 tokens.
    public boolean hasWinner(){

        // if player 1 or player 2 has 3 tokens then the game has a winner.
        if(firstPlayer.getTokens() == 3 || secondPlayer.getTokens() == 3){
            return true;
        }

        // else, the game continues.
        else{
            return false;
        }

    }

    // returns that status report of each player, this also includes the turn counter of the game to be displayed.
    public String gameReport(){

        message = "";
        message += "\n---=== GAME SUMMARY ===---";
        message += "\nThis game lasted "+ turnCounter +" turns\n";
        message += firstPlayer.statusReport();
        message += secondPlayer.statusReport();
        return message;

    }

    // determines what move the player does.
    public String play(String action){

        // sets the message variable to an empty string.
        message = "";

        // if the turn counter is even, then that means it is player 1's turn, so the target player is then player 2
        if(turnCounter % 2 == 0){
            player = firstPlayer;
            target = secondPlayer;
        }

        // else, if the turn counter is odd, then it is player 2's turn and vice versa.
        else{
            player = secondPlayer;
            target = firstPlayer;
        }

        // if the action is the string "swap" case insensitive, then it calls the swap function from the player and reports the play by adding it to the message, whcoh will be returned later.
        if(action.equalsIgnoreCase("SWAP")){
            message += "   " + player.getPlayerName() +" swaps out " + player.getActiveCard().getName() + "...\n";
            message += player.swap();
        }
        
        // if the action is the string "attack" case insensitive, then it calls the dealDamage() function with its own corresponding arguments. 
        else if(action.equalsIgnoreCase("ATTACK")){
            message += "   " + player.getPlayerName() + " attacks with " + player.getActiveCard().getName() + ".\n";
            message += dealDamage(player.getActiveCard(), target.getActiveCard());
        }

        // if the spell gamemode has been set to true, then the player can now do 2 additional things.
        if(newGamemode == true){

            // in the case that the player inputs the string "spell" case insenstive, then the player casts the spell in hand.
            if(action.equalsIgnoreCase("SPELL")){

                // it will only trigger if the player actually has a spell.
                if(player.returnSpellDeck().size() >= 1){

                    // it calls the castSpell function with its corresponding arguments.
                    message += player.getPlayerName() + " casts " + player.activeSpell().returnSpellName() + " spell.";
                    message += player.castSpell(player.activeSpell().returnSpellName(), target, player,
                                                checkWeakness(target.getActiveCard().getType(), player.getActiveCard().getType()),
                                                checkResistance(target.getActiveCard().getType(), player.getActiveCard().getType()));

                    // after the player casts the spell, the spell is then discarded and the player now has no spells in hand.
                    // it has to specify that the size of the deck is EQUAL to 1 since the Heroic Stand spell does not get used and should not report that the player has discard the spell.
                    // this works since the player draws the heroic stand spell again in the function. Evidently making the spell deck size to 2.
                    if(player.returnSpellDeck().size() == 1){
                        message += "\n   " + player.getPlayerName() + " discards " + player.activeSpell().returnSpellName() + " Spell.";
                    }
                    player.discardSpell();
                }

                // in the case that you do not have a spell in your deck, it reports a custom message and skips your turn.
                else{
                    message += "You do not have a spell in your deck.";
                }
                
            }

            // in the case that the player inputs the string "drawspell" case insensitive, then the player will draw a spell.
            else if(action.equalsIgnoreCase("DRAWSPELL")){

                // if the players spell deck has no spells, then it will draw a spell and get skip the players turn as a cost.
                if(player.returnSpellDeck().size() < 1){
                    int index;
                    index = random.nextInt(spellDeck.size());
                    player.drawSpell(spellDeck.get(index));
                    message += "   " + player.getPlayerName() + " draws a " + player.activeSpell().returnSpellName() + " spell.";
                }

                // if the player has a spell already, then it reports a custom message and also skips the players turn.
                else{
                    message += "You cannot draw a spell!! (maximum 1 spell per player)";
                }
            }
        }

        // if the players card or the target players card dies, then it will execute the ff:
        if(player.getActiveCard().getHealth() <= 0 || target.getActiveCard().getHealth() <= 0){

            // it will first determine which is the deadplayer and the player that inflicted the damage by the variable 'winnerPlayer'.
            if(target.getActiveCard().getHealth() <= 0){
                deadPlayer = target;
                winnerPlayer = player;
            }

            // else it does the opposite.
            else{
                deadPlayer = player;
                winnerPlayer = target;
            }

            // it first discards the dead card.
            message += "\n   " + deadPlayer.getPlayerName() + " discards " + deadPlayer.getActiveCard().getName() + ".\n";
            deadPlayer.discard();

            // then gets the first 2 cards on top of the deck to determine which is stronger.
            Card detercard1 = deck.get(0);
            Card detercard2 = deck.get(1);

            // removes both of them from the deck.
            deck.remove(0);
            deck.remove(0);

            // we then get their determined power by doing the formula provided.
            int detercard1pow = detercard1.getHealth() * detercard1.getPower();
            int detercard2pow = detercard2.getHealth() * detercard2.getPower();
            Card betterCard, returnCard;

            // if the power of the first card is better, then the card master will give that card to the player with the discard card.
            if(detercard1pow > detercard2pow){
                betterCard = detercard1;
                returnCard = detercard2;
            }

            // else it does the opposite, where the second card is given.
            else{
                betterCard = detercard2;
                returnCard = detercard1;
            }

            // the deadPlayer then draws the card and the returnCard is then added back in to the back of the deck.
            // it then reports a message regarding this interaction.
            deadPlayer.drawCard(betterCard);
            deck.add(returnCard);
            message += "   " + deadPlayer.getPlayerName() + " draws " + betterCard.getName() + ".\n";

            // the winnerPlayer then claims a token since the player has eliminated one of the cards of the opposing player.
            winnerPlayer.claimToken();
            message += "   " + winnerPlayer.getPlayerName() + " gets a token!";

            // it then checks if the hasWinner() function returns true.
            if(hasWinner() == true){

                // if it does, then it will report a message.
                message += "\n   " + player.getPlayerName() + " wins!!!\n";
            }

        }
        
        // increments turn counter by 1.
        turnCounter += 1;
        return message;

    }
}
