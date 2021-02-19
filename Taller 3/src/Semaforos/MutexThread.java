package Semaforos;

public class MutexThread extends Thread {

	private char tipo;
	private Mutex mutex;
	
	public MutexThread(char tipo, Mutex mutex ) {
		this.tipo = tipo;
		this.mutex = mutex;
	}
	
	
	
	public void run(){
		if(tipo=='A') {
			for (int i = 0; i < 10 ; i++) {
				mutex.a();
			}
			
		}
		else {
			for (int i = 0; i < 10 ; i++) {
				mutex.b();
			}
		}
	}
	
}
