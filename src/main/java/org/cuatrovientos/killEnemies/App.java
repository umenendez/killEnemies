package org.cuatrovientos.killEnemies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {
    static ArrayList<Character> characters = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de tu héroe: ");
        Hero hero = new Hero(scanner.nextLine());

        for (int i = 0; i < 5; i++) {
            characters.add(new Friend());
            characters.add(new Enemy());
        }

        Collections.shuffle(characters);
        System.out.println("¡Bienvenido, " + hero.getName() + "!");
        showCounts();
        showCharacters();
    }

    static void showCounts() {
        int friends = 0;
        int enemies = 0;
        for (Character character : characters) {
            if (character.isEnemy()) enemies++;
            else friends++;
        }
        System.out.println("Amigos: " + friends);
        System.out.println("Enemigos: " + enemies);
    }

    static void showCharacters() {
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            System.out.println(i + " - " + (character.isEnemy() ? "Enemigo" : "Amigo"));
        }
    }
}
