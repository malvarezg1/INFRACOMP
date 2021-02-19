package concurrencia;

import java.util.ArrayList;

public class Buffer {
	
	private int capacidad;
	private ArrayList<Producto> buff;
	Object lleno, vacio;
	
	
	public Buffer(int capacidad) {
		this.capacidad = capacidad;
		buff = new ArrayList();
		lleno = new Object();
		vacio = new Object();
	}

	
	public void  almacenar(Producto prod) {
		
		if( buff.size() == capacidad) {
			//lleno.wait();
		}
		
		synchronized (this) {
			buff.add(prod);
		}
		
		
	}
}
