package GUI.comprar;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * panel que muestra el número de butacas en el caso de que seleccione una zona no numerada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ComprarNoNum extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JComboBox<Integer> comboEntradas;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ComprarNoNum() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 400));
		comboEntradas = new JComboBox<Integer>();
		this.add(comboEntradas);
	}
	
	public JComboBox<Integer> getComboEntradas() {
		return this.comboEntradas;
	}
	
	public int getNumEntradas() {
		return this.comboEntradas.getSelectedIndex()+1;
	}
}
