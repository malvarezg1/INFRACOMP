package concurrencia;

import java.util.ArrayList;

public class Buffer {

	private String nom = "";
	private int capacidad;
	private ArrayList<Producto> buff;
	Object lleno, vacio;



	public Buffer(int capacidad, String nom) {
		this.nom = nom;
		this.capacidad = capacidad;
		buff = new ArrayList();
		lleno = new Object();
		vacio = new Object();

	}


	// Retirar espera Wait()
	public Producto retirar() {
		boolean continuar = true;
		Producto prod = null;

		while(continuar) {
			synchronized(this){
				if(buff.size() > 0){
					prod = buff.remove(0);
					continuar = false;
				}
			}
			if(continuar) {
				synchronized(vacio) {
					try {vacio.wait(); }
					catch(Exception e) {e.printStackTrace();}
				}
			}
		}
		synchronized(lleno) {
			try {lleno.notify();}
			catch(Exception e) {e.printStackTrace();}
		}

		return prod;
	}

	// Retirar espera Wait()
	public void almacenar(Producto prod) {
		boolean continuar = true;
		while(continuar) {
			synchronized(this){
				if(buff.size() < capacidad){
					buff.add(prod);
					continuar = false;
				}

			}
			if(continuar) {
				synchronized (lleno) {
					try {lleno.wait();}
					catch(Exception e) {e.printStackTrace();}
				}
			}
		}
		synchronized (vacio) {
			try {vacio.notify();}
			catch(Exception e) {e.printStackTrace();}
		}
	}

	//Almacenar espera SemiActiva Yield()
	public void  almacenarSemiActiva(Producto prod) {
		while(buff.size() == capacidad) {
			Thread.yield();
		}

		synchronized (this) {
			buff.add(prod);
			synchronized (vacio) {
				vacio.notify();
			}
		}	
	}

	//Retirar espera SemiActiva Yield()
	public Producto retirarSemiActiva(char tip) {

		while( buff.size() == 0) {
			Thread.yield();
		}

		synchronized (this) {
			for (int i = 0; i < buff.size(); i++) {
				if(buff.get(i).getTipo() == tip) {
					Producto prod = buff.remove(0);
					return prod;
				}
			}
		}
		return null;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public ArrayList<Producto> getBuff() {
		return buff;
	}


	public void setBuff(ArrayList<Producto> buff) {
		this.buff = buff;
	}


	public Object getLleno() {
		return lleno;
	}


	public void setLleno(Object lleno) {
		this.lleno = lleno;
	}


	public Object getVacio() {
		return vacio;
	}


	public void setVacio(Object vacio) {
		this.vacio = vacio;
	}


}