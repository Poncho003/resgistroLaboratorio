// Repositorio encargado del manejo de datos para la entidad Licenciatura
// Extiende JpaRepository para aprovechar las operaciones CRUD proporcionadas por Spring Data JPA
// No contiene consultas personalizadas ya que JpaRepository ofrece los metodos necesarios por defecto
package mx.edu.itspa.resgistrolaboratorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.edu.itspa.resgistrolaboratorio.models.Licenciatura;

public interface ILicenciaturaRepository extends JpaRepository<Licenciatura, Long> {
    // Repositorio vacio, JpaRepository proporciona los metodos basicos
    // como save, findAll, findById, deleteById, etc
}
