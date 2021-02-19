package Pasarela;

public class Main {
	
	public static void main(String[] args) {
		Pasarela pasa = new Pasarela();
		for (int i = 0; i < 8; i++) { 
			
			Persona p = new Persona(i, i % 2, pasa);
			p.start();
		}
	}

}
