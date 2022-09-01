package GUI.anyadirEvento;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 * panel para introducir datos sobre un evento de teatro
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class TeatroDatos extends JPanel{
	SpringLayout layout = new SpringLayout();
	JLabel elenco;
	JTextArea textElenco;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public TeatroDatos() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(670, 350));
		
		elenco=new JLabel("Elenco de la obra");
		textElenco = new JTextArea(10, 30);
		
		layout.putConstraint(SpringLayout.WEST, elenco, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, elenco, 118, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, textElenco, 20, SpringLayout.EAST, elenco);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, textElenco, 0, SpringLayout.SOUTH, elenco);
		
		this.add(elenco);
		this.add(textElenco);
	}
	public String getElenco() {
		return this.elenco.getText();
	}
}
