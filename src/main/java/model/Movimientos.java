package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "Movimientos")
public class Movimientos implements Serializable {
    @Id
    @Column(name = "id_movimientos")
    int id_pokemon_movimiento;
    @Column(name = "nivel")
    String nivel;
    @Column(name = "nombre")
    String nombre_movimiento;
    @Column(name = "tipo")
    String tipo_movimiento;
    @Column(name = "clase")
    String clase;

    public Movimientos(int id_pokemon_movimientos, String nivel,
                       String nombre_movimiento, String tipo_movimiento,
                       String clase) {
        this.id_pokemon_movimiento = id_pokemon_movimientos;
        this.nivel = nivel;
        this.nombre_movimiento = nombre_movimiento;
        this.tipo_movimiento = tipo_movimiento;
        this.clase = clase;
    }

    public Movimientos(){
        super();
    }

    public int getId_pokemon_movimiento() {
        return id_pokemon_movimiento;
    }

    public void setId_pokemon_movimiento(int id_pokemon_movimiento) {
        this.id_pokemon_movimiento = id_pokemon_movimiento;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNombre_movimiento() {
        return nombre_movimiento;
    }

    public void setNombre_movimiento(String nombre_movimiento) {
        this.nombre_movimiento = nombre_movimiento;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "Movimientos{" +
                "id_pokemon_movimientos=" + id_pokemon_movimiento +
                ", nivel='" + nivel + '\'' +
                ", nombre_movimiento='" + nombre_movimiento + '\'' +
                ", tipo_movimiento='" + tipo_movimiento + '\'' +
                ", clase='" + clase + '\'' +
                '}';
    }
}
