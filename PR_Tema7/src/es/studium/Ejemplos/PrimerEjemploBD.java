package es.studium.Ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrimerEjemploBD
{

	public static void main(String[] args)
	{
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/empresa";
		String login = "root";
		String password = "Studium2022;";
		String sentencia = "SELECT * FROM empleados";

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia); // Execute query para los select, execute update para los demas.
			while (rs.next())
			{
				// System.out.println(rs.getInt("idDepartamento") + "-" +
				// rs.getString("nombreDepartamento"));
				System.out.println(rs.getString("nombreEmpleado") + "-" + rs.getInt("salarioEmpleado")+" euros");
			}
		}

		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-" + cnfe.getMessage());
		} catch (SQLException sqle)
		{
			System.out.println("Error 2-" + sqle.getMessage());
		} finally
		{
			try
			{
				if (connection != null)
				{
					connection.close();
				}
			} catch (SQLException e)
			{
				System.out.println("Error 3-" + e.getMessage());
			}
		}

	}

}
