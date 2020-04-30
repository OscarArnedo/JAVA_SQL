package ancianos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import ancianos.Utilitat;

public class Registro {

	private String diaEntrada,diaSalida;
	private Anciano anciano;
	private Infraestructura infraestructura;
	public Utilitat util = new Utilitat();
	
	public Registro() {}
	
	public Registro(String diaEntrada, String diaSalida, Anciano anciano, Infraestructura infraestructura) {
		super();
		this.diaEntrada = diaEntrada;
		this.diaSalida = diaSalida;
		this.anciano = anciano;
		this.infraestructura = infraestructura;
	}

	public String getDiaEntrada() {
		return diaEntrada;
	}

	public void setDiaEntrada(String diaEntrada) {
		this.diaEntrada = diaEntrada;
	}

	public String getDiaSalida() {
		return diaSalida;
	}

	public void setDiaSalida(String diaSalida) {
		this.diaSalida = diaSalida;
	}

	public Anciano getAnciano() {
		return anciano;
	}

	public void setAnciano(Anciano anciano) {
		this.anciano = anciano;
	}

	public Infraestructura getInfraestructura() {
		return infraestructura;
	}

	public void setInfraestructura(Infraestructura infraestructura) {
		this.infraestructura = infraestructura;
	}
	
	//metodos de modificar datos en la db 
	public void añadirRegistro() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		int idInfraestructura = 0;
		String dniAnciano = null;
		String diaEntrada = null;
		String diaSalida = null;
		try {
			System.out.print("Introduce el id de la infraestructura: ");
			idInfraestructura = keyboard.nextInt();
			boolean x = true;
            while(x) {
            	System.out.print("Introduce el dni de la persona: ");
                dniAnciano = keyboard.next();
                 if (dniAnciano.length() == 9) {
                     x = false;
                 } else {
                     System.out.print("Por favor, introduce un DNI válido.\n");
                     x = true;
                 }
            }
			System.out.print("Introduce el dia de entrada de la persona (DD/MM/YY): ");
			diaEntrada = keyboard.next();
			System.out.print("Introduce el dia de salida de la persona (DD/MM/YY): ");
			diaSalida = keyboard.next();
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos.");
		}
		try{
			util.añadirRegistro(idInfraestructura,dniAnciano,diaEntrada,diaSalida);
		}catch(SQLException e) {
			System.out.println("");
		}
	}

	public void consultarRegistro() throws InputMismatchException, SQLException  {
		try{
			util.consultarRegistro();
		}catch(SQLException e) {
			System.out.println("");
		}
	}

	public void consultarPorId() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		int id = 0;
		try {
			System.out.print("Introduce el id de la reserva que desea consultar: ");
			id = keyboard.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos.");
		}
		try{
			util.ConsultarPorId(id);
		}catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void consultarPoridInfraestructura() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		int idInfraestructura = 0;
		try {
			System.out.print("Introduce el id de la Infraestructura que desea consultar: ");
			idInfraestructura = keyboard.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos.");
		}
		try{
			util.ConsultarPoridInfraestructura(idInfraestructura);
		}catch(SQLException e) {
			System.out.println("");
		}
	}

	public void consultarPordniAnciano() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		String dniAnciano = null;
		try {
			boolean x = true;
            while(x) {
            	System.out.print("Introduce el dni de la persona que desea consultar: ");
                dniAnciano = keyboard.next();
                 if (dniAnciano.length() == 9) {
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
			util.ConsultarPordniAnciano(dniAnciano);
		}catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void modificarRegistro() throws InputMismatchException, SQLException  {
		try{
			util.modificarRegistro();
		}catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void borrarRegistroPorId() throws SQLException, InputMismatchException {
		Scanner keyboard = new Scanner(System.in);
        int id = 0;
        try {
            System.out.println("ID del registro que quieres borrar: ");
            id = keyboard.nextInt();            
        }catch(InputMismatchException e){
            System.out.println("El ID ha de ser un numero.");
        }
        try{
        	util.borrarRegistro(id);
        }catch(SQLException e) {
			System.out.println("");
		}
	}
	
	@Override
	public String toString() {
		return "Registro [diaEntrada=" + diaEntrada + ", diaSalida=" + diaSalida + ", infraestructura="
				+ infraestructura + "]";
	}
	
	
	
}
