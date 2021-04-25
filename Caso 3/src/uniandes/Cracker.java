package uniandes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

class Cracker implements Runnable {

	private int start;
	private int end;
	private final MessageDigest digest;
	private static boolean DONE = false;
	private String found;

	public Cracker(int s, int e, String algoritmo) throws NoSuchAlgorithmException {
		start = s;
		end = e;
		digest = MessageDigest.getInstance(algoritmo);
	}

	public String[] concatenar(String candidato) {

		String[] rta = new String[2];
		rta[0] =  candidato + PasswordCracker.CADENA_INICIAL;
		rta[1] = PasswordCracker.CADENA_INICIAL + candidato;

		return rta;
	}

	public void generate(StringBuilder sb, int n) {
		if (DONE)
			return;

		if (n == sb.length()) {
			String candidate = sb.toString();
			// check password
			
			String[] cadenas = concatenar(candidate);

			byte[] bytes1 = digest.digest(cadenas[0].getBytes());
			byte[] bytes2 = digest.digest(cadenas[1].getBytes());
			
		    StringBuilder a1 = new StringBuilder();
		    StringBuilder a2 = new StringBuilder();
		    
		    for (int i = 0; i < PasswordCracker.numCeros/2; i++) {
		        a1.append(String.format("%02X", bytes1[i]));
		        a2.append(String.format("%02X", bytes2[i]));
			}
		    			
			boolean funciono1 = true;
			boolean funciono2 = true;
			for (int i = 0; i < PasswordCracker.numCeros; i++) {
				if(a1.charAt(i) != '0') {
					funciono1 = false;
				}
				if(a2.charAt(i) != '0') {
					funciono2 = false;
				} 
			}

			if(funciono1) {
				found = candidate + " , y se concatena adelante";
				DONE = true;
			}
			else if(funciono2) {
				found = candidate + " , y se concatena atrás";
				DONE = true;
			}
			return;
		}

		for (int i = 0; i < PasswordCracker.ALPHABET.length && !DONE; i++) {
			char letter = PasswordCracker.ALPHABET[i];
			sb.setCharAt(n, letter);
			generate(sb, n + 1);
		}

	}

	@Override
	public void run() {
		for (int length = start; length <= end && !DONE; length++) {
			StringBuilder sb = new StringBuilder();
			sb.setLength(length);
			generate(sb, 0);
		}

		if (DONE) {
			long duration = System.currentTimeMillis() - PasswordCracker.START_TIME;
			System.out.println("Cadena encontrada en  " + TimeUnit.MILLISECONDS.toSeconds(duration) + "." + TimeUnit.MILLISECONDS.toMillis(duration) + " segundos. La cadena fue = " + found);
		} else {
			System.out.println("Cadena no encontrada para set de [" + start + ", " + end + "] caracteres");
		}
	}
}