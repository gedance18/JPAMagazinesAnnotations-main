package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Access(AccessType.FIELD)
@Table(name = "pokemon")
public class Pokemon implements Serializable{
    @Id
    @Column(name = "id_pokemon")
    int id_pokemon;
    @Column(name = "nombre", length = 4000)
    String nombre;
    @Column(name = "categoría", length = 4000)
    String categoria;
    @Column(name = "habilidad", length = 4000)
    String habilidad;
    @Column(name = "altura", length = 4000)
    String altura;
    @Column(name = "generacion", length = 4000)
    String generacion;
    @Column(name = "evoluciones", length = 4000)
    String evoluciones;
    @Column(name = "tipo", length = 4000)
    String tipo;
}
