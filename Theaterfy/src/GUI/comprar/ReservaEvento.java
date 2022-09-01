package GUI.comprar;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * panel para que el usuario reserve una entrada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ReservaEvento extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JButton reservar;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ReservaEvento() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 300));
		
		reservar =new JButton("Realizar reserva");
		layout.putConstraint(SpringLayout.EAST, reservar, -250, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, reservar, 0, SpringLayout.NORTH, this);
		
		this.add(reservar);
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.reservar.addActionListener(c);
	}
}
