package ancianos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


public class main {

	public static void main(String[] args) throws SQLException {
		
		Utilitat util = new Utilitat();     
        
		Scanner keyboard = new Scanner(System.in);

		boolean salir = false;
		
		while (!salir) {
			System.out.print("Acceso a la aplicación: \n" 
					+ "1. Acceder como empleado. \n" 
					+ "2. Acceder como gestor. \n"
					+ "3. Acceder como coordinador. \n"
					+ "4. Salir del programa.  \n\n"
					+ "Opción: ");
			String agregar = keyboard.next();
			
			switch (agregar) {
				case "1":
					if(util.comprobarAcceso("empleado")) {
						menuEmpleado();
					}else {
						System.out.println("Error en la contraseña o en el nombre de usuario");
					}
					break;
				case "2":
					if(util.comprobarAcceso("gestor")) {
						menuGestor();
					}else {
						System.out.println("Error en la contraseña o en el nombre de usuario");
					}
					break;
				case "3":
					if(util.comprobarAcceso("coordinador")) {
						menuCoordinador();
					}else {
						System.out.println("Error en la contraseña o en el nombre de usuario");
					}
					break;
				case "4":
					System.out.println("\nCerrando el programa...");
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
		
	
	public static void menuCoordinador() throws SQLException {
		
		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nMENÚ COORDINADOR \n" 
					+ "1. Area Usuarios. \n" 
					+ "2. Area Ancianos. \n"
					+ "3. Area Infraestructuras. \n"
					+ "4. Area Sugerencias. \n"
					+ "5. Area Registros. \n"
					+ "6. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					menuUsuarios();
					break;
				case "2":
					menuAncianos();				
					break;
				case "3":
					menuInfraestructuras();				
					break;
				case "4":
					menuSugerencias();				
					break;
				case "5":
					menuRegistros();					
					break;
				case "6":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
	
	public static void menuEmpleado() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nMENÚ EMPLEADO \n" 
					+ "1. Area Ancianos. \n"
					+ "2. Area Infraestructuras. \n"
					+ "3. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					menuAncianosEmpleado();				
					break;
				case "2":
					menuInfraestructuras();
					break;
				case "3":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
	
	public static void menuGestor() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nMENÚ GESTOR \n" 
					+ "1. Area Ancianos. \n"
					+ "2. Area Infraestructuras. \n"
					+ "3. Area Sugerencias. \n"
					+ "4. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					menuAncianosEmpleado();
					break;
				case "2":
					menuInfraestructuras();
					break;
				case "3":
					menuSugerenciasGestor();
					break;
				case "4":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}	
	}
	
	
	public static void menuUsuarios() throws SQLException {
		
		Usuario u = new Usuario();
		
		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nAREA USUARIOS\n" 
					+ "1. Crear un usuario. \n" 
					+ "2. Borrar un usuario. \n"
					+ "3. Editar un usuario. \n"
					+ "4. Mostrar todos los usuarios. \n"
					+ "5. Mostrar un solo usuario. \n"
					+ "6. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					u.añadirUsuario();
					break;
				case "2":
					u.eliminarUsuario();					
					break;
				case "3":
					u.modificarUsuario();					
					break;
				case "4":
					u.mostrarUsuarios();				
					break;
				case "5":
					u.buscarUsuarioDni();					
					break;
				case "6":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
	
	public static void menuAncianos() throws SQLException {
		
		Anciano a = new Anciano();
		Utilitat util = new Utilitat();		

		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nAREA ANCIANOS\n" 
					+ "1. Crear/Registrar un anciano. \n" 
					+ "2. Borrar un anciano. \n"
					+ "3. Editar un anciano. \n"
					+ "4. Mostrar todos los ancianos. \n"
					+ "5. Mostrar un solo anciano. \n"
					+ "6. Filtrar ancianos por nivel de gravedad. \n"
					+ "7. Filtrar ancianos por edad. \n"
					+ "8. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					a.añadirAnciano();
					break;
				case "2":
					a.borrarAnciano();
					break;
				case "3":
					a.modificarAnciano();	
					break;
				case "4":
					a.consultarAnciano();			
					break;
				case "5":
					a.consultarPorDNI();					
					break;
				case "6":
					a.consultarPorGravedad();			
					break;
				case "7":
					//Aqui usamos la generica
					ComparadorAncianosEdad comparadorAncianosEdad = new ComparadorAncianosEdad();
					llistaOrdenada<Anciano> ancianos = new llistaOrdenada<>();
					ancianos = util.guardarAncianos();
					Collections.sort(ancianos.getLlista(), comparadorAncianosEdad);
			        System.out.println(ancianos);
			        break;
				case "8":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
	
	public static void menuInfraestructuras() throws SQLException {
		
		Infraestructura i = new Infraestructura();
		Utilitat util = new Utilitat();		

		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nAREA INFRAESTRUCTURAS\n" 
					+ "1. Añadir un hotel. \n" 
					+ "2. Añadir un local. \n" 
					+ "3. Borrar una infraestructura. \n"
					+ "4. Editar una infraestructura. \n"
					+ "5. Mostrar todas las infraestructuras. \n"
					+ "6. Mostrar una infraestructura. \n"
					+ "7. Mostrar hoteles. \n"
					+ "8. Mostrar locales. \n"
					+ "9. Mostrar infraestructuras ordenadas por el numero de camas. \n"
					+ "10. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					i.añadirHotel();
					break;
				case "2":
					i.añadirLocal();
					break;
				case "3":
					i.borrarPorId();
					break;
				case "4":
					i.modificarInfraestructura();
					break;
				case "5":
					i.mostrarTodos();					
					break;
				case "6":
					i.mostrarPorId();			
					break;
				case "7":
					i.mostrarHoteles();			
					break;
				case "8":
					i.mostrarLocales();
					break;
				case "9":
					//Aqui usamos la generica
					ComparadorInfraestructurasCamas comparadorInfraestructurasCamas = new ComparadorInfraestructurasCamas();
			        llistaOrdenada<Infraestructura> infraestructuras = util.guardarInfraestructuras();			        
			        Collections.sort(infraestructuras.getLlista(), comparadorInfraestructurasCamas);
			        System.out.println(infraestructuras);
					break;
				case "10":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
	
	public static void menuSugerencias() throws SQLException {
		
		Sugerencia s = new Sugerencia();
		
		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nAREA SUGERENCIAS\n" 
					+ "1. Hacer una sugerencia. \n" 
					+ "2. Aprobar una sugerencia. \n"
					+ "3. Rechazar una sugerencia. \n"
					+ "4. Mostrar todas las sugerencias. \n"
					+ "5. Buscar una sugerencia por ID. \n"
					+ "6. Filtrar sugerencias por DNI del anciano. \n"
					+ "7. Filtrar sugerencias por ID de la infraestructura. \n"
					+ "8. Editar una sugerencia. \n"
					+ "9. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					s.añadirSugerenncia();
					break;
				case "2":
					s.aprobarSugerencia();
					break;
				case "3":
					s.borrarSugerenciaPorId();		
					break;
				case "4":
					s.consultarSugerencia();	
					break;
				case "5":
					s.consultarPorIdSugerencia();					
					break;
				case "6":
					s.consultarPordniSugerencia();			
					break;
				case "7":
					s.consultarPoridInfraSugerencia();		
					break;
				case "8":
					s.modificarSugerencia();
					break;
				case "9":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}


	public static void menuRegistros() throws SQLException {
		
		Registro r = new Registro();
		
		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nAREA REGISTROS\n" 
					+ "1. Mostrar todos los registros. \n" 
					+ "2. Buscar registro por ID. \n"
					+ "3. Filtrar registros por el DNI del anciano. \n"
					+ "4. Filtrar registros por el ID de la infraestructura. \n"
					+ "5. Editar un registro. \n"
					+ "6. Borrar un registro. \n"
					+ "7. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					r.consultarRegistro();
					break;
				case "2":
					r.consultarPorId();					
					break;
				case "3":
					r.consultarPordniAnciano();					
					break;
				case "4":
					r.consultarPoridInfraestructura();				
					break;
				case "5":
					r.modificarRegistro();					
					break;
				case "6":
					r.borrarRegistroPorId();				
					break;
				case "7":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
	
	public static void menuAncianosEmpleado() throws SQLException {
		
		Anciano a = new Anciano();
		
		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nAREA ANCIANOS\n" 
					+ "1. Crear/Registrar un anciano. \n" 
					+ "2. Mostrar todos los ancianos. \n"
					+ "3. Mostrar un solo anciano. \n"
					+ "4. Filtrar ancianos por nivel de gravedad. \n"
					+ "5. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					a.añadirAnciano();
					break;
				case "2":
					a.consultarAnciano();			
					break;
				case "3":
					a.consultarPorDNI();					
					break;
				case "4":
					a.consultarPorGravedad();			
					break;
				case "5":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
	
	public static void menuSugerenciasGestor() throws SQLException {
		
		Sugerencia s = new Sugerencia();
		
		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("\nAREA SUGERENCIAS\n" 
					+ "1. Hacer una sugerencia. \n" 
					+ "2. Mostrar todas las sugerencias. \n"
					+ "3. Buscar una sugerencia por ID. \n"
					+ "4. Filtrar sugerencias por DNI del anciano. \n"
					+ "5. Filtrar sugerencias por ID de la infraestructura. \n"
					+ "6. Volver.  \n\n"
					+ "Opción: ");
			String opcion = keyboard.next();
			
			switch (opcion) {
				case "1":
					s.añadirSugerenncia();
					break;
				case "2":
					s.consultarSugerencia();	
					break;
				case "3":
					s.consultarPorIdSugerencia();					
					break;
				case "4":
					s.consultarPordniSugerencia();			
					break;
				case "5":
					s.consultarPoridInfraSugerencia();		
					break;
				case "6":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce una de las opciones.");
					break;
			}
		}
	}
	
	public static class ComparadorAncianosEdad implements Comparator<Anciano> {

        public int compare(Anciano ancianoA, Anciano ancianoB) {

            if (ancianoA.getEdad() < ancianoB.getEdad()) {
                return 1;

            } else if (ancianoA.getEdad() > ancianoB.getEdad()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
	
	public static class ComparadorInfraestructurasCamas implements Comparator<Infraestructura> {

        public int compare(Infraestructura infraA, Infraestructura infraB) {

            if (infraA.getNumCamas() < infraB.getNumCamas()) {
                return 1;

            } else if (infraA.getNumCamas() > infraB.getNumCamas()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
	
	
}
