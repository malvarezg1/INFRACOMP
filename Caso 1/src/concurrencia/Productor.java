package concurrencia;

public class Productor extends Thread {
	
	private int id;
	private Buffer buffer;
	private int max_productos;
	
	
	public Productor(int id, Buffer buffer, int max_productos ) {
		this.id = id;
		this.buffer = buffer;
		this.max_productos = max_productos;
	}
	
	
	public void run() {
		
		for(int i = 0; i < max_productos; i++) {
			
			Producto prod = new Producto('B');
			if(id % 2 == 0) {
				 prod = new Producto('A');
			}
			
			buffer.almacenar(prod);
			System.out.println("Productor: " + id + "- Producto # "+ i + " alamcenado");	
		}
	}
}
