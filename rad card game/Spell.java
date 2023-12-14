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
public class Spell {
    // declares the variable spell name as a global variable
    // spell class for the sake of organization.
    private String spellName;
    // declares the name of the spell with the name of the argumnt. Also instantiates the spell constructor.
    public Spell(String name){
        spellName = name;
    }
    // returns the name of the spell.
    public String returnSpellName(){
        return spellName;
    }

}