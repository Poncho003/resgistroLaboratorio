// Repositorio encargado del manejo de datos para la entidad Curso
// Extiende JpaRepository para aprovechar las operaciones CRUD de Spring Data JPA
// Incluye consultas personalizadas para obtener informacion detallada de los cursos
package mx.edu.itspa.resgistrolaboratorio.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.edu.itspa.resgistrolaboratorio.models.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {

    // Consulta personalizada que obtiene todos los cursos
    // Incluye la licenciatura y los estudiantes relacionados usando LEFT JOIN FETCH
    @Query("SELECT DISTINCT c FROM Curso c LEFT JOIN FETCH c.licenciatura LEFT JOIN FETCH c.estudiantes")
    List<Curso> encuentraTodos();

    // Consulta personalizada que obtiene un curso especifico por su id
    // Devuelve solo los campos principales sin relaciones para optimizar la carga
    @Query("SELECT NEW mx.edu.itspa.resgistrolaboratorio.models.Curso (c.id, c.nombre, c.creditos, c.unidades) FROM Curso c WHERE c.id = :id")
    Optional<Curso> encuentraPorId(@Param("id") Long id);
}
