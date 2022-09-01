package GUI.anyadirCiclo;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Suceso;

/**
 * JPanel para añadir un ciclo 
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class AnyadirCiclo extends JPanel{
	
	private SpringLayout layout = new SpringLayout();
	private JTextField nombreCiclo;
	private DefaultTableModel modeloTabla;
	private JTable tabla;
	private JButton agregar;
	private JButton volver;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public AnyadirCiclo() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		
		JLabel ciclo=new JLabel("Añadir ciclos");
		layout.putConstraint(SpringLayout.WEST, ciclo, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, ciclo, 10, SpringLayout.NORTH, this);
		
		JLabel introduce=new JLabel("Introduce el nombre del ciclo:");
		layout.putConstraint(SpringLayout.WEST, introduce, 100, SpringLayout.WEST, ciclo);
		layout.putConstraint(SpringLayout.NORTH, introduce, 40, SpringLayout.NORTH, ciclo);
		
		nombreCiclo= new JTextField(8);
		layout.putConstraint(SpringLayout.WEST, nombreCiclo, 10, SpringLayout.EAST, introduce);
		layout.putConstraint(SpringLayout.NORTH, nombreCiclo, 0, SpringLayout.NORTH, introduce);
		
		String[] titulo = {"Nombre", "Tipo"};
		Object[][] filas= {{}};
		modeloTabla = new DefaultTableModel(filas, titulo);
		
		this.actualizarTabla();
		
		tabla = new JTable(modeloTabla);
		JScrollPane scroll = new JScrollPane(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(270, 130));
		
		layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, introduce);
		layout.putConstraint(SpringLayout.NORTH, scroll, 50, SpringLayout.SOUTH, introduce);
		
		agregar=new JButton("Agregar");
		layout.putConstraint(SpringLayout.WEST, agregar, 0, SpringLayout.WEST, scroll);
		layout.putConstraint(SpringLayout.NORTH, agregar, 50, SpringLayout.SOUTH, scroll);
		
		volver=new JButton("Inicio");
		layout.putConstraint(SpringLayout.WEST, volver, 50, SpringLayout.EAST, agregar);
		layout.putConstraint(SpringLayout.NORTH, volver, 0, SpringLayout.NORTH, agregar);
		
		this.add(ciclo);
		this.add(introduce);
		this.add(nombreCiclo);
		this.add(scroll);
		this.add(agregar);
		this.add(volver);
		
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.agregar.addActionListener(c);
		this.volver.addActionListener(c);
	}
	
	/**
	 * Actualiza la tabla de sucesos existentes, solo muestra aquellos que no están contenidos dentro de otros
	 */
	public void actualizarTabla() {
		modeloTabla.setRowCount(0);
		String aux;
		for(Suceso s : Theaterfy.getTheaterfy().getSucesosExternos()){
			if(s.getClass()==Ciclo.class) {
				aux="Ciclo";
			}else {
				aux="Evento";
			}
			modeloTabla.addRow(new Object[] {s.getNombre(), aux});
		}
	}
	
	/**
	 * Devuelve los sucesos seleccionados por el usuario en la tabla de sucesos que se muestra en el panel
	 * @return un array con los sucesos seleccionados 
	 */
	public ArrayList<Suceso> getSubciclos(){
		int []rows = tabla.getSelectedRows();
		ArrayList<Suceso> ciclos= new ArrayList<>();
		for(int i=0;i<rows.length;i++) {
			for(Suceso s: Theaterfy.getTheaterfy().getSucesosExternos()) {
				if(s.getNombre().equals(modeloTabla.getValueAt(rows[i], 0))) {
					ciclos.add(s);
				}
			}
		}
		return ciclos;
	}

	public String getNombreCiclo() {
		return this.nombreCiclo.getText();
	}
}
