package uniandes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Memoria {

	//Atributos
	private int numMarcos;
	private int numPagProceso;
	private double nivelLocalidad;

	private ArrayList<Integer> secuencia;
	private int[] tablaPaginas;
	private int[] memoriaReal;
	private int[] contadoresEnvej;



	public Memoria() {
		secuencia = new ArrayList<>();

	}

	public static void main(String[] args) {
		Memoria m = new Memoria();
		m.leerArchivoConfig();
		System.out.println("Marcos Pag. " + m.numMarcos);
		System.out.println("Num Pag. Proceso " + m.numPagProceso);
		System.out.println("Nivel de Localidad " + m.nivelLocalidad);
		
		m.inicializar();
		Manejador manejador = new Manejador(m);
		manejador.start();
		

	}
	
	public void inicializar() {
		contadoresEnvej = new int[numMarcos];
		tablaPaginas = new int[numPagProceso];
		
		//Inicializando la Tabla de Paginas Vacia
		for (int i = 0; i < tablaPaginas.length; i++) {
			tablaPaginas[i] = -1;
		}

	}

	public void leerArchivoConfig(){
		try {
			File myObj = new File("./data/referencias1.txt");
			Scanner myReader = new Scanner(myObj);
			int cont = 0;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if(cont == 0)
					numMarcos = Integer.parseInt(data);
				else if (cont == 1)
					numPagProceso = Integer.parseInt(data);
				else if (cont == 2)
					nivelLocalidad = Double.parseDouble(data);
				else
					secuencia.add(Integer.parseInt(data));
				cont ++;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public int[] buscarPaginas(int i)
	{
		int paginaV =  secuencia.get(i);
		int paginaR = tablaPaginas[paginaV];
		int[] rta = new int[2]; 
		
		rta[0] = paginaV;
		rta[1] = paginaR;
		
		System.out.println("Pag V: " + paginaV);
		System.out.println("Pag R: " + paginaR);
		
		return rta;
	}

	public void recuperarPagina(int pag) {
		int min = 9999999;
		int indiceMin = -1 ;
		for (int i = 0; i < contadoresEnvej.length; i++) {
			if(contadoresEnvej[i] <= min ) {
				min = contadoresEnvej[i];
				indiceMin = i;
			}
		}
		
		System.out.println("Indice Min: " + indiceMin);
		for (int j = 0; j < tablaPaginas.length; j++) {
			if(tablaPaginas[j] == indiceMin )
				tablaPaginas[j] = -1;
		}
		tablaPaginas[pag] = indiceMin; 

	}
}
