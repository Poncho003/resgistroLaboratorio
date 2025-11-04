// Entidad que representa las licenciaturas dentro del sistema
// Se usa para mapear la tabla licenciatura en la base de datos mediante JPA
// Contiene una relacion uno a muchos con los cursos asociados a cada licenciatura
package mx.edu.itspa.resgistrolaboratorio.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import mx.edu.itspa.resgistrolaboratorio.commons.Views;

@Entity
@Table(name = "licenciatura")
public class Licenciatura {

    // Identificador unico de la licenciatura generado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    private Long id;

    // Atributos principales de la licenciatura
    @JsonView(Views.Public.class)
    private String nombre;
    @JsonView(Views.Public.class)
    private String especialidad;

    // Relacion uno a muchos con cursos
    // Una licenciatura puede tener varios cursos asociados
    @JsonView(Views.Private.class)
    @OneToMany(mappedBy = "licenciatura")
    private Set<Curso> cursos;

    // Constructor vacio requerido por JPA
    public Licenciatura() {
    }

    // Constructor con nombre y especialidad
    public Licenciatura(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Constructor con identificador, nombre y especialidad
    public Licenciatura(Long id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Constructor completo con cursos asociados
    public Licenciatura(Long id, String nombre, String especialidad, Set<Curso> cursos) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursos = cursos;
    }

    // Getters y Setters para acceder y modificar los datos de la licenciatura
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
}