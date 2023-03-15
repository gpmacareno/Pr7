package es.studium.Ejemplos;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;

import java.awt.TextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo3 implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Empleados");
	TextArea areaTexto = new TextArea();
	Button anterior = new Button("Volver");

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresa";
	String login = "root";
	String password = "Studium2022;";
	String sentencia = "SELECT * FROM empleados";
	Label lblid = new Label("ID");
	Label lblNombre = new Label("Nombre");
	Label lblSalario = new Label("Salario");

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	Ejemplo3()
	{
		ventana.setBackground(Color.pink);
		ventana.setSize(350, 230);
		ventana.setLayout(null);
		ventana.addWindowListener(this);
		anterior.addActionListener(this);
		ventana.add(lblid);
		lblid.setBounds(30,50,20,20);
		ventana.add(lblNombre);
		lblNombre.setBounds(60,50,80,20);
		ventana.add(lblSalario);
		lblSalario.setBounds(177,50,50,20);
		ventana.add(areaTexto);
		areaTexto.setBounds(30,70,300,100);
		areaTexto.setEditable(false);
		ventana.add(anterior);
		anterior.setBounds(150,180,50,40);
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
			while (rs.next())
			{
				areaTexto.setText(areaTexto.getText() + rs.getString("idEmpleado")+"-");
				areaTexto.setText(areaTexto.getText() + " " + rs.getString("nombreEmpleado")+"\t");
				areaTexto.setText(areaTexto.getText() + " " + rs.getString("salarioEmpleado")+ "€" + "\n");
			}

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
		new Ejemplo3();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		/*
		 * if (e.getSource().equals(anterior)) {
		 * 
		 * try {
		 * 
		 * if (!rs.isFirst()) {
		 * 
		 * rs.previous();
		 * 
		 * } }
		 * 
		 * catch (SQLException sqle) { System.out.println("Error 2-" +
		 * sqle.getMessage()); }
		 * 
		 * }
		 */
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
