package theaterfy.zona;

import theaterfy.sucesos.RestriccionAforo;

/**
 * Clase ZonaNumerada, esta clase gestiona todos los datos de una zona numerada
 * 
 * @author luis.lepore@estudiante.uam.es
 * @author oriol.julian@estudiante.uam.es
 * @author paula.golpe@estudiante.uam.es
 *
 */
public class ZonaNumerada extends Zona {
	private static final long serialVersionUID = -8733923753138856719L;
	private Butaca butacas[][];
	private int filas, columnas;
	
	
	/**
	 * Constructor, llama al constructor de zona y asocia los parámetros a las 
	 * variables de la clase y añade también las butacas de la zona
	 * 
	 * @param nombre es el nombre de la zona numerada
	 * @param precioAbonoAnual es el precio de un abono anual para una zona numerada
	 * @param filas es el numero de filas que contiene la zona
	 * @param columnas es el numero de columnas que contiene la zona
	 */
	public ZonaNumerada(String nombre, float precioAbonoAnual, int filas, int columnas) {
		super(nombre, precioAbonoAnual);
		this.filas=filas;
		this.columnas=columnas;
		this.butacas = new Butaca[columnas][filas];
		
		for(int i=0;i<columnas;i++) {
			for(int j=0;j<filas;j++) {
				addButaca(i,j);
			}
		}
	}

	/**
	 * Método, que añade todas las butacas a la zona seleccionada
	 * y las inicializa a no comprada y no deshabilitada
	 * 
	 * @param x es la posicion x de la butaca 
	 * @param y es la posicion y de la butaca
	 */
	public void addButaca(int x, int y) {
		butacas[x][y]=new Butaca(x, y, this);
	}

	/**
	 * 
	 * @param x columna
	 * @param y fila
	 * @return butaca en x e y
	 */
	public Butaca getButaca(int x, int y) {
		return butacas[x][y];
	}
	
	/**
	 * @return la matriz de butacas
	 */
	public Butaca[][] getTotalButacas(){
		return this.butacas;
	}
	/**
	 * @return devuelve el aforo
	 */
	public int getAforo() {
		return this.filas*this.columnas;
	}
	
	/**
	 * añade deshabilitacion a ciertas butacas, de acuerdo con el porcentaje de ra
	 * @param des deshabilitacion para añadir a ciertas butacas
	 * @param ra restriccion de aforo para la zona
	 */
	public void anyadirRestriccionZonaNumerada(Deshabilitacion des, RestriccionAforo ra) {
		int butacasadeshabilitar=(int) Math.floor(ra.getPorcentaje()*getAforo());
		int i,j,deshabilitadas=0;
		
		for(i=0;i<this.columnas;i++) {
			for(j=0;j<this.filas;j++) {
				if(butacas[i][j].isDeshabilitada(des.getFechaIni(), des.getFechaIni())==true) {
					butacasadeshabilitar--;
				}
			}
		}
		
		if(butacasadeshabilitar<=0) {
			return;
		}
		
		for(i=0; i<this.columnas; i+=2) {
			for(j=0; j<this.filas; j+=2) {
				if(butacas[i][j].isDeshabilitada(des.getFechaIni(), des.getFechaIni())==false) {
					butacas[i][j].addDeshabilitada(des);
					ra.anyadirDeshabilitada(butacas[i][j]);
					deshabilitadas++;
					if(deshabilitadas>=butacasadeshabilitar) {
						return;
					}
				}
			}
		}
		for(i=1; i<this.columnas; i+=2) {
			for(j=1; j<this.filas; j+=2) {
				if(butacas[i][j].isDeshabilitada(des.getFechaIni(), des.getFechaIni())==false) {
					butacas[i][j].addDeshabilitada(des);
					ra.anyadirDeshabilitada(butacas[i][j]);
					deshabilitadas++;
					if(deshabilitadas>=butacasadeshabilitar) {
						return;
					}
				}
			}
		}
		for(i=0; i<this.columnas; i+=2) {
			for(j=1; j<this.filas; j+=2) {
				if(butacas[i][j].isDeshabilitada(des.getFechaIni(), des.getFechaIni())==false) {
					butacas[i][j].addDeshabilitada(des);
					ra.anyadirDeshabilitada(butacas[i][j]);
					deshabilitadas++;
					if(deshabilitadas>=butacasadeshabilitar) {
						return;
					}
				}
			}
		}
		for(i=1; i<this.columnas; i+=2) {
			for(j=0; j<this.filas; j+=2) {
				if(butacas[i][j].isDeshabilitada(des.getFechaIni(), des.getFechaIni())==false) {
					butacas[i][j].addDeshabilitada(des);
					ra.anyadirDeshabilitada(butacas[i][j]);
					deshabilitadas++;
					if(deshabilitadas>=butacasadeshabilitar) {
						return;
					}
				}
			}
		}
		
	}
}
