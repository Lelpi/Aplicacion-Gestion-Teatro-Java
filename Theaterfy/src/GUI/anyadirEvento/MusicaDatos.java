package GUI.anyadirEvento;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * panel para introducir datos sobre un evento de música
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class MusicaDatos extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel orquesta;
	private JLabel solista;
	private JLabel programa;
	private JTextField nombreOrquesta;
	private JTextField nombreSolista;
	private JTextArea programaPiezas;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public MusicaDatos() {
		
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(670, 350));
		
		orquesta=new JLabel("Nombre de la orquesta");
		nombreOrquesta = new JTextField(25);
		solista=new JLabel("Interprete solista");
		nombreSolista = new JTextField(25);
		programa=new JLabel("Programa con las piezas");
		programaPiezas = new JTextArea(8, 25);
		
		layout.putConstraint(SpringLayout.WEST, orquesta, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, orquesta, 65, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, nombreOrquesta, 20, SpringLayout.EAST, orquesta);
		layout.putConstraint(SpringLayout.NORTH, nombreOrquesta, 0, SpringLayout.NORTH, orquesta);
		
		layout.putConstraint(SpringLayout.WEST, solista, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, solista, 22, SpringLayout.SOUTH, orquesta);
		layout.putConstraint(SpringLayout.WEST, nombreSolista, 0, SpringLayout.WEST, nombreOrquesta);
		layout.putConstraint(SpringLayout.NORTH, nombreSolista, 20, SpringLayout.SOUTH, nombreOrquesta);
		
		layout.putConstraint(SpringLayout.WEST, programa, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, programa, 70, SpringLayout.SOUTH, solista);
		layout.putConstraint(SpringLayout.WEST, programaPiezas, 0, SpringLayout.WEST, nombreOrquesta);
		layout.putConstraint(SpringLayout.NORTH, programaPiezas, 20, SpringLayout.SOUTH, nombreSolista);
		
		this.add(orquesta);
		this.add(nombreOrquesta);
		this.add(solista);
		this.add(nombreSolista);
		this.add(programa);
		this.add(programaPiezas);	
	}
	
	public String getOrquesta() {
		return this.nombreOrquesta.getText();
	}
	
	public String getSolista() {
		return this.nombreSolista.getText();
	}
	public String getPiezas() {
		return this.programaPiezas.getText();
	}
}