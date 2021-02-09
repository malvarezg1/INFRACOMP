package Pasarela;

public class Main {
	
	public static void main(String[] args) {
		Pasarela pasa = new Pasarela();
		for (int i = 0; i < 5; i++) {
			int dir = (int) Math.round(Math.random());
			Persona p = new Persona(i, dir, pasa);
			p.start();
		}
	}

}
