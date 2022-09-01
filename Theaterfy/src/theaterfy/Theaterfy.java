package theaterfy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import theaterfy.compras.*;
import theaterfy.sucesos.*;
import theaterfy.usuarios.*;
import theaterfy.zona.*;

/**
 * Esta clase abstracta almacena todo el teatro, patron singleton
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * 
 */
public class Theaterfy implements java.io.Serializable{
	
	private static final long serialVersionUID = 365140119536968936L;

	private static Theaterfy theaterfy;
	
	private int maxCompraEntradas=5;
	private int maxReservaEntradas=10;
	private int horasConfirmarReserva=24;
	private int horasSePermiteReserva=6;
	private ArrayList <Precio> preciosAbonoAnual=new ArrayList<>();
	private LinkedHashMap<Ciclo, Float> precioAbonoCiclo=new LinkedHashMap<>();	//el descuento va de 0 a 1
	
	private ArrayList <Suceso> sucesos=new ArrayList<>(); //Si se añade un ciclo que posee un evento se añade tanto el ciclo con su evento como el evento
	private ArrayList <Zona> zonas=new ArrayList<>();
	private Gestor gestor=null;
	private String contraseñaGestor="contraseña";
	private ArrayList <UsuarioRegistrado> usuarios=new ArrayList<>();
	private ArrayList <Abono> abonos=new ArrayList<>();
	private ArrayList <Suceso> sucesosExternos=new ArrayList<>();
	private UsuarioRegistrado usuarioActual=null;
	
	/**
	 * Constructor privado vacio
	 * 
	 */
	private Theaterfy() {
		
	}
	
	/**
	 * Metodo para obtener la instanciacion unica del Sistema
	 * 
	 * @return SistemaTheaterfy
	 */
	public static Theaterfy getTheaterfy() {
		if(theaterfy==null) {
			theaterfy=new Theaterfy();
		}
		return theaterfy;
	}
	
	/**
	 * Metodo para reiniciar el sistema
	 * 
	 */
	public void limpiar() {
        theaterfy=null;
    }
	
	/**
	 * Metodo para buscar eventos del teatro
	 * 
	 * @param s la cadena de caracteres que se busca
	 * @param f el filtro de la bsuqueda
	 * @return el array de eventos encotnrados
	 */
	public ArrayList<Evento> buscarEventos(String s, int f) {
		ArrayList <Evento> eventos=new ArrayList<>();
		switch(f) {
			case 0:
				for(Suceso e:this.sucesos) {
					if(e.getClass()==EventoDanza.class) {
						if(e.getNombre().contains(s)) {
							eventos.add((Evento)e);
						}
					}
				}
				break;
			case 1:
				for(Suceso e:this.sucesos) {
					if(e.getClass()==EventoTeatro.class) {
						if(e.getNombre().contains(s)) {
							eventos.add((Evento)e);
						}
					}
				}
				break;
			case 2:
				for(Suceso e:this.sucesos) {
					if(e.getClass()==EventoMusica.class) {
						if(e.getNombre().contains(s)) {
							eventos.add((Evento)e);
						}
					}
				}
				break;
			case 3:
				for(Suceso e:this.sucesos) {
					if(e.getClass()!=Ciclo.class) {
						if(e.getNombre().contains(s) && ((Evento)e).eventoActivo()) {
							eventos.add((Evento)e);
						}
					}
				}
				break;
			default:
				for(Suceso e:this.sucesos) {
					if(e.getClass()!=Ciclo.class) {
						if(e.getNombre().contains(s)) {
							eventos.add((Evento)e);
						}
					}
				}
		}
		return eventos;
	}
	
	/**
	 * Metodo para añadir una zona al array de zonas
	 * 
	 * @param zona la zona a añadir
	 */
	public void anyadirZona(Zona zona) {
		for(Zona z:this.zonas) {
			if(z.getNombre().compareTo(zona.getNombre())==0) {
				return;
			}
		}
		this.zonas.add(zona);
	}
	
	/**
	 * Metodo para obtener los sucesos del teatro (si un ciclo tiene eventos dentro se obtienen ambas cosas)
	 * 
	 * @return los sucesos del teatro
	 */
	public ArrayList<Suceso> getSucesos() {
		return this.sucesos;
	}

	/**
	 * Metodo para añadir un suceso al array de sucesos 
	 * 
	 * @param suceso el suceso a añadir 
	 */
	public void anyadirSuceso(Suceso suceso) {
		for(Suceso s:this.sucesos) {
			if(s.getNombre().compareTo(suceso.getNombre())==0) {
				return;
			}
		}
		this.sucesosExternos.add(suceso);
		this.anyadirSucesoRec(suceso);
	}
	
	private void anyadirSucesoRec(Suceso suceso) {
		for(Suceso s:this.sucesos) {
			if(s.getNombre().compareTo(suceso.getNombre())==0) {
				return;
			}
		}
		if(suceso.getClass()==Ciclo.class) {
			for(Suceso s:((Ciclo)suceso).getSubciclos()) {
				this.anyadirSucesoRec(s);
			}
		}
		this.sucesos.add(suceso);
	}
	
	/**
	 * Metodo para añadir un abono al array de abonos
	 * 
	 * @param abono el abono a añadir
	 */
	public void anyadirAbono(Abono abono) {
		this.abonos.add(abono);
	}
	
	/**
	 * 
	 * @param p precio a añadir al array de abonos anuales
	 */
	public void anyadirPreciosAbonoAnual(Precio p) {
		this.preciosAbonoAnual.add(p);
	}

	/**
	 * añade un tipo de abono de ciclo
	 * @param c ciclo correspondiente al abono
	 * @param descuento descuento asociado al abono de ciclo
	 */
	public void anyadirPrecioAbonoCiclo(Ciclo c, float descuento) {
		this.precioAbonoCiclo.put(c, descuento);
	}
	
	
	/**
	 * Metodo que busca un suceso del sistema y lo modifica por el que se pasa por parametro
	 * 
	 * @param suceso el suceso que se modificara
	 */
	public void modificarSuceso(Suceso suceso) {
		int index=-1;
		for(int i=0; i<sucesos.size(); i++) {
			if(this.sucesos.get(i).getNombre().compareTo(suceso.getNombre())==0) {
				index=i;
				break;
			}
		}
		if(index!=-1)
			this.sucesos.set(index, suceso);
	}
	
	/**
	 * comprueba si existe algún evento activo
	 * @return true si existe algun evento activo dentro del teatro
	 */
	public boolean algunEventoActivo() {
		for(Suceso s:this.sucesos) {
			if(s.getClass()!=Ciclo.class) {
				if(((Evento)s).eventoActivo()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Metodo para registrar un usuario en el sistema
	 * 
	 * @param n nick
	 * @param c contraseña
	 * @return false si ya existe alguien con ese nick
	 */
	public boolean registrarse(String n, String c) {
		for(UsuarioRegistrado usuario : usuarios) {
			if(usuario.getNick().compareTo(n)==0) {
				return false;
			}
		}
		this.usuarios.add(new UsuarioRegistrado(n,c));
		return true;
	}
	
	/**
	 * Metodo para hacer login
	 * 
	 * @param n nick
	 * @param c contraseña
	 * @return true si pudo hacer login
	 */
	public boolean login(String n, String c) {
		for(UsuarioRegistrado u:this.usuarios) {
			if(u.getNick().compareTo(n)==0 && u.getContraseña().compareTo(c)==0) {
				this.usuarioActual=u;
				this.gestor=null;
				return true;
			}
		}
		return false;
	}
	/**
	 * Metodo para hacer login del gestor
	 * 
	 * @param c contraseña
	 * @return true si pudo hacer login
	 */
	public boolean login(String c) {
		if(c.compareTo(this.contraseñaGestor)==0) {
			this.gestor=new Gestor(c);
			this.usuarioActual=null;
			return true;
		}
		return false;
	}
	
	
	/**
	 * Metodo para obtener el numero maximo de entradas que se pueden comprar a la vez
	 * 
	 * @return el numero maxCompraEntradas
	 */
	public int getMaxCompraEntradas() {
		return maxCompraEntradas;
	}

	/**
	 * Metodo para obtener el numero maximo de entradas que se pueden reservar a la vez
	 * 
	 * @return el numero maxReservaEntradas
	 */
	public int getMaxReservaEntradas() {
		return maxReservaEntradas;
	}

	/**
	 * Metodo para obtener las horas maximas antes del evento para confirmar una reserva
	 * 
	 * @return las horas para confirmar reserva
	 */
	public int getHorasConfirmarReserva() {
		return horasConfirmarReserva;
	}

	/**
	 * Metodo para obtener las horas maximas antes del evento en las que se puede hacer una reserva
	 * 
	 * @return the horasSePermiteReserva
	 */
	public int getHorasSePermiteReserva() {
		return horasSePermiteReserva;
	}

	/**
	 * Metodo para asignar el numero maximo de entradas que se pueden comprar a la vez
	 * 
	 * @param maxCompraEntradas el numero que se asignara
	 */
	public void setMaxCompraEntradas(int maxCompraEntradas) {
		this.maxCompraEntradas = maxCompraEntradas;
	}

	/**
	 * Metodo para asignar el numero maximo de entradas que se pueden reservar a la vez
	 * 
	 * @param maxReservaEntradas el numero que se asignara
	 */
	public void setMaxReservaEntradas(int maxReservaEntradas) {
		this.maxReservaEntradas = maxReservaEntradas;
	}

	/**
	 * Metodo para asignar las horas que tiene un usuario para confirmar una reserva
	 * 
	 * @param horasConfirmarReserva el numero que se asignara
	 */
	public void setHorasConfirmarReserva(int horasConfirmarReserva) {
		this.horasConfirmarReserva = horasConfirmarReserva;
	}

	/**
	 * Metodo para asignar las horas que tiene un usuario para hacer una reserva antes de una actuacion
	 * 
	 * @param horasSePermiteReserva the horasSePermiteReserva to set
	 */
	public void setHorasSePermiteReserva(int horasSePermiteReserva) {
		this.horasSePermiteReserva = horasSePermiteReserva;
	}

	/**
	 * Metodo para añadir el precio de un abono anual de una zona
	 * 
	 * @param precioAbonoAnual the precioAbonoAnual to set
	 */
	public void setPrecioAbonoAnual(Precio precioAbonoAnual) {
		this.preciosAbonoAnual.add(precioAbonoAnual);
	}

	/**
	 * Metodo para obtener los precios del abono anual
	 * 
	 * @return the preciosAbonoAnual
	 */
	public ArrayList<Precio> getPreciosAbonoAnual() {
		return preciosAbonoAnual;
	}
	
	/**
	 * @return the precioAbonoCiclo
	 */
	public LinkedHashMap<Ciclo, Float> getPrecioAbonoCiclo() {
		return precioAbonoCiclo;
	}

	/**
	 * Metodo para calcular las estadisticas de ocupacion de las representaciones de un evento (se toma como total de asientos el total del teatro y no el aforo permitido en ese momento)
	 * 
	 * @param evento para el cual se calcula la ocupacion de sus representaciones
	 * @return un mapa con la fecha de la representación como clave y el porcentaje de ocupación como dato, de 0 a 1
	 */
	public LinkedHashMap<String, Float> generarEstadisticasOcupacionRepresentacion(Evento evento) {
        GregorianCalendar fecha=new GregorianCalendar();
        float contador;
        LinkedHashMap<String, Float> datos=new LinkedHashMap<>();
        String str;
        
        for(Representacion r : evento.getRepresentaciones()) {
        	contador=0;
            if(r.getFecha().compareTo(fecha)<0) {
                for(Entrada entrada : r.getEntradaDeRepresentacion()) {
                    if(entrada.getClass()==Entrada.class)
                        contador++;
                }
                str=r.getFecha().get(GregorianCalendar.DATE)+"/"+(r.getFecha().get(GregorianCalendar.MONTH)+1)+"/"+r.getFecha().get(GregorianCalendar.YEAR)+" "
                		+ r.getFecha().get(GregorianCalendar.HOUR_OF_DAY)+":"+r.getFecha().get(GregorianCalendar.MINUTE);
            
                datos.put(str, ((Float)contador/(Theaterfy.getTheaterfy().totalAforo())));
            }
        }
        return datos;
    }
	
	/**
	 * Metodo para generar las estadisticas de ocupacion de las zonas del teatro (se toma como total de asientos la capacidad total de la zona y no la cantidad permitida en ese momento)
	 * 
	 * @return un mapa con el nombre de la zona como clave y el porcentaje de ocupación como dato (de 0 a 1);
	 */
	public LinkedHashMap<String, Float> generarEstadisticasOcupacionZona() {
        GregorianCalendar fecha=new GregorianCalendar();
        float contador;
        int numEventos, numRepresentaciones;
        ArrayList<Zona> zonas = new ArrayList<>();
        LinkedHashMap<String, Float> datos=new LinkedHashMap<>();
        
        zonas=Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas());
        for(Zona z: zonas) {
        	contador=0;
        	numEventos=0;
        	numRepresentaciones=0;
        	for(Suceso s:Theaterfy.getTheaterfy().getSucesos()) {
        		if(s.getClass()!=Ciclo.class) {
        			numEventos++;
        			for(Representacion r:((Evento)s).getRepresentaciones()){
        				if(r.getFecha().compareTo(fecha)<0) {
        					numRepresentaciones++;
        					for(Entrada e: r.getEntradaDeRepresentacion()) {
        						if(e.getClass()==Entrada.class) {
        							if(e.getZona().getNombre().compareTo(z.getNombre())==0) {
        								contador++;
        							}
        						}
        					}
        				}
        			}
        		}
        	}
        	datos.put(z.getNombre(),((Float)contador/(z.getAforo()*numRepresentaciones*numEventos)));
        }
		return datos;
	}
	
	/**
	 * Metodo para calcular las estadisticas de remuneracion de los eventos del teatro
	 * 
	 * @return un mapa con el nombre de evento como clave y el dinero ganado como dato 
	 */
	public LinkedHashMap<String, Float> generarEstadisticasRemuneracionEvento() {
        GregorianCalendar fecha=new GregorianCalendar();
        float total;
		LinkedHashMap<String, Float> datos=new LinkedHashMap<>();
		
        for(Suceso s:Theaterfy.getTheaterfy().getSucesos()) {
        	total=0;
        	if(s.getClass()!=Ciclo.class) {
        		for(Representacion r:((Evento)s).getRepresentaciones()) {
        			if(r.getFecha().compareTo(fecha)<0) {
        				for(Entrada e:r.getEntradaDeRepresentacion()) {
        					if(e.getClass()==Entrada.class) {
        						for(Precio p:((Evento)s).getPrecios()) {
        							if(p.getZona().getNombre().compareTo(e.getZona().getNombre())==0) {
        								total+=p.getPrecio();
        							}
        						}
        					}
        				}
        			}
        		}
        		datos.put(s.getNombre(), total);
        	}
        }
		
		return datos;
	}

	/**
	 * Metodo para calcular las estadisticas de remuneracion de cada zona del teatro
	 * 
	 * @return un mapa con el nombre de la zona como clave y el dinero ganado como dato
	 */
	public LinkedHashMap<String, Float> generarEstadisticasRemuneracionZona() {
        GregorianCalendar fecha=new GregorianCalendar();
        float total;
        ArrayList<Zona> zonas = new ArrayList<>();
        LinkedHashMap<String, Float> datos=new LinkedHashMap<>();
        
        zonas=Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas());
        for(Zona z: zonas) {
        	total=0;
        	for(Suceso s:Theaterfy.getTheaterfy().getSucesos()) {
        		if(s.getClass()!=Ciclo.class) {
        			for(Representacion r:((Evento)s).getRepresentaciones()){
        				if(r.getFecha().compareTo(fecha)<0) {
        					for(Entrada e: r.getEntradaDeRepresentacion()) {
        						if(e.getClass()==Entrada.class) {
        							if(e.getZona().getNombre().compareTo(z.getNombre())==0) {
        								for(Precio p: ((Evento)s).getPrecios()) {
        									if(p.getZona().getNombre().compareTo(z.getNombre())==0) {
        										total+=p.getPrecio();
        									}
        								}
        							}
        						}
        					}
        				}
        			}
        		}
        	}
        	datos.put(z.getNombre(), total);
        }
		return datos;
		
	}

	/**
	 * Metodo para obtener las zonas del teatro
	 * 
	 * @return the zonas
	 */
	public ArrayList<Zona> getZonas() {
		return this.zonas;
	}
	
	/**
	 * Metodo que calcula el aforo total del teatro
	 * 
	 * @return el aforo total del teatro
	 */
	public int totalAforo() {
		int contador=0;
		for(Zona z: Theaterfy.getTheaterfy().zonas) {
			contador+=z.getAforo();
		}
		return contador;
	}
	
	/**
	 * Metodo para obtener las zonas numeradas y no numeradas a partir de un array de zonas mixtas, numeradas y no numeradas
	 * 
	 * @param zonas array de zonas a comprobar 
	 * @return laz zonas numeradas y no numeradas del teatro
	 */
	public ArrayList<Zona> getZonasNoMixtas(ArrayList<Zona> zonas){
		ArrayList<Zona> zonasNoMixtas=new ArrayList<>();
		for(Zona z: zonas) {
			if(z.getClass()!=ZonaMixta.class) {
				zonasNoMixtas.add(z);
			}
			else {
				zonasNoMixtas.addAll(((ZonaMixta)z).getSubzonas());
			}
		}
		return zonasNoMixtas;
	}
	
	
	/**
	 * @return the usuarios
	 */
	public ArrayList<UsuarioRegistrado> getUsuarios() {
		return usuarios;
	}

	/**
	 * guarda los datos de la aplicación en el archivo ./tmp/theaterfy.ser
	 */
	public void guardar() {
		this.usuarioActual=null;
		this.gestor=null;
		try {
			FileOutputStream fileOut = new FileOutputStream("./tmp/theaterfy.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Theaterfy.getTheaterfy());
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in /tmp/theaterfy.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * carga los datos de la aplicación del archivo ./tmp/theaterfy.ser
	 */
	public void cargar() {
		try {
			FileInputStream fileIn = new FileInputStream("./tmp/theaterfy.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			theaterfy=(Theaterfy) in.readObject();
			in.close();
			fileIn.close();
		} catch(FileNotFoundException e) {
			return;
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Theaterfy class not found");
			c.printStackTrace();
			return;
		}
	}
	
	public Gestor getGestor() {
		return this.gestor;
	}
	
	public UsuarioRegistrado getUsuarioActual() {
		return this.usuarioActual;
	}
	
	/**
	 * se pone el usuario del momento a null
	 */
	public void cerrarSesion() {
		this.usuarioActual=null;
		this.gestor=null;
	}
	
	public ArrayList<Suceso> getSucesosExternos(){
		return this.sucesosExternos;
	}
}
