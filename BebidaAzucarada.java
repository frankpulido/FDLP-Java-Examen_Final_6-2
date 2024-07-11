package examenfinal_frank_pulido_2;

public class BebidaAzucarada extends Bebida {
	
	// Atributos de la sub clase
	
	private int porcentaje;
	private boolean promocion;
	
	// Constructor
	
	BebidaAzucarada (int litros, float precio, String marca, int stock, int indexArrayList, int indexArray, int porcentaje, boolean promocion) {
		super(litros, precio, marca, stock, indexArrayList, indexArray);
		this.porcentaje = porcentaje;
		this.promocion = false;
	}
	
	// Getters
	
	public int getPorcentaje() {
		return this.porcentaje;
	}
	
	public boolean getPromocion() {
		return this.promocion;
	}
	
	
	// Setters
	
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public void setPromocion(boolean promocion) {
		this.promocion = promocion;
	}
	
	
	public String toString() {
		String promo = (this.promocion)? "10%" : "No";
		return "Bebida ID : " + super.getId() + ", Litros : " + super.getLitros() + ", Precio : "
				+ "" + super.getPrecio() + ", Marca : " + super.getMarca() + ".\n"
						+ "Porcentaje de azúcar : " + this.porcentaje + "%.\n"
								+ "Promoción : " + promo + ".\n"
										+ "Stock en tienda = " + super.getStock() + "\n" +
										((super.getStock() == 0)? "" : "Posición en Lineal : " + super.getIndexArrayList() + ". Posición en "
												+ "Estantería : " + super.getIndexArray());
	}

}