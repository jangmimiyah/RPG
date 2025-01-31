import java.util.Scanner;

public class RPGGame {
    public static void main(String[] args) {
        // Game loop
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Welcome message
                System.out.println("Welcome to the RPG Game!");
                
                // Player name and job selection
                System.out.print("Enter your character name: ");
                String playerName = scanner.nextLine();
                System.out.println("Choose your job:");
                System.out.println("1. Warrior");
                System.out.println("2. Mage");
                System.out.println("3. Archer");
                System.out.println("4. Assassin");
                System.out.print("Enter choice: ");
                int jobChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                String job = switch (jobChoice) {
                    case 1 -> "Warrior";
                    case 2 -> "Mage";
                    case 3 -> "Archer";
                    case 4 -> "Assassin";
                    default -> "Warrior";
                };
                
                Character player = new Character(playerName, job);
                System.out.println("You chose " + job + "! Prepare for battle!");
                
                // Create enemies
                Enemy goblin = new Enemy("Goblin", 50, 10, 8);
                Enemy orc = new Enemy("Orc", 75, 15, 6);
                Enemy dragon = new Enemy("Dragon", 150, 25, 10);
                
                // Battle loop
                while (player.isAlive()) {
                    // Encounter enemy
                    System.out.println("\nAn enemy appears!");
                    System.out.println("1. Fight Goblin");
                    System.out.println("2. Fight Orc");
                    System.out.println("3. Fight Dragon (Boss)");
                    System.out.println("4. Quit Game");
                    System.out.print("Choose an action: ");
                    int choice = scanner.nextInt();
                    
                    Enemy enemy = null;
                    switch (choice) {
                        case 1 -> enemy = goblin;
                        case 2 -> enemy = orc;
                        case 3 -> enemy = dragon;
                        case 4 -> {
                            System.out.println("Exiting game. Goodbye!");
                            scanner.close();
                            System.exit(0); // Properly exit the game
                        }
                        default -> System.out.println("Invalid choice.");
                    }
                    
                    if (enemy != null) {
                        // Battle turn order (speed determines who attacks first)
                        while (player.isAlive() && enemy.isAlive()) {
                            if (player.getSpeed() >= enemy.getSpeed()) {
                                // Player attacks first
                                System.out.println(player.getName() + "'s turn.");
                                System.out.println("Choose an attack:");
                                System.out.println("1. Basic Attack");
                                System.out.println("2. Power Attack");
                                System.out.println("3. Critical Strike");
                                System.out.println("4. Ultimate Move");
                                System.out.print("Enter choice: ");
                                int attackChoice = scanner.nextInt();
                                player.attack(enemy, attackChoice);
                                
                                if (enemy.isAlive()) {
                                    player.takeDamage(enemy.getDamage());
                                }
                            } else {
                                // Enemy attacks first
                                System.out.println(enemy.getName() + "'s turn.");
                                player.takeDamage(enemy.getDamage());
                                if (player.isAlive()) {
                                    System.out.println(player.getName() + "'s turn.");
                                    System.out.println("Choose an attack:");
                                    System.out.println("1. Basic Attack");
                                    System.out.println("2. Power Attack");
                                    System.out.println("3. Critical Strike");
                                    System.out.println("4. Ultimate Move");
                                    System.out.print("Enter choice: ");
                                    int attackChoice = scanner.nextInt();
                                    player.attack(enemy, attackChoice);
                                }
                            }
                        }
                        
                        if (enemy.isAlive()) {
                            System.out.println("You were defeated by " + enemy.getName());
                        } else {
                            System.out.println(enemy.getName() + " has been defeated!");
                            player.gainExp(50); // Award EXP for defeating an enemy
                        }
                    }
                }
                
                System.out.println("Game over. You were defeated.");
                System.out.println("Do you want to restart the game? (Y/N)");
                String restart = scanner.nextLine().toUpperCase();
                if (!restart.equals("Y")) {
                    break;
                }
            }
        }
    }
}
