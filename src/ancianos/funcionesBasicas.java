package ancianos;

import java.sql.SQLException;
import java.util.InputMismatchException;


//En esta interfaz definimos unas funciones básicas que han de implementar tanto Gestor como Empleado
public interface funcionesBasicas {
	
	public void añadirAnciano() throws InputMismatchException, SQLException;
	
	public void consultarPorDNI() throws InputMismatchException, SQLException;
	
	public void consultarPorGravedad() throws InputMismatchException, SQLException;
	
	public void consultarAnciano() throws InputMismatchException, SQLException;
	
	public void añadirHotel() throws InputMismatchException, SQLException;
	
	public void añadirLocal() throws InputMismatchException, SQLException;
	
	public void mostrarPorInfraestructuraId() throws InputMismatchException, SQLException;
	
	public void mostrarInfraestructurasTodos() throws InputMismatchException, SQLException;
	
	public void mostrarHoteles() throws InputMismatchException, SQLException;
	
	public void mostrarLocales() throws InputMismatchException, SQLException;
}
