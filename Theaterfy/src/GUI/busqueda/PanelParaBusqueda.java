package GUI.busqueda;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * JPanel para buscar eventos, va incluido en otros paneles de busqueda
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class PanelParaBusqueda extends JPanel {

	private JTextField buscar;
	private JComboBox<String> comboFiltros;
	private JButton boton;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public PanelParaBusqueda() {
		
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 20);
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(650, 100));
		
		buscar=new JTextField(15);
		JLabel busqueda=new JLabel("Introduzca el nombre de la obra:");
		
		
		String filtros[] = {"--filtros de búsqueda--", "Danza", "Teatro", "Música", "Eventos Próximos"};
		comboFiltros = new JComboBox<String> (filtros);
		
		boton= new JButton("Buscar");
		
		this.add(busqueda);
		this.add(buscar);
		this.add(comboFiltros);
		this.add(boton);
	}

	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.boton.addActionListener(c);
	}
	
	public String getEvento() {
		return this.buscar.getText();
	}
	
	public int getFiltro() {
		return this.comboFiltros.getSelectedIndex();
	}
}
