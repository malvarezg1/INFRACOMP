package uniandes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneracionPruebas {



	public void GenPruebas() {
		
		int b = 1;
		int marcos = 8;
		double i = 0.6;

		for (int numpaginas = 40 ; numpaginas <= 120 ; numpaginas += 20) {
			try {
				File archivo = new File("./data/prueba"+b+"_5.txt");
				PrintWriter myWriter = new PrintWriter(archivo);
				myWriter.println(marcos);
				myWriter.println(numpaginas);
				myWriter.println(i+"");
				for (int j = 0; j < 1000; j++) {
					double rand = Math.random();
					int paginaMax = (int) (0.25 * numpaginas);
					int pagina = 0;
					if(rand < i) {
						pagina = (int)(Math.random() * (paginaMax - 0));
					}
					else {
						pagina = (int)(paginaMax + (Math.random() * (numpaginas - paginaMax)));
					}
					myWriter.println(pagina);
				}
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
				b ++;
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
	}



}
