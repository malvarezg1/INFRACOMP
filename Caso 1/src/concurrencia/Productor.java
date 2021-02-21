package concurrencia;

public class Productor extends Thread {
	
	private int id;
	private Buffer buffer;
	private int max_productos;
	private char tipo;
	
	
	public Productor(int id, Buffer buffer, int max_productos, char tipo ) {
		this.id = id;
		this.buffer = buffer;
		this.max_productos = max_productos;
		this.tipo = tipo;
	}
	
	
	public void run() {
		
		for(int i = 0; i < max_productos; i++) {

			Producto prod = new Producto(tipo, "P" + id + "#" + i + "-" + tipo );
			buffer.almacenarSemiActiva(prod);
			System.out.println("Productor: " + id + " - Produjo: \t"+ prod.getRef() );	
		}

	}
}
