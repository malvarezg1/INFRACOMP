package co.edu.uniandes.infracom.taller4.barrier;
import co.edu.uniandes.infracom.taller4.rendezvous.Rendezvous;
import co.edu.uniandes.infracom.taller4.rendezvous.RendezvousMethods;
import co.edu.uniandes.infracom.taller4.rendezvous.RendezvousThread;
import co.edu.uniandes.infracom.taller4.testhandler.Semaforo;
import co.edu.uniandes.infracom.taller4.testhandler.TestHandler;

public class Barrier extends TestHandler<BarrierMethods> implements BarrierInterface<BarrierMethods> {
	
	// Semaforos
    private Semaforo[] semaphores;
	
	public Barrier() {
        super();
        this.semaphores = new Semaforo[] {new Semaforo(0), new Semaforo(0)};
    }

    @Override
    public void A(BarrierThread thread) {
        //TODO Completar el metodo A
        System.out.println(String.format(
                "Thread ID: %s " + "Ejecutando el metodo A",
                thread.getThreadId()
        ));
    }

    @Override
    public void B(BarrierThread thread) {
        //TODO Completar el metodo B
    	System.out.println(String.format(
                "Thread ID: %s " + "Ejecutando el metodo B",
                thread.getThreadId()
        ));
    }

    @Override
    public void C(BarrierThread thread) {
        //TODO Completar el metodo C
    	System.out.println(String.format(
                "Thread ID: %s " + "Ejecutando el metodo C",
                thread.getThreadId()
        ));
    }

    @Override
    public Semaforo[] getSemaphoresRef() {
        return this.semaphores;

    }

    public static void main(String[] args) {
        //TODO Completar el main y lanzar el programa
    	Barrier b = new Barrier();
        String[] threads = new String[] {"A", "B", "C"};
        for (int i = 0; i < threads.length; i++) {
            new BarrierThread(threads[i], b).start();
        }
    }
}
