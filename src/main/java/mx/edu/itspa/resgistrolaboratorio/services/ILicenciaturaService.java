// Interfaz que define los metodos del servicio para la entidad Licenciatura
// Contiene las operaciones basicas de negocio que implementara la clase LicenciaturaService
// Facilita la comunicacion entre el controlador y la capa de acceso a datos
package mx.edu.itspa.resgistrolaboratorio.services;

import java.util.List;
import mx.edu.itspa.resgistrolaboratorio.models.Licenciatura;

public interface ILicenciaturaService {

    // Devuelve todas las licenciaturas registradas
    List<Licenciatura> todos();

    // Busca una licenciatura por su identificador
    Licenciatura obtenerPorId(Long id);
}