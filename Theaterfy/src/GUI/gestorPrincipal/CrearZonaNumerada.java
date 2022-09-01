package GUI.gestorPrincipal;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * subpanel para crear una zona numerada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class CrearZonaNumerada extends JPanel{
	SpringLayout layout = new SpringLayout();
	private JLabel crear;
	private JLabel numFilas;
	private JTextField filas;
	private JLabel numColumnas;
	private JTextField columnas;
	private JLabel precioZona;
	private JTextField precio;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public CrearZonaNumerada() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(320, 200));
		
		crear=new JLabel("Nueva zona numerada");
		numFilas=new JLabel("Número de filas");
		filas = new JTextField(3);
		numColumnas=new JLabel("Número de columnas");
		columnas = new JTextField(3);
		precioZona=new JLabel("Precio de la zona");
		precio = new JTextField(3);
		
		crear.setFont(new Font("Verdana", Font.BOLD, 14));
		layout.putConstraint(SpringLayout.WEST, crear, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, crear, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, numFilas, 0, SpringLayout.WEST, crear);
		layout.putConstraint(SpringLayout.NORTH, numFilas, 20, SpringLayout.SOUTH, crear);
		layout.putConstraint(SpringLayout.WEST, filas, 60, SpringLayout.EAST, numFilas);
		layout.putConstraint(SpringLayout.NORTH, filas, 0, SpringLayout.NORTH, numFilas);
		layout.putConstraint(SpringLayout.WEST, numColumnas, 0, SpringLayout.WEST, numFilas);
		layout.putConstraint(SpringLayout.NORTH, numColumnas, 10, SpringLayout.SOUTH, numFilas);
		layout.putConstraint(SpringLayout.WEST, columnas, 0, SpringLayout.WEST, filas);
		layout.putConstraint(SpringLayout.NORTH, columnas, 0, SpringLayout.NORTH, numColumnas);
		layout.putConstraint(SpringLayout.WEST, precioZona, 0, SpringLayout.WEST, numColumnas);
		layout.putConstraint(SpringLayout.NORTH, precioZona, 10, SpringLayout.SOUTH, numColumnas);
		layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, filas);
		layout.putConstraint(SpringLayout.NORTH, precio, 0, SpringLayout.NORTH, precioZona);
		
		this.add(crear);
		this.add(numFilas);
		this.add(numColumnas);
		this.add(filas);
		this.add(columnas);
		this.add(precioZona);
		this.add(precio);	
	}
	
	public int getFilas() {
		if(this.filas.getText().toString().equals(""))
			return -1;
		return Integer.parseInt(this.filas.getText().toString());
	}
	
	public int getColumnas() {
		if(this.columnas.getText().toString().equals(""))
			return -1;
		return Integer.parseInt(this.columnas.getText().toString());
	}
	
	public float getPrecioZona() {
		if(this.precio.getText().toString().equals(""))
			return -1;
		return Float.parseFloat(this.precio.getText().toString());
	}
}
