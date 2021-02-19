package Semaforos;

import java.util.LinkedList;

public class Semaforo {
	private int contador;
	private LinkedList<Object> lista;

	public Semaforo(int cont)
	{
		contador = cont;
		lista = new LinkedList<Object>();
	}

	public synchronized void p(){
		contador--;
		if(contador < 0 ) {
			Object o = new Object();
			lista.add(o);
			try {
				synchronized (o) {
					o.wait();
				}
			}
			catch(Exception e) {

			}
		}
	}


	public synchronized void v() {
		contador++;
		if( contador <= 0) {
			Object o = lista.poll();
			synchronized (o) {
				o.notify();	
			}
			
		}
		
	}

}
