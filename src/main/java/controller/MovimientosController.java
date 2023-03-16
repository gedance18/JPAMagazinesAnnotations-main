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

public class MovimientosController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;

    public MovimientosController(Connection connection, EntityManagerFactory entityManagerFactory){
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
    }

    public MovimientosController(Connection connection) {
        this.connection = connection;
    }

    public List<Movimientos> readMovimientosFile (String filename) throws IOException {
        int id_movimientos;
        String nivel;
        String nombre;
        String tipo;
        String clase;
        List<Movimientos> movimientosList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            id_movimientos = Integer.parseInt(str.nextToken().replace("\"", ""));
            for (int i = 0; i < 19 ; i++) {
                str.nextToken();
            }
            nivel = str.nextToken();
            nombre = str.nextToken();
            tipo = str.nextToken();
            clase = str.nextToken();
            movimientosList.add(new Movimientos(id_movimientos, nivel, nombre, tipo, clase));
        }
        br.close();
        return movimientosList;
    }

    public void listMovimientos() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Movimientos> result = em.createQuery("from Movimientos", Movimientos.class)
                .getResultList();
        for (Movimientos movimiento : result) {
            System.out.println(movimiento.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    public void createTableMovimientos(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAMagazines");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(
                "CREATE TABLE Movimientos(\n" +
                        "    Id_movimientos serial NOT NULL,\n" +
                        "    Nivel VARCHAR(4000),\n" +
                        "    Nombre VARCHAR(4000),\n" +
                        "    Tipo VARCHAR(4000),\n" +
                        "    Clase VARCHAR(4000),\n" +
                        "    CONSTRAINT pk_movimientos PRIMARY KEY (Id_movimientos)\n" +
                        ");"
        ).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void listMovimientoByName(String movimiento) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Movimientos> result = em.createQuery("SELECT m FROM Movimientos m WHERE LOWER(m.nombre) = LOWER(:nombre)", Movimientos.class)
                .setParameter("nombre", movimiento.toLowerCase())
                .getResultList();

        for (Movimientos movimientos : result) {
            System.out.println(movimientos.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    public void deleteMovimiento(Integer id_pokemon_movimiento) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Movimientos movimiento = (Movimientos) em.find(Movimientos.class, id_pokemon_movimiento);
        em.remove(movimiento);
        em.getTransaction().commit();
        em.close();
    }

    public void addMovimiento(Movimientos movimiento) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(movimiento);
        em.getTransaction().commit();
        em.close();
    }

    public void updateMovimiento(Integer id_pokemon_movimiento) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Movimientos movimiento = (Movimientos) em.find(Movimientos.class, id_pokemon_movimiento);
        em.merge(movimiento);
        em.getTransaction().commit();
        em.close();
    }

    public void dropTableMovimientos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAMagazines");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("DROP TABLE Movimientos").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
