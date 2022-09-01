package GUI.comprar;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * panel para que el usuario introduzca sus datos bancarios cuando vaya a comprar una entrada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class DatosBancarios extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel banco=new JLabel("4. Introduce tus datos bancarios");
	private JLabel numTarjeta = new JLabel("Numero de tarjeta");
	private JTextField tarjeta=new JTextField(15);
	private JButton comprar=new JButton("Confirmar compra");

	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public DatosBancarios() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 300));
		
		layout.putConstraint(SpringLayout.WEST, banco, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, banco, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, numTarjeta, 45, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, numTarjeta, 10, SpringLayout.SOUTH, banco);
		layout.putConstraint(SpringLayout.WEST, tarjeta, 8, SpringLayout.EAST, numTarjeta);
		layout.putConstraint(SpringLayout.NORTH, tarjeta, 10, SpringLayout.SOUTH, banco);

		layout.putConstraint(SpringLayout.EAST, comprar, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, comprar, -150, SpringLayout.SOUTH, this);
		
		this.add(banco);
		this.add(tarjeta);
		this.add(numTarjeta);
		this.add(comprar);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.comprar.addActionListener(c);
	}
	
	public String getTarjeta() {
		return this.tarjeta.getText();
	}
	
}
