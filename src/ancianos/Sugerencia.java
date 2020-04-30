package ancianos;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import ancianos.Utilitat;

public class Sugerencia {
	public int id,idInfraestructura;
	public String dniAnciano,diaEntrada,diaSalida;
	public Utilitat util = new Utilitat();
	
	public Sugerencia() {}
	
	public Sugerencia(int id, int idInfraestructura, String dniAnciano, String diaEntrada, String diaSalida) {
		super();
		this.id = id;
		this.idInfraestructura = idInfraestructura;
		this.dniAnciano = dniAnciano;
		this.diaEntrada = diaEntrada;
		this.diaSalida = diaSalida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdInfraestructura() {
		return idInfraestructura;
	}

	public void setIdInfraestructura(int idInfraestructura) {
		this.idInfraestructura = idInfraestructura;
	}

	public String getDniAnciano() {
		return dniAnciano;
	}

	public void setDniAnciano(String dniAnciano) {
		this.dniAnciano = dniAnciano;
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

	@Override
	public String toString() {
		return "Sugerencia [id=" + id + ", idInfraestructura=" + idInfraestructura + ", dniAnciano=" + dniAnciano
				+ ", diaEntrada=" + diaEntrada + ", diaSalida=" + diaSalida + "]";
	}
	
	
	//metodos de modificar datos en la db 
	public void añadirSugerenncia() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		int idInfraestructura = 0;
		String dniAnciano = null;
		String diaEntrada = null;
		String diaSalida = null;
		try {
			System.out.print("Introduce el id de la infraestructura: ");
			idInfraestructura = keyboard.nextInt();
			System.out.print("Introduce el dni de la persona: ");
			dniAnciano = keyboard.next();
			System.out.print("Introduce el dia de entrada de la persona (DD/MM/YY): ");
			diaEntrada = keyboard.next();
			System.out.print("Introduce el dia de salida de la persona (DD/MM/YY): ");
			diaSalida = keyboard.next();
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos.");
		}
		try{
			util.añadirSugerencia(idInfraestructura,dniAnciano,diaEntrada,diaSalida);
		}catch(SQLException e) {
			System.out.println("");
		}
	}

	public void consultarSugerencia() throws InputMismatchException, SQLException  {
		try{
			util.consultarSugerencia();
		}catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void consultarPorIdSugerencia() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		int id = 0;
		try {
			System.out.print("Introduce el id de la sugerencia que desea consultar: ");
			id = keyboard.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos.");
		}
		try{
			util.ConsultarPorIdSugerencia(id);
		}catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void consultarPoridInfraSugerencia() throws InputMismatchException, SQLException {
		Scanner keyboard = new Scanner(System.in);
		int idInfraestructura = 0;
		try {
			System.out.print("Introduce el id de la Infraestructura que desea consultar: ");
			idInfraestructura = keyboard.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Ha habido un error en la inserción de datos.");
		}
		try{
			util.ConsultarPoridInfraSugerencia(idInfraestructura);
		}catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void consultarPordniSugerencia() throws InputMismatchException, SQLException {
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
			util.ConsultarPordniSugerencia(dniAnciano);
		}catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void borrarSugerenciaPorId() throws SQLException, InputMismatchException {
		Scanner keyboard = new Scanner(System.in);
        int id = 0;
        try {
            System.out.print("ID de la sugerencia que quieres borrar: ");
            id = keyboard.nextInt();            
        }catch(InputMismatchException e){
            System.out.println("El ID ha de ser un numero.");
        }
        try{
        	util.borrarSugerencia(id);
        }catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void aprobarSugerencia() throws SQLException, InputMismatchException {
		Scanner keyboard = new Scanner(System.in);
        int id = 0;
        try {
            System.out.print("ID de la sugerencia que quieres aprobar: ");
            id = keyboard.nextInt();            
        }catch(InputMismatchException e){
            System.out.println("El ID ha de ser un numero.");
        }
		try{
        	util.aprobarSugerencia(id);
        }catch(SQLException e) {
			System.out.println("");
		}
	}
	
	public void modificarSugerencia() throws InputMismatchException, SQLException  {
		Scanner keyboard = new Scanner(System.in);
        String cambio = null;
        int idReal = 0;
        String nuevoDia = null;
        
        boolean x = true;
        while(x) {
	        try {
	        	System.out.print("\nIntroduzca el ID de la sugerencia que desea modificar: ");
	        	idReal = keyboard.nextInt();
	        	if(idReal*0 == 0) {
	        		x = false;
	        	}
	        }catch(InputMismatchException e) {
	        	System.out.println("Por favor, introduce un numero válido.");
	        }
        }
        
        x = true;
        while(x) {
        	System.out.println("Que dato desea modificar de la Sugerencia? ");
            System.out.println("1 . Día Entrada");
            System.out.println("2 . Día Salida");
            cambio = keyboard.next();
             if(cambio.equals("1") || cambio.equals("2")) {
                 x = false;
             } else {
                 System.out.print("Por favor, introduce una de las opciones.\n");
                 x = true;
             }
        }
        System.out.print("Introduzca el nuevo día: ");
        nuevoDia = keyboard.next();
	
		try{
			util.modificarSugerencia(idReal,cambio,nuevoDia);
	    }catch(SQLException e) {
	    	System.out.println("");
		}
	}
	
	
	
	
}
