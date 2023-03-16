package controller;

import model.Movimientos;
import model.Pokemon;
import model.Tipo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
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

    public List<Pokemon> readPokemonsFile (String pokemonsFile) throws IOException {
        int id_pokemon, id_movimientos, id_tipo;
        String nombre;
        String categoria;
        String habilidad;
        String peso;
        String altura;
        String generacion;
        String evoluciones;
        List<Pokemon> pokemonList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(pokemonsFile));
        String linea = "";
        int contador = 0;
        while ((linea = br.readLine()) != null) {
            contador ++;
            StringTokenizer str = new StringTokenizer(linea, ",");
            id_pokemon = Integer.parseInt(str.nextToken().replace("\"", ""));
            id_tipo = contador;
            id_movimientos = contador;
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


            pokemonList.add(new Pokemon(id_pokemon, id_tipo, id_movimientos, nombre, categoria, habilidad, peso, altura, generacion, evoluciones));
        }
        br.close();
        return pokemonList;
    }

    public void listPokemon() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Pokemon> result = em.createQuery("from Pokemon", Pokemon.class)
                .getResultList();
        for (Pokemon pokemon : result) {
            System.out.println(pokemon.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    public void createTablePokemon(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAMagazines");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(
                "CREATE TABLE Pokemon (\n" +
                        "    Id_pokemon serial NOT NULL,\n" +
                        "    Id_movimientos INT,\n" +
                        "    Id_tipo INT,\n" +
                        "    Nombre VARCHAR(4000),\n" +
                        "    Categoría VARCHAR(4000),\n" +
                        "    Habilidad VARCHAR(4000),\n" +
                        "    Peso VARCHAR(4000),\n" +
                        "    Altura VARCHAR(4000),\n" +
                        "    Generacion VARCHAR(4000),\n" +
                        "    Evoluciones VARCHAR(4000),\n" +
                        "    CONSTRAINT pk_pokemons PRIMARY KEY (Id_pokemon),\n" +
                        "    CONSTRAINT fk_pokemons_tipo FOREIGN KEY (Id_tipo)\n" +
                        "        REFERENCES Tipo (Id_tipo) MATCH SIMPLE\n" +
                        "        ON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
                        "    CONSTRAINT fk_pokemons_movimientos FOREIGN KEY (Id_movimientos)\n" +
                        "        REFERENCES Movimientos (Id_movimientos) MATCH SIMPLE\n" +
                        "        ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
                        ")"
        ).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void orderPokemonsByName() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<String> result = em.createQuery("SELECT p.nombre FROM Pokemon p ORDER BY p.nombre", String.class)
                .getResultList();

        for (String name : result) {
            System.out.println(name);
        }
        em.getTransaction().commit();
        em.close();
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

    public void dropTablePokemon() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAMagazines");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("DROP TABLE Pokemon").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
