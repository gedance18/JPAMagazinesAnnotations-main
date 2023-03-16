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