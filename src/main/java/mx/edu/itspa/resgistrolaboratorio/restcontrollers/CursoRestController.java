// Controlador REST encargado de manejar las peticiones relacionadas con los cursos
// Expone endpoints para obtener, registrar y actualizar cursos mediante operaciones HTTP
// Se comunica con los servicios de Curso y Licenciatura para gestionar la logica de negocio

package mx.edu.itspa.resgistrolaboratorio.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import mx.edu.itspa.resgistrolaboratorio.commons.Views;
import mx.edu.itspa.resgistrolaboratorio.models.Curso;
import mx.edu.itspa.resgistrolaboratorio.models.Licenciatura;
import mx.edu.itspa.resgistrolaboratorio.services.ICursoService;
import mx.edu.itspa.resgistrolaboratorio.services.ILicenciaturaService;

@CrossOrigin(methods = {RequestMethod.PUT})
@RestController
public class CursoRestController {

    // Inyeccion de dependencias para acceder a los servicios de cursos y licenciaturas
    @Autowired
    private ICursoService cursoService;

    @Autowired
    private ILicenciaturaService licenciaturaService;

    // Endpoint para obtener todos los cursos disponibles
    // Usa @JsonView para mostrar solo los datos definidos como publicos
    @GetMapping("/api/cursos/todos")
    @JsonView(Views.Public.class)
    public List<Curso> obtenerTodos() {
        return cursoService.todos();
    }

    // Endpoint para obtener un curso especifico por su identificador
    // Devuelve solo los campos visibles segun la vista publica
    @GetMapping("/api/cursos/{id}")
    @JsonView(Views.Public.class)
    public Curso obtenerPorId(@PathVariable Long id) {
        return cursoService.obtenerPorId(id);
    }

    // Endpoint para registrar un nuevo curso en la base de datos
    // Se asocia la licenciatura correspondiente antes de guardar
    @PostMapping("/api/cursos/guardar")
    @JsonView(Views.Public.class)
    public Curso guardarCurso(@RequestBody Curso curso) {
        Licenciatura licenciatura = licenciaturaService.obtenerPorId(curso.getLicenciatura().getId());
        curso.setLicenciatura(licenciatura);
        return cursoService.nuevo(curso);
    }

    // Endpoint para actualizar los datos de un curso existente
    // Actualiza tambien la relacion con la licenciatura correspondiente
    @PutMapping("/api/cursos/actualizar/{id}")
    @JsonView(Views.Public.class)
    public Curso actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Licenciatura licenciatura = licenciaturaService.obtenerPorId(curso.getLicenciatura().getId());
        curso.setLicenciatura(licenciatura);
        return cursoService.actualizar(curso);
    }
}
