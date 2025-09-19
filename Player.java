/**

Player.java contains the players name and a method to return the players name
Player.java has an aggregation relationship with Team.java

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah 
@version 3.0
@since 11/19/2024

*/

public class Player {
    private String name; // Name of the player.


    /**
     
    Player is a constructor that creates a new player with the name given.
    @param name the name of the player given by the program user.

    */

    public Player(String name){
        this.name = name;
    }


    /**
    
    getName gets the name of the player
    @return the player's name.

     */

    public String getName(){
        return name;
    }
}
