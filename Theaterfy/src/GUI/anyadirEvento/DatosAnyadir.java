package GUI.anyadirEvento;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
/**
 * panel con datos a añadir para un evento, comunes a los tres tipos
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class DatosAnyadir extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel encabezado;
	private JLabel tituloEvento;
	private JLabel duracionEvento;
	private JLabel autorEvento;
	private JLabel directorEvento;
	private JLabel dia;
	private JLabel horasEvento;
	private JTextField titulo;
	private JTextField duracion;
	private JTextField autor;	
	private JTextField director;
	private JTextField horas;
	private JTextField dias;
	private JButton guardar;
	private JButton precios;
	private JComboBox<String> fechasMostrar;
	private ArrayList<GregorianCalendar> fechas;

	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public DatosAnyadir() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(330, 600));
		
		encabezado=new JLabel("Añadir evento");
		tituloEvento=new JLabel("Título del evento");
		duracionEvento=new JLabel("Duración del evento");
		autorEvento=new JLabel("Autor del evento");
		directorEvento=new JLabel("Director del evento");
		dia=new JLabel("Introduce el dia");
		horasEvento=new JLabel("Introduce las horas");
		titulo = new JTextField(15);
		duracion = new JTextField(15);
		autor = new JTextField(15);	
		director = new JTextField(15);
		horas = new JTextField(4);
		dias = new JTextField(8);
		guardar = new JButton("Añadir fecha y hora");
		precios = new JButton("Configurar precios");
		fechasMostrar = new JComboBox<String>();
		fechas = new ArrayList<>();
		
		encabezado.setFont(new Font("Verdana", Font.BOLD, 24));
		layout.putConstraint(SpringLayout.WEST, encabezado, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, encabezado, 15, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, tituloEvento, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tituloEvento, 20, SpringLayout.SOUTH, encabezado);
		layout.putConstraint(SpringLayout.EAST, titulo, 1, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, titulo, 0, SpringLayout.NORTH, tituloEvento);
		
		layout.putConstraint(SpringLayout.WEST, duracionEvento, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, duracionEvento, 10, SpringLayout.SOUTH, tituloEvento);
		layout.putConstraint(SpringLayout.EAST, duracion, 1, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, duracion, 0, SpringLayout.NORTH, duracionEvento);
		
		layout.putConstraint(SpringLayout.WEST, autorEvento, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, autorEvento, 10, SpringLayout.SOUTH, duracionEvento);
		layout.putConstraint(SpringLayout.EAST, autor, 1, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, autor, 0, SpringLayout.NORTH, autorEvento);
		
		layout.putConstraint(SpringLayout.WEST, directorEvento, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, directorEvento, 10, SpringLayout.SOUTH, autorEvento);
		layout.putConstraint(SpringLayout.EAST, director, 1, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, director, 0, SpringLayout.NORTH, directorEvento);
		
		layout.putConstraint(SpringLayout.WEST, dia, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dia, 20, SpringLayout.SOUTH, director);
		
		layout.putConstraint(SpringLayout.WEST, dias, 0, SpringLayout.WEST, titulo);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, dias, 0, SpringLayout.VERTICAL_CENTER, dia);
		
		layout.putConstraint(SpringLayout.WEST, horasEvento, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, horasEvento, 15, SpringLayout.SOUTH, dia);
		
		layout.putConstraint(SpringLayout.WEST, horas, 0, SpringLayout.WEST, titulo);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, horas, 0, SpringLayout.VERTICAL_CENTER, horasEvento);
		
		layout.putConstraint(SpringLayout.WEST, guardar, 0, SpringLayout.WEST, horasEvento);
		layout.putConstraint(SpringLayout.NORTH, guardar, 20, SpringLayout.SOUTH, horas);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, precios, 0, SpringLayout.HORIZONTAL_CENTER, guardar);
		layout.putConstraint(SpringLayout.NORTH, precios, 100, SpringLayout.SOUTH, guardar);
		
		layout.putConstraint(SpringLayout.WEST, fechasMostrar, 0, SpringLayout.WEST, guardar);
		layout.putConstraint(SpringLayout.NORTH, fechasMostrar, 20, SpringLayout.SOUTH, guardar);
		fechasMostrar.setVisible(false);
		
		this.add(encabezado);
		this.add(tituloEvento);
		this.add(titulo);
		this.add(duracionEvento);
		this.add(duracion);
		this.add(autorEvento);
		this.add(autor);
		this.add(directorEvento);
		this.add(director);
		this.add(dia);
		this.add(dias);
		this.add(horasEvento);
		this.add(horas);
		this.add(guardar);
		this.add(precios);
		this.add(fechasMostrar);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.guardar.addActionListener(c);
		this.precios.addActionListener(c);
	}
	
	/**
	 * muestra las fechas añadidas por el JTextField en el comboBox 
	 * fechasMostrar
	 * @param date fecha
	 */
	public void imprimirArrayFechas(GregorianCalendar date) {
		this.fechas.add(date);
		String s = "";
		s += date.get(GregorianCalendar.DAY_OF_MONTH ) + "/";
		s += date.get(GregorianCalendar.MONTH )+1 + "/";
		s += date.get(GregorianCalendar.YEAR ) + " ";
		s += date.get(GregorianCalendar.HOUR ) + ":";
		s += date.get(GregorianCalendar.MINUTE );
		this.fechasMostrar.addItem(s);
		fechasMostrar.setVisible(true);
	}
	
	public String getTitulo() {
		return this.titulo.getText();
	}
	
	public String getDuracion() {
		return this.duracion.getText();
	}
	
	public String getAutor() {
		return this.autor.getText();
	}
	
	public String getDirector() {
		return this.director.getText();
	}
	
	public String getHora() {
		return this.horas.getText();
	}
	
	public String getDia() {
		return this.dias.getText();
	}
	
	public ArrayList<GregorianCalendar> getArrayFechas(){
		return this.fechas;
	}
}
