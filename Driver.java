/**

This Java Program is the driver class for a computer game application that loads a list of heroes. It is a continuation of Project 2.
Each player builds a team of heroes, and then the heroes' traits and abilities are randomly changed. After the update, the player with the most powerful team wins.

1. Show all characters     
2. How many non-living?    
3. Add players to the game 
4. Build the teams         
5. Start the game          
6. Quit           

The program runs in a loop until the user chooses to quit.

@author IECE 201 | Fall 2024 | Mon 9:30AM | Saugat Shah  
@version Project 3.0 Finalized 
@since 11/19/2024

Note: The test data used for this project is located in "CharacterList.txt".

*/


import java.io.*; //Imports all classes from package java.io
import java.util.*; //Imports all classes from package java.util

public class Driver {

    public static Scanner keyboard = new Scanner(System.in); // Creates a scanner object to be used by all parts of the program.

    /**
    Main method that initializes the hero list and runs the program loop.
    @param args command-line arguments that are not used in the application.
    @throws FileNotFoundException if the file CharacterList.txt is not found.

    */

    public static void main(String[] args) throws FileNotFoundException {

        // Load heroes from the file
        File characterList = new File("CharacterList.txt");
        Scanner fileScanner = new Scanner(characterList);
        fileScanner.useDelimiter(",\\s*");

        ArrayList<Hero> heroes = new ArrayList<>();

        // Load heroes into the ArrayList
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",\\s*");

            char type = lineScanner.next().charAt(0);
            String name = lineScanner.next();
            String description = lineScanner.next();
            int health = lineScanner.nextInt();

            // Creates the attack type using the enum
            String attackTypeStr = lineScanner.next();
            AttackType attackType = null;
            attackType = AttackType.valueOf(attackTypeStr); 

            String ability = lineScanner.next();

            // Determine the type of Hero and create an object for it.
            Hero hero = null;
            switch (type) {
                case 'H': // Human
                    hero = new Human(name, health, description, new AttackAbility(100.0, attackType, ability));
                    break;
                case 'M': // Monster
                    hero = new Monster(name, health, 10.0, description, new AttackAbility(100.0, attackType, ability));
                    break;
                case 'N': // Non-Living
                    hero = new NonLiving(name, health, description, new AttackAbility(100.0, attackType, ability));
                    break;
                default:
                    // If the hero does not match up with the following types it will have this format
                    System.out.println("Unknown hero type: " + type);
            }

            if (hero != null) {
                // Hero's health is randomized and added to the ArrayList
                hero.randomHealth();
                heroes.add(hero);
            }
            lineScanner.close();
        }
        fileScanner.close();


        //Player and Team variables initialized
        boolean loopbreaker = false;
        Player player1 = null;
        Player player2 = null;
        Team team1 = null;
        Team team2 = null;


        // MAIN LOOP FOR GAME
        // Displays the 6 options and assigns the appropriate methods
        System.out.println("=============================================================\nHello Victim!");
        while (!loopbreaker) {
            System.out.println("Please pick an option below: \n1. Show all characters \n2. Number of Non-Living characters \n3. Add players to the game \n4. Build the teams \n5. Start the game \n6. Quit \n=============================================================");
            int userAnswer = keyboard.nextInt();

            //This switch case makes reads the user's input and executes the method corresponding to the option.
            switch (userAnswer) {
                case 1:

                    System.out.println("=============================================================\nHere is the list of avaliable Heros:\n=============================================================");
                    displayAvailableHeroes(heroes);
                    System.out.println("=============================================================");
                    break;

                case 2:

                    System.out.println("=============================================================\nThe number of NonLiving characters in this game is: " + numberOfNLChar() + "\n=============================================================");
                    break;

                case 3:

                    System.out.println("=============================================================\nAdding players to the game...\n=============================================================");
                    player1 = playerAdder();
                    player2 = playerAdder();
                    //If there are two players added, 2 new teams are created.
                    if (player1 != null && player2 != null) {
                        team1 = new Team(player1);
                        team2 = new Team(player2);
                    }
                    break;

                case 4:

                    //If there are 2 players added the teamBuilder method is called, if not then it will display "There have been no players added to the game!".
                    if (team1 != null && team2 != null && !heroes.isEmpty()) {
                        teamBuilder(team1, team2, heroes);
                    } else {
                        System.out.println("=============================================================\nThere have been no players added to the game!\n=============================================================");
                    }
                    break;

                case 5:

                    System.out.println("\n========================== Final Teams ==========================");
                    team1.showTeam();
                    team2.showTeam();
                    System.out.println("========================== Game Results ==========================");
                    System.out.println(winnerCalculation(team1, team2, player1, player2));
                    System.out.println("=============================================================");
                    // When the game is run, the players, teams, and Arraylist of heroes are reset in order to play again and stored in SavedTeams.txt
                    player1 = null;
                    player2 = null;
                    team1 = null;
                    team2 = null;
                    heroes = loadHeroes();
                    break;

                case 6:

                    loopbreaker = true;
                    System.out.println("=============================================================\nThank you for playing!");
                    break;

                default:

                    System.out.println("=============================================================\nIncorrect input, please input an integer value from 1-5: ");
                    break;

            }
        }

        keyboard.close();
    }


    /**
    
    displayAvaliableHeroes displays the heros that are avaliable for the player to pick.
    @param heroes the ArrayList of Heroes to choose from

    */

    private static void displayAvailableHeroes(ArrayList<Hero> heroes) {
        System.out.println("Available Heroes:");
        System.out.println("| Hero Name | Description | Health | Power | Attack Type | Ability |");
        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            System.out.println((i + 1) + ". " + hero.toString());
        }
    }


    /**
    
    numberOfNLChar counts the number of non-living characters in the game.
    @return the total number of Non Living characters in the game.
    
    */

    private static int numberOfNLChar(){
        return NonLiving.nonLivingNumber;
    }


    /**
    
    winnerCalculation calculates the winner based on the total power of each team
    @param team1 player 1's team.
    @param team2 player 2's team.
    @param player1 the first player registered.
    @param player2 the second player registered.
    @return a string message declaring the winner or if it's a tie.

    */

    private static String winnerCalculation(Team team1, Team team2, Player player1, Player player2){
        double team1Power = team1.getTotalPower();
        double team2Power = team2.getTotalPower();

        if (team1Power > team2Power) {
            return player1.getName() + " wins with a total power of " + team1Power + "!";
        } else if (team2Power > team1Power) {
            return player2.getName() + " wins with a total power of " + team2Power + "!";
        } else {
            return "It's a tie! Both teams have a power of " + team1Power + "!";
        }

    }
    

    /**
    
    teamBuilder builds teams by alternating hero selection between the two registered players. 
    @param team1 player 1's team.
    @param team2 player 2's team.
    @param availableHeroes the Arraylist of heroes avaliable to select from.
    @throws FileNotFoundException if there is an error saving the teams to the file.

    */

    private static void teamBuilder (Team team1, Team team2, ArrayList<Hero> availableHeroes) throws FileNotFoundException{
        for (int i = 0; !availableHeroes.isEmpty(); i++) {
            Team currentTeam = (i % 2 == 0) ? team1 : team2;
            Player currentPlayer = currentTeam.getPlayer();

            System.out.println("=============================================================\n" + currentPlayer.getName() + ", it's your turn to pick a hero.\n=============================================================");
            displayAvailableHeroes(availableHeroes);

            System.out.print("\n=============================================================\nEnter the number of the hero you want to pick: ");
            int choice = keyboard.nextInt();

            if (choice < 1 || choice > availableHeroes.size()) {
                System.out.println("Invalid choice, please pick a valid hero number.");
                continue;
            }

            Hero selectedHero = availableHeroes.remove(choice - 1);
            currentTeam.addHero(selectedHero);
        }
        saveTeams(team1, team2);
    }


    /**
    
    saveTeams saves the details of both teams and the winner in the SavedTeams.txt file.
    @param team1 player 1's team.
    @param team2 player 2's team.
    @throws FileNotFoundException if the file cannot be writtent to.

    */

    private static void saveTeams(Team team1, Team team2) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(new FileOutputStream("SavedTeams.txt", true)); //Googled FileOutputStream - This line makes sure to append the saved teams rather than rewrite the file every time the game is run.

        writer.println("============================================================= " + team1.getPlayer().getName() + "'s Team =============================================================");
        for (Hero hero : team1.getTeam()){
            writer.println(hero);
        }

        writer.println("\n============================================================= " + team2.getPlayer().getName() + "'s Team =============================================================");
        for (Hero hero : team2.getTeam()){
            writer.println(hero);
        }

        writer.println("\n" + winnerCalculation(team1, team2, team1.getPlayer(), team2.getPlayer()));
        writer.close();
    }


    /**
    
    playerAdder adds a new player to the game by prompting them for their name.
    @return a new Player object.
    
    */

    private static Player playerAdder(){
        System.out.println("Enter Player name: ");
        String name = keyboard.next();
        System.out.println("=============================================================");
        return new Player(name);
    }


    /**
    
    Loads the heroes from CharacterList.txt to populate the arraylist again.
    @return an Arraylist of Hero objects.
    @throws FileNotFoundException if the file CharacterList.txt is not found.

    */

    private static ArrayList<Hero> loadHeroes() throws FileNotFoundException {
        File characterList = new File("CharacterList.txt");
        Scanner fileScanner = new Scanner(characterList);
        fileScanner.useDelimiter(",\\s*");

        ArrayList<Hero> heroes = new ArrayList<>();

        // Load heroes into the ArrayList
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",\\s*");

            char type = lineScanner.next().charAt(0);
            String name = lineScanner.next();
            String description = lineScanner.next();
            int health = lineScanner.nextInt();
            String attackTypeStr = lineScanner.next();
            // Creates the attack type using the enum
            AttackType attackType = null;
            attackType = AttackType.valueOf(attackTypeStr); 
            String ability = lineScanner.next();

            // Determine the type of Hero and create the appropriate object
            Hero hero = null;
            switch (type) {
                case 'H': // Human
                    hero = new Human(name, health, description, new AttackAbility(100.0, attackType, ability));
                    break;
                case 'M': // Monster
                    hero = new Monster(name, health, 10.0, description, new AttackAbility(100.0, attackType, ability));
                    break;
                case 'N': // Non-Living
                    hero = new NonLiving(name, health, description, new AttackAbility(100.0, attackType, ability));
                    break;
                default:
                    System.out.println("Unknown hero type: " + type);
            }

            if (hero != null) {
                // Randomizes the heroes health and adds it to the ArrayList of heroes
                hero.randomHealth();
                heroes.add(hero);
            }
            lineScanner.close();
        }
        fileScanner.close();
        return heroes;
    }
    
}
