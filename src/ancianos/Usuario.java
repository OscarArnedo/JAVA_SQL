package ancianos;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Usuario {

	protected String dni;
	protected String nombre;
	protected String contraseña;
	Scanner keyboard = new Scanner(System.in);
	Utilitat util = new Utilitat();
	
	public Usuario() {}
	
	public Usuario(String dni, String nombre, String contraseña) {
		this.dni = dni;
		this.nombre = nombre;
		this.contraseña = contraseña;
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
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public void añadirUsuario() throws SQLException{
		String dni = null;
		String nombre = null;
		String apellido = null;
		String tipoUsuario = null;
		String contraseña = null;
        try {
			System.out.println("Añadir un usuario\n");
            System.out.print("DNI: ");
            dni = keyboard.next();
            System.out.print("Nombre: ");
            nombre = keyboard.next();
            System.out.print("Apellido: ");
            apellido = keyboard.next();
            boolean x = false;
            while(!x) {
            	System.out.print("Tipo de usuario (empleado, gestor, coordinador): ");
                tipoUsuario = keyboard.next();
                 if (tipoUsuario.equals("coordinador")) {
                     x = true;
                 }else if(tipoUsuario.equals("gestor")) {
                     x = true;
                 }else if(tipoUsuario.equals("empleado")) {
                     x = true;
                 } else {
                     System.out.print("Introduce uno de los 3 tipos válidos.\n");
                     x = false;
                 }
            }
            System.out.print("Contraseña: ");
            contraseña = keyboard.next();
            try {
                util.añadirUsuario(dni, nombre, apellido, tipoUsuario, contraseña);
            }catch(SQLException e) {
            	System.out.print("");
            }
            
        }catch(InputMismatchException e){
            System.out.println("Error al insertar los datos.");
        }
	}
	
	public void modificarUsuario() throws SQLException{
		Scanner keyboard = new Scanner(System.in);
        String dni = null;
        try {
            boolean x = true;
            while(x) {
            	System.out.print("DNI del usuario que quieres modificar: ");
                dni = keyboard.next();
                 if (dni.length() == 9) {
                     x = false;
                 } else {
                     System.out.print("Por favor, introduce un DNI válido.\n");
                     x = true;
                 }
            }
            try {
                util.modificarUsuario(dni);
            }catch(SQLException e) {
            	System.out.print("");
            }
        }catch(InputMismatchException e){
            System.out.println("DNI no válido.");
        }
        
	}
	
	public void eliminarUsuario() throws SQLException{
		Scanner keyboard = new Scanner(System.in);
        String dni = null;
        try {
        	boolean x = true;
            while(x) {
            	System.out.print("DNI del usuario que quieres modificar: ");
                dni = keyboard.next();
                 if (dni.length() == 9) {
                     x = false;
                 } else {
                     System.out.print("Por favor, introduce un DNI válido.\n");
                     x = true;
                 }
            }
        }catch(InputMismatchException e){
            System.out.println("DNI no valido.");
        }
        try{
        	util.borrarUsuario(dni);
        }catch(SQLException e) {
            System.out.println("");
        }
	}
	
	public void mostrarUsuarios() throws SQLException{
		System.out.println("Mostrando usuarios...");
        util.mostrarUsuarios();
	}
	
	public void buscarUsuarioDni() throws SQLException{
		Scanner keyboard = new Scanner(System.in);
        String dni = null;
        try {
        	boolean x = true;
            while(x) {
            	System.out.print("DNI del usuario que quieres modificar: ");
                dni = keyboard.next();
                 if (dni.length() == 9) {
                     x = false;
                 } else {
                     System.out.print("Por favor, introduce un DNI válido.\n");
                     x = true;
                 }
            }            
            try {
                util.mostrarUsuarioDni(dni);;
            }catch(SQLException e) {
                System.out.println("");
            }
        }catch(InputMismatchException e){
            System.out.print("Introduce un DNI válido.");
        }	
	}
}
