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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.*;
public class GameGUI implements ActionListener{
// declares all variables used in the GUI.
    private JFrame gui;
    private JTextField player1TextField, player2TextField;
    private JLabel player1JLabel, player2JLabel, titleJLabel, invalidJLabel, turncounterJLabel, pOneDeckLabel, pTwoDeckLabel, activeCardLabel, vsLabel;
    private JLabel playerOneActiveLabel, playerOneFirstLabel, playerOneSecondLabel, playerOneThirdLabel, playerOneFourthLabel, playerOneFifthLabel, playerOneSpellLabel;
    private JLabel playerTwoActiveLabel, playerTwoFirstLabel, playerTwoSecondLabel, playerTwoThirdLabel, playerTwoFourthLabel, playerTwoFifthLabel, playerTwoSpellLabel;
    private JTextArea matchArea, matchHistoryArea;
    private JButton playGame, attackButton, swapButton, restartButton, quitButton, matchHistoryButton, castSpell, drawSpell;
    private JButton playerOneActiveCard, playerOneFirstCard, playerOneSecondCard, playerOneThirdCard, playerOneFourthCard, playerOneFifthCard, playerOneSpellCard;
    private JButton playerTwoActiveCard, playerTwoFirstCard, playerTwoSecondCard, playerTwoThirdCard, playerTwoFourthCard, playerTwoFifthCard, playerTwoSpellCard;
    private JCheckBox gameModeCheckBox, newDeckCheckBox, randomDealCheckBox;
    private ImageIcon winningIcon, swordIcon, swapIcon, restartIcon, quitIcon, matchHistoryIcon;
    private ImageIcon robotCard, dragonCard, godCard, alienCard, humanCard, fairyCard, ghostCard;
    private ImageIcon lightningSpell, rageSpell, healSpell, swapSpell, bloodExchangSpell, heroicStandSpell, duelSpell, smiteSpell, noSpell;
    private JScrollPane matchScrollPane;
    private String player1Name, player2Name, matchHistory, roundString, dealCardString, cardName, spellName;
    private boolean gameMode, newDeck, randomDeal;
    private ArrayList<Card> playerDeck;
    private Card card;
    private Spell spell;
    private Player player1, player2, player;
    private GameMaster cardMaster;
    Font nameFont = new Font("Serif Plain", Font.PLAIN, 10);

    // instanciates the constructor of the GameGUI
    public GameGUI(){
        gui = new JFrame();
        introScreen();
    }

    // declares a function to instantiate the intro screen.
    public void introScreen(){

        // makes new components to be used in the introScreen function.
        player1TextField = new JTextField();
        player2TextField = new JTextField();

        player1JLabel = new JLabel("Player 1:");
        player2JLabel = new JLabel("Player 2:");
        invalidJLabel = new JLabel();
        titleJLabel = new JLabel("Tribe Tussle");

        gameModeCheckBox = new JCheckBox();
        newDeckCheckBox = new JCheckBox();
        randomDealCheckBox = new JCheckBox();

        playGame = new JButton("Play");

        // we set the gui to its respectove width and height (800 x 600).
        // we also set the layout manager to null, since we will be putting the layout pixel by pixel with no order.
        gui.setSize(800,600);
        gui.setLayout(null);

        // sets the close operation of the GUI, as well as its title.
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Card Game");

        // we set the buttons to and check boxes to non focusable so that there are no borders surrounding the button or checkbox.
        playGame.setFocusable(false);
        gameModeCheckBox.setFocusable(false);
        newDeckCheckBox.setFocusable(false);
        randomDealCheckBox.setFocusable(false);

        // sets the font of the title.
        titleJLabel.setFont(new Font("Times New Roman",Font.BOLD,50));

        // sets the texts of the check boxes.
        gameModeCheckBox.setText("Spell Madness");
        newDeckCheckBox.setText("Sci-Fi Deck");
        randomDealCheckBox.setText("Random Deal");

        // puts specific coordinates for the components in the intro screen.
        playGame.setBounds(350, 400, 100, 30);
        gameModeCheckBox.setBounds(150, 350, 120, 40);
        newDeckCheckBox.setBounds(550, 350, 100, 40);
        randomDealCheckBox.setBounds(350, 350, 100, 40);
        player1JLabel.setBounds(190, 255, 100, 30);
        player2JLabel.setBounds(190, 300, 100, 30);
        titleJLabel.setBounds(265, 70, 300, 50);
        invalidJLabel.setBounds(340, 230, 150, 30);
        player1TextField.setBounds(250, 255, 300, 30);
        player2TextField.setBounds(250, 300, 300, 30);

        // adds an action listener to the button playGame to make it do certain things.
        playGame.addActionListener(this);

        // adds all those components declares above to the gui.
        gui.add(playGame);
        gui.add(player1TextField);
        gui.add(player2TextField);
        gui.add(player1JLabel);
        gui.add(player2JLabel);
        gui.add(invalidJLabel);
        gui.add(titleJLabel);
        gui.add(gameModeCheckBox);
        gui.add(newDeckCheckBox);
        gui.add(randomDealCheckBox);

        // we also set the gui to be visible.
        gui.setVisible(true);

    }


    // initializes the roundStart interface with its components and features.
    private void roundStart() throws FileNotFoundException{

        // sets the text of matchHistory to an empty string.
        matchHistory = "";

        // makes all the new components to be used in the roundStart function with their respective types.
        attackButton = new JButton("Attack");
        swapButton = new JButton("Swap");
        quitButton = new JButton("Quit");
        restartButton = new JButton("Restart");
        matchHistoryButton = new JButton("View Match History");

        playerOneActiveCard = new JButton();
        playerOneFirstCard = new JButton();
        playerOneSecondCard = new JButton();
        playerOneThirdCard = new JButton();
        playerOneFourthCard = new JButton();
        playerOneFifthCard = new JButton();

        playerTwoActiveCard = new JButton();
        playerTwoFirstCard = new JButton();
        playerTwoSecondCard = new JButton();
        playerTwoThirdCard = new JButton();
        playerTwoFourthCard = new JButton();
        playerTwoFifthCard = new JButton();

        playerOneActiveLabel = new JLabel();
        playerOneFirstLabel = new JLabel();
        playerOneSecondLabel = new JLabel();
        playerOneThirdLabel = new JLabel();
        playerOneFourthLabel = new JLabel();
        playerOneFifthLabel = new JLabel();

        playerTwoActiveLabel = new JLabel();
        playerTwoFirstLabel = new JLabel();
        playerTwoSecondLabel = new JLabel();
        playerTwoThirdLabel = new JLabel();
        playerTwoFourthLabel = new JLabel();
        playerTwoFifthLabel = new JLabel();

        turncounterJLabel = new JLabel("It is " + player1.getPlayerName() + "'s turn!");
        pOneDeckLabel = new JLabel(player1Name + "'s Deck");
        pTwoDeckLabel = new JLabel(player2Name + "'s Deck");
        activeCardLabel = new JLabel("Active Cards:");
        vsLabel = new JLabel("vs");

        matchArea = new JTextArea();
        matchHistoryArea = new JTextArea(matchHistory);

        // instantiates all icons to be used in the roundStart function.
        winningIcon = new ImageIcon("confetti.png");
        swordIcon = new ImageIcon("sword.png");
        swapIcon = new ImageIcon("swapcard.png");
        restartIcon = new ImageIcon("restart.png");
        quitIcon = new ImageIcon("quit.png");
        matchHistoryIcon = new ImageIcon("matchhistory.png");

        robotCard = new ImageIcon("robotcard.png");
        dragonCard = new ImageIcon("dragoncard.png");
        godCard = new ImageIcon("godcard.png");
        alienCard = new ImageIcon("aliencard.png");
        humanCard = new ImageIcon("humancard.png");
        fairyCard = new ImageIcon("fairycard.png");
        ghostCard = new ImageIcon("ghostcard.png");

        // sets the labels for some JLabels
        turncounterJLabel.setFont(new Font("Times New Roman",Font.BOLD,20));
        vsLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));

        // sets where each and every component needs to be.
        attackButton.setBounds(50, 140, 120, 40);
        swapButton.setBounds(230, 140, 120, 40);
        quitButton.setBounds(230, 140, 120, 40);
        restartButton.setBounds(50, 140, 120, 40);
        matchHistoryButton.setBounds(550,20,220,40);
        matchArea.setBounds(50, 200, 300, 320);
        turncounterJLabel.setBounds(20,10,300,40);
        player1JLabel.setBounds(20, 50, 300, 30);
        player2JLabel.setBounds(20,80,300,30);
        pOneDeckLabel.setBounds(410, 150, 300, 30);
        pTwoDeckLabel.setBounds(410,340,300,30);
        activeCardLabel.setBounds(410, 0, 100, 30);
        vsLabel.setBounds(442, 45, 20, 30);

        playerOneActiveCard.setBounds(400, 30, 40, 70);
        playerOneFirstCard.setBounds(410, 180, 40, 70);
        playerOneSecondCard.setBounds(480, 180, 40, 70);
        playerOneThirdCard.setBounds(550, 180, 40, 70);
        playerOneFourthCard.setBounds(620, 180, 40, 70);
        playerOneFifthCard.setBounds(690, 180, 40, 70);

        playerTwoActiveCard.setBounds(460, 30, 40, 70);
        playerTwoFirstCard.setBounds(410, 370, 40, 70);
        playerTwoSecondCard.setBounds(480, 370, 40, 70);
        playerTwoThirdCard.setBounds(550, 370, 40, 70);
        playerTwoFourthCard.setBounds(620, 370, 40, 70);
        playerTwoFifthCard.setBounds(690, 370, 40, 70);

        // sets the coordinates of the labels and their fonts.
        playerOneActiveLabel.setBounds(395, 105, 50, 10); playerOneActiveLabel.setFont(nameFont);
        playerOneFirstLabel.setBounds(410, 255, 40, 10); playerOneFirstLabel.setFont(nameFont);
        playerOneSecondLabel.setBounds(480, 255, 40, 10); playerOneSecondLabel.setFont(nameFont);
        playerOneThirdLabel.setBounds(550, 255, 40, 10); playerOneThirdLabel.setFont(nameFont);
        playerOneFourthLabel.setBounds(620, 255, 40, 10); playerOneFourthLabel.setFont(nameFont);
        playerOneFifthLabel.setBounds(690, 255, 40, 10); playerOneFifthLabel.setFont(nameFont);

        playerTwoActiveLabel.setBounds(460, 105, 50, 10); playerTwoActiveLabel.setFont(nameFont);
        playerTwoFirstLabel.setBounds(410, 445, 40, 10); playerTwoFirstLabel.setFont(nameFont);
        playerTwoSecondLabel.setBounds(480, 445, 40, 10); playerTwoSecondLabel.setFont(nameFont);
        playerTwoThirdLabel.setBounds(550, 445, 40, 10); playerTwoThirdLabel.setFont(nameFont);
        playerTwoFourthLabel.setBounds(620, 445, 40, 10); playerTwoFourthLabel.setFont(nameFont);
        playerTwoFifthLabel.setBounds(690, 445, 40, 10); playerTwoFifthLabel.setFont(nameFont);

        player1JLabel.setText("Player 1 (" + player1Name + ") tokens: " + player1.getTokens());
        player2JLabel.setText("Player 2 (" + player2Name +") tokens: " + player1.getTokens());

        // sets all the buttons that the player can interact with and respond to not focusable to negate the black border around texts.
        attackButton.setFocusable(false);
        swapButton.setFocusable(false);
        quitButton.setFocusable(false);
        restartButton.setFocusable(false);
        matchHistoryButton.setFocusable(false);
        matchArea.setEditable(false);
        matchHistoryArea.setEditable(false);

        // adds all the action listeners needed for the buttons to have a function.
        attackButton.addActionListener(this);
        swapButton.addActionListener(this);
        quitButton.addActionListener(this);
        restartButton.addActionListener(this);
        matchHistoryButton.addActionListener(this);

        // sets certain icons for the buttons to be used.
        attackButton.setIcon(swordIcon);
        swapButton.setIcon(swapIcon);
        matchHistoryButton.setIcon(matchHistoryIcon);

        // adds all the components declared to be added to the GUI.
        gui.add(player1JLabel);
        gui.add(player2JLabel);
        gui.add(matchHistoryButton);
        gui.add(matchArea);
        gui.add(attackButton);
        gui.add(swapButton);
        gui.add(turncounterJLabel);
        gui.add(pOneDeckLabel);
        gui.add(pTwoDeckLabel);
        gui.add(activeCardLabel);
        gui.add(vsLabel);
        gui.add(playerOneActiveCard);
        gui.add(playerOneFirstCard);
        gui.add(playerOneSecondCard);
        gui.add(playerOneThirdCard);
        gui.add(playerOneFourthCard);
        gui.add(playerOneFifthCard);
        gui.add(playerTwoActiveCard);
        gui.add(playerTwoFirstCard);
        gui.add(playerTwoSecondCard);
        gui.add(playerTwoThirdCard);
        gui.add(playerTwoFourthCard);
        gui.add(playerTwoFifthCard);
        gui.add(playerOneActiveLabel);
        gui.add(playerOneFirstLabel);
        gui.add(playerOneSecondLabel);
        gui.add(playerOneThirdLabel);
        gui.add(playerOneFourthLabel);
        gui.add(playerOneFifthLabel);
        gui.add(playerTwoActiveLabel);
        gui.add(playerTwoFirstLabel);
        gui.add(playerTwoSecondLabel);
        gui.add(playerTwoThirdLabel);
        gui.add(playerTwoFourthLabel);
        gui.add(playerTwoFifthLabel);

        // also calls a GameMaster to facilitate the game.
        cardMaster = new GameMaster(player1, player2, randomDeal, newDeck, gameMode);

        // makes the string to be printed out by the deal card function the a variable so that it can be added to the match history.
        dealCardString = cardMaster.dealCard();

        // if the spell gamemode is set to true, or if the check box is checked then it runs the spell mode interface.
        if(gameMode == true){
            spellModeInterface();
        }
        // it then updates the deck GUI with the first player
        updateDeckGUI();

        // updates the turn counter to update the deck gui of the second player.
        cardMaster.updateTurnCounter(1);
        updateDeckGUI();

        // reverts it back to its original turn counter. ( this needs to be done since the updateDeckGUI updates it based on the turn counter,
        // if the player that attacks kills the target, it does not update that targets GUI rather the player that attacks's GUI. To solve this,
        // I just made a turn counter updater so that it updates both GUI's (could've just updated both GUI's with one function) ) 
        cardMaster.updateTurnCounter(-1);

        // sets the matchArea with the dealCardString that was assigned above, also adds the string to the match history along with some formatting and relevant information.
        matchArea.setText(dealCardString);
        matchHistory += "10 Turns" + "\n\n" + dealCardString + "\n";
    }


    // this function removes all components in the GUI that are presently active at the time of calling it.
    private void removeAllComponents() {

        // makes an array of type components and adds every single component by calling the getContentPane() function followed by getting every single component in the content pane.
        Component[] components = gui.getContentPane().getComponents();

        // iterates through a for loop function to remove these components one by one.
        for (Component component : components) {
            gui.remove(component);
        }

        // we then repaint and revalidate the GUI to update it so that it displays nothing.
        gui.revalidate();
        gui.repaint();
    }



    public void updateDeckGUI(){

        // if the current turn counter at the time the function is used is odd, then that means player1's deck will get updates, else it will be player 2.
        if(cardMaster.getTurnCounter() % 2 == 1){
            playerDeck = player1.returnDeck();
            player = player1;
        }
        else{
            playerDeck = player2.returnDeck();
            player = player2;
        }

        // goes through a for loop to iterate through every single card and determines its type, to then be assigned its correct card backIcon.
        for(int i = 0; i < playerDeck.size(); i++){

            // gest the current card at the index and its card name.
            ImageIcon backIcon;
            card = playerDeck.get(i);
            cardName = card.getName();

            // if the newDeck gamemode is checked, then it is assigned to only scan through the cards in the newDeck.
            if(newDeck == true){

                if(card.getType().equalsIgnoreCase("god")){
                    backIcon = godCard;
                }
                else if(card.getType().equalsIgnoreCase("robot")){
                    backIcon = robotCard;
                }
                else if(card.getType().equalsIgnoreCase("alien")){
                    backIcon = alienCard;
                }
                else{
                    backIcon = humanCard;
                }

            }

            // else, then it only looks for the default deck card types.
            else{
                if(card.getType().equalsIgnoreCase("dragon")){
                    backIcon = dragonCard;
                }
                else if(card.getType().equalsIgnoreCase("fairy")){
                    backIcon = fairyCard;
                }
                else{
                    backIcon = ghostCard;
                }

            }

            // it then checks if the player is either player 1 or player 2 and does the corresponding action.
            if(player == player1){

                // it uses the counter that is also used by the for loop to look for the corresponding card, since the counter also counts as which index the card is from the players deck.
                // it also updates the card names to the corresponding card as well as each and every button, including the active cards that are displayed.
                if(i == 0){
                    playerOneFirstCard.setIcon(backIcon);
                    playerOneActiveCard.setIcon(backIcon);
                    playerOneActiveLabel.setText("P1 " + cardName);
                    playerOneFirstLabel.setText(cardName);
                }
                else if(i == 1){
                    playerOneSecondCard.setIcon(backIcon);
                    playerOneSecondLabel.setText(cardName);;
                }
                else if(i == 2){
                    playerOneThirdCard.setIcon(backIcon);
                    playerOneThirdLabel.setText(cardName);
                }
                else if(i == 3){
                    playerOneFourthCard.setIcon(backIcon);
                    playerOneFourthLabel.setText(cardName);
                }
                else{
                    playerOneFifthCard.setIcon(backIcon);
                    playerOneFifthLabel.setText(cardName);;
                }

            }

            // in the case that the player is player 2 then it updates the GUI of player 2 with the same logic as player 1 updating the players GUI.
            else if(player == player2){

                if(i == 0){
                    playerTwoFirstCard.setIcon(backIcon);
                    playerTwoActiveCard.setIcon(backIcon);
                    playerTwoActiveLabel.setText("P2 " + cardName);
                    playerTwoFirstLabel.setText(cardName);
                }
                else if(i == 1){
                    playerTwoSecondCard.setIcon(backIcon);
                    playerTwoSecondLabel.setText(cardName);
                }
                else if(i == 2){
                    playerTwoThirdCard.setIcon(backIcon);
                    playerTwoThirdLabel.setText(cardName);
                }
                else if(i == 3){   
                    playerTwoFourthCard.setIcon(backIcon);
                    playerTwoFourthLabel.setText(cardName);
                }
                else{
                    playerTwoFifthCard.setIcon(backIcon);
                    playerTwoFifthLabel.setText(cardName);
                }

            }
                
        }

        // in the case that the new gamemode is true, then the GUI also updates the spell GUI.
        if(gameMode == true){

            // in uses the same logic, where in it uses the turn counter to determine which players GUI must be updated.
            if(cardMaster.getTurnCounter() % 2 == 1){

                // in the case that the player has a spell in its deck, then it calls the updateSpellGui() function with the spell as its argument.
                if(player1.returnSpellDeck().size() >= 1){
                    spell = player1.activeSpell();
                    updateSpellGUI(spell);
                }

                // if the user does not have a spell, it will set it to its default icon which is the noSpell icon with the text "No Spell".
                else{
                    playerOneSpellCard.setIcon(noSpell);
                    playerOneSpellLabel.setText("No Spell");
                }
            }

            // same logic above.
            else{
                if(player2.returnSpellDeck().size() >= 1){
                    spell = player2.activeSpell();
                    updateSpellGUI(spell);
                }
                else{
                    playerTwoSpellCard.setIcon(noSpell);
                    playerTwoSpellLabel.setText("No Spell");
                }
            }

        }

    }

    // this function is called whenever the action can grant you a token.
    public void winner(){

        // if the game does indeed have a winner then the if statement will be true.
        if(cardMaster.hasWinner() == true){

            // it sets the match area to the game report, since there is a winner.
            matchArea.setText(cardMaster.gameReport());

            // it removes the attack and swap button from the gui to prevent further moves.
            gui.remove(attackButton);
            gui.remove(swapButton);

            // we then revalidate and repaint the GUI to ensure that it is updated.
            gui.revalidate();
            gui.repaint();

            // in the place of the attack and swap button, the restart and quit button will replace it and sets their icons.
            gui.add(restartButton);
            gui.add(quitButton);
            restartButton.setIcon(restartIcon);
            quitButton.setIcon(quitIcon);

            // determines which player won by checking the token count of each player and shows a seperate window via JOptionPane with a confetti icon.
            if(player1.getTokens() == 3){
                turncounterJLabel.setText( player1Name + " WINS!!");
                JOptionPane.showMessageDialog(null,player1.getPlayerName() + " won the game!", "WINNER!!", JOptionPane.OK_OPTION, winningIcon);
            }

            else{
                turncounterJLabel.setText( player2Name + " WINS!!");
                JOptionPane.showMessageDialog(null,player2.getPlayerName() + " won the game!", "WINNER!!", JOptionPane.OK_OPTION, winningIcon);
            }

        }

        // in the case that there is no winner, then the game will continue and the winner() function will not do anything.
    }

    // updates the counter label and as well as the token label.
    public void updateCounters(){

        player1JLabel.setText("Player 1 (" + player1Name + ") tokens: " + player1.getTokens());
        player2JLabel.setText("Player 2 (" + player2Name +") tokens: " + player2.getTokens());

        if(cardMaster.getTurnCounter() % 2 == 1){
            turncounterJLabel.setText("It is " + player2.getPlayerName() + "'s turn!");
        }
        else{
            turncounterJLabel.setText("It is " + player1.getPlayerName() + "'s turn!");
        }
    }

    // updates the spell GUI.
    public void updateSpellGUI(Spell spell){

        // declares the variables to be used in the GUI.
        ImageIcon spellIcon;
        String label;

        // uses the turn counter to see which players spell was used or drawed.
        if(cardMaster.getTurnCounter() % 2 == 1){
            player = player1;
        }
        else{
            player = player2;
        }
        spellName = spell.returnSpellName();

        // uses a switch case statement to see which spell is being casted and sets the icon and label accordingly.
        switch(spellName){
            case "Lightning":
                spellIcon = lightningSpell;
                label = "Lightning Spell";
                break;
            case "Rage":
                spellIcon = rageSpell;
                label = "Rage Spell";
                break;
            case "Heal":
                spellIcon = healSpell;
                label = "Heal Spell";
                break;
            case "Swap":
                spellIcon = swapSpell;
                label = "Swap Spell";
                break;
            case "Heroic Stand":
                spellIcon = heroicStandSpell;
                label = "Heroic Stand Spell";
                break;
            case "Blood Exchange":
                spellIcon = bloodExchangSpell;
                label = "Blood Exchange Spell";
                break;
            case "Smite":
                spellIcon = smiteSpell;
                label = "Smite Spell";
                break;
            default:
                spellIcon = duelSpell;
                label = "Duel Spell";
                break;
        }

        // if the player is player 1, then it sets the spell of player 1 to the icon and label, else its player 2.
        if(player == player1){
            playerOneSpellCard.setIcon(spellIcon);
            playerOneSpellLabel.setText(label);
        }
        else{
            playerTwoSpellCard.setIcon(spellIcon);
            playerTwoSpellLabel.setText(label);
        }
    }

    // is called when the spell game mode is activated.
    public void spellModeInterface(){

        // creates all the new components to be set on to the spell GUI.
        playerOneSpellCard = new JButton();
        playerTwoSpellCard = new JButton();
        castSpell = new JButton("Cast Spell");
        drawSpell = new JButton("Draw Spell");

        playerOneSpellLabel = new JLabel();
        playerTwoSpellLabel = new JLabel();

        lightningSpell = new ImageIcon("lightningspell.png");
        rageSpell = new ImageIcon("ragespell.png");
        healSpell = new ImageIcon("healspell.png");
        swapSpell = new ImageIcon("swapspell.png");
        duelSpell = new ImageIcon("duelspell.png");
        smiteSpell = new ImageIcon("smitespell.png");
        bloodExchangSpell = new ImageIcon("bloodexchangespell.png");
        heroicStandSpell = new ImageIcon("heroicstandspell.png");
        noSpell = new ImageIcon("nospell.png");
        
        // sets the fonts of the labels for the spells to the ones used for the names of the cards.
        playerOneSpellLabel.setFont(nameFont);
        playerTwoSpellLabel.setFont(nameFont);

        // sets the bounds and placements of the components that will be added to the GUI.
        playerOneSpellCard.setBounds(540 , 275, 60, 60);
        playerTwoSpellCard.setBounds(540 , 465, 60, 60);
        playerOneSpellLabel.setBounds(540,330,200,30);
        playerTwoSpellLabel.setBounds(540,520,200,30);
        drawSpell.setBounds(550,80,100,40);
        castSpell.setBounds(670,80,100,40);

        // to erase the black border around the buttons.
        drawSpell.setFocusable(false);
        castSpell.setFocusable(false);

        // adds an action listener to the buttons to make them have a function.
        castSpell.addActionListener(this);
        drawSpell.addActionListener(this);

        // adds all the components to the GUI.
        gui.add(playerOneSpellCard);
        gui.add(playerTwoSpellCard);
        gui.add(playerOneSpellLabel);
        gui.add(playerTwoSpellLabel);
        gui.add(castSpell);
        gui.add(drawSpell);
    }

    // makes an action listener function.
    @Override
    public void actionPerformed(ActionEvent action){

        // if the playGame button is pressed then the ff. will be done.
        if(action.getSource() == playGame){
            
            // it gets the players name from the text field provided and set them to their corresponding player.
            player1Name = player1TextField.getText();
            player2Name = player2TextField.getText();

            // if the name was left blank, then the GUI will send them a message regarding this.
            if(player1Name.equals("") || player2Name.equals("")){
                invalidJLabel.setText("Invalid player name(s).");
            }
            
            // if the names are 15 characters long or more, then the players name is invalid and reports a custom message to the GUI.
            // this limit is done to not overlap and not display (...) on the JLabels when the round starts due to long names.
            else if(player1Name.length() > 15 || player2Name.length() > 15){
                invalidJLabel.setText("Name(s) too long.");
            }

            // if both conditions are false, then the game can start.
            else{

                // starts it of by declaring the names of the players as a player type.
                player1 = new Player(player1Name);
                player2 = new Player(player2Name);

                // it then evaluates the check boxes that have been selected in the intro screen, if they have been selected by the user.
                gameMode = gameModeCheckBox.isSelected();
                newDeck = newDeckCheckBox.isSelected();
                randomDeal = randomDealCheckBox.isSelected();

                // we then remove all the components from the intro screen to have a blank canvas, to be then be overwritten by the roundStart function.
                removeAllComponents();

                // we make sure to error handle the FileNotFoundException given by the text file, 'newDeck.txt'.
                try {
                    roundStart();
                } catch (FileNotFoundException e){}
            }

        }

        // if the player presses the attack button then the statement triggers.
        if(action.getSource() == attackButton){

            // it first calls the cardMaster of the GameMaster type to call in the function, play("attack"). This is then detected by the play function as an attempted attack from the player.
            roundString = cardMaster.play("attack");

            // we do the same thing where we update the turn counter 2 times, one where its even, and one where it is odd, to update both GUI's of th players in the case that the other player's card dies.
            cardMaster.updateTurnCounter(1);
            updateDeckGUI();
            cardMaster.updateTurnCounter(-1);

            // we set the text of the match area to the one in the roundString and also adds the roundString to the matchHistory variables.
            matchArea.setText(roundString);
            matchHistory += "Turn: " + cardMaster.getTurnCounter() + "\n" + roundString + "\n\n";

            // we then update the counters and see if there is a winner.
            updateCounters();
            winner();

        }

        // in the case that the user presses the swap button then the ff. will trigger.
        if(action.getSource() == swapButton){

            // it does the same thing with the attack function, except this time it will use the "swap" keyword to trigger the swap.
            roundString = cardMaster.play("swap");

            // it updates the deck GUI, this time with no +- turn counters since the updateDeckGUI() was meant for swapping.
            updateDeckGUI();

            // it then sets the text to the roundString and also adds it to the match history.
            matchArea.setText(roundString);
            matchHistory += "Turn: " + cardMaster.getTurnCounter() + "\n" + roundString + "\n\n";

            // since a swap move will never give the player a token, we only update the label for whose move it is.
            if(cardMaster.getTurnCounter() % 2 == 1){
                turncounterJLabel.setText("It is " + player2.getPlayerName() + "'s turn!");
            }
            else{
                turncounterJLabel.setText("It is " + player1.getPlayerName() + "'s turn!");
            }

        }

        // in the case that the user presses the quit button, it will force close and stop the GUI.
        if(action.getSource() == quitButton){
            System.exit(0);
        }
        
        // if the user pressses restart, then the we remove all the components and go back to the intro screen by calling it.
        if(action.getSource() == restartButton){
            removeAllComponents();
            introScreen();
        }

        // if the user presses the match history, then an option pane will pop up where it will display the match history.
        if(action.getSource() == matchHistoryButton){

            // we set the text of the match history to whatever was added to the match history string.
            matchHistoryArea.setText(matchHistory);

            // it then sets that string as a JScrollPane, which makes the text area into a scrollable text area with set dimensions.
            matchScrollPane = new JScrollPane(matchHistoryArea);
            matchScrollPane.setPreferredSize(new Dimension(500,500));

            // opens up a different window where the match history is shown in the form of a JOptionPane.
            JOptionPane.showMessageDialog(null, matchScrollPane, "Match History", JOptionPane.OK_OPTION, matchHistoryIcon);
        }

        // if the user presses the cast spell, then the user will cast the spell at hand.
        if(action.getSource() == castSpell){

            // we use the keyword "spell" to execute the code that will cast the spell of the user.
            roundString = cardMaster.play("spell");

            // does the same logic, where the player casting the spell can also change its deck.
            updateDeckGUI();
            cardMaster.updateTurnCounter(1);
            updateDeckGUI();
            cardMaster.updateTurnCounter(-1);

            // we set the output of the spell into the match area and add it to the match history.
            matchArea.setText(roundString);
            matchHistory += "Turn: " + cardMaster.getTurnCounter() + "\n" + roundString + "\n\n";

            // we then check if there is a winner and updates the counters.
            updateCounters();
            winner();
        }

        // in the case that the user wants to draw a spell, then the statement will trigger.
        if(action.getSource() == drawSpell){

            // it uses the keyword "drawspell" to let the cardmaster know that the player would like to draw a spell.
            // it also updates the GUI to accomodate the new spell in hand of the player, if any. 
            roundString = cardMaster.play("drawspell");
            updateDeckGUI();

            // we then set the match area to the prompt when you draw a spell as well as add it to the match history.
            matchArea.setText(roundString);
            matchHistory += "Turn: " + cardMaster.getTurnCounter() + "\n" + roundString + "\n\n";

            // same thing with the swap, when you draw a spell, you cannot win. It will only update the turn label.
            if(cardMaster.getTurnCounter() % 2 == 1){
                turncounterJLabel.setText("It is " + player2.getPlayerName() + "'s turn!");
            }
            else{
                turncounterJLabel.setText("It is " + player1.getPlayerName() + "'s turn!");
            }

        }
    }
}