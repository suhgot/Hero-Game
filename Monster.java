/**

Monster.java contains the neccessary methods for the Monster-type hero in the game.
Monster.java is a subclass of Hero.java and adds a method for calculating the power based on health and power.

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah 
@version 3.0
@since 11/19/2024

*/

public class Monster extends Hero {
    private double minPower;

    /**
    
    Monster is a constructor that uses the constructor from Hero.java and adds onto it to create a monster object.
    @param name name of the monster hero
    @param health health of the monster hero
    @param minPower minimum power of the monster hero
    @param description the description of the monster hero
    @param attackAbility the attack ability of the monster hero

    */

    public Monster(String name, int health, double minPower, String description, AttackAbility attackAbility) {
        super(name, health, description, attackAbility); 
        this.minPower = minPower;
    }


    /**
    getPower calculates the power of the monster based on its health

    Monster's power is:
    1. maximum if health is 75% or above
    2. half of the maximum if health is 50% or above but less than 75%
    3. a quarter of the maximum if health is 25% or above but less than 50%
    4. minimum if health is above 0% but less than 25%
    5. zero if health is 0%

    @return the calculated power of the monster hero.

    */
    @Override
    public double getPower() {
        if (this.health >= 75) return this.attackAbility.getMaxPower();
        if (this.health >= 50) return this.attackAbility.getMaxPower() / 2;
        if (this.health >= 25) return this.attackAbility.getMaxPower() / 4;
        if (this.health > 0) return this.minPower; 
        return 0;
    }

    /**
    
    toString displays a string representaiton of the monster hero and its attributes seperated with a " | ".
    @return the formatted string representing the monster hero's attributes.

    */

    @Override
    public String toString() {
        StringBuilder monsterResult = new StringBuilder();

        // Build the string representation
        monsterResult.append(name).append(" | ");
        monsterResult.append(description).append(" | ");
        monsterResult.append(health).append(" | ");
        monsterResult.append(attackAbility.toString()).append(" | ");
        return monsterResult.toString();
    }
}
