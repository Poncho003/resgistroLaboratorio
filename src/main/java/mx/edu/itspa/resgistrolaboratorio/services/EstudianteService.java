// Servicio encargado de la logica de negocio para la entidad Estudiante
// Implementa la interfaz IEstudianteService con las operaciones principales
// Utiliza el repositorio IEstudianteRepository para acceder y manipular los datos
package mx.edu.itspa.resgistrolaboratorio.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.edu.itspa.resgistrolaboratorio.models.Estudiante;
import mx.edu.itspa.resgistrolaboratorio.repositories.IEstudianteRepository;

@Service
public class EstudianteService implements IEstudianteService {

    // Inyeccion del repositorio que maneja las operaciones con la base de datos
    @Autowired
    private IEstudianteRepository estudianteRepository;

    // Devuelve la lista completa de estudiantes registrados
    @Override
    public List<Estudiante> todos() {
        return estudianteRepository.findAll();
    }

    // Busca un estudiante por su identificador este devuelve null si no se encuentra
    @Override
    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    // Registra un nuevo estudiante en la base de datos
    @Override
    public Estudiante nuevo(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Actualiza los datos de un estudiante existente
    @Override
    public Estudiante actualizar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Elimina un estudiante segun su identificador
    @Override
    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }
}
