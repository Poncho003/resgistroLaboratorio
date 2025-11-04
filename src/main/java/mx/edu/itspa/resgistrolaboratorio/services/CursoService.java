// Servicio encargado de la logica de negocio para la entidad Curso
// Implementa la interfaz ICursoService para definir las operaciones principales
// Utiliza el repositorio ICursoRepository para acceder a la base de datos
package mx.edu.itspa.resgistrolaboratorio.services;

import java.util.List;
import org.springframework.stereotype.Service;
import mx.edu.itspa.resgistrolaboratorio.models.Curso;
import mx.edu.itspa.resgistrolaboratorio.repositories.ICursoRepository;

@Service
public class CursoService implements ICursoService {

    // Repositorio de cursos inyectado a traves del constructor
    private final ICursoRepository cursoRepository;

    public CursoService(ICursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Devuelve la lista de todos los cursos registrados
    @Override
    public List<Curso> todos() {
        return cursoRepository.encuentraTodos();
    }

    // Busca un curso por su identificador, devuelve null si no existe
    @Override
    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    // Registra un nuevo curso en la base de datos
    @Override
    public Curso nuevo(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Actualiza los datos de un curso existente
    @Override
    public Curso actualizar(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Elimina un curso segun su identificador
    @Override
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}
