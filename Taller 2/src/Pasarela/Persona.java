package Pasarela;

public class Persona extends Thread{

	int id ;
	int dir;
	Pasarela pasarela;


	public Persona(int n, int dir, Pasarela p) {
		this.id = n;
		this.dir = dir;
		this.pasarela = p;
	}


	public void run() {
		try {
			pasarela.entrar(dir);
			System.out.println("Persona "+ id + " - dir " + dir + " - Entra" );
			sleep(100);
			pasarela.salir(dir);
			System.out.println("Persona "+ id + " - dir " + dir + " - Sale" );
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

	}

}
