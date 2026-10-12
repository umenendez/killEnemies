package org.cuatrovientos.killEnemies;

public class Enemy implements Character {
    private String type;
    private int health;
    private int damage;

    public Enemy() {
        this("Normal", 20, 10);
    }

    public Enemy(String type, int health, int damage) {
        this.type = type;
        this.health = health;
        this.damage = damage;
    }

    @Override
    public boolean isEnemy() {
        return true;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void kill() {
        System.out.println("¡Has derrotado al enemigo " + type + "!");
    }
}
