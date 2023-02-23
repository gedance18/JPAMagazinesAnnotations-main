package controller;

import javax.persistence.EntityManagerFactory;
import java.sql.Connection;

public class PokemonController {

    private Connection connection;
    private EntityManagerFactory entityManagerFactory;

    private MagazineController magazineController = new MagazineController(connection);
    private AuthorController authorController = new AuthorController(connection);


    public PokemonController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
    }

}
