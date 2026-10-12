package org.cuatrovientos.killEnemies;

public class Friend implements Character {
    @Override
    public boolean isEnemy() {
        return false;
    }

    public void saved() {
        System.out.println("¡¡Ahhhh muchas gracias, tenía mucho miedo!!");
    }
}
