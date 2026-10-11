package org.cuatrovientos.killEnemies;

public class Hero implements Character {
    private String name;
    private int enemiesKilled;
    private int friendsDefended;

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

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public int getFriendsDefended() {
        return friendsDefended;
    }
}
