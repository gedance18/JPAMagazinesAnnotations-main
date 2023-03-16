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

            System.out.println("1. Crear tablas");
            System.out.println("2. Poblar masivamente la base de datos de pokemons");
            System.out.println("3. Listar la tabla Pokemon");
            System.out.println("4. Listar la tabla Tipo");
            System.out.println("5. Listar la tabla Movimientos");
            System.out.println("6. Listar el nombre de los pokemons por el nombre");
            System.out.println("7. Buscar un movimiento y mostrarlo");
            System.out.println("8. Crear un pokemon");
            System.out.println("9. Crear un tipo");
            System.out.println("10. Crear un movimiento");
            System.out.println("11. Borrar tablas");
            System.out.println("0. Salir del programa");
            System.out.println("Escoge una opcion: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
                && option != 8 && option != 9 && option != 10 && option != 11 && option != 0);

        return option;
    }
}