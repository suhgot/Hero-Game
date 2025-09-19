/**
 
Human.java contains the neccessary methods for the Human-type hero in the game.
Human.java is a subclass of Hero.java and contains a method to get the power for the Human using the method listed in the project directions

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah 
@version 3.0
@since 11/19/2024

*/


public class Human extends Hero {
    /**
     
    Human is a constructor that creates a hero with the attributes and uses the superclass constrcutor from Hero.java:
    @param name name of the human hero
    @param health health of the human hero
    @param description description of the human hero
    @param attackAbility attackability of the human hero

     */

    public Human(String name, int health, String description, AttackAbility attackAbility) {
        super(name, health, description, attackAbility); 
    }


    /** 
    
    getPower calculates the power of the human hero (Human's power is a maximum prorated to its health).
    @return the calculated power of the human hero as a double value.

    */
    @Override
    public double getPower() {
        return (this.health / 100.0) * attackAbility.getMaxPower();
    }
}
