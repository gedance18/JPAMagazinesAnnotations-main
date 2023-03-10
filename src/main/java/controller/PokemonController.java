package controller;

import model.Movimientos;
import model.Pokemon;
import model.Tipo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PokemonController {

    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    private TipoController tiposController = new TipoController(connection);
    private MovimientosController movimientosController = new MovimientosController(connection);

    public PokemonController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
    }


    public PokemonController(Connection connection) {
        this.connection = connection;
    }

    public List<Pokemon> readPokemonsFile (String pokemonsFile, String tiposFile, String movimientosFile) throws IOException {
        int id_pokemon, id_tipo, id_movimiento;
        String nombre;
        String categoria;
        String habilidad;
        String peso;
        String altura;
        String generacion;
        String evoluciones;

        BufferedReader br = new BufferedReader(new FileReader(pokemonsFile));
        String linea = "";
        List<Tipo> tipoList = tiposController.readTiposFile(tiposFile);
        List<Movimientos> movimientosList = movimientosController.readMovimientosFile(movimientosFile);

        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            id_pokemon = Integer.parseInt(str.nextToken());

            nombre = str.nextToken();
            str.nextToken();
            categoria = str.nextToken();
            str.nextToken();
            habilidad = str.nextToken();
            str.nextToken();
            peso = str.nextToken();
            str.nextToken();
            altura = str.nextToken();
            str.nextToken();
            generacion = str.nextToken();
            str.nextToken();
            evoluciones = str.nextToken();


        }
    }
    public void deletePokemon(Integer id_pokemon) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Pokemon pokemon = (Pokemon) em.find(Pokemon.class, id_pokemon);
        em.remove(pokemon);
        em.getTransaction().commit();
        em.close();
    }

    public void addPokemon(Pokemon pokemon) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(pokemon);
        em.getTransaction().commit();
        em.close();
    }

    public void updatePokemon(Integer id_pokemon) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Pokemon pokemon = (Pokemon) em.find(Pokemon.class, id_pokemon);
        em.merge(pokemon);
        em.getTransaction().commit();
        em.close();
    }
}
