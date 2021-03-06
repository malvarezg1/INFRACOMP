package uniandes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintStream;


public class Memoria {

	//Atributos
	private int numMarcos;
	private int numPagProceso;
	private double nivelLocalidad;

	//Estructuras de Datos
	private  Queue<Integer> secuencia;
	private  int[] tablaPaginas;
	private  int[] contadoresEnvej;
	private  Queue<Integer> colaReferencias;

	public Memoria() {
		secuencia =  new LinkedList<>();
		colaReferencias = new LinkedList<>();
	}

	public static void main(String[] args) {
		PrintStream fileStream;
		try {
			//Si se descomenta este metodo se activa la generacion de las pruebas
			/*
			GeneracionPruebas g = new GeneracionPruebas();
			g.GenPruebas();
			
			*/
			fileStream = new PrintStream("Resultados.txt");
			System.setOut(fileStream);
			
			Memoria m = new Memoria();
			m.leerArchivoConfig();
			System.out.println("Marcos Pag. " + m.numMarcos);
			System.out.println("Num Pag. Proceso " + m.numPagProceso);
			System.out.println("Nivel de Localidad " + m.nivelLocalidad);

			m.inicializar();

			Manejador manejador = new Manejador(m);
			manejador.start();

			LRU envejecimiento = new LRU(m);
			envejecimiento.start();
			
			manejador.join();
			envejecimiento.join();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
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
			File myObj = new File("./data/referencias4_16.txt");
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
			imprimirSecuencia();

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public void imprimirSecuencia() {
		String copia = secuencia.toString();
		System.out.println("Secuencia: "+ copia);
	}

	public synchronized int[] buscarPaginas()
	{
		int paginaV =  secuencia.remove();
		int paginaR = tablaPaginas[paginaV];
		int[] rta = new int[2]; 

		rta[0] = paginaV;
		rta[1] = paginaR;

		return rta;
	}

	public void recuperarPagina(int pag) {
		int min = (int) Math.pow(2, 31) ;
		int indiceMin = -1 ;
		synchronized (this) {
			for (int i = 0; i < contadoresEnvej.length; i++) {
				if(contadoresEnvej[i] < min ) {
					min = contadoresEnvej[i];
					indiceMin = i;
				}
			}
		}
		System.out.println("Indice Min: " + indiceMin);

		for (int j = 0; j < tablaPaginas.length; j++) {
			if(tablaPaginas[j] == indiceMin )
				tablaPaginas[j] = -1;
		}
		tablaPaginas[pag] = indiceMin;
		synchronized (this) {
			contadoresEnvej[indiceMin]= 0;
		}
		agregarColaReferencias(indiceMin);
		System.out.println("Agrego a la cola la Pag: " + indiceMin );
	}

	public  synchronized void agregarColaReferencias(int i ) {
		colaReferencias.add(i);
	}

	public  synchronized int sacarColaReferencias() {
		return colaReferencias.remove();
	}

	public synchronized boolean verificarColaVacia() {
		return colaReferencias.isEmpty();
	}

	public  synchronized boolean verificarSecuenciaVacia() {
		return secuencia.isEmpty();
	}

	public  synchronized void corrimientosDerecha() {
		for (int i = 0; i < contadoresEnvej.length; i++) {
			System.out.println("Sin corrimiento: " + i + " ContEnve: " + contadoresEnvej[i]);
			contadoresEnvej[i] = contadoresEnvej[i] >> 1;  
			System.out.println("Con corrimiento: " + i + " ContEnve: " + contadoresEnvej[i]);
		}
	}

	public  synchronized void actualizarBitR(int i) {
		System.out.println("R: " + Math.pow(2, 30) );
		System.out.println("Sin R: " + i + " ContEnve: " + contadoresEnvej[i]);
		contadoresEnvej[i] = contadoresEnvej[i] + (int) Math.pow(2, 30);
		System.out.println("Con R: " + i + " ContEnve: " + contadoresEnvej[i]);
	}

}
