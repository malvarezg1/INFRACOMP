import java.util.Scanner;

public class Taller1_v1 extends Thread {

	int max;
	int id;

	public Taller1_v1(int id, int maximo) {
		this.max = maximo;
		this.id = id;
	}

	public void  run() {
		try {
			if(id % 2 == 0) { 
				for(int i = 1; i <= max ;i++) {
					if( i % 2 ==  0) {
						System.out.println(i);
						Thread.sleep(50);
					}
				}
			}
			if(id % 2 == 1) {
				for(int i = 1; i <= max ;i++) {
					if( i % 2 ==  1) {
						System.out.println(i);
						Thread.sleep(50);
					}
				}
			}
		}
		catch(Exception e) {

		}
	}



	public static  void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String i = sc.nextLine();
		Taller1_v1 t0 = new Taller1_v1(0, Integer.parseInt(i));
		Taller1_v1 t1 = new Taller1_v1(1, Integer.parseInt(i));

		t0.start();
		t1.start();
	}

}
