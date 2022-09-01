package GUI.gestorPrincipal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * JPanel para configurar zonas del teatro
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */

public class ConfiguraZonas extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel encabezado;
	private CrearZonas crear;
	private Subzonas subzonas;
	private JButton inicio;

	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ConfiguraZonas() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		encabezado=new JLabel("Configurar zonas");
		crear = new CrearZonas();
		subzonas = new Subzonas();
		inicio=new JButton("Inicio");

		encabezado.setFont(new Font("Verdana", Font.BOLD, 24));
		layout.putConstraint(SpringLayout.WEST, encabezado, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, encabezado, 15, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, crear, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, crear, 40, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, subzonas, 0, SpringLayout.EAST, crear);
		layout.putConstraint(SpringLayout.SOUTH, subzonas, 40, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, inicio, 850, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, inicio, 50, SpringLayout.NORTH, this);
		
		this.add(encabezado);
		this.add(crear);
		this.add(subzonas);
		this.add(inicio);
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		crear.setControladores(c);
		this.subzonas.setControladores(c);
		this.inicio.addActionListener(c);
	}
	
	public CrearZonas getCrear() {
		return this.crear;
	}
	
	public Subzonas getSubzonas() {
		return this.subzonas;
	}
}
