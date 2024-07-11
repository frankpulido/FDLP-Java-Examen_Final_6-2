package examenfinal_frank_pulido_2;

public class BebidaAgua extends Bebida {
	
	// Atributos de la sub clase
	
	private String origen;
	
	
	// Constructor
	//BebidaAgua (int id, int contador, int litros, float precio, String marca, int stock, int indexArrayList, int indexArray, String origen) {

	BebidaAgua (int litros, float precio, String marca, int stock, int indexArrayList, int indexArray, String origen) {
		super(litros, precio, marca, stock, indexArrayList, indexArray);
		this.origen = origen;
	}
	
	// Getters
	
	public String getOrigen() {
		return this.origen;
	}
	
	
	// Setters
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	
	// Método toString()
	
	public String toString() {
		return "Bebida ID : " + super.getId() + ", Litros : " + super.getLitros() + ", Precio : "
				+ "" + super.getPrecio() + ", Marca : " + super.getMarca() + "\nOrigen : " + this.origen + ".\n"
						+ "Stock en tienda = " + super.getStock() + "\n" +
						((super.getStock() == 0)? "" : "Posición en Lineal : " + super.getIndexArrayList() + ". Posición en "
								+ "Estantería : " + super.getIndexArray());
						
	}

}