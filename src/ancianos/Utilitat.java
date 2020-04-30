package ancianos;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Utilitat {

    Connection conexion = null;
    Statement statement = null;
    Scanner keyboard = new Scanner(System.in);

    public Utilitat() {}
    
    
	public void conectar() throws SQLException {
	    try{
	        Class.forName("com.mysql.jdbc.Driver");
	        conexion = DriverManager.getConnection("jdbc:mysql://localhost/ancianos","root","");
	        statement = conexion.createStatement();
	    }catch(ClassNotFoundException cn){
	        cn.printStackTrace();
	    }catch(SQLException e){
	        e.printStackTrace();
	    }
	}
	
	public void cerrarSesion() throws SQLException {
	    try {
	        conexion.close();
	    } catch (SQLException e) {
	        throw e;
	    }
	}
    
	
	
	//----------------------------------------------PILLAR DATOS BBDD--------------------------------------------------
	public llistaOrdenada<Infraestructura> guardarInfraestructuras() throws SQLException {

        llistaOrdenada<Infraestructura> lista = new llistaOrdenada<>();
        try {
        	conectar();
            ResultSet resul = statement.executeQuery("SELECT * FROM infraestructura");
            while (resul.next()) {
                int numHab = resul.getInt("numHabitaciones");
            	if( numHab != 0) {
                	//Añadimos hotel
            		lista.afegir(new Infraestructura(resul.getString("nombre"), resul.getBoolean("espacioDisponible"), resul.getInt("numCamas"), resul.getInt("numCamasOcupadas"), resul.getInt("numPersonalCualificado"), resul.getInt("numPersonal"), resul.getString("localizacion"), resul.getInt("numHabitaciones")));
                }else{
                	//Añadimos loal
                	lista.afegir(new Infraestructura(resul.getInt("numSalas"),resul.getString("nombre"), resul.getBoolean("espacioDisponible"), resul.getInt("numCamas"), resul.getInt("numCamasOcupadas"), resul.getInt("numPersonalCualificado"), resul.getInt("numPersonal"), resul.getString("localizacion")));
                }
            }
            cerrarSesion();
        } catch (SQLException e) {
            System.out.println("Error.");
        }

        return lista;
    }
	
	public llistaOrdenada<Anciano> guardarAncianos() throws SQLException {

        llistaOrdenada<Anciano> lista = new llistaOrdenada<>();
        try {
        	conectar();
            ResultSet resul = statement.executeQuery("SELECT * FROM anciano");
            while (resul.next()) {
            	lista.afegir(new Anciano(resul.getString("dni"), resul.getString("nombre"), resul.getString("apellido"), resul.getInt("edad"), resul.getInt("telefono"), resul.getInt("telefonoFamiliar"), resul.getInt("gravedad")));
            }
            cerrarSesion();
        } catch (SQLException e) {
            System.out.println("Error.");
        }

        return lista;
    }
       
    //---------------------------------------------------ANCIANO--------------------------------------------------------------------
    
    
    public void añadirAnciano(String dni,String nombre,String apellido,int edad,int telefono,int telefonoFamiliar,int gravedad) throws SQLException, InputMismatchException {
        try {
            conectar();
            String sqlString = "INSERT INTO anciano VALUES('" + dni + "','" + nombre + "','" + apellido + "'," + edad +"," + telefono + "," + telefonoFamiliar + ","+ gravedad +");";
            statement.executeUpdate(sqlString);
            cerrarSesion();
        } catch (SQLException e) {
            System.out.println("Ha habido un error en la inserción de datos.");
            e.printStackTrace();
        }
    }
    
    //Metodo de consultar de todos los ancianos
    public void consultarAnciano() throws SQLException, InputMismatchException{
        try {
        	conectar();
            ResultSet resul = statement.executeQuery("SELECT * FROM anciano");
            while(resul.next()){
                System.out.println("-----------------------------------------------");
                System.out.println("dni: "+resul.getString("dni"));
                System.out.println("Nombre: "+resul.getString("nombre"));
                System.out.println("Apellido: "+resul.getString("apellido"));
                System.out.println("Edad: "+resul.getString("edad"));
                System.out.println("Telefono: "+resul.getString("telefono"));
                System.out.println("Telefono Familiar: "+resul.getString("telefonoFamiliar"));
                System.out.println("Gravedad: "+resul.getString("gravedad"));
                System.out.println("-----------------------------------------------\n");  
            }
           cerrarSesion();
        }catch(SQLException e){
        	System.out.println("Ha habido un error en la consulta.");
            e.printStackTrace();
        }
    }

	//metodo de consultar un Anciano por su dni
	public void CosultarPorDNI(String dni) throws SQLException, InputMismatchException{
        try {
        	conectar();
            ResultSet resul = statement.executeQuery("SELECT * FROM anciano WHERE dni = '" + dni + "';");
            while(resul.next()){
                System.out.println("-----------------------------------------------");
                System.out.println("dni: "+resul.getString("dni"));
                System.out.println("Nombre: "+resul.getString("nombre"));
                System.out.println("Apellido: "+resul.getString("apellido"));
                System.out.println("Edad: "+resul.getString("edad"));
                System.out.println("Telefono: "+resul.getString("telefono"));
                System.out.println("Telefono Familiar: "+resul.getString("telefonoFamiliar"));
                System.out.println("Gravedad: "+resul.getString("gravedad"));
                System.out.println("-----------------------------------------------\n");  
            }
            cerrarSesion();
        }catch(SQLException e){
        	System.out.println("Ha habido un error en la consulta.");
            e.printStackTrace();
        }
	}
	
	//metodo de consultar un Anciano por su graved
	public void CosultarPorGravedad(int gravedad ) throws SQLException, InputMismatchException{
        try {
        	conectar();
            ResultSet resul = statement.executeQuery("SELECT * FROM anciano WHERE gravedad = " + gravedad + ";");
            while(resul.next()){
                System.out.println("-----------------------------------------------");
                System.out.println("dni: "+resul.getString("dni"));
                System.out.println("Nombre: "+resul.getString("nombre"));
                System.out.println("Apellido: "+resul.getString("apellido"));
                System.out.println("Edad: "+resul.getString("edad"));
                System.out.println("Telefono: "+resul.getString("telefono"));
                System.out.println("Telefono Familiar: "+resul.getString("telefonoFamiliar"));
                System.out.println("Gravedad: "+resul.getString("gravedad"));
                System.out.println("-----------------------------------------------\n");  
            }
            cerrarSesion();
        }catch(SQLException e){
        	System.out.println("Ha habido un error en la consulta.");
            e.printStackTrace();
        }
	}
    
	//metodo de borrar un anciano por dni
  	public void borrarAnciano(String dni) throws SQLException, InputMismatchException{
        try {
        	conectar();
            statement.executeUpdate("DELETE FROM anciano WHERE dni = '" + dni + "';");
            System.out.println("-----------------------------------------------");
            System.out.println("Se ha borrado el anciano con el siguiente dni: " + dni);
            System.out.println("-----------------------------------------------\n"); 
            cerrarSesion();
        }catch(SQLException e){
        	System.out.println("Error al eliminar el anciano.");
            e.printStackTrace();
        }
   }
	
  	public void modificarAnciano(String dni) throws SQLException, InputMismatchException {
  		conectar();
  		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print(""
					+ "1. Modificar nombre. \n" 
					+ "2. Modificar apellido. \n"
					+ "3. Modificar edad. \n"
					+ "4. Modificar telefono. \n"
					+ "5. Modificar telefono familiar. \n"
					+ "6. Modificar gravedad. \n"
					+ "7. Volver.  \n\n"
					+ "Opción: ");
			String agregar = keyboard.next();
			
			switch (agregar) {
				case "1":
					System.out.print("Nuevo nombre: ");
					String nombre = keyboard.next();
					try {
		                String sqlString = "UPDATE anciano SET nombre= '" + nombre + "' WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "2":
					System.out.print("Nuevo apellido: ");
					String apellido = keyboard.next();
					try {
		                String sqlString = "UPDATE anciano SET apellido= '" + apellido + "' WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "3":
					System.out.print("Edad: ");
					int edad = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE anciano SET edad= " + edad + "' WHERE dni=" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "4":
					System.out.print("Nuevo telefono: ");
					int telefono = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE usuario SET telefono= " + telefono + " WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "5":
					System.out.print("Nuevo telefono familiar: ");
					int telefonoF = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE usuario SET telefonoFamiliar= " + telefonoF + " WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "6":
					System.out.print("Gravedad: ");
					int gravedad = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE usuario SET telefonoFamiliar= " + gravedad + " WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		                }
					break;
				case "7":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce un  número del 1 al 5.");
					break;
			}
		}
  	}
  	
  	
  	//---------------------------------------------INFRAESTRUCTURAS-------------------------------------------------------------------

    
    public void añadirHotel(String nombre, int espacio, int numCamas, int numCamasOcupadas, int numPersonal, int numPersonalCualificado, String localizacion, int numHabitaciones) throws SQLException, InputMismatchException {
        try {
        	conectar();
            String sqlString = "INSERT INTO infraestructura (nombre,espacioDisponible,numCamas,numCamasOcupadas,numPersonal,numPersonalCualificado,localizacion,numHabitaciones) VALUES('"+ nombre + "'," + espacio + "," + numCamas + "," + numCamasOcupadas + "," + numPersonalCualificado + "," + numPersonal + ",'" + localizacion + "',"+ numHabitaciones+");";
            statement.executeUpdate(sqlString);
            statement.close();
            cerrarSesion();
        } catch (SQLException e){
        	System.out.println(e);
        	System.out.println("Error al añadir el hotel.");        
        }
    }
    
    public void añadirLocal(String nombre, int espacio, int numCamas, int numCamasOcupadas, int numPersonal, int numPersonalCualificado, String localizacion, int numSalas) throws SQLException, InputMismatchException {
        try {
        	conectar();
            String sqlString = "INSERT INTO infraestructura (nombre,espacioDisponible,numCamas,numCamasOcupadas,numPersonal,numPersonalCualificado,localizacion,numSalas) VALUES('"+ nombre + "'," + espacio + "," + numCamas + "," + numCamasOcupadas + "," + numPersonalCualificado + "," + numPersonal + ",'" + localizacion + "',"+ numSalas+");";
            statement.executeUpdate(sqlString);
            statement.close();
            cerrarSesion();
        } catch (SQLException e){
        	System.out.println(e);
        	System.out.println("Error al añadir el local.");        
        }
    }
    
    
    public void mostrarInfraId(int id) throws SQLException{
    	Scanner keyboard = new Scanner(System.in);	
    	conectar();
    	try {
     		Statement sentencia = conexion.createStatement();
 	        ResultSet resul = sentencia.executeQuery("SELECT * FROM infraestructura WHERE id = "+id);
            while(resul.next()){
            	System.out.println("-----------------------------------------------\n");
                System.out.println("ID: "+resul.getString("id"));
                System.out.println("Nombre: "+resul.getString("nombre"));
                System.out.println("Espacio disponible: "+resul.getString("espacioDisponible"));
                System.out.println("Numero de camas: "+resul.getString("numCamas"));
                System.out.println("Numero de camas ocupadas: "+resul.getString("numCamasOcupadas"));
                System.out.println("Cantidad de personal: "+resul.getString("numPersonal"));
                System.out.println("Cantidad de personal cualificado: "+resul.getString("numPersonalCualificado"));
                System.out.println("Localización: "+resul.getString("localizacion"));
                System.out.println("Numero de habitaciones: "+resul.getString("numHabitaciones"));
                System.out.println("Numero de salas: "+resul.getString("numSalas")+"s\n");
            	System.out.println("-----------------------------------------------\n");
            }
            resul.close();
            sentencia.close();
            cerrarSesion();
    	}catch(SQLException e){
        	System.out.println("Error al mostrar las infraestructuras.");
        }
    }
    
    public void mostrarInfraestructuras() throws SQLException{
    	Scanner keyboard = new Scanner(System.in);	
    	conectar();
    	try {
     		Statement sentencia = conexion.createStatement();
 	        ResultSet resul = sentencia.executeQuery("SELECT * FROM infraestructura");
            while(resul.next()){
            	System.out.println("-----------------------------------------------\n");
                System.out.println("ID: "+resul.getString("id"));
                System.out.println("Nombre: "+resul.getString("nombre"));
                System.out.println("Espacio disponible: "+resul.getString("espacioDisponible"));
                System.out.println("Numero de camas: "+resul.getString("numCamas"));
                System.out.println("Numero de camas ocupadas: "+resul.getString("numCamasOcupadas"));
                System.out.println("Cantidad de personal: "+resul.getString("numPersonal"));
                System.out.println("Cantidad de personal cualificado: "+resul.getString("numPersonalCualificado"));
                System.out.println("Localización: "+resul.getString("localizacion"));
                System.out.println("Numero de habitaciones: "+resul.getString("numHabitaciones"));
                System.out.println("Numero de salas: "+resul.getString("numSalas")+"s\n");
            	System.out.println("-----------------------------------------------\n");
            }
            resul.close();
            sentencia.close();
            cerrarSesion();
    	}catch(SQLException e){
        	System.out.println("Error al mostrar las infraestructuras.");
        }
    }
    
    public void mostrarHoteles() throws SQLException{
    	Scanner keyboard = new Scanner(System.in);	
    	conectar();
    	try {
     		Statement sentencia = conexion.createStatement();
 	        ResultSet resul = sentencia.executeQuery("SELECT * FROM infraestructura WHERE numHabitaciones != 0");
            while(resul.next()){
            	System.out.println("-----------------------------------------------");
                System.out.println("ID: "+resul.getString("id"));
                System.out.println("Nombre: "+resul.getString("nombre"));
                System.out.println("Espacio disponible: "+resul.getString("espacioDisponible"));
                System.out.println("Numero de camas: "+resul.getString("numCamas"));
                System.out.println("Numero de camas ocupadas: "+resul.getString("numCamasOcupadas"));
                System.out.println("Cantidad de personal: "+resul.getString("numPersonal"));
                System.out.println("Cantidad de personal cualificado: "+resul.getString("numPersonalCualificado"));
                System.out.println("Localización: "+resul.getString("localizacion"));
                System.out.println("Numero de habitaciones: "+resul.getString("numHabitaciones")+"\n");
            	System.out.println("-----------------------------------------------\n");
            }
            resul.close();
            sentencia.close();
            cerrarSesion();
    	}catch(SQLException e){
        	System.out.println("Error al mostrar los hoteles.");
        }
    }
    
    public void mostrarLocales() throws SQLException{
    	Scanner keyboard = new Scanner(System.in);	
    	conectar();
    	try {
     		Statement sentencia = conexion.createStatement();
 	        ResultSet resul = sentencia.executeQuery("SELECT * FROM infraestructura WHERE numSalas != 0");
            while(resul.next()){
            	System.out.println("-----------------------------------------------");
                System.out.println("ID: "+resul.getString("id"));
                System.out.println("Nombre: "+resul.getString("nombre"));
                System.out.println("Espacio disponible: "+resul.getString("espacioDisponible"));
                System.out.println("Numero de camas: "+resul.getString("numCamas"));
                System.out.println("Numero de camas ocupadas: "+resul.getString("numCamasOcupadas"));
                System.out.println("Cantidad de personal: "+resul.getString("numPersonal"));
                System.out.println("Cantidad de personal cualificado: "+resul.getString("numPersonalCualificado"));
                System.out.println("Localización: "+resul.getString("localizacion"));
                System.out.println("Numero de salas: "+resul.getString("numSalas")+"\n");
            	System.out.println("-----------------------------------------------\n");
            }
            resul.close();
            sentencia.close();
            cerrarSesion();
    	}catch(SQLException e){
        	System.out.println("Error al mostrar los locales.");
        }
    }
    
    public void borrarInfraestructuras(int id) throws SQLException, InputMismatchException{
        try {
        	conectar();
            statement.executeUpdate("DELETE FROM infraestructura WHERE id = " + id + ";");
            System.out.println("-----------------------------------------------");
            System.out.println("Se ha borrado la infraestructura con el siguiente id: " + id);
            System.out.println("-----------------------------------------------\n"); 
            cerrarSesion();
        }catch(SQLException e){
        	System.out.println("Error al eliminar la infraestructura.");
            e.printStackTrace();
        }
    }
    
    
    //metodo de modificar los datos de ifraestructura	
  	public void modificarInfraestructura(int id) throws SQLException, InputMismatchException {
  		conectar();
  		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print(""
					+ "1. Nombre del Hotel/Local. \n" 
					+ "2. Espacio Disponible. \n"
					+ "3. Numero de camas. \n"
					+ "4. Numero de camas Ocupadas. \n"
					+ "5. Numero Personal Cualificado. \n"
					+ "6. Numero Personal. \n"
					+ "7. Localizacion. \n"
					+ "8. Numero de Habitaciones. \n"
					+ "9. Numero de Salas. \n"
					+ "10. Volver. \n\n"
					+ "Opción: ");
			String agregar = keyboard.next();
			
			switch (agregar) {
				case "1":
					System.out.print("Nombre del Hotel/Local: ");
					String nombre = keyboard.next();
					try {
		                String sqlString = "UPDATE infraestructura set nombre = '" + nombre + "' WHERE id = " + id + ";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "2":
					System.out.print("Espacio Disponible 0 = No / 1 = Si:  ");
					int espacioDisponible  = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE infraestructura set espacioDisponible= " + espacioDisponible + " WHERE id="+id+";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "3":
					System.out.print("Numero de camas: ");
					int numCamas = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE infraestructura set numCamas = " + numCamas + " WHERE id = " + id + ";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "4":
					System.out.print("Numero de camas Ocupadas: ");
					int numCamasOcupadas = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE infraestructura set numCamasOcupadas="+numCamasOcupadas+" WHERE id="+id+";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "5":
					System.out.print("Numero Personal Cualificado: ");
					int numPersonalCualificado = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE infraestructura set numPersonalCualificado='"+numPersonalCualificado+"' WHERE id="+id+";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		            }
					break;
				case "6":
					System.out.print("Numero Personal: ");
					int numPersonal = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE infraestructura set numPersonal="+numPersonal+" WHERE id="+id+";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		                }
					break;
				case "7":
					System.out.print("Localizacion: ");
					int localizacion = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE infraestructura set localizacion="+localizacion+" WHERE id="+id+";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		                }
					break;
				case "8":
					System.out.print("Numero de Habitaciones: ");
					int numHabitaciones = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE infraestructura set numHabitaciones="+numHabitaciones+" WHERE id="+id+";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		                }
					break;
				case "9":
					System.out.print("Numero de Salas ");
					int numSalas = keyboard.nextInt();
					try {
		                String sqlString = "UPDATE infraestructura set numSalas="+numSalas+" WHERE id="+id+";";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el anciano.");
		                }
					break;
				case "10":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce un  número del 1 al 5.");
					break;
			}
		}
  	}
    
    //-------------------------------------------------REGISTROS------------------------------------------------------------------------
	
  	//metodo de añadir un registro a la bd
    public void añadirRegistro(int idInfraestructura,String dniAnciano, String diaEntrada ,String diaSalida) throws SQLException, InputMismatchException {
      try {
          conectar();
          String sqlString = "INSERT INTO registro (idInfraestructura,dniAnciano,diaEntrada,diaSalida) VALUES("+ idInfraestructura + ",'" + dniAnciano + "','" + diaEntrada + "','" + diaSalida + "');";
          statement.executeUpdate(sqlString);
          cerrarSesion();
      } catch (SQLException e) {
          System.out.println("Ha habido un error en la inserciÃ³n de datos.");
          e.printStackTrace();
      }
    }
     
  	//metodo de consultar todos los registros en la bd
  	public void consultarRegistro() throws SQLException, InputMismatchException{
          try {
          	conectar();
              ResultSet resul = statement.executeQuery("SELECT * FROM registro");
              while(resul.next()){
                  System.out.println("-----------------------------------------------");
                  System.out.println("id: "+resul.getString("id"));
                  System.out.println("idInfraestructura: "+resul.getString("idInfraestructura"));
                  System.out.println("dniAnciano: "+resul.getString("dniAnciano"));
                  System.out.println("diaEntrada: "+resul.getString("diaEntrada"));
                  System.out.println("diaSalida: "+resul.getString("diaSalida"));
                  System.out.println("-----------------------------------------------\n");  
              }
             cerrarSesion();
          }catch(SQLException e){
          	System.out.println("Ha habido un error en la consulta.");
              e.printStackTrace();
          }
     }

  	//metodo de consultar un registros por su id
  	public void ConsultarPorId(int id) throws SQLException, InputMismatchException{
          try {
          	conectar();
              ResultSet resul = statement.executeQuery("SELECT * FROM registro WHERE id = " + id + ";");
              while(resul.next()){
                  System.out.println("-----------------------------------------------");
                  System.out.println("id: "+resul.getString("id"));
                  System.out.println("idInfraestructura: "+resul.getString("idInfraestructura"));
                  System.out.println("dniAnciano: "+resul.getString("dniAnciano"));
                  System.out.println("diaEntrada: "+resul.getString("diaEntrada"));
                  System.out.println("diaSalida: "+resul.getString("diaSalida"));
                  System.out.println("-----------------------------------------------\n");  
              }
              cerrarSesion();
          }catch(SQLException e){
          	System.out.println("Ha habido un error en la consulta.");
              e.printStackTrace();
          }
     }
  		
  	//metodo de consultar un registros por su id Infraestructura
  	public void ConsultarPoridInfraestructura(int idInfraestructura) throws SQLException, InputMismatchException{
          try {
          	conectar();
              ResultSet resul = statement.executeQuery("SELECT * FROM registro WHERE idInfraestructura = " + idInfraestructura + ";");
              while(resul.next()){
                  System.out.println("-----------------------------------------------");
                  System.out.println("id: "+resul.getString("id"));
                  System.out.println("idInfraestructura: "+resul.getString("idInfraestructura"));
                  System.out.println("dniAnciano: "+resul.getString("dniAnciano"));
                  System.out.println("diaEntrada: "+resul.getString("diaEntrada"));
                  System.out.println("diaSalida: "+resul.getString("diaSalida"));
                  System.out.println("-----------------------------------------------\n");  
              }
              cerrarSesion();
          }catch(SQLException e){
          	System.out.println("Ha habido un error en la consulta.");
              e.printStackTrace();
          }
     }

  	//metodo de consultar un registros por su id Anciano
  	public void ConsultarPordniAnciano(String dniAnciano) throws SQLException, InputMismatchException{
          try {
          	conectar();
              ResultSet resul = statement.executeQuery("SELECT * FROM registro WHERE dniAnciano = " + dniAnciano + ";");
              while(resul.next()){
                  System.out.println("-----------------------------------------------");
                  System.out.println("id: "+resul.getString("id"));
                  System.out.println("idInfraestructura: "+resul.getString("idInfraestructura"));
                  System.out.println("dniAnciano: "+resul.getString("dniAnciano"));
                  System.out.println("diaEntrada: "+resul.getString("diaEntrada"));
                  System.out.println("diaSalida: "+resul.getString("diaSalida"));
                  System.out.println("-----------------------------------------------\n");  
              }
              cerrarSesion();
          }catch(SQLException e){
          	System.out.println("Ha habido un error en la consulta.");
              e.printStackTrace();
          }
    }
    
  	//metodo para modificar los registros
  	public void modificarRegistro() throws SQLException, InputMismatchException {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Has elegido la opción Modificar Registro");
        int cambio = 0;
        String idReal = null;
        String nuevoDia = null;
        consultarRegistro();
        try {
        	conectar();
        	System.out.println("-----------------------------------------------");
            System.out.print("Introduzca el ID del registro que desea modificar: ");
            idReal = keyboard.nextLine();
            System.out.println("Que dato desea modificar del Registro? ");
            System.out.println("1. Día Entrada");
            System.out.println("2. Día Salida");
            System.out.println("-----------------------------------------------\n"); 
            cambio = keyboard.nextInt();
            System.out.println("Introduzca el nuevo día: ");
            nuevoDia = keyboard.next();

            if (cambio == 1) {
                try {
                String sqlString = "UPDATE registro SET diaEntrada = '" + nuevoDia + "' WHERE id =" + idReal + ";";
                statement.executeUpdate(sqlString);
                System.out.println("Se ha modificado el registro con el siguiente id: " + idReal);
                } catch (SQLException e) {
                    System.out.println("Se produjo un error al modificar el registro.");
                }
            }

            if (cambio == 2) {
                try {
                String sqlString = "UPDATE registro SET diaSalida= '" + nuevoDia + "' WHERE id =" + idReal + ";";
                statement.executeUpdate(sqlString);
                System.out.println("Se ha modificado el registro con el siguiente id: " + idReal);
                } catch (SQLException e) {
                    System.out.println("Se produjo un error al modificar el registro.");
                }
            }
            cerrarSesion();
        } catch (InputMismatchException e) {
            System.out.println("Los datos insertados no son correctos. Por favor, comprueba que el id y/o la fecha son datos validos.");
        }
    }
  	
    
  	//metodo de borrar un registro por id 
  	public void borrarRegistro(int id) throws SQLException, InputMismatchException{
        try {
        	conectar();
            statement.executeUpdate("DELETE FROM registro WHERE id = " + id + ";");
            System.out.println("-----------------------------------------------");
            System.out.println("Se ha borrado el registro con el siguiente id: " + id);
            System.out.println("-----------------------------------------------\n"); 
            cerrarSesion();
        }catch(SQLException e){
        	System.out.println("Error al eliminar el registro.");
            e.printStackTrace();
        }
  	}
  	
  	//----------------------------------------------------Usuarios-----------------------------------------------------------------------
  	
  	//Comprobamos el usuario que accede al menú
  	public boolean comprobarAcceso (String tipoUsuario) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ancianos", "root","");
            Statement sentencia = conexion.createStatement();
            try {
                System.out.print("Nombre de usuario: ");
                String nombre = keyboard.nextLine();
                System.out.print("Contraseña: ");
                String pwd = keyboard.nextLine();

                ResultSet resulN = sentencia.executeQuery("SELECT * FROM usuario where nombre = '" + nombre + "' AND contraseña = '" + pwd + "';");


	            while(resulN.next()){
	                String tipo = resulN.getString("tipoUsuario");
	                if(tipo.equals(tipoUsuario)) {
	                	System.out.println("\nBienvenido "+nombre);
	                	return true;
	                }
	            }
	            resulN.close();
            }catch(InputMismatchException e){
                System.out.print("");
            }catch(SQLException e){
                System.out.println("Error al buscar les dades del usuari");
            }
        }catch(ClassNotFoundException cn){
            System.out.println("Error de conexión");
        }catch(SQLException e){
            System.out.println("Error de conexión");
        }
        return false;
    }
  	
  	//metodo de añadir un usuario a la bd
    public void añadirUsuario(String dni, String nombre, String apellido, String tipoUsuario, String contraseña) throws SQLException, InputMismatchException {
      try {
          conectar();
          String sqlString = "INSERT INTO usuario VALUES('"+ dni + "','" + nombre + "','" + apellido + "','" + tipoUsuario + "','"+ contraseña+"');";
          statement.executeUpdate(sqlString);
          cerrarSesion();
      } catch (SQLException e) {
          System.out.println("Ha habido un error en la inserción de datos.");
          e.printStackTrace();
      }
    }
  	
    //Metodo para mostrar TODOS los usuarios
    public void mostrarUsuarios() throws SQLException{
    	Scanner keyboard = new Scanner(System.in);	
    	conectar();
    	try {
     		Statement sentencia = conexion.createStatement();
 	        ResultSet resul = sentencia.executeQuery("SELECT * FROM usuario");
            while(resul.next()){
            	System.out.println("-----------------------------------------------\n");
                System.out.println("DNI: "+resul.getString("dni"));
                System.out.println("Nombre: "+resul.getString("nombre"));
                System.out.println("Apellido: "+resul.getString("apellido"));
                System.out.println("Tipo de usuario: "+resul.getString("tipoUsuario"));
                System.out.println("Contraseña: "+resul.getString("contraseña")+"\n");
                System.out.println("-----------------------------------------------\n");
            }
            resul.close();
            sentencia.close();
            cerrarSesion();
    	}catch(SQLException e){
        	System.out.println("Error al mostrar los usuarios.");
        }
    }
    
    //metodo de consultar un usuario por su dni
  	public void mostrarUsuarioDni(String dni) throws SQLException, InputMismatchException{
      try {
		  conectar();
	      ResultSet resul = statement.executeQuery("SELECT * FROM usuario WHERE dni = '" + dni + "';");
	      while(resul.next()){
	    	  System.out.println("\n-----------------------------------------------");
	          System.out.println("DNI: "+resul.getString("dni"));
	          System.out.println("Nombre: "+resul.getString("nombre"));
	          System.out.println("Apellido: "+resul.getString("apellido"));
	          System.out.println("Tipo de usuario: "+resul.getString("tipoUsuario"));
	          System.out.println("Contraseña: "+resul.getString("contraseña")+"\n");
	          System.out.println("-----------------------------------------------");  
	      }
	      cerrarSesion();
	  }catch(SQLException e){
		  System.out.println("Ha habido un error en la consulta.");
	      e.printStackTrace();
	  }
  	}
    
  	public void borrarUsuario(String dni) throws SQLException, InputMismatchException{
        try {
            conectar();
            statement.executeUpdate("DELETE FROM usuario WHERE dni = '" + dni + "';");
            System.out.println("-----------------------------------------------");
            System.out.println("Se ha borrado el usuario con el siguiente dni: " + dni);
            System.out.println("-----------------------------------------------\n"); 
            cerrarSesion();
        }catch(SQLException e){
            System.out.println("Error al eliminar el usuario.");
            e.printStackTrace();
        }
  	}
  	
  	public void modificarUsuario(String dni) throws SQLException, InputMismatchException {
  		conectar();
  		Scanner keyboard = new Scanner(System.in);
		boolean salir = false;
		
		while (!salir) {
			System.out.print("1. Modificar nombre. \n" 
					+ "2. Modificar apellido. \n"
					+ "3. Modificar tipo de usuario. \n"
					+ "4. Modificar contraseña. \n"
					+ "5. Volver.  \n\n"
					+ "Opción: ");
			String agregar = keyboard.next();
			
			switch (agregar) {
				case "1":
					System.out.print("Nuevo nombre: ");
					String nombre = keyboard.next();
					try {
		                String sqlString = "UPDATE usuario SET nombre= '" + nombre + "' WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el usuario.");
		            }
					break;
				case "2":
					System.out.print("Nuevo apellido: ");
					String apellido = keyboard.next();
					try {
		                String sqlString = "UPDATE usuario SET apellido= '" + apellido + "' WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el usuario.");
		            }
					break;
				case "3":
					System.out.print("Nuevo tipo de usuario (empleado, gestor, coordinador): ");
					String tipo = keyboard.next();
					try {
		                String sqlString = "UPDATE usuario SET tipoUsuario= '" + tipo + "' WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el usuario.");
		            }
					break;
				case "4":
					System.out.print("Nueva contraseña: ");
					String pwd = keyboard.next();
					try {
		                String sqlString = "UPDATE usuario SET contraseña= '" + pwd + "' WHERE dni='" + dni + "';";
		                statement.executeUpdate(sqlString);
		                } catch (SQLException e) {
		                	System.out.println("Se produjo un error al modificar el usuario.");
		            }
					break;
				case "5":
					salir = true;
					break;
				default:
					System.out.println("Por favor, introduce un  número del 1 al 5.");
					break;
			}
		}
  	}
  	
  	
  	//-----------------------------------------SUGERENCIA----------------------------------------------------------------------
  	
  	//metodo añadir una sugerencia a la db
    public void añadirSugerencia(int idInfraestructura,String dniAnciano, String diaEntrada ,String diaSalida) throws SQLException, InputMismatchException {
        try {
            conectar();
            String sqlString = "INSERT INTO sugerencia (idInfraestructura,dniAnciano,diaEntrada,diaSalida) VALUES("+ idInfraestructura + ",'" + dniAnciano + "','" + diaEntrada + "','" + diaSalida + "');";
            statement.executeUpdate(sqlString);
            cerrarSesion();
        } catch (SQLException e) {
            System.out.println("Ha habido un error en la inserción de datos.");
            e.printStackTrace();
        }
    }
  	
    //metodo de consultar todos los registros en la bd
  	public void consultarSugerencia() throws SQLException, InputMismatchException{
          try {
          	conectar();
              ResultSet resul = statement.executeQuery("SELECT * FROM sugerencia");
              while(resul.next()){
                  System.out.println("-----------------------------------------------");
                  System.out.println("id: "+resul.getString("id"));
                  System.out.println("idInfraestructura: "+resul.getString("idInfraestructura"));
                  System.out.println("dniAnciano: "+resul.getString("dniAnciano"));
                  System.out.println("diaEntrada: "+resul.getString("diaEntrada"));
                  System.out.println("diaSalida: "+resul.getString("diaSalida"));
                  System.out.println("-----------------------------------------------\n");  
              }
             cerrarSesion();
          }catch(SQLException e){
          	System.out.println("Ha habido un error en la consulta.");
              e.printStackTrace();
          }
     }

  	//metodo de consultar un registros por su id
  	public void ConsultarPorIdSugerencia(int id) throws SQLException, InputMismatchException{
          try {
          	conectar();
              ResultSet resul = statement.executeQuery("SELECT * FROM sugerencia WHERE id = " + id + ";");
              while(resul.next()){
                  System.out.println("-----------------------------------------------");
                  System.out.println("id: "+resul.getString("id"));
                  System.out.println("idInfraestructura: "+resul.getString("idInfraestructura"));
                  System.out.println("dniAnciano: "+resul.getString("dniAnciano"));
                  System.out.println("diaEntrada: "+resul.getString("diaEntrada"));
                  System.out.println("diaSalida: "+resul.getString("diaSalida"));
                  System.out.println("-----------------------------------------------\n");  
              }
              cerrarSesion();
          }catch(SQLException e){
          	System.out.println("Ha habido un error en la consulta.");
              e.printStackTrace();
          }
     }
  		
  	//metodo de consultar un registros por su id Infraestructura
  	public void ConsultarPoridInfraSugerencia(int idInfraestructura) throws SQLException, InputMismatchException{
          try {
          	conectar();
              ResultSet resul = statement.executeQuery("SELECT * FROM sugerencia WHERE idInfraestructura = " + idInfraestructura + ";");
              while(resul.next()){
                  System.out.println("-----------------------------------------------");
                  System.out.println("id: "+resul.getString("id"));
                  System.out.println("idInfraestructura: "+resul.getString("idInfraestructura"));
                  System.out.println("dniAnciano: "+resul.getString("dniAnciano"));
                  System.out.println("diaEntrada: "+resul.getString("diaEntrada"));
                  System.out.println("diaSalida: "+resul.getString("diaSalida"));
                  System.out.println("-----------------------------------------------\n");  
              }
              cerrarSesion();
          }catch(SQLException e){
          	System.out.println("Ha habido un error en la consulta.");
              e.printStackTrace();
          }
     }

  	//metodo de consultar un registros por su id Anciano
  	public void ConsultarPordniSugerencia(String dniAnciano) throws SQLException, InputMismatchException{
      try {
      	conectar();
          ResultSet resul = statement.executeQuery("SELECT * FROM sugerencia WHERE dniAnciano = '" + dniAnciano + "';");
          while(resul.next()){
              System.out.println("-----------------------------------------------");
              System.out.println("id: "+resul.getString("id"));
              System.out.println("idInfraestructura: "+resul.getString("idInfraestructura"));
              System.out.println("dniAnciano: "+resul.getString("dniAnciano"));
              System.out.println("diaEntrada: "+resul.getString("diaEntrada"));
              System.out.println("diaSalida: "+resul.getString("diaSalida"));
              System.out.println("-----------------------------------------------\n");  
          }
          cerrarSesion();
      }catch(SQLException e){
      	System.out.println("Ha habido un error en la consulta.");
          e.printStackTrace();
      }
    }
    
  	//metodo de borrar unn registro por id 
  	public void borrarSugerencia(int id) throws SQLException, InputMismatchException{
        try {
        	conectar();
            statement.executeUpdate("DELETE FROM sugerencia WHERE id = " + id + ";");
            System.out.println("-----------------------------------------------");
            System.out.println("Se ha borrado la sugerencia con el id: " + id);
            System.out.println("-----------------------------------------------\n"); 
            cerrarSesion();
        }catch(SQLException e){
        	System.out.println("Error al eliminar el registro.");
            e.printStackTrace();
        }
  	}
  	
  	public void aprobarSugerencia(int id) throws SQLException, InputMismatchException{
        int idS = 0;
        int idInfra = 0;
        String dni = null;
        String diaEntrada = null;
        String diaSalida = null;
  		try {
        	conectar();
        	ResultSet resul = statement.executeQuery("SELECT * FROM sugerencia WHERE id = " + id +";");
            while(resul.next()){
                idS = resul.getInt("id");
                idInfra = resul.getInt("idInfraestructura");
                dni = resul.getString("dniAnciano");
                diaEntrada = resul.getString("diaEntrada");
                diaSalida = resul.getString("diaSalida");
            }
            String sqlString = "INSERT INTO registro (idInfraestructura,dniAnciano,diaEntrada,diaSalida) VALUES("+ idInfra + ",'" + dni + "','" + diaEntrada + "','" + diaSalida + "');";
            statement.executeUpdate(sqlString);
            statement.executeUpdate("DELETE FROM sugerencia WHERE id = " + id + ";");
            cerrarSesion();
        }catch(SQLException e){
        	System.out.println("Error al eliminar el registro.");
            e.printStackTrace();
        }
  		System.out.println("Aprobada la sugerencia con ID "+id);
  	}


  	public void modificarSugerencia(int idReal, String cambio, String nuevoDia) throws SQLException, InputMismatchException {
  		try {
  			conectar();
            if (cambio.equals("1")) {
                try {
                String sqlString = "UPDATE sugerencia SET diaEntrada = '" + nuevoDia + "' WHERE id =" + idReal + ";";
                statement.executeUpdate(sqlString);
                System.out.println("Se ha modificado el registro con el siguiente id: " + idReal);
                } catch (SQLException e) {
                    System.out.println("Se produjo un error al modificar el registro.");
                }
            }

            if (cambio.equals("2")) {
                try {
                String sqlString = "UPDATE sugerencia SET diaSalida= '" + nuevoDia + "' WHERE id =" + idReal + ";";
                statement.executeUpdate(sqlString);
                System.out.println("Se ha modificado el registro con el siguiente id: " + idReal);
                } catch (SQLException e) {
                    System.out.println("Se produjo un error al modificar el registro.");
                }
            }
            cerrarSesion();
        } catch (InputMismatchException e) {
            System.out.println("Los datos insertados no son correctos. Por favor, comprueba que el id y/o la fecha son datos validos.");
        }
    }

}
