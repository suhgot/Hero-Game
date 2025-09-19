/**

Team.java allows adding heroes to the team, calculating a team's total power, and displaying the team's details.
It allows team-related methods to be accessed by the user in the Driver class.
Team.java has an aggregation relationship with Hero.java and Player.java

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah
@version 3.0
@since 11/19/2024

*/

import java.util.*;

public class Team {

    //List of heroes in the team. 
    private ArrayList<Hero> team = new ArrayList<>();

    //The player who owns the team.
    private Player player;

    /**
    Team creates a Team object for the specified player.
    @param player the player who owns this team.

    */

    public Team(Player player) {
        this.player = player;
    }


    /**
    
    addHero adds a hero to the player's team.
    @param hero the hero to be added.
    
    */

    public void addHero(Hero hero) {
            team.add(hero);
            System.out.println(hero.getName() + " has been added to the team.\n=============================================================");
    }


    /**
    
    getTotalPower calculates the total power of all heroes in the team.
    @return the double value of the total power the team has.

    */

    public double getTotalPower() {
        double totalPower = 0.0;
        for (Hero hero : team) {
            totalPower += hero.getPower();
        }
        return totalPower;
    }


    /**
     
    showTeam Displays the details of the team, including each hero and the total team power.

    */
    public void showTeam() {
        System.out.println(player.getName() + "'s Team:\n=============================================================");
        
        for (Hero hero : team) {
            System.out.println(hero.toString());
        }

        System.out.println("=============================================================\nTotal Power: " + getTotalPower());
    }


    /**
     
    getTeam displays the team of the desired player.
    @return the ArrayList of heroes in the team.
 
    */

    public ArrayList<Hero> getTeam() {
        return team;
    }


    /**
    
    getPlayer displays the team's owner.
    @return the player object associated with this team.

    */
    public Player getPlayer() {
        return player;
    }
}
