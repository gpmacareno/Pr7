package es.studium.Ejemplos;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo2 implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Empleados");
	Label lblNombre = new Label("idEmpleado");
	TextField txtNombre = new TextField(17);
	Label lblNombre2 = new Label("Nombre");
	TextField txtNombre2 = new TextField(20);
	Label lblNombre3 = new Label("Salario");
	TextField txtNombre3 = new TextField(21);
	Button anterior = new Button("Anterior");
	Button siguiente = new Button("Siguiente");

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresa";
	String login = "root";
	String password = "Studium2022;";
	String sentencia = "SELECT * FROM empleados";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	Ejemplo2()
	{
		ventana.setBackground(Color.magenta);
		ventana.setSize(300, 200);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		anterior.addActionListener(this);
		siguiente.addActionListener(this);

		txtNombre.setEditable(false);
		txtNombre2.setEditable(false);
		txtNombre3.setEditable(false);

		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblNombre2);
		ventana.add(txtNombre2);
		ventana.add(lblNombre3);
		ventana.add(txtNombre3);
		ventana.add(anterior);
		ventana.add(siguiente);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia); // Execute query para los select, execute update para los demas.
			rs.next();

			txtNombre.setText(rs.getString("idEmpleado"));
			txtNombre2.setText(rs.getString("nombreEmpleado"));
			txtNombre3.setText(rs.getString("salarioEmpleado") + " €");

		}

		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-" + cnfe.getMessage());
		} catch (SQLException sqle)
		{
			System.out.println("Error 2-" + sqle.getMessage());
		}

		ventana.setVisible(true);
	}

	public static void main(String[] args)
	{
		new Ejemplo2();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource().equals(siguiente))
		{
			try
			{
				if (!rs.isLast())
				{

					rs.next();
					txtNombre.setText(rs.getString("idEmpleado"));
					txtNombre2.setText(rs.getString("nombreEmpleado"));
					txtNombre3.setText(rs.getString("salarioEmpleado") + " €");
				}
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-" + sqle.getMessage());
			}
		}

		else if (e.getSource().equals(anterior))
		{

			try
			{

				if (!rs.isFirst())
				{

					rs.previous();
					txtNombre.setText(rs.getString("idEmpleado"));
					txtNombre2.setText(rs.getString("nombreEmpleado"));
					txtNombre3.setText(rs.getString("salarioEmpleado") + " €");

				}
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-" + sqle.getMessage());
			}

		}

	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent ee)
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

		System.out.println("Closing");
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

}
