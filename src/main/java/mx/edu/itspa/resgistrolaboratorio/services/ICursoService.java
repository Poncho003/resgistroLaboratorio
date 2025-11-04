// Interfaz que define los metodos del servicio para la entidad Curso
// Establece las operaciones principales de negocio que implementara la clase CursoService
// Permite mantener una capa de abstraccion entre el controlador y el repositorio
package mx.edu.itspa.resgistrolaboratorio.services;

import java.util.List;
import mx.edu.itspa.resgistrolaboratorio.models.Curso;

public interface ICursoService {

    // Devuelve todos los cursos registrados
    List<Curso> todos();

    // Busca un curso por su identificador
    Curso obtenerPorId(Long id);

    // Registra un nuevo curso en la base de datos
    Curso nuevo(Curso curso);

    // Actualiza la informacion de un curso existente
    Curso actualizar(Curso curso);

    // Elimina un curso segun su identificador
    void eliminar(Long id);
}
