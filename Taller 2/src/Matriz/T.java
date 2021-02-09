package Matriz;

import java.util.concurrent.ThreadLocalRandom;

public class T extends Thread{
	
	private final static int INT_MAX = 105345;
	private static Identificar objId;
	private static Maximo m;
	private static int[][] mat;
	private int id; 

	public T(int n) {
		id = n;
	}
	public void run() {
		int locMax = 0;
		int id = objId.darNumId();
		for (int i = 0; i < mat[id].length; i++) {
			if(mat[id][i] > locMax)
				locMax = mat[id][i];
			
		if(m.anotar(locMax))
			System.out.println("Max: " + m.darMaximo());
			
		}
	}
	
	
	public static void main(String args[]) {
		int n = 10;
		inicializar(n);
		objId =  new Identificar();
		m = new Maximo(n);
		for (int i = 0; i < n; i++) {
			new T(i).start();
		}
		
		for (int i = 0; i < n; i++) {
			String linea ="";
			for (int j = 0; j < n; j++) {
				linea += mat[i][j]+"\t";
			}
			System.out.println(linea);
		}
	}
	
	
	public static void inicializar(int n){
	
		mat= new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = ThreadLocalRandom.current().nextInt(0, INT_MAX);
			}
		}
	}
	
	

}
