/**
 
AttackType.java represents the different ypes of attack tyes a hero can have.
Enumeration is used to provide a predefined attack type for the hero to be categorized in.

The attack Types are:
1. Biological
2. Electromagnetic
3. Mechanical
4. NatureControl

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah
@version 3.0
@since 11/19/2024

*/

public enum AttackType{
    Biological,
    Electromagnetic,
    Mechanical,
    NatureControl;

    
    /** 
    
    toString returns the anme of the attack type as a string ovveriding the default toString method.
    @return the attack type.

    */

    @Override
    public String toString(){
        return name();
    }
}