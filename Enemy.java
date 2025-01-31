public class Enemy {
    private final String name;
    private int health;
    private final int damage;
    private final int speed; // Added speed attribute

    public Enemy(String name, int health, int damage, int speed) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            System.out.println(name + " has been defeated!");
        } else {
            System.out.println(name + " takes " + amount + " damage! Remaining health: " + health);
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() { // Getter for speed
        return speed;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }
}
