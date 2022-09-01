package GUI.anyadirEvento;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import theaterfy.Theaterfy;
import theaterfy.sucesos.Precio;
import theaterfy.zona.Zona;

/**
 * panel para añadir precios a las zonas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Precios extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel encabezado;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scroll;
	private JButton confirma;

	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public Precios() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(670, 350));
		
		encabezado = new JLabel("Configurar precios de las zonas");
		String[] titulo = {"Nombre zona", "Fije precio"};
		
		Object[][] filas= {{}};
		modeloTabla = new DefaultTableModel(filas, titulo);
		
		tabla = new JTable(modeloTabla);
		scroll = new JScrollPane(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(420, 130));
		confirma = new JButton("Guardar precios");
		
		layout.putConstraint(SpringLayout.WEST, encabezado, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, encabezado, 30, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, encabezado);
		layout.putConstraint(SpringLayout.NORTH, scroll, 30, SpringLayout.SOUTH, encabezado);
		layout.putConstraint(SpringLayout.EAST, confirma, 0, SpringLayout.EAST, scroll);
		layout.putConstraint(SpringLayout.NORTH, confirma, 15, SpringLayout.SOUTH, scroll);
		
		this.add(encabezado);
		this.add(scroll);
		this.add(confirma);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.confirma.addActionListener(c);
	}
	
	/**
	 * @return el array con los precios escritos
	 */
	public ArrayList<Precio> getPreciosZonas(){
		ArrayList<Precio> precios = new ArrayList<>();
		if(tabla.isEditing())
			tabla.getCellEditor().stopCellEditing();
		for(int i=0; i<tabla.getRowCount()-1; i++) {
			for(Zona z : Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
				if(tabla.getValueAt(i, 0).equals(z.getNombre()))
					precios.add(new Precio(Float.parseFloat((String)tabla.getValueAt(i, 1)), z));
			}
		}
		return precios;
	}
	
	/**
	 * actualiza la información de los componentes de Precios
	 */
	public void actualizar() {
		modeloTabla.setRowCount(Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas()).size()+1);
		int i=0;
		for(Zona z : Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
			modeloTabla.setValueAt(z.getNombre(), i, 0);
			i++;
		}
	}
}
