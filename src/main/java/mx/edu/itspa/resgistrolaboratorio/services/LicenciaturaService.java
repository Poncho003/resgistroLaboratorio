// Servicio encargado de la logica de negocio para la entidad Licenciatura
// Implementa la interfaz ILicenciaturaService con las operaciones principales
// Utiliza el repositorio ILicenciaturaRepository para acceder a la base de datos
package mx.edu.itspa.resgistrolaboratorio.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.edu.itspa.resgistrolaboratorio.models.Licenciatura;
import mx.edu.itspa.resgistrolaboratorio.repositories.ILicenciaturaRepository;

@Service
public class LicenciaturaService implements ILicenciaturaService {

    // Inyeccion del repositorio que maneja las operaciones con la base de datos
    @Autowired
    private ILicenciaturaRepository licenciaturaRepository;

    // Devuelve la lista completa de licenciaturas registradas
    @Override
    public List<Licenciatura> todos() {
        return licenciaturaRepository.findAll();
    }

    // Busca una licenciatura por su identificador, devuelve null si no se encuentra
    @Override
    public Licenciatura obtenerPorId(Long id) {
        return licenciaturaRepository.findById(id).orElse(null);
    }
}
