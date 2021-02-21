package concurrencia;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Main {

	private int numProdCons;
	private int numProductos;
	private int buzonesProd;
	private int buzonesCons;
	private ArrayList<Consumidor> consumidores;
	
	public Main() {
		this.consumidores = new ArrayList<Consumidor>();
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
		
		for (int i = 0; i < numProdCons; i++) {
			Buffer buffer = new Buffer(buzonesProd);
			Productor p = new Productor(i, buffer, numProductos);
			p.start();
		//}
		
		
		//for (int i = 0; i < numProdCons; i++) {
			//Buffer buffer = new Buffer(buzonesProd);
			Consumidor c = new Consumidor (i, buffer, numProductos);
			consumidores.add(c);
			c.start();
		}
		
	}
	
	public void reporte() {
		for (int i = 0; i < numProdCons; i++) {
			Consumidor c =consumidores.get(i);
			System.out.println("Consumidor:" + c.getId());
			ArrayList<Producto> productos= c.getConsumidos();
			for (int j = 0; j < productos.size(); j++) {
				System.out.println("\t " + productos.get(j).getRef());
			}
			
		}
		
	}

	
	public void leerProperties()throws Exception{  

		FileReader reader=new FileReader("C:/Users/Martin Alvarez/Documents/202110/INFRACOMP/INFRACOMP/Caso 1/in.properties");  
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



