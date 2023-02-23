package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private int option;

    public Menu() {
        super();
    }

    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {

            System.out.println(" \nMENU PRINCIPAL \n");

            System.out.println("1. Borrar tablas");
            System.out.println("2. Crear tablas");
            System.out.println("3. Poblar masivamente la base de datos de pokemons");
            System.out.println("4. Seleccionar todos los pokemon de la tabla pokemon");
            System.out.println("5. Seleccionar los tipos de pokemons de la tabla tipo");
            System.out.println("6. Crear Pokemon");
            System.out.println("7. Crear Tipo");
            System.out.println("0. Salir del programa");
            System.out.println("Escoge una opcion: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
                && option != 8 && option != 9 && option != 10);

        return option;
    }






}