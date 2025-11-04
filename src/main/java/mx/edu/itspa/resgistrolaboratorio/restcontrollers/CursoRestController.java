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

    @Autowired
    private ICursoService cursoService;

    @Autowired
    private ILicenciaturaService licenciaturaService;

    // Endpoint para obtener todos los cursos (solo datos públicos)
    @GetMapping("/api/cursos/todos")
    @JsonView(Views.Public.class)
    public List<Curso> obtenerTodos() {
        return cursoService.todos();
    }

    // Endpoint para obtener un curso por su ID (solo datos públicos)
    @GetMapping("/api/cursos/{id}")
    @JsonView(Views.Public.class)
    public Curso obtenerPorId(@PathVariable Long id) {
        return cursoService.obtenerPorId(id);
    }

    @PostMapping("/api/cursos/guardar")
    @JsonView(Views.Public.class)
    public Curso guardarCurso(@RequestBody Curso curso) {
        Licenciatura licenciatura = licenciaturaService.obtenerPorId(curso.getLicenciatura().getId());
        curso.setLicenciatura(licenciatura);
        return cursoService.nuevo(curso);
    }

    @PutMapping("/api/cursos/actualizar/{id}")
    @JsonView(Views.Public.class)
    public Curso actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Licenciatura licenciatura = licenciaturaService.obtenerPorId(curso.getLicenciatura().getId());
        curso.setLicenciatura(licenciatura);
        return cursoService.actualizar(curso);
    }
}
