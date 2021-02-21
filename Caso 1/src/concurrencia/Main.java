package concurrencia;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.CyclicBarrier;

public class Main {

	private int numProdCons;
	private int numProductos;
	private int buzonesProd;
	private int buzonesCons;
	private ArrayList<Consumidor> consumidores;
	private ArrayList<Productor> productores;

	public Main() {
		this.consumidores = new ArrayList<Consumidor>();
		this.productores = new ArrayList<Productor>();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Main main = new Main();
			main.leerProperties();
			main.inicio();
			main.reporte();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void inicio() {

		Buffer bufferP = new Buffer(buzonesProd, "Buf Productores");
		Buffer bufferC  = new Buffer(buzonesCons, "Buf Consumidores");
		Buffer bufferI = new Buffer(1, "Buf Intermedio");

		//Creación de los Intermediarios BuzonProductores - Buzon Intermedio 
		Intermediario in = new Intermediario(1, bufferP, bufferI, numProductos*numProdCons);
		in.start();

		//Creación de los Intermediarios Buzon Intermedio  - Buzon Consumidores 
		Intermediario out = new Intermediario(2, bufferI, bufferC, numProductos*numProdCons);
		out.start();

		for (int i = 0; i < numProdCons; i++) {

			Productor p;
			Consumidor c;
			if(i % 2 == 0 ) {
				p = new Productor(i, bufferP, numProductos, 'A');
				c = new Consumidor (i, bufferC, numProductos, 'A');
			}
			else {
				p = new Productor(i, bufferP, numProductos, 'B');
				c = new Consumidor (i, bufferC, numProductos, 'B');
			}

			//Creación de los Productores
			productores.add(p);
			p.start();

			//Creación de los Comsumidores
			consumidores.add(c);
			c.start();
			try {
				c.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public  void reporte() {

		System.out.println("----------------Reporte----------------");
		for (int i = 0; i < numProdCons; i++) {
			Consumidor c = consumidores.get(i);
			System.out.println("Consumidor:" + c.getIdConsumidor());
			ArrayList<Producto> productos= c.getConsumidos();
			for (int j = 0; j < productos.size(); j++) {
				System.out.println("\t " + productos.get(j).getRef());
			}
		}

	}


	public void leerProperties()throws Exception{  

		FileReader reader=new FileReader("./in.properties");  
		Properties p=new Properties();  
		p.load(reader);  

		System.out.println(numProdCons = Integer.parseInt(p.getProperty("numProdCons")));  
		System.out.println(numProductos = Integer.parseInt(p.getProperty("numProductos"))); 
		System.out.println(buzonesProd = Integer.parseInt(p.getProperty("buzonesProd"))); 
		System.out.println(buzonesCons = Integer.parseInt(p.getProperty("buzonesCons"))); 
	}  


	private  void crearProperties() throws IOException {
		///Instantiating the properties file
		Properties props = new Properties();
		//Populating the properties file
		props.put("numProdCons", "4");
		props.put("numProductos", "7");
		props.put("buzonesProd", "3");
		props.put("buzonesCons", "8");
		//Instantiating the FileInputStream for output file
		String path = "C:/Users/Martin Alvarez/Documents/202110/INFRACOMP/INFRACOMP/Caso 1/in.properties";
		FileOutputStream outputStrem = new FileOutputStream(path);
		//Storing the properties file
		props.store(outputStrem, "This is a sample properties file");
	}

}



