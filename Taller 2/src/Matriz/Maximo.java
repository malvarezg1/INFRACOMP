package Matriz;

public class Maximo {
	private int cont, maximo, ntThreads;
	
	public Maximo(int numT) {
		ntThreads = numT;
		maximo = 0;
		cont=0;
	}
	
	public synchronized boolean anotar(int n) {
		if(n > maximo) maximo = n;
		return (++cont == ntThreads) ? true: false;
	}
	
	public int darMaximo()
	{
		return maximo;
	}
}
