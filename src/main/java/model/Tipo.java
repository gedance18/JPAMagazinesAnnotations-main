package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "tipo")
public class Tipo implements Serializable {
    @Id
    @Column(name = "id_pokemon")
    int id_pokemon_tipo;
    @Column(name = "Nombre")
    String nombre;

    public Tipo(int id_pokemon_tipo, String tipo) {
        this.id_pokemon_tipo = id_pokemon_tipo;
        this.nombre = tipo;
    }

    public Tipo(){
        super();
    }

    public int getId_pokemon_tipo() {
        return id_pokemon_tipo;
    }

    public void setId_pokemon_tipo(int id_pokemon_tipo) {
        this.id_pokemon_tipo = id_pokemon_tipo;
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
                "id_pokemon_tipo=" + id_pokemon_tipo +
                ", tipo='" + nombre + '\'' +
                '}';
    }
}
