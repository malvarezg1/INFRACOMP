package concurrencia;

import java.util.ArrayList;

public class Buffer {

	private int capacidad;
	private ArrayList<Producto> buff;
	Object lleno, vacio;
	private int posicion;



	public Buffer(int capacidad) {
		this.capacidad = capacidad;
		buff = new ArrayList();
		lleno = new Object();
		vacio = new Object();
		posicion = 0;

	}


	public void  almacenar(Producto prod) {

		while(posicion == capacidad) {
			Thread.yield();
		}

		synchronized (this) {
			buff.add(prod);
			posicion++;
		}	
	}

	public Producto retirar() {

		while( posicion == 0) {
			Thread.yield();
		}
		
		synchronized (this) {
			Producto prod = buff.get(posicion-1);
			posicion--;
			return prod;
		}	
	}
}