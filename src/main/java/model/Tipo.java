package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "Tipo")
public class Tipo implements Serializable {
    @Id
    @Column(name = "id_tipo")
    int id_tipo;
    @Column(name = "Nombre")
    String nombre;

    public Tipo(int id_pokemon_tipo, String tipo) {
        this.id_tipo = id_pokemon_tipo;
        this.nombre = tipo;
    }

    public Tipo(){
        super();
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "id_tipo=" + id_tipo +
                ", tipo='" + nombre + '\'' +
                '}';
    }
}
