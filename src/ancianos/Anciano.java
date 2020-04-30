package ancianos;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import ancianos.Utilitat;

public class Anciano {

	private String dni;
	private String nombre;
	private String apellido;
	private int edad;
	private int telefono;
	private int telefonoFamiliar;
	private int gravedad;
	public Utilitat util = new Utilitat();
	
	public Anciano() {}
	
	public Anciano(String dni, String nombre,String apellido, int edad, int telefono, int telefonoFamiliar, int gravedad) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.telefono = telefono;
		this.telefonoFamiliar = telefonoFamiliar;
		this.gravedad = gravedad;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getTelefonoFamiliar() {
		return telefonoFamiliar;
	}

	public void setTelefonoFamiliar(int telefonoFamiliar) {
		this.telefonoFamiliar = telefonoFamiliar;
	}

	public int getGravedad() {
		return gravedad;
	}

	public void setGravedad(int gravedad) {
		this.gravedad = gravedad;
	}
	
	//metodos de modificar datos en la db 
	public void añadirAnciano() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		String dni = null;
		String nombre = null;
		String apellido = null;
		int edad = 0;
		int telefono = 0;
		int telefonoFamiliar = 0;
		int gravedad = 0;
		try {
			boolean x = true;
            while(x) {
            	System.out.print("Introduce el dni de la persona: ");
    			dni = keyboard.next();
                if (dni.length() == 9) {
                	x = false;
                } else {
                    System.out.print("Por favor, introduce un DNI válido.\n");
                    x = true;
                }
            }
			System.out.print("Introduce el nombre de la persona: ");
			nombre = keyboard.next();
			System.out.print("Introduce el apellidos de la persona: ");
			apellido = keyboard.next();
			System.out.print("Introduce la edad de la persona: ");
			edad = keyboard.nextInt();
			System.out.print("Introduce el telefono de la persona: ");
			telefono = keyboard.nextInt();
			System.out.print("Introduce el telefono de un familiar: ");
			telefonoFamiliar = keyboard.nextInt();
			x = true;
			while(x) {
				System.out.print("Introduce la gravedad de la persona (del 1 al 10): ");
				gravedad = keyboard.nextInt();
                if (gravedad >= 1 && gravedad <= 10) {
                	x = false;
                } else {
                    System.out.print("Por favor, introduce una gravedad válida.\n");
                    x = true;
                }
            }
			
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos, recuerda que los teléfonos\n"
					+ "han de ser números (sin extensión) y que la edad ha de estar en números.");
		}
		try{
			util.añadirAnciano(dni, nombre, apellido, edad, telefono, telefonoFamiliar, gravedad);
		}catch(SQLException e) {
        	System.out.println("");
        }
	}
	
	public void consultarPorDNI() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		String dni = null;
		try {
			boolean x = true;
            while(x) {
            	System.out.print("Introduce el dni de la persona que desea consultar: ");
    			dni = keyboard.next();
                if (dni.length() == 9) {
                	x = false;
                } else {
                    System.out.print("Por favor, introduce un DNI válido.\n");
                    x = true;
                }
            }
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos.");
		}
		try{
			util.CosultarPorDNI(dni);
		}catch(SQLException e){
			System.out.println("");
		}
	}

	public void consultarPorGravedad() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		int gravedad = 0;
		try {
			System.out.print("Introduce la graved de la persona que desea consultar: ");
			gravedad = keyboard.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos.");
		}
		try{
			util.CosultarPorGravedad(gravedad);
		}catch(SQLException e) {
        	System.out.println("");
        }
	}

	public void consultarAnciano() throws InputMismatchException, SQLException  {
		try{
			util.consultarAnciano();
		}catch(SQLException e) {
        	System.out.println("");
        }
	}
	
	public void borrarAnciano() throws SQLException, InputMismatchException {
		Scanner keyboard = new Scanner(System.in);
        String dni = null;
        try {
        	boolean x = true;
            while(x) {
            	System.out.print("DNI del ancianno que quieres borrar: ");
                dni = keyboard.next();
                if (dni.length() == 9) {
                	x = false;
                } else {
                    System.out.print("Por favor, introduce un DNI válido.\n");
                    x = true;
                }
            }         
        }catch(InputMismatchException e){
            System.out.println("Error al introducir el dni.");
        }
        try{
        	util.borrarAnciano(dni);
        }catch(SQLException e) {
        	System.out.println("");
        }
	}
	
	public void modificarAnciano() throws SQLException{
		Scanner keyboard = new Scanner(System.in);
        String dni = null;
        try {
            boolean x = true;
            while(x){
            	System.out.print("DNI del anciano que quieres modificar: ");
                dni = keyboard.next();
                if (dni.length() == 9) {
                	x = false;
                } else {
                    System.out.print("Por favor, introduce un DNI válido.\n");
                    x = true;
                }
            }
            try {
                util.modificarAnciano(dni);
            }catch(SQLException e) {
            	System.out.print("");
            }
        }catch(InputMismatchException e){
            System.out.println("DNI no válido.");
        }
        
	}
	
	@Override
	public String toString() {
		return "\nAnciano [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", telefono=" + telefono + ", telefonoFamiliar=" + telefonoFamiliar + ", gravedad=" + gravedad + "]";
	}



		
}