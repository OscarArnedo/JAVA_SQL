package ancianos;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Infraestructura {

	protected boolean espacioDisponible;
	protected int numCamasOcupadas,numPersonalCualificado,numCamas,numPersonal,numHabitaciones, numSalas;
	protected String nombre,localizacion;
	public Utilitat util = new Utilitat();

	//Constructor vacío
	public Infraestructura() {}
	
	//Constructor de Hoteles
	public Infraestructura(String nombre, boolean espacioDisponible, int numCamas, int numCamasOcupadas,
			int numPersonalCualificado, int numPersonal, String localizacion, int numHabitaciones) {
		
		this.nombre = nombre;
		this.espacioDisponible = espacioDisponible;
		this.numCamas = numCamas;
		this.numCamasOcupadas = numCamasOcupadas;
		this.numPersonalCualificado = numPersonalCualificado;
		this.numPersonal = numPersonal;
		this.localizacion = localizacion;
		this.numHabitaciones = numHabitaciones;
	}
	
	//Constructor de Locales
		public Infraestructura(int numSalas, String nombre, boolean espacioDisponible, int numCamas, int numCamasOcupadas,
				int numPersonalCualificado, int numPersonal, String localizacion) {
			
			this.numSalas = numSalas;
			this.nombre = nombre;
			this.espacioDisponible = espacioDisponible;
			this.numCamas = numCamas;
			this.numCamasOcupadas = numCamasOcupadas;
			this.numPersonalCualificado = numPersonalCualificado;
			this.numPersonal = numPersonal;
			this.localizacion = localizacion;
		}
	
	
	public boolean isEspacioDisponible() {
		return espacioDisponible;
	}

	public void setEspacioDisponible(boolean espacioDisponible) {
		this.espacioDisponible = espacioDisponible;
	}

	public int getNumCamasOcupadas() {
		return numCamasOcupadas;
	}

	public void setNumCamasOcupadas(int numCamasOcupadas) {
		this.numCamasOcupadas = numCamasOcupadas;
	}

	public int getNumPersonalCualificado() {
		return numPersonalCualificado;
	}

	public void setNumPersonalCualificado(int numPersonalCualificado) {
		this.numPersonalCualificado = numPersonalCualificado;
	}

	public int getNumCamas() {
		return numCamas;
	}

	public void setNumCamas(int numCamas) {
		this.numCamas = numCamas;
	}

	public int getNumPersonal() {
		return numPersonal;
	}

	public void setNumPersonal(int numPersonal) {
		this.numPersonal = numPersonal;
	}

	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public int getNumSalas() {
		return numSalas;
	}

	public void setNumSalas(int numSalas) {
		this.numSalas = numSalas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Utilitat getUtil() {
		return util;
	}

	public void setUtil(Utilitat util) {
		this.util = util;
	}
		
	@Override
	public String toString() {
		return "\nInfraestructura [espacioDisponible=" + espacioDisponible + ", numCamasOcupadas=" + numCamasOcupadas
				+ ", numPersonalCualificado=" + numPersonalCualificado + ", numCamas=" + numCamas + ", numPersonal="
				+ numPersonal + ", numHabitaciones=" + numHabitaciones + ", numSalas=" + numSalas + ", nombre=" + nombre
				+ ", localizacion=" + localizacion + "]";
	}
	
	
	//Metodos
	public void añadirHotel() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
        String nombre = null;
        int espacio = 0;
        int numCamas = 0;
        int numCamasOcupadas = 0;
        int numPersonal = 0;
        int numPersonalCualificado = 0;
        String localizacion = null;
        int numHabitaciones = 0;
        try {
			System.out.println("Añadir un hotel\n");
            System.out.print("Nombre: ");
            nombre = keyboard.next();
            System.out.print("Espacio disponible? (1 o 0): ");
            espacio = keyboard.nextInt();
            System.out.print("Numero de camas: ");
            numCamas = keyboard.nextInt();
            System.out.print("Numero de camas ocupadas: ");
            numCamasOcupadas = keyboard.nextInt();
            System.out.print("Cantidad personal: ");
            numPersonal = keyboard.nextInt();
            System.out.print("Cantidad personal cualificado: ");
            numPersonalCualificado = keyboard.nextInt();
            System.out.print("Localizacion: ");
            localizacion = keyboard.next();
            System.out.print("Numero de habitaiones: ");
            numHabitaciones = keyboard.nextInt();
            
            try{
            	util.añadirHotel(nombre, espacio, numCamas, numCamasOcupadas, numPersonal, numPersonalCualificado, localizacion,numHabitaciones);
            }catch(SQLException e) {
            	System.out.println("");
            }
            
        }catch(InputMismatchException e){
            System.out.println("Error al insertar los datos.");
        }
	}
	
	public void añadirLocal() throws SQLException {
		Scanner keyboard = new Scanner(System.in);
        String nombre = null;
        int espacio = 0;
        int numCamas = 0;
        int numCamasOcupadas = 0;
        int numPersonal = 0;
        int numPersonalCualificado = 0;
        String localizacion = null;
        int numSalas = 0;
        try {
			System.out.println("Añadir un local\n");
            System.out.print("Nombre: ");
            nombre = keyboard.next();
            System.out.print("Espacio disponible? (1 o 0): ");
            espacio = keyboard.nextInt();
            System.out.print("Numero de camas: ");
            numCamas = keyboard.nextInt();
            System.out.print("Numero de camas ocupadas: ");
            numCamasOcupadas = keyboard.nextInt();
            System.out.print("Cantidad personal: ");
            numPersonal = keyboard.nextInt();
            System.out.print("Cantidad personal cualificado: ");
            numPersonalCualificado = keyboard.nextInt();
            System.out.print("Localizacion: ");
            localizacion = keyboard.next();
            System.out.print("Numero de salas: ");
            numSalas = keyboard.nextInt();
            
            try{
            	util.añadirLocal(nombre, espacio, numCamas, numCamasOcupadas, numPersonal, numPersonalCualificado, localizacion,numSalas);
            }catch(SQLException e) {
            	System.out.println("");
            }
            
        }catch(InputMismatchException e){
            System.out.println("Error al insertar los datos.");
        }
	}

	public void mostrarPorId() throws SQLException, InputMismatchException {
		Scanner keyboard = new Scanner(System.in);
        int id = 0;
        try {
            System.out.println("ID de la infraestructura a mostrar: ");
            id = keyboard.nextInt();            
            try{
            	util.mostrarInfraId(id);
            }catch(SQLException e) {
            	System.out.println("");
            }
        }catch(InputMismatchException e){
            System.out.println("El ID ha de ser un numero.");
        }
	}
	
	public void mostrarTodos() throws SQLException {
        System.out.println("Mostrando infraestructuras...");
        try{
        	util.mostrarInfraestructuras();
        }catch(SQLException e) {
        	System.out.println("");
        }
	}
	
	public void mostrarHoteles() throws SQLException {
        System.out.println("Mostrando hoteles...");
        try{
        	util.mostrarHoteles();
        }catch(SQLException e) {
        	System.out.println("");
        }
	}
	
	public void mostrarLocales() throws SQLException {
        System.out.println("Mostrando locales...");
        try{
        	util.mostrarLocales();
        }catch(SQLException e) {
        	System.out.println("");
        }
	}
	
	public void borrarPorId() throws SQLException, InputMismatchException {
		Scanner keyboard = new Scanner(System.in);
        int id = 0;
        try {
            System.out.print("ID de la infraestructura que quieres borrar: ");
            id = keyboard.nextInt();            
        }catch(InputMismatchException e){
            System.out.println("El ID ha de ser un numero.");
        }
        try{
        	util.borrarInfraestructuras(id);
        }catch(SQLException e) {
        	System.out.println("");
        }
	}
	
	public void modificarInfraestructura() throws SQLException{
        Scanner keyboard = new Scanner(System.in);
        int id = 0;
        try {
            System.out.print("id de la infraestructura que deseas modificar: ");
            id = keyboard.nextInt();
            try {
            	util.modificarInfraestructura(id);
            }catch(SQLException e) {
                System.out.print("");
            }
        }catch(InputMismatchException e){
            System.out.println("id no válido.");
        }

    }
	
}
