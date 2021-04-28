package uniandes;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Prototipo {

	public static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	public static final int PASSWORD_MAX_LENGTH = 7;
	public static long START_TIME;

	public static int numCeros ;
	public static String CADENA_INICIAL;
	public static String algoritmo;
	public static String found;


	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) +
					Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
        Scanner in = new Scanner(System.in);
        
        System.out.println("Porfavor ingrese el algoritmo (SHA-256 o SHA-512)");
		algoritmo = in.nextLine();
		if(!algoritmo.equals("SHA-256") && !algoritmo.equals("SHA-512" )) {
			System.out.println("Reinicie el programa e ingrese un algoritmo correcto ya sea SHA-256 o SHA-512");
		}
        System.out.println("Porfavor ingrese la cadena de caracteres inicial ( no superior a 32 caracteres)");
		CADENA_INICIAL = in.nextLine();
		System.out.println("Porfavor ingrese el numero de ceros buscados (20, 24, 28, 32 o 36 )");
		numCeros = Integer.parseInt(in.nextLine())/4;
		long inicio = System.currentTimeMillis();
		int cores = Runtime.getRuntime().availableProcessors();
		int lengthSet = PASSWORD_MAX_LENGTH / cores; // Cuantos dígitos le asigno a cada core
		int end = 0;

		START_TIME = System.currentTimeMillis();

		ExecutorService executorService = Executors.newFixedThreadPool(cores);

		while (end < PASSWORD_MAX_LENGTH) {
			int start = end + 1;
			end = start + lengthSet;

			if (end > PASSWORD_MAX_LENGTH)
				end = PASSWORD_MAX_LENGTH;
			executorService.submit(new Validador(start, end, algoritmo));
		}

		executorService.shutdown();
		
		
		try {
			if(executorService.awaitTermination(3, TimeUnit.HOURS)){
				long fin = System.currentTimeMillis();
				System.out.println("\n");
				System.out.println("La cadena de inicial utilizada fue: " + CADENA_INICIAL);
				System.out.println("Tiempo de ejecución: " + (fin - inicio)/1000 + " segundos" );
				System.out.println("El valor v fue: "+ found);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

