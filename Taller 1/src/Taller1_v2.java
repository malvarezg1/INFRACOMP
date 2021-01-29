import java.util.Scanner;

public class Taller1_v2 implements Runnable{

	int max;
	int id;

	public Taller1_v2(int id, int maximo) {
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
		Thread t0 = new Thread(new Taller1_v2(0, Integer.parseInt(i)));
		Thread t1 =  new Thread(new Taller1_v2(1, Integer.parseInt(i)));

		t0.start();
		t1.start();
		sc.close();
	}
}
