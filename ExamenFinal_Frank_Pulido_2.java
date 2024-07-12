package examenfinal_frank_pulido_2;

//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ExamenFinal_Frank_Pulido_2 {
	

	static ArrayList<Bebida> altaBebidas = new ArrayList<Bebida>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * El enunciado del Examen "insinuaba" que la creación de la Estantería de productos debía ser un Array.
		 * La razón :
		 * Decía que al incorporar un producto nuevo éste debía tomar la "primera posición libre". Los ArrayLists siempre incorporan
		 * los productos al final no en posiciones intermedias. Al eliminar un objeto en una posición intermedia el ArrayList se acorta
		 * y todas las posiciones de índice superior a la eliminada se reindexan. El Array en cambio vacía el valor de esta posición
		 * sin cambiar su tamaño ni reindexar ubicación de los objetos.
		 */
		
		
		// Declaramos e inicializamos variables
		
		byte opcionMenu = -1;
		int testigo = -1;
		int altaTienda = -1;
		int indexAlta = -1;
		int indexTienda = -1;
		int indexArrayList = -1;

		int altaStock = 0;
		int stock = 0;
		
		int id1 = -1;
		int id2 = -1;
		int cambiarIndex1 = -1;
		int cambiarIndex2 = -1;
		int posicionLineal1 = 0;
		//int posicionLineal2 = 0;
		int posicionEstanteria1 = 0;
		//int posicionEstanteria2 = 0;

		String respuesta = "";
		
		// Las 3 variables a continuación sirven para construir el lineal indicando
		int numEstanteriasLineal = 0;
		int numBandejas = 0;
		ArrayList<Integer> bandejasEstanterias = new ArrayList<Integer>();
		//String posicionesDisponibles = "";
		
		Scanner dataentry = new Scanner(System.in);
		
		
		
		/*
		 *  Lo primero : construir el LINEAL de la sección de Bebidas
		 *  
		 * Antes de presentar el menú de Usuario debemos \"inicializar\" la Tienda.
		 * Para empezar necesitamos construir el \"lineal\" en el que se almacenarán las Bebidas.
		 * El lineal es el conjunto de estanterías concatenadas a lo largo del pasillo de la sección Bebidas.
		 * El lineal será un ArrayList<Estanteria>. Su tamaño corresponderá con el número de estanterías.
		 * Cada estantería contendrá un determinado número de productos distintos almacenados sobre sus bandejas.
		 * 
		 * Queremos que los productos se asignen a una posición \"fija\" en su Estantería que no cambie al eliminar
		 * algún otro producto de alguna posición anterior.
		 * 
		 * Por esta razón la estantería será un Array y no un ArrayList. Aquí se abren 2 caminos :
		 * 1- Hacer un Array que nos indique si la posición está ocupada tomando el valor del ID del producto o "-1" si está libre.
		 * También definiré la posición en Lineal un como atributo del Objeto Bebida (horizontal : ArrayList / vertical : Array).
		 * 2- Otro camino es que el Array almacene directamente los objetos de Clase Bebida... (no usamos Arrays de Objetos en clase).
		 * 
		 * Yo opté por la opción 1. Un ArrayList<Estanteria>, siendo la Estantería un Array de enteros (int ID o el valor "-1"). Aunque
		 * parezca redundante, será muy útil. Entre otras cosas yo podría redimensionar el Lineal y vaciar todos los Objetos Bebida
		 * en el recién construido Lineal asignándoles nuevas posiciones y manteniendo el stock.
		 * 
		 * En un estantería puedes tener un único producto de muy alta rotación (el agua de la marca blanca de la Tienda),
		 * o puedes tener varios productos de algún tipo del que se demanda variedad (ej : cervezas artesanas).
		 * Por este motivo, los Array que conforman los elementos del ArrayList podrán tener distinto tamaño.
		 */
		
		System.out.println();
		System.out.println("Vamos a abrir una nueva Tienda de Bebidas. Necesitamos comenzar por construir el Lineal.");
		System.out.println("Cuantas estanterías conformarán el lineal?");
		numEstanteriasLineal = dataentry.nextInt();
		for (int i = 0; i < (numEstanteriasLineal); i++) {
			System.out.println("Número de productos diferentes (cualquier cantidad) a colocar en la Estantería " + (i + 1) + " :");
			numBandejas = dataentry.nextInt();
			bandejasEstanterias.add(numBandejas);
		}
		
		Lineal linealBebidas = new Lineal (numEstanteriasLineal, bandejasEstanterias);
		
		System.out.println();
		System.out.println();
		System.out.println("Así ha quedado diseñado el Lineal de Bebidas :");
		System.out.println();
		System.out.println(linealBebidas.mapearLineal());
		System.out.println();
		System.out.println(linealBebidas.contenidoLineal());
		System.out.println();
		
		/*
		 * Ya hemos construido el lineal tomando los parámetros del Jefe de Tienda con el Scanner a través de la App.
		 * 
		 * Ahora daremos de alta una serie de Bebidas para hacer pruebas. Se almacenarán en un static ArrayList<Bebidas>
		 * para tener las altas siempre disponibles en el MAIN, aún cuando no estén en stock en Tienda. Al dar de baja en Tienda
		 * los productos continuarán en el main (mismo objeto coexiste en 2 ArrayLists), pero con reSET de stock (0) y coordenadas
		 * en Lineal (-1 ArrayList horizontal, -1 en Array vertical).
		 * Existen productos de temporada y también "bundles" que no están permanente en Tienda sino durante promociones. Estos productos
		 * no deben elimarse nunca del ArrayList del main : ArrayList<Bebida> altaBebidas.  
		 */
		
		BebidaAzucarada cocacola = new BebidaAzucarada(2, 3.50f, "Coca Cola", 0, -1, -1, 3, false);
		BebidaAzucarada fanta = new BebidaAzucarada(2, 3.75f, "Fanta", 0, -1, -1, 2, false);
		BebidaAgua fontvella = new BebidaAgua(2, 4f, "Fontvella", 0, -1, -1, "Viladrau");
		BebidaAgua boix = new BebidaAgua(2, 3f, "Boix", 0, -1, -1, "Malavella");
		
		altaBebidas.add(cocacola);
		altaBebidas.add(fanta);
		altaBebidas.add(fontvella);
		altaBebidas.add(boix);
		
		// Ahora crearemos la Tienda
		
		ArrayList<Bebida> bebidasTienda1 = new ArrayList<Bebida>();
		Tienda tienda1 = new Tienda (linealBebidas, bebidasTienda1);
		
		System.out.println("El sistema ha creado automáticamente una Tienda y dado de Alta " + altaBebidas.size() + " bebidas en el "
				+ "sistema que usted puede dar de alta en la Tienda, asignándoles un Stock y una posición en el Lineal.\n"
				+ "Ahora le presentaremos las Bebidas de Alta y el Menú de Usuario.\n");
		
		System.out.println(bebidasSistema());

		
		// Menu de Usuario
		
		do {
			System.out.println(menuUsuario());
			opcionMenu = dataentry.nextByte();
			
			switch (opcionMenu) {
			
				case 1 :
					
					System.out.println();
					System.out.println("Usted ha seleccionado listar productos en el Sistema.");
					System.out.println();
					for (Bebida bebida : altaBebidas) {
						System.out.println(bebida.toString() + "\n");
					}
					
				break;
				
				case 2 :
					
					System.out.println();
					System.out.println("Usted ha seleccionado listar productos en stock en la Tienda.");
					System.out.println();

					if (tienda1.getBebidas().size() == 0) {
						System.out.println("Aún no se ha dado de alta ningún stock en Tienda. Use la opción 4.");
					} else {
						for (Bebida bebida : tienda1.getBebidas()) {
							System.out.println(bebida.toString() + "\n");
						}
					}
					
				break;
				
				case 3 :
					
					System.out.println();
					System.out.println("Usted ha seleccionado ver disponibilidad de espacio en el lineal de Bebidas de la Tienda.");
					System.out.println();
					System.out.println(tienda1.getLineal().mapearLineal());
					System.out.println();
					System.out.println(tienda1.getLineal().contenidoLineal());
					System.out.println();

					
				break;
				
				case 4 :
					
					// No estamos creando productos nuevos sino incorporando a la Tienda productos existentes en el sistema
					testigo = 0;
					System.out.println();
					System.out.println("Usted ha seleccionado dar de alta Producto en Tienda.");
					
					if (testigo == altaBebidas.size() - 1) {
						System.out.println("Lo sentimos, el sistema indica que TODOS los productos de alta en el sistema ya han sido\n"
								+ " dados de alta en Tienda.");
					} else if (tienda1.getLineal().espacioLineal() == 0) {
						System.out.println("Aún existen productos no dados de alta en tienda, pero no existe espacio en el Lineal\n"
								+ "de la sección de Bebidas.");
					} else {
						System.out.println("Productos en el sistema aún no stockados en Tienda :\n");
						System.out.println();
						
						for (int i = 0; i < altaBebidas.size(); i++) {
							if (!tienda1.getBebidas().contains(altaBebidas.get(i))) {
								System.out.println(altaBebidas.get(i).toString());
								System.out.println();
							} else {
								testigo++;
							}
						}
						
						System.out.println("Por favor, indíquenos el ID de la Bebida a dar de Alta en Tienda :");
						altaTienda = dataentry.nextInt();
						indexAlta = buscarIndexAlta(altaTienda);
						
						if (indexAlta == -1) {
							System.out.println("El ID [" + altaTienda + "] no es válido");
							
						} else if (tienda1.getBebidas().contains(altaBebidas.get(indexAlta))) {
							System.out.println("El producto seleccionado ya está de Alta en Tienda");
							
						} else {	
							tienda1.altaProductosTienda(altaBebidas.get(indexAlta));
							
							System.out.println("El producto Bebida ha sido dado de Alta. Debe indicar el stock :");
							altaStock = dataentry.nextInt();
							while (altaStock <= 0) {
								System.out.println("El Alta en Tienda genera un pedido al almacén Central, debe seleccionar una "
										+ "cantidad de pedido válida.");
								altaStock = dataentry.nextInt();
							}
							
							indexTienda = tienda1.buscarIndex(altaTienda);
							System.out.println();

							tienda1.getBebidas().get(indexTienda).setStock(altaStock);
							
							System.out.println("Procedemos a asignar una posición en Lineal de forma automática que corresponderá con "
									+ "la primera posición libre del Lineal.\n"
									+ "Puede modificarla a través de la Opción 5 del Menú de Usuario.\n");
							respuesta = tienda1.asignarPosicionAuto(altaTienda);
							System.out.println(respuesta + "\nStock en Tienda : " + tienda1.getBebidas().get(indexTienda).getStock());
						}
					}
					
				break;
				
				case 5 :
					
					System.out.println();
					System.out.println("Usted ha seleccionado cambiar posición en Lineal de una Bebida.");
					System.out.println();
					
					if (tienda1.getBebidas().size() == 0) {
						System.out.println("Aún no se ha dado de alta ningún stock en Tienda. Use la opción 4.");
					}
					else if (tienda1.getLineal().espacioLineal() == 0) {
						System.out.println("No existe ninguna posición libre en el Lineal. Sólo puede intercambiar posición con otra bebida\n"
								+ "(opción 6 del Menú de Usuario).");
					}
					
					else {
						testigo = -1;
						while (testigo == -1) {
							System.out.println(tienda1.showProductosTienda());
							System.out.println();
							System.out.println("Seleccione el ID de la Bebida a cambiar de posición en Lineal");
							indexTienda = dataentry.nextInt();
							testigo = tienda1.buscarIndex(indexTienda);

							if (testigo == -1) { System.out.println("Seleccione una Bebida en Stock, por favor."); }
						}
						System.out.println();
						System.out.println("Estas son las posiciones disponibles en el Lineal :");
						System.out.println();
						System.out.println(tienda1.getLineal().mapearLineal());
						System.out.println();
						System.out.println(tienda1.getLineal().contenidoLineal());
						System.out.println();
						System.out.println("Por favor, escoja la posición de la Estantería en el Lineal :");
						posicionLineal1 = dataentry.nextInt();
						System.out.println("Por favor, escoja la posición de la Bebida en la Estantería " + posicionLineal1 + " : ");
						posicionEstanteria1 = dataentry.nextInt();
						
						System.out.println(tienda1.escogerPosicion(indexTienda, posicionLineal1, posicionEstanteria1));
						System.out.println();
						System.out.println(tienda1.getLineal().mapearLineal());
					}
					
				break;
				
				case 6 :
					
					System.out.println();
					System.out.println("Usted ha seleccionado Intercambiar de posición de 2 productos del Lineal de Bebidas.");
					System.out.println("Productos en stock en Tienda :");
					System.out.println();
					System.out.println(tienda1.getLineal().mapearLineal());
					System.out.println();
					System.out.println(tienda1.showProductosTienda());
					do {
						System.out.println("Seleccione la PRIMERA Bebida a cambiar de posición en Lineal");
						id1 = dataentry.nextInt();
						cambiarIndex1 = tienda1.buscarIndex(id1);
					} while (cambiarIndex1 == -1);
					do {
						System.out.println("Seleccione la SEGUNDA Bebida a cambiar de posición en Lineal");
						id2 = dataentry.nextInt();
						cambiarIndex2 = tienda1.buscarIndex(id2);
					} while (cambiarIndex2 == -1);
					System.out.println();

					System.out.println(tienda1.swapPosicion(id1, id2));;

				break;
				
				case 7 :
					
					testigo = 0;
					id1 = -1;
					indexTienda = -1;
					respuesta = "";
					System.out.println();
					System.out.println("Usted ha seleccionado Activar Promoción a Bebida Azucarada (-10%)");
					
					for (Bebida bebida : tienda1.getBebidas()) {
						if (bebida instanceof BebidaAzucarada) {
							testigo++;
							respuesta = respuesta + bebida.toString() + "\n" + "\n";
						}
					}
					
					if (testigo == 0) {
						System.out.println("Actualmente no existen bebidas azucaradas en stock en Tienda.");
					} else {
						System.out.println("Las Bebidas Azucaradas en stock son las siguientes :");
						System.out.println();
						System.out.println(respuesta);
						do {
							System.out.println("Por favor, indique el ID de la Bebida para la que desea activar la promoción :");
							id1 = dataentry.nextInt();
							indexTienda = tienda1.buscarIndex(id1);
						} while (indexTienda == -1);
						
						if (tienda1.getBebidas().get(indexTienda) instanceof BebidaAzucarada) {
							((BebidaAzucarada)tienda1.getBebidas().get(indexTienda)).setPromocion(true);
							System.out.println("A la bebida azucarada con ID: " + id1 + " se le ha activado un descuento del 10%.");
						} else {
							System.out.println("La bebida indicada no es de clase \"azucarada\" por lo que no aplica el descuento.");
						}
					}
					
				break;
				
				case 8 :
					
					indexTienda = -1;
					altaStock = 0;
					stock = 0;
					System.out.println();
					System.out.println("Usted ha seleccionado Añadir Stock de producto.");
					System.out.println("Estos son los productos en Tienda, si no está en el listado debe antes darlo de Alta a través de\n"
							+ "la opción 4 del Menú de Usuario");
					System.out.println();
					System.out.println(tienda1.showProductosTienda());
					
					do {
						System.out.println("Seleccione el ID del producto para el que quiere incrementar el stock :");
						id1 = dataentry.nextInt();
						indexTienda = tienda1.buscarIndex(id1);
						if (indexTienda == -1) { System.out.println("Debe seleccionar algún ID de producto existente en Tienda."); }
					} while (indexTienda == -1);
					
					do {
						System.out.println("Seleccione la cantidad en la que se debe incrementar el stock de ID" + id1 + " :");
						altaStock = dataentry.nextInt();
						if (altaStock < 0) { System.out.println("No se puede reducir artificialmente el Stock."); }
					} while (altaStock <= 0);
					
					stock = tienda1.getBebidas().get(indexTienda).getStock();
					stock = stock + altaStock;
					tienda1.getBebidas().get(indexTienda).setStock(stock);
					
					System.out.println();
					System.out.println("Se ha modificado el Stock del producto ID" + id1 + " :");
					System.out.println(tienda1.getBebidas().get(indexTienda).toString());
					
				break;
				
				case 9 :
					
					System.out.println();
					System.out.println("Usted ha seleccionado LIQUIDAR producto en Tienda.");
					System.out.println("Estos son los productos en Tienda, si no está en el listado no es necesario liquidar Stock.");
					System.out.println();
					System.out.println(tienda1.showProductosTienda());
					
					do {
						System.out.println("Seleccione el ID del producto para el que quiere liquidar el stock :");
						id1 = dataentry.nextInt();
						indexTienda = tienda1.buscarIndex(id1);
						if (indexTienda == -1) { System.out.println("Debe seleccionar algún ID de producto existente en Tienda."); }
					} while (indexTienda == -1);
					
					System.out.println(tienda1.liquidarProductosTienda(id1));
					System.out.println(tienda1.getLineal().mapearLineal());

				break;
				
				case 10 :
					opcionMenu = -1;
					System.out.println();
					System.out.println("Usted ha seleccionado valorar el stock de producto (bebidas) en Tienda.");
					System.out.println("Así está organizado el Lineal de bebidas de la Tienda :\n");
					System.out.println(tienda1.getLineal().mapearLineal());

					do {
						System.out.println(subMenuValoracionInventario());
						opcionMenu = dataentry.nextByte();
						if (opcionMenu < 1 || opcionMenu > 3) {System.out.println("Debe seleccionar una opción válida.\n");}
					} while (opcionMenu < 1 || opcionMenu > 3);
					
						switch (opcionMenu) {
						
						case 1 :
							int id = -1;
							int indexBebida = -1;
							System.out.println();
							do {
								System.out.println("Seleccione el PRODUCTO de cuyo inventario desea conocer el valor. Debe indicar alguno de"
										+ "los ID del mapa mostrado anteriormente.");
								id = dataentry.nextInt();
								indexBebida = tienda1.buscarIndex(id);
								if (indexBebida == -1) { System.out.println("Debe seleccionar un ID de producto en stock en Tienda."); }
							} while (indexBebida == -1);
							
							System.out.println("El valor del inventario de la Bebida con ID" + id + " es : "
									+ tienda1.valorStockProducto(id) + " €.\n");
							
							break;

						case 2 :
							indexArrayList = -1;
							System.out.println();
							do {
								System.out.println("Seleccione la ESTANTERÍA de cuyo inventario desea conocer el valor. Debe indicar alguna de"
										+ "las columnas del mapa mostrado anteriormente (0 al " +
										(tienda1.getLineal().getNumEstanteriasLineal() - 1) + ").");
								indexArrayList = dataentry.nextInt();
								if (indexArrayList < 0 || indexArrayList > (tienda1.getLineal().getNumEstanteriasLineal() - 1)) {
									System.out.println("Debe seleccionar una opción válida.");
								}
							} while (indexArrayList < 0 || indexArrayList > (tienda1.getLineal().getNumEstanteriasLineal() - 1));
							System.out.println("El valor del inventario en la Estantería " + indexArrayList + " : "
									+ tienda1.valorStockEstanteria(indexArrayList) + " €.\n");

							break;
							
						case 3 :
							System.out.println();
							System.out.println("El valor TOTAL del inventario de Bebidas en stock : " + tienda1.valorStockTienda() + " €.\n");
							
							break;
						}
					
				break;
				
				case 0 :
					
					System.out.println();
					System.out.println("Usted ha seleccionado Salir del Sistema. Cerramos su sesión de Usuario.");
					
				break;
				
				default :
					
					System.out.println();
					System.out.println("Debe seleccionar una opción válida.");
					
				break;
			
			}
				
		} while (opcionMenu != 0);
		
		
		dataentry.close();

	}
	
	
	// Método Menu de Usuario

	public static String menuUsuario() {
		String menu = "";
		menu = "\nBienvenidos al Menú de Usuario de la ITienda de Bebidas.\n"
				+ "1- Ver productos de la central de Compras.\n"
				+ "2- Ver productos en Tienda.\n"
				+ "3- Ver disponibilidad de espacio en el Lineal de Bebidas.\n"
				+ "4- Dar de alta producto en tienda.\n"
				+ "5- Cambiar posición de producto en Lineal de Bebidas.\n"
				+ "6- Intercambiar de posición de 2 productos del Lineal de Bebidas.\n"
				+ "7- Activar promoción bebida azucarada.\n"
				+ "8- Añadir stock de producto.\n"
				+ "9- Liquidar stock de producto y dar de baja en Tienda.\n"
				+ "10 - Valoración de inventario en Tienda.\n"
				+ "0- Salir del sistema.\n\n"
				+ "Indique su opción a continuación :";
		
		return menu;
		
	}
	
	public static String subMenuValoracionInventario() {
		String subMenuValoracion = "";
		subMenuValoracion = "A continuación puede elegir una de las siquientes opciones :\n"
				+ "1- Valor del inventario de un determinado producto (bebida).\n"
				+ "2- Valor total del producto (bebidas) en una determinada estantería.\n"
				+ "3- Valor total del producto en stock (todas las bebidas).";
		
		return subMenuValoracion;
	}
	
	public static String bebidasSistema() {
		String listado = "";
		for (int i = 0; i < altaBebidas.size(); i++) {
			listado = listado + altaBebidas.get(i).toString() + "\n\n";
		}
		return listado;
	}
	
	public static int buscarIndexAlta(int id) {
		int indexAlta = -1;
		int testigo = 0;
		for (int i = 0; testigo == 0 || i < altaBebidas.size(); i++) {
			if (id == altaBebidas.get(i).getId()) {
				testigo = -1;
				indexAlta = i;
			}
		}
		return indexAlta;
	}

}
