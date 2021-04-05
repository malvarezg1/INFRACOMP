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

		while(!m.verificarSecuenciaVacia() ) {
			int[] paginas = m.buscarPaginas();
			int paginaV = paginas[0];
			int paginaR = paginas[1];
			
			System.out.println("Pag V: " + paginaV);
			System.out.println("Pag R: " + paginaR);

			if(paginaR < 0 ) {
				System.out.println("Fallo!!");
				//Envejecimiento
				fallos ++;
				m.recuperarPagina(paginaV);
			}
			else {
				m.agregarColaReferencias(paginaR);
				System.out.println("Agrego a la cola la Pag: " + paginaR );
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


