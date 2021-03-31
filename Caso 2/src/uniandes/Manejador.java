package uniandes;

public class Manejador extends Thread {

	private Memoria m;
	private int fallos;


	public Manejador(Memoria m) {
		this.m = m;
	}
	
	public void imprimirFallas() {
		System.out.println("Num Fallas: " + fallos);
	}


	public void run() {

		for (int i = 0; i < 1000; i++) {
			int paginaV = m.buscarPaginas(i)[0];
			int paginaR = m.buscarPaginas(i)[1];

			if(paginaR < 0 ) {
				System.out.println("Entro");
				//Envejecimiento
				fallos ++;
				m.recuperarPagina(paginaV);
			}
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		imprimirFallas();
	}
}


