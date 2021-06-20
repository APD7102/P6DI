package Tiendecita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * Clase para la conexion con la base de datos
 * 
 * 
  * @author polib
  * @since 10/06/2021
  * @version 1.0
 * 
 * 
 */
public class BDCon {


	String BaseDatos="tiendecita";
	String login="root";
	String password="Studium2019;";
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/"+BaseDatos+"?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

	Connection connection = null;
	Statement statement = null;
	Statement statement1 = null;
	Statement statement2 = null;
	ResultSet rs = null;

	public Connection conectar()
	{
		try
		{
			//Cargar los controladores 
			Class.forName(driver);
			//Establecer la conexión con la BD
			connection = DriverManager.getConnection(url, login, password);
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return connection;
	}
	public void desconectar(Connection c)
	{
		try
		{
			if(c!=null)
			{
				c.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error 3-"+e.getMessage());
		}
	}
	public int AltaArticulos(Connection c, String sentencia)
	{
		int resultado = 1;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error en el alta de articulos-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int BajaArticulos(Connection c, int idArticulos)
	{
		int resultado = 1;
		try
		{
			String sentencia1 = "DELETE FROM tickets WHERE idArticulosFK1 = "+ idArticulos;
			String sentencia2 = "DELETE FROM articulos WHERE idArticulos = "+ idArticulos;
			//Crear una sentencia
			statement1 = c.createStatement();
			statement2 = c.createStatement();
			// Ejecutar la sentencia SQL
			if((statement1.executeUpdate(sentencia1))==1)
			{
				if((statement2.executeUpdate(sentencia2))==1) 
				{
					resultado = 0;
				}
			}
			else
			{
				if((statement2.executeUpdate(sentencia2))==1) 
				{
					resultado = 0;
				}
				else
				{
					resultado = 1;
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error en la eliminacion de articulos-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarArticulo(Connection c, int idArticulos)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM articulos WHERE idArticulos="+idArticulos;
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			resultado = resultado + rs.getInt("idArticulos") + "#" +
					rs.getString("DescripcionArticulos") + "#" +
					rs.getString("CantidadStock")+ "#" +
					rs.getString("PrecioArticulos")+ "#";

		}
		catch (SQLException sqle)
		{

		}
		return (resultado);
	}
	public String consultarArticulosChoice(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM articulos";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL

			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idArticulos") + "-" +
						rs.getString("DescripcionArticulos") + "-" +
						rs.getString("CantidadStock")+ "-" +
						rs.getString("PrecioArticulos")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error en consultar los articulos-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int ModificacionArticulos(Connection c, String sentencia)
	{
		int resultado = 1;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			// Ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error en la modificación de articulos-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int AltaTickets(Connection c, String sentencia)
	{
		int resultado = 1;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error en el alta de ticket-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String ConsultarTicketsChoice(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM tickets";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idTickets") + "-" +
						rs.getString("FechaTicket") + "-" +
						rs.getString("ArticulosTickets")+ "-" +
						rs.getString("TotalTicket")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error en consultar los tickets-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarTickets(Connection c, int idTickets)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM tickets WHERE idTickets="+idTickets;
					//Crear una sentencia
					statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido yejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			resultado = resultado + rs.getInt("idTickets") + "#" +
					rs.getString("FechaTicket") + "#" +
					rs.getString("ArticulosTickets")+ "#" +
					rs.getString("TotalTicket")+ "#";
		}
		catch (SQLException sqle)
		{
		}
		return (resultado);
	}
}



