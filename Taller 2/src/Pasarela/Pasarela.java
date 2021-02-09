package Pasarela;

public class Pasarela {

	private int personasI = 0;
	private int personasD = 0;

	public synchronized void entrar(int dir) {
		try {
			if(dir == 0) {
				while(personasD != 0)
					wait();
				personasI++;
			}
			else {
				while(personasI != 0)
					wait();
				personasD++;
			}
		}catch(Exception e) {

		}

	}


	public  synchronized void salir(int dir) {
		if(dir == 0) {
			personasI--;
			if(personasI == 0) {
				notifyAll();
			}
		}
		else
		{
			personasD--;
			if(personasD == 0) {
				notifyAll();
			}
		}
		
	}

	
}
