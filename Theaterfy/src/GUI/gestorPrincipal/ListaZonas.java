package GUI.gestorPrincipal;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * subpanel con el titulo "Lista de zonas creadas"
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ListaZonas extends JPanel{
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ListaZonas() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(340, 600));
		
		JLabel tit=new JLabel("Lista de zonas creadas");
		tit.setFont(new Font("Verdana", Font.BOLD, 14));
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, tit, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, tit, 50, SpringLayout.NORTH, this);
		
		this.add(tit);
	}

}
