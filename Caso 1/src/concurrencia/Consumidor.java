package concurrencia;

import java.util.ArrayList;

public class Consumidor extends Thread {


	private int id;
	private Buffer buffer;
	private int max_productos;
	private ArrayList<Producto> consumidos;
	private char tipo;

	Consumidor(int id, Buffer buffer, int max_productos, char tipo) {
		this.id = id;
		this.buffer = buffer;
		this.max_productos = max_productos;
		this.consumidos = new ArrayList<Producto>();
		this.tipo = tipo;
	}

	public void run() {

		while(consumidos.size() < max_productos) {
			Producto p = buffer.retirarSemiActiva(tipo);
			if(p != null) {
			consumidos.add(p); 
			System.out.println("Consumido: " + id + " - Consumió: \t"+ p.getRef()  );	
			}
		}
		
	}

	public ArrayList<Producto> getConsumidos() {
		return consumidos;
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

	public void setConsumidos(ArrayList<Producto> consumidos) {
		this.consumidos = consumidos;
	}

}

