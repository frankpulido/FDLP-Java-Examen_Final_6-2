package examenfinal_frank_pulido_2;

import java.util.Arrays;

public class Estanteria {
	
	/*
	 * La estantería es una estructura vertical con un conjunto de bandejas. En cada bandeja existe un único tipo de producto;
	 * es decir, una único objeto de la Clase Bebida al que se le da un stock máximo en bandeja que depende de las dimensiones
	 * de la bandeja y del packaging del producto, pero esto último no es objeto del ejercicio.
	 */
	
	// Atributos de la estantería
	
	private int numBandejas;
	private int bandejasEstado[];
	
	
	// Constructor
	
	Estanteria (int numBandejas) {
		this.numBandejas = numBandejas;
		this.bandejasEstado = inicializarEstanteriaEstado(numBandejas);
	}
	
	
	// Getters
	
	public int getNumBandejas() {
		return this.numBandejas;
	}
	
	public int[] getBandejasEstado() {
		return this.bandejasEstado;
	}
	
	// Setters
	
	public void setNumBandejas (int numBandejas) {
		this.numBandejas = numBandejas;
	}
	
	public void setBandejasEstado(int bandejasEstado[]) {
		this.bandejasEstado = bandejasEstado;
	}
	
	
	// Metodo inicializar estanterias : Se inicializan en "-1" (vacías). Luego tomaran el atributo "id" de la bebida que las ocupe
	
	public int[] inicializarEstanteriaEstado(int numBandejas) {
		int[] newEstanteriaEstado = new int[numBandejas];
		Arrays.fill(newEstanteriaEstado, -1);
		/*
		for (int i = 0; i < numBandejas; i++) {
			newEstanteriaEstado [i] = -1;
		}
		*/
		return newEstanteriaEstado;
	}
	
}
