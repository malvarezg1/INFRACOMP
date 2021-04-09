package concurrencia;

import java.util.ArrayList;

public class Productor extends Thread {

	private int id;
	private Buffer buffer;
	private int max_productos;
	private char tipo;
	private ArrayList<Producto> producidos;


	public Productor(int id, Buffer buffer, int max_productos, char tipo ) {
		this.id = id;
		this.buffer = buffer;
		this.max_productos = max_productos;
		this.tipo = tipo;
		this.producidos = new ArrayList<Producto>();
	}


	public void run() {
		for(int i = 0; i < max_productos; i++) {
			Producto prod = new Producto(tipo, "P" + id + "#" + i + "-" + tipo );
			buffer.almacenarSemiActiva(prod);
			if(prod != null) {
				producidos.add(prod); 
				System.out.println("Productor: " + id + " - Produjo: \t"+ prod.getRef() );	
			}
		}
	}

	public int getIdConsumidor() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Buffer getBuffer() {
		return buffer;
	}


	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}


	public int getMax_productos() {
		return max_productos;
	}


	public void setMax_productos(int max_productos) {
		this.max_productos = max_productos;
	}


	public char getTipo() {
		return tipo;
	}


	public void setTipo(char tipo) {
		this.tipo = tipo;
	}


	public void setProducidos(ArrayList<Producto> producidos) {
		this.producidos = producidos;
	}


	public ArrayList<Producto> getProducidos() {
		return producidos;
	}
}
