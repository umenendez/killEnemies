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
        System.out.println("Número de personajes: " + characters.size());
    }
}
