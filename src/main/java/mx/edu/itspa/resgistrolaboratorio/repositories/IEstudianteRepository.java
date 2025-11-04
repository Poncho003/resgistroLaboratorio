// Repositorio encargado del manejo de datos para la entidad Estudiante
// Extiende JpaRepository para utilizar las operaciones CRUD de Spring Data JPA
// Incluye consultas personalizadas para obtener informacion detallada de los estudiantes
package mx.edu.itspa.resgistrolaboratorio.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.edu.itspa.resgistrolaboratorio.models.Estudiante;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Consulta personalizada que obtiene todos los estudiantes
    // Incluye los cursos y las licenciaturas relacionadas mediante JOIN FETCH
    @Query("SELECT e FROM Estudiante e JOIN FETCH e.cursos c JOIN FETCH c.licenciatura")
    List<Estudiante> encuentraTodos();

    // Consulta personalizada que obtiene un estudiante por su id
    // Devuelve solo los campos principales sin relaciones para mejorar el rendimiento
    @Query("SELECT NEW mx.edu.itspa.resgistrolaboratorio.models.Estudiante(e.id, e.nombre, e.semestre, e.email) FROM Estudiante e WHERE e.id = :id")
    Optional<Estudiante> encuentraPorId(@Param("id") Long id);
}
