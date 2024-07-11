package examenfinal_frank_pulido_2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tienda {
	
	/*
	 * La Tienda está conformada por 2 atributos :
	 * 1- Un ArrayList de Bebidas EXISTENTES en la tienda que no son necesariamente
	 * todas las bebidas que tenemos de alta en el sistema.
	 * 2- Un Lineal que hemos construido en el main como un ArrayList que contiene Arrays de tipo boolean.
	 * Es una matriz : index en ArrayList nos ubica horizontalmente e index en Array nos ubica verticalmente.
	 * 
	 * Las bebidas tienen como atributos su posición en el ArrayList y su posición en el Array.
	 * De esta forma podemos mapear con booleanos que bandejas están libres dentro de la matriz que es el Lineal
	 */
	
	// Atributos de la tienda
	
	private Lineal lineal;
	private ArrayList<Bebida> bebidas;
	
	
	// Contructor
	
	Tienda (Lineal lineal, ArrayList<Bebida> bebidas) {
		this.lineal = lineal;
		this.bebidas = bebidas;
	}
	
	
	// Getters
	
	public Lineal getLineal() {
		return this.lineal;
	}
	
	public ArrayList<Bebida> getBebidas() {
		return this.bebidas;
	}
	
	// Setters
	
	public void setLineal (Lineal lineal) {
		this.lineal = lineal;
	}
	
	public void setBebidas(ArrayList<Bebida> bebidas) {
		this.bebidas = bebidas;
	}
	
	
	
	// Método alta productos
	
	public void altaProductosTienda(Bebida bebida) {
		this.bebidas.add(bebida);
	}
	
	
	
	// Método LIQUIDAR producto : elimina inventario, libera posición en Lineal y se elimina del ArrayList<Bebida> bebidas
	
	public String liquidarProductosTienda(int id) {
		
		String reply = "";
		int indexBebida = buscarIndex(id);
		this.bebidas.get(indexBebida).setStock(0);
		
		int posicionLineal = this.bebidas.get(indexBebida).getIndexArrayList();
		int posicionEstanteria = this.bebidas.get(indexBebida).getIndexArray();

		Array.set(this.lineal.getEstanteriasLineal().get(posicionLineal).getBandejasEstado(), posicionEstanteria, -1);
		
		this.bebidas.get(indexBebida).setIndexArrayList(-1);
		this.bebidas.get(indexBebida).setIndexArray(-1);
		this.bebidas.remove(indexBebida);
		
		reply = "El producto con ID:" + id + " ha sido liquidado y eliminado del sistema de la Tienda.\n"
				+ "Puede volver a darlo de alta en el futuro a través de la opción 4 del Menú de Usuario.\n";
		
		return reply;
		
	}
	
	
	
	// Método listar productos en Tienda
	
	
	public String showProductosTienda() {
		String reply = "";
		if (this.bebidas.size() == 0) {
			reply = "Aún no se ha dado de alta ningún stock en Tienda. Use la opción 4 del Menú de Usuario.";
		} else {
			for (int i = 0; i < this.getBebidas().size(); i++) {
				reply = reply + this.getBebidas().get(i).toString() + "\n\n";
			}
		}
		return reply;
	}
	
	
	
	// Método asignación automática de posición en Lineal
	
	public String asignarPosicionAuto(int id) {
		String reply = "";
		int testigo = 0;
		int testigo1 = 0; // Nos dará la posición de la Estantería en el Lineal.
		int testigo2 = 0; // Nos dará la posición de la bebida en la estantería.
		int indexBebida = -1;
		for (int j = 0; testigo == 0 && j < this.getLineal().getEstanteriasLineal().size(); j++) {
			testigo1 = j;
			for (int i = 0; testigo == 0 && i < this.getLineal().getEstanteriasLineal().get(j).getNumBandejas(); i++) {
				if ((int) Array.get(this.getLineal().getEstanteriasLineal().get(j).getBandejasEstado(), i) == -1) {
					testigo++;
					testigo2 = i;
				}
			}
		}
		System.out.println(testigo1 + " es su posición en ArrayList Lineal : posición Estantería en Lineal.");
		System.out.println(testigo2 + " es su posición en Array Estantería : posición Bebida en Estantería.");
		
		// A continuación definimos nuevos atributos de la Bebida (posición en Lineal, 2 coordenadas)
		indexBebida = this.buscarIndex(id);
		this.bebidas.get(indexBebida).setIndexArrayList(testigo1);
		this.bebidas.get(indexBebida).setIndexArray(testigo2);
		
		// También vamos a indicar en el Lineal (mismas coordenadas) el id del producto que la ocupa.
		Array.set((this.lineal.getEstanteriasLineal().get(testigo1).getBandejasEstado()), testigo2, id);

		reply = "A la Bebida se le ha asignado la Estantería " + testigo1 + ", posición " + testigo2 + " del Lineal.";
		return reply;
	}
	
	
	
	// Método escoger posición en Lineal
	
	public String escogerPosicion (int id, int newIndexArrayList, int newIndexArray) {
		String reply = "";
		int index = this.buscarIndex(id);
		int indexArrayList = this.bebidas.get(index).getIndexArrayList();
		int indexArray =  this.bebidas.get(index).getIndexArray();
		if (newIndexArrayList < 0 || newIndexArray < 0) {
			reply = "Las posiciones de las Estanterías en el Lineal y las posiciones de las Bebidas en las Estanterías deben ser\n"
					+ "números enteros mayores o iguales a cero";
		} else if (newIndexArrayList > this.lineal.getNumEstanteriasLineal() - 1) {
			reply = "La nueva posición indicada de Estantería en el Lineal supera a la de la última Estantería.";
		} else if (newIndexArray > this.lineal.getBandejasEstanterias().get(newIndexArrayList) - 1) {
			reply = "La nueva posición indicada de la Bebida en Estantería supera la posición máxima.";
		} else {
			if (this.checkLibre(newIndexArrayList, newIndexArray) == -1) {
				Array.set(this.lineal.getEstanteriasLineal().get(indexArrayList).getBandejasEstado(), indexArray, -1);
				this.getBebidas().get(index).setIndexArrayList(newIndexArrayList);
				this.getBebidas().get(index).setIndexArray(newIndexArray);
				Array.set(this.lineal.getEstanteriasLineal().get(newIndexArrayList).getBandejasEstado(), newIndexArray, id);
				reply = "Se ha efectuado el cambio de posición en el Lineal";
			} else {
				reply = "Debe escoger una posición que no esté ocupada. Puede optar por la opción 6 del Menú de Usuario.";
			}
		}
		return reply;
	}
	
	// Obtener estado de una posición  : -1 es "libre", de lo contrario retorna un ID
	
	public int checkLibre (int indexArrayList, int indexArray) {
		int free = -1;
		free = (int) Array.get(this.lineal.getEstanteriasLineal().get(indexArrayList).getBandejasEstado(), indexArray);
		return free;
	}
	
	
	
	// Método intercambiar posición de 2 productos en Lineal

	public String swapPosicion (int id1, int id2) {
		
		String reply = "";
		int posicionLineal1 = -1;
		int posicionLineal2 = -1;
		int posicionEstanteria1 = -1;
		int posicionEstanteria2 = -1;
		int cambiarIndex1 = this.buscarIndex(id1);
		int cambiarIndex2 = this.buscarIndex(id2);
		posicionLineal1 = this.getBebidas().get(cambiarIndex1).getIndexArrayList();
		posicionEstanteria1 = this.getBebidas().get(cambiarIndex1).getIndexArray();
		posicionLineal2 = this.getBebidas().get(cambiarIndex2).getIndexArrayList();
		posicionEstanteria2 = this.getBebidas().get(cambiarIndex2).getIndexArray();
		
		this.getBebidas().get(cambiarIndex1).setIndexArrayList(posicionLineal2);
		this.getBebidas().get(cambiarIndex1).setIndexArray(posicionEstanteria2);
		this.getBebidas().get(cambiarIndex2).setIndexArrayList(posicionLineal1);
		this.getBebidas().get(cambiarIndex2).setIndexArray(posicionEstanteria1);
		
		Array.set((this.lineal.getEstanteriasLineal().get(posicionLineal1).getBandejasEstado()), posicionEstanteria1, id2);
		Array.set((this.lineal.getEstanteriasLineal().get(posicionLineal2).getBandejasEstado()), posicionEstanteria2, id1);
		
		reply = "Las posiciones se han intercambiado. Este es el nuevo mapa del Lineal :\n" + this.lineal.mapearLineal();
		return reply;
		
	}
	
	
	
	// Método valorar Stock de 1 única bebida
	
	public float valorStockProducto(int id) {
		int index = -1;
		float valorStockProducto = 0f;
		int stock = 0;
		float precio = 0f;
		
		index = buscarIndex(id);
		stock = this.getBebidas().get(index).getStock();
		precio = this.getBebidas().get(index).getPrecio();
		valorStockProducto = stock * precio;
		
		return valorStockProducto;
	}
	
	
	
	// Método valorar Stock en 1 única Estantería
	
	public float valorStockEstanteria(int indexArrayList) {
		float valorStockEstanteria = 0f;
		int stock = 0;
		float precio = 0f;
		int id = -1;
		int index = -1;
		
		int numMaxProductos = this.getLineal().getBandejasEstanterias().get(indexArrayList);
		
		for (int i = 0; i < numMaxProductos; i++) {
			id = (int) Array.get(this.getLineal().getEstanteriasLineal().get(indexArrayList).getBandejasEstado(), i);
			if (id != -1) {
				index = buscarIndex(id);
				stock = this.getBebidas().get(index).getStock();
				precio = this.getBebidas().get(index).getPrecio();
				valorStockEstanteria = valorStockEstanteria + (stock * precio);
			}
		}
		return valorStockEstanteria;
	}
	
	
	
	// Método valorar Stock TOTAL de la tienda
	
	public float valorStockTienda () {
		float valorStockTienda = 0f;
		int stock = 0;
		float precio = 0f;
		for (int i = 0; i < this.getBebidas().size(); i++) {
			stock = this.getBebidas().get(i).getStock();
			precio = this.getBebidas().get(i).getPrecio();
			valorStockTienda = valorStockTienda + (stock * precio);
		}
		return valorStockTienda;
	}
	
	
	
	// Método buscarIndex Bebida en ArrayList Bebidas
	
	public int buscarIndex (int id) {
		int indexBebida = -1;
		int testigo = -1;
		for (int i = 0; testigo == -1 || i < this.bebidas.size(); i++) {
			if (id == this.bebidas.get(i).getId()) {
				testigo++;
				indexBebida = i;
			}
		}
		return indexBebida;
	}
	

}
