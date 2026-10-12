package org.cuatrovientos.killEnemies;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {
    static final String SAVE_FILE = "game.dat";
    static ArrayList<Character> characters = new ArrayList<>();
    static Hero loadedHero;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hero hero;

        if (loadGame()) {
            hero = loadedHero;
            System.out.println("Partida cargada.");
        } else {
            System.out.print("Nombre de tu héroe: ");
            hero = new Hero(scanner.nextLine());
            for (int i = 0; i < 5; i++) {
                characters.add(new Friend());
                characters.add(new Enemy("Normal", 20, 10));
                characters.add(new Enemy("Fuerte", 40, 20));
                characters.add(new Enemy("Jefe", 80, 30));
            }
            Collections.shuffle(characters);
        }

        System.out.println("¡Bienvenido, " + hero.getName() + "!");
        showCounts();
        showCharacters();
        System.out.println("1. Atacar");
        System.out.println("2. Defender");
        System.out.println("3. Recibir ataque");
        System.out.println("4. Guardar y salir");
        System.out.print("Elige una acción: ");
        int action = readInt(scanner);

        if (action == 4) {
            saveGame(hero);
            System.out.println("Partida guardada.");
            return;
        }

        System.out.print("Índice del personaje: ");
        int index = readInt(scanner);
        if (index >= 0 && index < characters.size()) {
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
            } else if (action == 3) {
                if (target instanceof Enemy) {
                    hero.takeDamage(((Enemy) target).getDamage());
                } else {
                    System.out.println("Ese personaje no puede atacarte.");
                }
            } else {
                System.out.println("Acción no válida.");
            }
        } else {
            System.out.println("Índice no válido.");
        }

        saveGame(hero);
        showCounts();
        System.out.println("Enemigos derrotados: " + hero.getEnemiesKilled());
        System.out.println("Amigos defendidos: " + hero.getFriendsDefended());
        System.out.println("Vida del héroe: " + hero.getHealth());
    }

    static boolean loadGame() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            loadedHero = (Hero) input.readObject();
            characters = (ArrayList<Character>) input.readObject();
            for (Character character : characters) {
                if (character instanceof Enemy && ((Enemy) character).getType() == null) {
                    characters.clear();
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static void saveGame(Hero hero) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            output.writeObject(hero);
            output.writeObject(characters);
        } catch (Exception e) {
            System.out.println("No se ha podido guardar la partida.");
        }
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
            if (character instanceof Enemy) {
                Enemy enemy = (Enemy) character;
                System.out.println(i + " - Enemigo " + enemy.getType() + " (vida: " + enemy.getHealth() + ")");
            } else {
                System.out.println(i + " - Amigo");
            }
        }
    }
}
