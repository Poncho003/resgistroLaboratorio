// Entidad que representa a los estudiantes dentro del sistema
// Se usa para mapear la tabla estudiante en la base de datos mediante JPA
// Contiene una relacion muchos a muchos con los cursos registrados
package mx.edu.itspa.resgistrolaboratorio.models;

import java.util.HashSet;
import java.util.Set;
//import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    // Identificador unico del estudiante generado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos principales del estudiante
    private String nombre;
    private Integer semestre;
    private String email;

    // Relacion muchos a muchos con cursos
    // Un estudiante puede tener varios cursos y un curso puede tener varios estudiantes
    @ManyToMany
    @JoinTable(name = "cursos_estudiantes",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private Set<Curso> cursos = new HashSet<>();

    // Constructor vacio requerido por JPA
    public Estudiante() {
    }

    // Constructor completo con todos los atributos
    public Estudiante(Long id, String nombre, Integer semestre, String email, Set<Curso> cursos) {
        this.id = id;
        this.nombre = nombre;
        this.semestre = semestre;
        this.email = email;
        this.cursos = cursos;
    }

    // Constructor basico sin relacion
    public Estudiante(String nombre, Integer semestre, String email) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.email = email;
    }

    // Constructor con identificador y datos principales
    public Estudiante(Long id, String nombre, Integer semestre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.semestre = semestre;
        this.email = email;
    }

    // Getters y Setters para acceder y modificar los datos del estudiante
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

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
}