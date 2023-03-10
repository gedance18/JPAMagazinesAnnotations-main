package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "pokemon")
public class Pokemon implements Serializable{
    @Id
    @Column(name = "id_pokemon")
    private int id_pokemon;
    @Column(name = "nombre", length = 4000)
    private String nombre;
    @Column(name = "categoría", length = 4000)
    private String categoria;
    @Column(name = "habilidad", length = 4000)
    private String habilidad;
    @Column(name = "peso", length = 4000)
    private String peso;
    @Column(name = "altura", length = 4000)
    private String altura;
    @Column(name = "generacion", length = 4000)
    private String generacion;
    @Column(name = "evoluciones", length = 4000)
    private String evoluciones;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_tipo", referencedColumnName = "Id_tipo")
    private List<Tipo> tipos = new ArrayList<Tipo>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_movimientos", referencedColumnName = "Id_movimientos")
    private List<Movimientos> movimientos = new ArrayList<Movimientos>();

    public Pokemon(int id_pokemon, String nombre, String categoria, String habilidad,
                   String peso, String altura, String generacion,
                   String evoluciones) {
        super();
        this.id_pokemon = id_pokemon;
        this.nombre = nombre;
        this.categoria = categoria;
        this.habilidad = habilidad;
        this.peso = peso;
        this.altura = altura;
        this.generacion = generacion;
        this.evoluciones = evoluciones;
    }

    public Pokemon(){
        super();
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getGeneracion() {
        return generacion;
    }

    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    public String getEvoluciones() {
        return evoluciones;
    }

    public void setEvoluciones(String evoluciones) {
        this.evoluciones = evoluciones;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id_pokemon=" + id_pokemon +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", habilidad='" + habilidad + '\'' +
                ", peso='" + peso + '\'' +
                ", altura='" + altura + '\'' +
                ", generacion='" + generacion + '\'' +
                ", evoluciones='" + evoluciones + '\'' +
                ", tipos=" + tipos +
                ", movimientos=" + movimientos +
                '}';
    }
}
