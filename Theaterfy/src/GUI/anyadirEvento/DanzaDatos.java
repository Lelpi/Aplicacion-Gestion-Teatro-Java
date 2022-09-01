package GUI.anyadirEvento;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * panel para introducir datos de danza en anyadirEvento
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class DanzaDatos extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel orquesta;
	private JLabel director;
	private JLabel bailarines;
	private JTextField nombreDirector;
	private JTextField nombreOrquesta;
	JTextArea areaBailarines;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public DanzaDatos() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(670, 350));
		
		orquesta=new JLabel("Nombre de la orquesta");
		director=new JLabel("Director de la orquesta");
		nombreDirector = new JTextField(25);
		nombreOrquesta = new JTextField(25);
		bailarines=new JLabel("Bailarines");
		areaBailarines = new JTextArea(8, 25);
		
		layout.putConstraint(SpringLayout.WEST, orquesta, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, orquesta, 65, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, nombreOrquesta, 20, SpringLayout.EAST, orquesta);
		layout.putConstraint(SpringLayout.NORTH, nombreOrquesta, 0, SpringLayout.NORTH, orquesta);
		
		layout.putConstraint(SpringLayout.WEST, director, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, director, 22, SpringLayout.SOUTH, orquesta);
		layout.putConstraint(SpringLayout.WEST, nombreDirector, 0, SpringLayout.WEST, nombreOrquesta);
		layout.putConstraint(SpringLayout.NORTH, nombreDirector, 20, SpringLayout.SOUTH, nombreOrquesta);
		
		layout.putConstraint(SpringLayout.WEST, bailarines, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, bailarines, 70, SpringLayout.SOUTH, director);
		layout.putConstraint(SpringLayout.WEST, areaBailarines, 0, SpringLayout.WEST, nombreOrquesta);
		layout.putConstraint(SpringLayout.NORTH, areaBailarines, 20, SpringLayout.SOUTH, nombreDirector);
		
		this.add(orquesta);
		this.add(nombreOrquesta);
		this.add(director);
		this.add(nombreDirector);
		this.add(bailarines);
		this.add(areaBailarines);
		
	}
	
	public String getDirector() {
		return this.nombreDirector.getText();
	}
	
	public String getOrquesta() {
		return this.nombreOrquesta.getText();
	}
	
	public String getBailarines() {
		return this.areaBailarines.getText();
	}

}
