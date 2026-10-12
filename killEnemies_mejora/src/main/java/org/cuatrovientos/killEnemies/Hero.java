package org.cuatrovientos.killEnemies;

public class Hero implements Character {
    private String name;
    private int enemiesKilled;
    private int friendsDefended;
    private int health = 100;

    public Hero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    public void attack(Enemy enemy) {
        System.out.println("¡He atacado a un enemigo!");
        enemy.kill();
        enemiesKilled++;
    }

    public void defend(Friend friend) {
        System.out.println("¡He defendido a un amigo!");
        friendsDefended++;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
        System.out.println("Has recibido " + damage + " puntos de daño.");
        if (health == 0) System.out.println("Te has quedado sin vida.");
    }

    public int getHealth() {
        return health;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public int getFriendsDefended() {
        return friendsDefended;
    }
}
