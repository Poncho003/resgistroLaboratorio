// Controlador principal del sistema de registro de laboratorio
// Administra las rutas principales del proyecto y conecta las vistas con los servicios
// Gestiona estudiantes, cursos y licenciaturas de manera centralizada
package mx.edu.itspa.resgistrolaboratorio.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.edu.itspa.resgistrolaboratorio.models.Curso;
import mx.edu.itspa.resgistrolaboratorio.models.Estudiante;
import mx.edu.itspa.resgistrolaboratorio.services.CursoService;
import mx.edu.itspa.resgistrolaboratorio.services.EstudianteService;
import mx.edu.itspa.resgistrolaboratorio.services.LicenciaturaService;

@Controller
public class HomeController {

    // Inyeccion de dependencias para acceder a los servicios principales
    @Autowired
    private LicenciaturaService licenciaturaService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    // Seccion general del sistema
    // Carga la vista principal del sistema con el titulo asignado
    @GetMapping("/home")
    public String init(Model model) {
        model.addAttribute("pageTitle", "Registro de laboratorio");
        return "index";
    }

    // Carga una vista alternativa sin datos para mostrar mensajes simples
    @GetMapping("/")
    public String sinDatos(Model model) {
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("mensaje", "Welcome to my page, be happy");
        return "sinDatos";
    }

    // Seccion de estudiantes
    // Muestra todos los estudiantes registrados en la base de datos
    @GetMapping("/estudiantes")
    public String goEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteService.todos();
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("pageTitle", "Administrar estudiantes");
        return "/estudiantes/index";
    }

    // Carga el formulario para registrar un nuevo estudiante
    @GetMapping("/nuevoEstudiante")
    public String goNuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("cursos", cursoService.todos());
        model.addAttribute("pageTitle", "Nuevo Estudiante");
        return "/estudiantes/form";
    }

    // Carga los datos de un estudiante existente para su edicion
    @GetMapping("/editarEstudiante")
    public String goEditarEstudiante(Model model, @RequestParam Long id) {
        Estudiante estudiante = estudianteService.obtenerPorId(id);
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("cursos", cursoService.todos());
        model.addAttribute("pageTitle", "Editar Estudiante");
        return "/estudiantes/form";
    }

    // Elimina un estudiante segun su id
    @GetMapping("/estudiantes/eliminar")
    public String eliminarEstudiante(@RequestParam Long id) {
        estudianteService.eliminar(id);
        return "redirect:/estudiantes";
    }

    // Guarda un estudiante nuevo o actualizado y redirige a la lista
    // Se imprimen los datos en consola para verificar los valores capturados
    @PostMapping("/estudiantes/agregar")
    public String guardarEstudiante(Model model, @ModelAttribute Estudiante estudiante) {
        System.out.println(estudiante.getNombre());
        System.out.println(estudiante.getSemestre());
        System.out.println(estudiante.getEmail());
        estudianteService.nuevo(estudiante);
        return "redirect:/estudiantes";
    }


    // Seccion de cursos
    // Carga la lista de cursos y controla posibles errores al consultar
    @GetMapping("/cursos")
    public String goCursos(Model model) {
        try {
            List<Curso> cursos = cursoService.todos();
            System.out.println("Cursos encontrados: " + cursos.size());
            model.addAttribute("cursos", cursos);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("cursos", List.of());
        }
        model.addAttribute("pageTitle", "Registro de Cursos");
        return "/cursos/index";
    }

    // Muestra el formulario para crear un nuevo curso
    @GetMapping("/nuevoCurso")
    public String nuevoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("licenciaturas", licenciaturaService.todos());
        model.addAttribute("pageTitle", "Registrar Curso");
        return "/cursos/form";
    }

    // Guarda un curso nuevo y muestra en consola la licenciatura asociada
    @PostMapping("/cursos/agregar")
    public String agregarCurso(@ModelAttribute Curso curso) {
        System.out.println("Licenciatura seleccionada: " +
                (curso.getLicenciatura() != null ? curso.getLicenciatura().getId() : "NULA"));
        cursoService.nuevo(curso);
        return "redirect:/cursos";
    }

    // Elimina un curso segun su id
    @GetMapping("/cursos/eliminar")
    public String eliminarCurso(@RequestParam Long id) {
        cursoService.eliminar(id);
        return "redirect:/cursos";
    }

    // Carga los datos de un curso para editarlos desde el formulario
    @GetMapping("/cursos/editar")
    public String goEditarCurso(Model model, @RequestParam Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        model.addAttribute("curso", curso);
        model.addAttribute("licenciaturas", licenciaturaService.todos());
        model.addAttribute("pageTitle", "Editar Curso");
        return "/cursos/form";
    }
}