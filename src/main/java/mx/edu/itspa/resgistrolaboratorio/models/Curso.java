// Entidad que representa los cursos dentro del sistema
// Se usa para mapear la tabla 'cursos' en la base de datos mediante JPA
// Contiene relaciones con estudiantes y licenciaturas
package mx.edu.itspa.resgistrolaboratorio.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import mx.edu.itspa.resgistrolaboratorio.commons.Views;

@Entity
@Table(name = "cursos")
public class Curso {

    // Identificador unico del curso generado automáticamente
    // Se muestra en la vista publica del API
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    private Long id;

    // Nombre del curso este no puede ser nulo, longitud máxima 100
    // Visible en la vista publica
    @Column(name = "nombre", nullable = false, length = 100)
    @JsonView(Views.Public.class)
    private String nombre;

    // Creditos académicos del curso
    // Incluido en la vista publica
    @Column(name = "creditos", nullable = false)
    @JsonView(Views.Public.class)
    private Integer creditos;

    // Numero de unidades o temas del curso
    // Incluido en la vista publica
    @Column(name = "unidades", nullable = false)
    @JsonView(Views.Public.class)
    private Integer unidades;

    // Relacion muchos a uno con licenciatura
    // Cada curso pertenece a una licenciatura y se muestra en la vista publica
    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn(name = "licenciatura_id", nullable = true)
    private Licenciatura licenciatura;

    // Relacion muchos a muchos con estudiantes
    // Esta informacion solo se expone en la vista privada
    @JsonView(Views.Private.class)
    @ManyToMany(mappedBy = "cursos")
    private Set<Estudiante> estudiantes = new HashSet<>();

    // Constructor vacio requerido por JPA
    public Curso() {
    }

    // Constructor completo
    public Curso(Long id, String nombre, Integer creditos, Integer unidades, Set<Estudiante> estudiantes,
            Licenciatura licenciatura) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.unidades = unidades;
        this.estudiantes = estudiantes;
        this.licenciatura = licenciatura;
    }

    // Constructor sin relaciones
    public Curso(String nombre, Integer creditos, Integer unidades) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.unidades = unidades;
    }

    // Constructor con ID y datos básicos
    public Curso(Long id, String nombre, Integer creditos, Integer unidades) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.unidades = unidades;
    }

    // Constructor con licenciatura asociada
    public Curso(Long id, String nombre, Integer creditos, Integer unidades, Licenciatura licenciatura) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.unidades = unidades;
        this.licenciatura = licenciatura;
    }

    // toString() para mostrar el nombre o un texto por defecto
    @Override
    public String toString() {
        return nombre != null ? nombre : "Curso sin nombre";
    }

    // Getters y Setters
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

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Licenciatura getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(Licenciatura licenciatura) {
        this.licenciatura = licenciatura;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}