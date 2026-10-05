package org.cuatrovientos.killEnemies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class App {
    static ArrayList<Character> characters = new ArrayList<Character>();

    public static void main(String[] args) {

        characters.add(new Friend());
        characters.add(new Friend());
        characters.add(new Friend());
        characters.add(new Friend());
        characters.add(new Friend());

        characters.add(new Enemy());
        characters.add(new Enemy());
        characters.add(new Enemy());
        characters.add(new Enemy());
        characters.add(new Enemy());
        
        Collections.shuffle(characters);
        System.out.println("Número de personajes: " + characters.size());
        
        int position = 0;

        for (Character character : characters) {
            if (character.isEnemy()) {
                Enemy enemy = (Enemy) character;
                System.out.println("¡El personaje " + position + " es un enemigo! ¡¡MATALOOOOOO!!");
                enemy.kill();
            } else {
            	System.out.println("¡El personaje " + position + " es un amigo! Saludalo :)");
                System.out.println("¡¿Qué pasa compañero?!");
            }

            position++;
        }

    }
}
