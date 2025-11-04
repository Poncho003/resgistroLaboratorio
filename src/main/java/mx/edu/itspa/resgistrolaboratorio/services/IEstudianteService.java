// Interfaz que define los metodos del servicio para la entidad Estudiante
// Contiene las operaciones principales de negocio que implementara la clase EstudianteService
// Facilita la separacion entre la logica de negocio y el acceso a datos
package mx.edu.itspa.resgistrolaboratorio.services;

import java.util.List;
import mx.edu.itspa.resgistrolaboratorio.models.Estudiante;

public interface IEstudianteService {

    // Devuelve la lista completa de estudiantes registrados
    List<Estudiante> todos();

    // Busca un estudiante por su identificador
    Estudiante obtenerPorId(Long id);

    // Registra un nuevo estudiante en la base de datos
    Estudiante nuevo(Estudiante estudiante);

    // Actualiza los datos de un estudiante existente
    Estudiante actualizar(Estudiante estudiante);

    // Elimina un estudiante segun su identificador
    void eliminar(Long id);
}
