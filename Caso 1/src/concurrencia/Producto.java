package concurrencia;

public class 	Producto  {
	
	private String ref;
	private char tipo; 
	
	
	public Producto(char tipo, String ref) {
		this.ref = ref;
		this.tipo = tipo;
	}


	public char getTipo() {
		return tipo;
	}


	public String getRef() {
		return ref;
	}


	public void setRef(String ref) {
		this.ref = ref;
	}


	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

}
