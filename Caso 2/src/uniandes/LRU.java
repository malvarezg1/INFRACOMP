package uniandes;

import java.util.Iterator;

public class LRU extends Thread{
	
	private Memoria memoria;
	
	
	public LRU(Memoria m) {
		this.memoria = m;
	}
	
	public void run() {
		while(!memoria.verificarSecuenciaVacia()) {
			if(!memoria.verificarColaVacia()) {
				memoria.corrimientosDerecha();
				while(!memoria.verificarColaVacia()) {
					int i = memoria.sacarColaReferencias();
					memoria.actualizarBitR(i);
				}
			}
			
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
