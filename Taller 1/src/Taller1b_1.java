
public class Taller1b_1 {
	

		private static int contador = 0;
		
		public void incrementar(){
			for (int i = 0; i < 10000; i++) {
				contador++;
			}
		}
		
		public int getContador(){
			return contador;
		}
		
		public static void main(String[] args){
			Taller1b_1 c = new Taller1b_1();
			
			for (int i = 0; i < args.length; i++) {
				c.incrementar();
			}
			
			System.out.println(c.getContador());
		}
}
