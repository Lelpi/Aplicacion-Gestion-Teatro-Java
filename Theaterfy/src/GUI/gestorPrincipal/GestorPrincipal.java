package GUI.gestorPrincipal;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Suceso;

/**
 * panel de bienvenida del gestor
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class GestorPrincipal extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel tit;
	private JLabel maxCompra;
	private JLabel maxReserva;
	private JLabel horasConfirmar;
	private JLabel horasReservar;
	private JLabel abonoCiclo;
	private JButton deshab;
	private JButton est;
	private JButton anyadirEvento;
	private JButton config;
	private JButton guardar;
	private JTextField max;
	private JTextField maxR;
	private JTextField horasC;
	private JTextField horasR;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scroll;
	private PanelParaBusqueda busqueda;
	private JButton cerrar;
	private JButton guardarAbonos;
	private JButton ciclos;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public GestorPrincipal() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		tit=new JLabel("Pagina principal del gestor");
		busqueda = new PanelParaBusqueda();
		est=new JButton("Consultar estadisticas");
		anyadirEvento=new JButton("Añadir evento");
		maxCompra=new JLabel("Max. entradas por persona (comprar)");
		max = new JTextField(4);
		maxReserva=new JLabel("Max. entradas por persona (reservar)");
		maxR = new JTextField(4);
		horasConfirmar=new JLabel("Horas para confirmar reserva");
		horasC = new JTextField(4);
		horasReservar=new JLabel("Horas reservar");
		horasR = new JTextField(4);
		abonoCiclo=new JLabel("Precio abono por ciclo:");
		deshab = new JButton("Deshabilitar butacas");
		config = new JButton("Configurar zonas del teatro");
		guardar = new JButton("Guardar cambios");
		cerrar= new JButton("Cerrar sesión");
		guardarAbonos= new JButton("Guardar abonos");
		ciclos=new JButton("Añadir ciclos");
		
		String[] titulo = {"Ciclo", "Porcentaje del total a pagar (%)"};
		Object[][] filas = {{}};
		modeloTabla = new DefaultTableModel(filas, titulo);
		
		tabla = new JTable(modeloTabla);
		scroll = new JScrollPane(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 130));
		
		layout.putConstraint(SpringLayout.WEST, tit, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tit, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, busqueda, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, busqueda, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, est, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, est, 0, SpringLayout.SOUTH, busqueda);
		layout.putConstraint(SpringLayout.WEST, anyadirEvento, 0, SpringLayout.WEST, est);
		layout.putConstraint(SpringLayout.NORTH, anyadirEvento, 5, SpringLayout.SOUTH, est);
		layout.putConstraint(SpringLayout.WEST, maxCompra, 0, SpringLayout.WEST, est);
		layout.putConstraint(SpringLayout.NORTH, maxCompra, 20, SpringLayout.SOUTH, anyadirEvento);
		layout.putConstraint(SpringLayout.WEST, max, 50, SpringLayout.EAST, maxCompra);
		layout.putConstraint(SpringLayout.NORTH, max, 0, SpringLayout.NORTH, maxCompra);
		layout.putConstraint(SpringLayout.WEST, maxReserva, 0, SpringLayout.WEST, est);
		layout.putConstraint(SpringLayout.NORTH, maxReserva, 10, SpringLayout.SOUTH, maxCompra);
		layout.putConstraint(SpringLayout.WEST, maxR, 0, SpringLayout.WEST, max);
		layout.putConstraint(SpringLayout.NORTH, maxR, 0, SpringLayout.NORTH, maxReserva);
		layout.putConstraint(SpringLayout.WEST, horasConfirmar, 0, SpringLayout.WEST, est);
		layout.putConstraint(SpringLayout.NORTH, horasConfirmar, 10, SpringLayout.SOUTH, maxReserva);
		layout.putConstraint(SpringLayout.WEST, horasC, 0, SpringLayout.WEST, max);
		layout.putConstraint(SpringLayout.NORTH, horasC, 0, SpringLayout.NORTH, horasConfirmar);
		layout.putConstraint(SpringLayout.WEST, horasReservar, 0, SpringLayout.WEST, est);
		layout.putConstraint(SpringLayout.NORTH, horasReservar, 10, SpringLayout.SOUTH, horasConfirmar);
		layout.putConstraint(SpringLayout.WEST, horasR, 0, SpringLayout.WEST, max);
		layout.putConstraint(SpringLayout.NORTH, horasR, 0, SpringLayout.NORTH, horasReservar);
		layout.putConstraint(SpringLayout.WEST, abonoCiclo, 0, SpringLayout.WEST, est);
		layout.putConstraint(SpringLayout.NORTH, abonoCiclo, 20, SpringLayout.SOUTH, horasReservar);
		
		layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, abonoCiclo);
		layout.putConstraint(SpringLayout.NORTH, scroll, 10, SpringLayout.SOUTH, abonoCiclo);
		layout.putConstraint(SpringLayout.EAST, deshab, -125, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, deshab, 405, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, guardar, 0, SpringLayout.EAST, deshab);
		layout.putConstraint(SpringLayout.SOUTH, guardar, -10, SpringLayout.NORTH, deshab);
		layout.putConstraint(SpringLayout.EAST, config, 0, SpringLayout.EAST, deshab);
		layout.putConstraint(SpringLayout.NORTH, config, 10, SpringLayout.SOUTH, deshab);
		layout.putConstraint(SpringLayout.EAST, cerrar, 0, SpringLayout.EAST, config);
		layout.putConstraint(SpringLayout.NORTH, cerrar, 10, SpringLayout.SOUTH, config);

		layout.putConstraint(SpringLayout.WEST, guardarAbonos, 230, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, guardarAbonos, -30, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.WEST, ciclos, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, ciclos, 90, SpringLayout.NORTH, this);
		
		this.add(tit);
		this.add(busqueda);
		this.add(est);
		this.add(anyadirEvento);
		this.add(maxCompra);
		this.add(max);
		this.add(maxReserva);
		this.add(maxR);
		this.add(horasConfirmar);
		this.add(horasC);
		this.add(horasReservar);
		this.add(horasR);
		this.add(abonoCiclo);
		this.add(scroll);
		this.add(deshab);
		this.add(config);
		this.add(guardar);
		this.add(cerrar);
		this.add(guardarAbonos);
		this.add(ciclos);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.deshab.addActionListener(c);
		this.est.addActionListener(c);
		this.anyadirEvento.addActionListener(c);
		this.config.addActionListener(c);
		this.guardar.addActionListener(c);
		this.busqueda.setControlador(c);
		this.cerrar.addActionListener(c);
		this.guardarAbonos.addActionListener(c);
		this.ciclos.addActionListener(c);
	}

	public String getMaxCompradas() {
		return this.max.getText();
	}
	
	public String getMaxReservadas() {
		return this.maxR.getText();
	}
	
	public String getHorasConfirmar() {
		return this.horasC.getText();
	}
	
	public String getHorasReservar() {
		return this.horasR.getText();
	}
	
	public PanelParaBusqueda getPanelBusqueda() {
		return this.busqueda;
	}
	
	/**
	 * actualiza la información de los componentes de GestorPrincipal. 
	 * El "set" en el nombre es una errata
	 */
	public void setActualizarTabla() {
		modeloTabla.setRowCount(0);
		for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getClass()==Ciclo.class) {
				modeloTabla.addRow(new Object[] {s.getNombre(), ""});	
			}
		}
		modeloTabla.addRow(new Object[] {"",""});
	}
	
	/**
	 * @return un mapa con los ciclos, asociados al descuento de sus abonos de ciclo (de 0 a 1) 
	 * obtenido de la modeloTabla
	 */
	public Map<Ciclo, Float> getPreciosAbonos(){
		Map<Ciclo, Float> mapa = new LinkedHashMap<>();
		if(tabla.isEditing())
			tabla.getCellEditor().stopCellEditing();
		for(int i=0; i<tabla.getRowCount()-1; i++) {
			for(Suceso s:Theaterfy.getTheaterfy().getSucesos()) {
				if(s.getNombre().equals((String)modeloTabla.getValueAt(i, 0))) {
					mapa.put((Ciclo)s, (Float.parseFloat((String)modeloTabla.getValueAt(i, 1))/100f));
				}
			}
		}
		return mapa;
	}
}
