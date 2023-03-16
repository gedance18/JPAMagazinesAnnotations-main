import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import controller.*;
import database.ConnectionFactory;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {

  static SessionFactory sessionFactoryObj;

  private static SessionFactory buildSessionFactory() {
    try {
      StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();
      Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();

    } catch (HibernateException he) {
      System.out.println("Session Factory creation failure");
      throw he;
    }
  }

  public static EntityManagerFactory createEntityManagerFactory(){
    EntityManagerFactory emf;
    try {
      emf = Persistence.createEntityManagerFactory("JPAMagazines");
    } catch (Throwable ex) {
      System.err.println("Failed to create EntityManagerFactory object."+ ex);
      throw new ExceptionInInitializerError(ex);
    }
    return emf;
  }

  public static void main(String[] args) throws IOException {
    boolean salirMenu = false;
    Scanner scanner = new Scanner(System.in);
    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    Connection c = connectionFactory.connect();

    EntityManagerFactory entityManagerFactory = createEntityManagerFactory();


    PokemonController pokemonController = new PokemonController(c, entityManagerFactory);
    TipoController tipoController = new TipoController(c, entityManagerFactory);
    MovimientosController movimientosController = new MovimientosController(c,entityManagerFactory);

    Menu menu = new Menu();
    int opcio;

    while(!salirMenu){
      opcio = menu.mainMenu();
      switch (opcio) {

        case 1:
          tipoController.createTableTipo();
          movimientosController.createTableMovimientos();
          pokemonController.createTablePokemon();
          break;

        case 2:
          List<Tipo> tipos = tipoController.readTiposFile("src/main/resources/pokemon.csv");
          for (Tipo tp:tipos) {
            tipoController.addTipo(tp);
          }
          List<Movimientos> movimientos = movimientosController.readMovimientosFile("src/main/resources/caracteristicas_pokemon.csv");
          for (Movimientos mv:movimientos) {
            movimientosController.addMovimiento(mv);
          }
          List<Pokemon> pokemons = pokemonController.readPokemonsFile("src/main/resources/caracteristicas_pokemon.csv");
          for (Pokemon pk:pokemons) {
            pokemonController.addPokemon(pk);
            break;
          }

        case 3:
          pokemonController.listPokemon();
          break;

        case 4:
          tipoController.listTipo();
          break;

        case 5:
          movimientosController.listMovimientos();
          break;

        case 6:
          pokemonController.orderPokemonsByName();
          break;

        case 7:
          System.out.println("Que movimiento quieres buscar?");
          String movimiento = scanner.nextLine();

          try{
            movimientosController.listMovimientoByName(movimiento);
          } catch (Exception e){
            System.out.println("El movimiento que estas buscando no existe");
          }
          break;

        case 8:
          System.out.println("Para crear un pokemon manualmente elige sus atributos:");
          System.out.println("Id del pokemon:");
          int id_pokemon = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Id del movimiento");
          int id_movimiento = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Id del tipo");
          int id_tipo = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Nombre:");
          String nombre = scanner.nextLine();
          System.out.println("Categoria:");
          String categoria = scanner.nextLine();
          System.out.println("Habilidad:");
          String habilidad = scanner.nextLine();
          System.out.println("Peso:");
          String peso = scanner.nextLine();
          System.out.println("Altura:");
          String altura = scanner.nextLine();
          System.out.println("Generacion:");
          String generacion = scanner.nextLine();
          System.out.println("Evoluciones:");
          String evoluciones = scanner.nextLine();

          pokemonController.createPokemon(id_pokemon, id_movimiento, id_tipo, nombre, categoria,
                  habilidad, peso, altura, generacion, evoluciones);
          break;

        case 9:
          System.out.println("Para crear un tipo manualmente elige sus atributos:");
          System.out.println("Id_tipo:");
          int tipo_id = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Nombre:");
          String nombre_tipo = scanner.nextLine();
          tipoController.createTipo(tipo_id, nombre_tipo);
          break;

        case 10:
          System.out.println("Para crear un movimiento manualmente elige sus atributos:");
          System.out.println("Id_movimiento:");
          int movimiento_id = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Nivel:");
          String nivel = scanner.nextLine();
          System.out.println("Nombre:");
          String nombre_movimiento = scanner.nextLine();
          System.out.println("Tipo:");
          String tipo = scanner.nextLine();
          System.out.println("Clase:");
          String clase= scanner.nextLine();

          movimientosController.createMovimiento(movimiento_id, nivel, nombre_movimiento, tipo, clase);

        case 11:
          try{
            pokemonController.dropTablePokemon();
            tipoController.dropTableTipo();
            movimientosController.dropTableMovimientos();
          } catch (Exception e){
            System.out.println("Alguna tabla que quieres borrar no existe");
          }
          break;

        case 0:
          System.out.println("Adeu!!");
          salirMenu = true;
      }
    }
  }
}