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
public class Card{
    // declares all variables to be used in the card class.
    private String name;
    private String type;
    private int health;
    private int attack;
    // instantiates the values for the card type.
    public Card(String t, String n, int h, int p){
        type = t;
        name = n;
        health = h;
        attack = p;
    }
    // takes in an argument for how much damage the card is going to take.
    public void takeDamage(int d){
        health = health - d;
    }
    // takes in an argument and changes the name of the card to the one in the argument.
    public void changeName(String newname){
        name = newname;
    }
    // takes in an argument and updates the health of the card to the one in the argument to heal the card.
    public void updateHealth(int d){
        health = health + d;
    }
    // replaces the health of the card to the one in the argument.
    public void replaceHealth(int d){
        health = d;
    }
    // replaces the attack of the card to the one in the argument.
    public void updateAttack(int d){
        attack = d;
    }
    
    // returns different characteristics of the card.
    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getHealth(){
        return health;
    }

    public int getPower(){
        return attack;
    }
}