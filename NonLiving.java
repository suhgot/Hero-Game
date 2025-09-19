/**

NonLiving.java contains the neccessary methods for the NonLiving-type hero in the game.
NonLiving.java is a subclass of Hero.java and has a counter to find how many non living characters there are in the game.
NonLiving.java also has a method to get the power as described by the project instructions.

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah 
@version 3.0
@since 11/19/2024

*/


public class NonLiving extends Hero {
    public static int nonLivingNumber = 0; // Amount of non Living characters in the game.


    /**
     
    NonLiving is a constructor that uses the constructor from Hero.java to create a unique NonLiving object.
    @param name the NonLiving hero's name
    @param health the NonLiving hero's health
    @param description the NonLiving hero's description
    @param attackAbility the NonLiving hero's attack ability

     */
    public NonLiving(String name, int health, String description, AttackAbility attackAbility) {
        super(name, health, description, attackAbility); 
        nonLivingNumber++;
    }


    /** 
     
    getPower calculates the power of the NonLiving hero.
    If the hero has a health greater than 0 the power is 100.
    @return the calculated power of the NonLiving hero.

    */

    @Override
    public double getPower() {
        if (this.health > 0){
            return attackAbility.getMaxPower();
        }
        else{
            return 0;
        }
    }
}
