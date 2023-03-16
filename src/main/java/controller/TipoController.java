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

public class TipoController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;

    public TipoController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
    }

    public TipoController(Connection connection) {
        this.connection = connection;
    }

    public List<Tipo> readTiposFile(String filename) throws IOException {
        int id_tipo;
        String nombre_tipo;
        List<Tipo> tipoList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            id_tipo = Integer.parseInt(str.nextToken().replace("\"", ""));
            str.nextToken();
            nombre_tipo = str.nextToken();

            tipoList.add(new Tipo(id_tipo, nombre_tipo));
        }
        br.close();
        return tipoList;
    }

    public void listTipo() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Tipo> result = em.createQuery("from Tipo", Tipo.class)
                .getResultList();
        for (Tipo tipo : result) {
            System.out.println(tipo.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    public void createTableTipo(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAMagazines");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(
                "CREATE TABLE Tipo (\n" +
                        "    Id_tipo serial NOT NULL,\n" +
                        "    Nombre VARCHAR(4000),\n" +
                        "    CONSTRAINT pk_tipo PRIMARY KEY (Id_tipo)\n" +
                        ");"
        ).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void deleteTipo(Integer id_pokemon_tipo) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Tipo tipo = (Tipo) em.find(Tipo.class, id_pokemon_tipo);
        em.remove(tipo);
        em.getTransaction().commit();
        em.close();
    }

    public void addTipo(Tipo tipo) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(tipo);
        em.getTransaction().commit();
        em.close();
    }

    public void updateTipo(Integer id_pokemon_tipo) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Tipo tipo = (Tipo) em.find(Tipo.class, id_pokemon_tipo);
        em.merge(tipo);
        em.getTransaction().commit();
        em.close();
    }

    public void dropTableTipo() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAMagazines");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("DROP TABLE Tipo").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void createTipo(int id_tipo, String nombre) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Tipo tipo = new Tipo();
        tipo.setId_tipo(id_tipo);
        tipo.setNombre(nombre);
        em.persist(tipo);
        em.getTransaction().commit();
        em.close();
    }
}
