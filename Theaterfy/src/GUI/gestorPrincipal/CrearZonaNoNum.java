package GUI.gestorPrincipal;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * subpanel para crear una zona no numerada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class CrearZonaNoNum extends JPanel{
	private SpringLayout layout= new SpringLayout();
	private JLabel crear;
	private JLabel numButacas;
	private JLabel precioZona;
	private JTextField butacas;
	private JTextField precio;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public CrearZonaNoNum() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(320, 200));
		
		crear=new JLabel("Nueva zona no numerada");
		numButacas=new JLabel("Número de butacas");
		precioZona=new JLabel("Precio de la zona");
		butacas = new JTextField(3);
		precio = new JTextField(3);
		
		crear.setFont(new Font("Verdana", Font.BOLD, 14));
		
		layout.putConstraint(SpringLayout.WEST, crear, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, crear, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, numButacas, 0, SpringLayout.WEST, crear);
		layout.putConstraint(SpringLayout.NORTH, numButacas, 20, SpringLayout.SOUTH, crear);
		layout.putConstraint(SpringLayout.WEST, butacas, 60, SpringLayout.EAST, numButacas);
		layout.putConstraint(SpringLayout.NORTH, butacas, 0, SpringLayout.NORTH, numButacas);
		layout.putConstraint(SpringLayout.WEST, precioZona, 0, SpringLayout.WEST, numButacas);
		layout.putConstraint(SpringLayout.NORTH, precioZona, 10, SpringLayout.SOUTH, numButacas);
		layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, butacas);
		layout.putConstraint(SpringLayout.NORTH, precio, 0, SpringLayout.NORTH, precioZona);
		
		this.add(crear);
		this.add(numButacas);
		this.add(butacas);
		this.add(precioZona);
		this.add(precio);
	}
	public int getNumButacas() {
		if(this.butacas.getText().toString().equals(""))
			return -1;
		return Integer.parseInt(this.butacas.getText().toString());
	}
	
	public double getPrecioZona() {
		if(this.precio.getText().toString().equals(""))
			return -1;
		return Double.parseDouble(this.precio.getText().toString());
	}
}
