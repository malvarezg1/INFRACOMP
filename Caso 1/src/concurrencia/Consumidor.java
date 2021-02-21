package concurrencia;

import java.util.ArrayList;

public class Consumidor extends Thread {

	private int id;
	private Buffer buffer;
	private int max_productos;
	private ArrayList<Producto> consumidos;

	Consumidor(int id, Buffer buffer, int max_productos) {
		this.id = id;
		this.buffer = buffer;
		this.max_productos = max_productos;
		this.consumidos = new ArrayList<Producto>();
	}

	public void run() {

		for(int i = 0; i < max_productos; i++) {

			consumidos.add(buffer.retirar());
			System.out.println("Consumidor: " + id + "- Producto # "+ i + " consumido");	
		}
	}

	public ArrayList<Producto> getConsumidos() {
		return consumidos;
	}
}

