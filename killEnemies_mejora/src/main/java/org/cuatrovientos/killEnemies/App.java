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
        System.out.println("1. Atacar");
        System.out.println("2. Defender");
        System.out.print("Elige una acción: ");
        int action = readInt(scanner);
        System.out.print("Índice del personaje: ");
        int index = readInt(scanner);

        if (index < 0 || index >= characters.size()) {
            System.out.println("Índice no válido.");
        } else {
            Character target = characters.get(index);
            if (action == 1) {
                if (target instanceof Enemy) {
                    hero.attack((Enemy) target);
                } else {
                    System.out.println("Has atacado a un amigo.");
                }
                characters.remove(index);
            } else if (action == 2) {
                if (target instanceof Friend) {
                    hero.defend((Friend) target);
                    ((Friend) target).heal();
                } else {
                    characters.add(target);
                    System.out.println("El enemigo se ha duplicado.");
                }
            } else {
                System.out.println("Acción no válida.");
            }
        }

        showCounts();
        System.out.println("Enemigos derrotados: " + hero.getEnemiesKilled());
        System.out.println("Amigos defendidos: " + hero.getFriendsDefended());
    }

    static int readInt(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
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
