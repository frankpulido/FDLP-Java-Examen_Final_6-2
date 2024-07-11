package examenfinal_frank_pulido_2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lineal {
	
	/*
	 * El Lineal es un conjunto de estanterías que se extiende a lo largo del pasillo
	 */
	
	// Atributos de la clase LINEAL
	
	private int numEstanteriasLineal;
	private ArrayList<Integer> bandejasEstanterias;
	private ArrayList<Estanteria> estanteriasLineal;
	
	
	// Constructor de la Clase Lineal
	
	Lineal (int numEstanteriasLineal, ArrayList<Integer> bandejasEstanterias) {
		this.numEstanteriasLineal = numEstanteriasLineal;
		this.bandejasEstanterias = bandejasEstanterias;
		this.estanteriasLineal = inicializarLineal (numEstanteriasLineal, bandejasEstanterias);
	}
	
	
	// Métodos get o "getters"
	
	public int getNumEstanteriasLineal() {
		return this.numEstanteriasLineal;
	}
	
	
	public ArrayList<Integer> getBandejasEstanterias() {
		return this.bandejasEstanterias;
	}
	
	
	public ArrayList<Estanteria> getEstanteriasLineal() {
		return this.estanteriasLineal;
	}
	
	
	// Métodos set o "setters"
	
	public void setNumEstanteriasLineal(int numEstanteriasLineal) {
		this.numEstanteriasLineal = numEstanteriasLineal;
	}
	
	
	public void setBandejasEstanterias(ArrayList<Integer> bandejasEstanterias) {
		this.bandejasEstanterias = bandejasEstanterias;
	}
	
	
	public void setEstanteriasLineal(ArrayList<Estanteria> EstanteriasLineal) {
		this.estanteriasLineal = EstanteriasLineal;
	}
	
	
	
	
	// Método inicializarLineal
	
	public ArrayList<Estanteria> inicializarLineal (int numEstanteriasLineal, ArrayList<Integer>bandejasEstanterias) {
		ArrayList<Estanteria> newEstanteriasLineal = new ArrayList<Estanteria>();
		int bandejas = 0;
		for (int i = 0; i < numEstanteriasLineal; i++) {
			bandejas = bandejasEstanterias.get(i);
			Estanteria newEstanteria = new Estanteria(bandejas);
			newEstanteriasLineal.add(newEstanteria);
		}
		return newEstanteriasLineal;
	}
	
	
	// Método existencia de espacio disponible en Lineal
	
	public int espacioLineal() {
		int vacios = 0;
		for (Estanteria estanteria : this.estanteriasLineal) {
			for (int i = 0; i < estanteria.getNumBandejas(); i++) {
				if ((int) Array.get(estanteria.getBandejasEstado(), i) == -1) {
					vacios++;
				}
			}
		}
		return vacios;
	}
	
	
	// Método contenidoLineal
	
	public String contenidoLineal() {
		String respuesta = "Contenido del Lineal de Bebidas :\n";
		String estanteria = "";
		String bandeja = "";
		int z = -1; // Estado de la bandeja (-1 es libre, de lo contrario tiene el ID del producto)
		for (int i = 0; i < this.getNumEstanteriasLineal(); i++) {
			estanteria = "Estantería " + i + " : \t";
			bandeja = "";
			for (int j = 0; j < this.getEstanteriasLineal().get(i).getNumBandejas(); j++) {
				z = (int) Array.get(this.getEstanteriasLineal().get(i).getBandejasEstado(), j);
				bandeja = bandeja + "Posición " + j + " : " + ((z == -1)? "libre" : "ID:" + z) + "\t";
			}
			respuesta = respuesta + estanteria + bandeja + "\n";
		}
		
		return respuesta;
	}
	
	
	// Método mapear (matriz)
	
	public String mapearLineal() {
		String mapa = "";
		String bandeja = "";
		int mayorEstanteria = 0;
		int testigo = 0;
		int z = -1; // Estado de la bandeja (-1 es libre, de lo contrario tiene el ID del producto)
		String estado = "";
		for (int i = 0; i < this.getNumEstanteriasLineal(); i++) {
			testigo = this.getBandejasEstanterias().get(i);
			if (testigo > mayorEstanteria) { mayorEstanteria = testigo; }
			mapa = mapa + "\t" + i;
		}
		for (int i = 0; i < mayorEstanteria; i++) {
			for (int j = 0; j < numEstanteriasLineal; j++) {
					if (this.getBandejasEstanterias().get(j) >= i + 1) {
						z = (int) Array.get(this.getEstanteriasLineal().get(j).getBandejasEstado(), i);
						estado = (z == -1 )?
								"libre" : ("ID:" + (int) Array.get(this.getEstanteriasLineal().get(j).getBandejasEstado(),i));
						bandeja = bandeja + "\t" + estado;
					} else {
						bandeja = bandeja + "\t" + "";
					}
			}
			mapa = mapa + "\n" + i + bandeja + "\n";
			bandeja = "";
		}
		return mapa;
	}	

}
