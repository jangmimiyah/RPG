public class Character {
    private final String name;
    private final String job;
    private int health;
    private int damage;
    private int speed;
    private int exp;
    private final int levelUpThreshold = 100;

    public Character(String name, String job) {
        this.name = name;
        this.job = job;
        this.exp = 0;
        
        // Set stats based on job
        switch (job) {
            case "Warrior" -> {
                this.health = 120;
                this.damage = 20;
                this.speed = 10;
            }
            case "Mage" -> {
                this.health = 80;
                this.damage = 25;
                this.speed = 12;
            }
            case "Archer" -> {
                this.health = 100;
                this.damage = 15;
                this.speed = 15;
            }
            case "Assassin" -> {
                this.health = 90;
                this.damage = 18;
                this.speed = 18;
            }
            default -> {
                this.health = 100;
                this.damage = 15;
                this.speed = 10;
            }
        }
    }

    public void attack(Enemy enemy, int attackChoice) {
        int attackDamage;
        switch (attackChoice) {
            case 1 -> attackDamage = damage;
            case 2 -> attackDamage = damage * 2;
            case 3 -> attackDamage = damage + 10;
            case 4 -> attackDamage = damage * 3;
            default -> {
                System.out.println("Invalid attack choice.");
                return;
            }
        }
        System.out.println(name + " attacks " + enemy.getName() + " for " + attackDamage + " damage!");
        enemy.takeDamage(attackDamage);
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage! Remaining health: " + health);
    }

    public void gainExp(int exp) {
        this.exp += exp;
        System.out.println(name + " gains " + exp + " EXP!");
        
        if (this.exp >= levelUpThreshold) {
            levelUp();
        }
    }

    public void levelUp() {
        this.exp -= levelUpThreshold; // Reset EXP after leveling up
        this.health += 10;
        this.damage += 2;
        this.speed += 2;
        System.out.println(name + " leveled up! Stats increased: Health, Damage, Speed.");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public String getJob() {
        return job;
    }
}
