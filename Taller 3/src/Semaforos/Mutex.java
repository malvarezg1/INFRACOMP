package Semaforos;

public class Mutex extends Thread{
	
	private static int cont = 0;
	private static Semaforo semaforo;
	
	
	public static void main(String[] args) {
		
		Mutex mutex = new Mutex();
		
		semaforo = new Semaforo(1);
		MutexThread a = new MutexThread('A', mutex);
		MutexThread b = new MutexThread('B', mutex);

		int orden = (int) (Math.random() * 100) % 2;
		if (orden == 0) {
		System.out.println("Inicia a" + " cont:" + cont);
		a.start();
		b.start();
		} else {
		System.out.println("Inicia b" + " cont:" + cont);
		b.start();
		a.start();
		}
	}

	public  void a() {
		
		semaforo.p();
		System.out.println("Comienza A" + " cont:" + cont);
		cont+= 1000;
		System.out.println("Termina A" + " cont:" + cont);
		semaforo.v();
		
		esperaAleatoria();
	}
	
	
	public void b() {
		
		semaforo.p();
		System.out.println("Comienza B"+ " cont:" + cont);
		cont+=1;
		System.out.println("Termina B"+ " cont:" + cont);
		semaforo.v();
		
		esperaAleatoria();
	}
	
	private void esperaAleatoria() {
		try {
		Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}

}
