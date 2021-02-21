package concurrencia;

public class Intermediario extends Thread {

	private int id;
	private Buffer bufferE;
	private Buffer bufferS;
	private int cantProd;


	public Intermediario(int id, Buffer bufferE, Buffer bufferS , int cantProd) {
		this.id = id;
		this.bufferE = bufferE;
		this.bufferS = bufferS;
		this.cantProd = cantProd;
	}

	public void run() {
		int cont = 0;
		while (cantProd > 0 ) {
			cont ++;
			try {
				Producto p = bufferE.retirar();
				System.out.println("Intermedi: " + id + " - Retiro:   \t"  + p.getRef() + " - Del Buffer: " + bufferE.getNom() );	

				bufferS.almacenar(p);
				System.out.println("Intermedi: " + id + " - Almaceno: \t"  + p.getRef() + " - Del Buffer: " + bufferS.getNom());	

				cantProd --;
				
			} 
			catch (Exception e) {e.printStackTrace();}
		}
	}
}
