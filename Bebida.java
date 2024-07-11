package examenfinal_frank_pulido_2;

public class Bebida {
	
	// Atributos de la super clase
	
	private int id;
	static private int contador = 1;
	private int litros;
	private float precio;
	private String marca;
	private int stock;
	private int indexArrayList;
	private int indexArray;
	
	
	// Constructor
	
	
	Bebida (int litros, float precio, String marca, int stock) {
		this.id = contador;
		this.contador++;
		this.litros = litros;
		this.precio = precio;
		this.marca = marca;
		this.stock = 0;
		this.indexArrayList = -1;
		this.indexArray= -1;
	}
	
	
	Bebida (int litros, float precio, String marca, int stock, int indexArrayList, int indexArray) {
		this.id = contador;
		this.contador++;
		this.litros = litros;
		this.precio = precio;
		this.marca = marca;
		this.stock = 0;
		this.indexArrayList = -1;
		this.indexArray= -1;
	}
	
	
	// Métodos get o getters
	
	public int getId() {
		return this.id;
	}
	
	/*
	public int getContador() {
		return this.contador;
	}
	*/
	
	public int getLitros() {
		return this.litros;
	}
	
	public float getPrecio() {
		return this.precio;
	}
	
	public String getMarca() {
		return this.marca;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public int getIndexArrayList() {
		return this.indexArrayList;
	}
	
	public int getIndexArray() {
		return this.indexArray;
	}
	
	
	// Métodos set o setters
	
	/*
	public void setId(int id) {
		this.id = id;
	}
	
	public void setContador(int contador) {
		this.contador = contador;
	}
	*/
	
	public void setLitros(int litros) {
		this.litros = litros;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void setIndexArrayList(int indexArrayList) {
		this.indexArrayList = indexArrayList;
	}
	
	public void setIndexArray(int indexArray) {
		this.indexArray = indexArray;
	}
	
	
	//
	
	public String toString() {
		return "Bebida ID : " + this.id + ", Litros : " + this.litros + ", Precio : "
				+ "" + this.precio + ", Marca : " + this.marca + "."
						+ "\nStock en tienda = " + this.stock;
	}

}
