/**
 
AttackAbility.java represents the attack capabilites of a hero in the game.
AttakAbility.java contains the values for the min power, max power, attack type, and ability name.
AttackAbility.java has an aggregation relationship with Hero.java and AttackType.java
AttackAbility.java has methods to get the maximum power and to represent the attack ability as a string.

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah 
@version 3.0
@since 11/19/2024

*/

public class AttackAbility {
    private double maxPower; // Max power of the Hero.
    protected double minPower; // The min power of the Hero.
    private AttackType attackType; // The attackType of the Hero represented by the attackType enum
    private String ability; // The name of the Ability


    /**
    
    AttackAbility is a constructor that creates an AttackAbility object with the specified max power, attack type, and ability name
    @param maxPower
    @param attackType
    @param ability

    */

    public AttackAbility(double maxPower, AttackType attackType, String ability){
        this.maxPower = maxPower;
        this.attackType = attackType;
        this.ability = ability;
    }


    /**
    
    getMaxPower gets the maximum power of the hero.
    @return the maximum power value as a double.

    */
    public double getMaxPower(){
        return this.maxPower;
    }


    /**
    
    toString returns a string representation of the attack ability, showing its type and description.
    @return a formatted string containing the attack type and ability name seperated with a " | ".

    */

    @Override
    public String toString(){
        StringBuilder heroAttackAbility = new StringBuilder();
        heroAttackAbility.append(attackType.toString()+ " | ").append(ability);
        return heroAttackAbility.toString();
    }

}
