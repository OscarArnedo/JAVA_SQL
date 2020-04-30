package ancianos;

import java.sql.SQLException;
import java.util.InputMismatchException;

public class Empleado extends Usuario implements funcionesBasicas{

	Anciano a = new Anciano();
	Infraestructura i = new Infraestructura();
	
	public Empleado() {};
	
	public Empleado(String dni, String nombre, String contraseña) {
		super(dni, nombre, contraseña);
		// TODO Auto-generated constructor stub
	}

	public void añadirAnciano() throws InputMismatchException, SQLException {
		a.añadirAnciano();
	}
	
	public void consultarPorDNI() throws InputMismatchException, SQLException {
		a.consultarPorDNI();
	}
	
	public void consultarPorGravedad() throws InputMismatchException, SQLException {
		a.consultarPorGravedad();
	}
	
	public void consultarAnciano() throws InputMismatchException, SQLException {
		a.consultarAnciano();
	}
	
	public void añadirHotel() throws InputMismatchException, SQLException {
		i.añadirHotel();
	}
	
	public void añadirLocal() throws InputMismatchException, SQLException {
		i.añadirLocal();
	}
	
	public void mostrarPorInfraestructuraId() throws InputMismatchException, SQLException {
		i.mostrarPorId();
	}
	
	public void mostrarInfraestructurasTodos() throws InputMismatchException, SQLException {
		i.mostrarTodos();
	}
	
	public void mostrarHoteles() throws InputMismatchException, SQLException {
		i.mostrarHoteles();
	}
	
	public void mostrarLocales() throws InputMismatchException, SQLException {
		i.mostrarLocales();
	}
	
}
