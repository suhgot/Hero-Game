/**

Hero.java is an abstract superclass for specific 3 types of heroes: Human, Monster, and NonLiving
Hero.java allows for the creation of hero objects that contain a name, health, description, attackability, and avaliability status.
Hero.Java provides methods for calculating power, randomizing health, and displaying hero attributes.
Hero.java has an aggregation relationship with AttackAbility.java and Team.java and is the superclass for Monster.java, Human.java, and NonLiving.java

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah 
@version 3.0
@since 11/19/2024

*/



import java.util.*;

abstract class Hero {
    protected static int totalNumber = 0; // The number of heroes created
    protected String name; // Name of the hero
    protected int health; // Health of the hero
    protected AttackAbility attackAbility; // Attack Ability of the hero
    protected String description; // The description of the Hero
    protected boolean avaliable; // Avaliability status of the hero.


    /**
    
    Hero is a constructor that creates a `Hero` object with the specified attributes.
    @param name the name of the hero.
    @param health the initial health value of the hero.
    @param description a brief description of the hero.
    @param attackAbility the attack ability of the hero.

    */

    public Hero(String name, int health, String description, AttackAbility attackAbility){
        this.name = name;
        this.health = health;
        this.attackAbility = attackAbility;
        this.avaliable = true;
        this.description = description;
        totalNumber++;
    }


    /**
    
    getPower is an abstract method to calculate and return the power of the hero.
    Subclasses must implement this method to define the specific calculation of power.
    @return the power of the hero as a double.

    */

    public abstract double getPower();



    /**
     
    randomHealth randomizes the hero health to a value between 0 and 100.
    @return the new randomized health value.

    */

    public int randomHealth(){
        Random r = new Random();
        return this.health = r.nextInt(101);
    }


    /**
    
    getName retrieves the name of the hero.
    @return the name of the hero as a string

    */

    public String getName(){
        return name;
    }
    

    /**
    
    toString returns a string representation of the hero, detailing its attributes in a formatted manner.
    @return a string containing the hero's attributes separated by " | ".
    
    */
    @Override

    public String toString(){
        
       //Converts the type to a detailed string value and creates a line of String containing the hero's attributes with " | " separating them.
       StringBuilder heroresult = new StringBuilder();

       //Creates the String and returns it.
       heroresult.append(name).append(" | ");
       heroresult.append(description).append(" | ");
       heroresult.append(health).append(" | ");
       heroresult.append(getPower()).append(" | ");
       heroresult.append(attackAbility.toString()).append(" | ");
       return heroresult.toString();
    }
}
