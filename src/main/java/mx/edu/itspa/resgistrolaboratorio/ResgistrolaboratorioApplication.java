// Clase principal del proyecto Spring Boot RegistroLaboratorio
// Es el punto de entrada de la aplicacion y se encarga de inicializar el contexto de Spring
// La anotacion SpringBootApplication habilita el escaneo de componentes, configuraciones y beans o frijolitos
package mx.edu.itspa.resgistrolaboratorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResgistrolaboratorioApplication {

	// Metodo principal que ejecuta la aplicacion
	// Inicializa el servidor embebido y carga todos los componentes del proyecto
	public static void main(String[] args) {
		SpringApplication.run(ResgistrolaboratorioApplication.class, args);
	}

}