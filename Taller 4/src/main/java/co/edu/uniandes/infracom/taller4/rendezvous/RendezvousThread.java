package co.edu.uniandes.infracom.taller4.rendezvous;
import co.edu.uniandes.infracom.taller4.testhandler.Semaforo;
public class RendezvousThread extends Thread {
    private String id;
    private Rendezvous ref;
    private Semaforo[] sems;

    // Constructor
    public RendezvousThread(String pId, Rendezvous pRef) {
        this.id = pId;
        this.ref = pRef;
        this.sems = this.ref.getSemaphoresRef();
    }

    // Devolver el ID asignado
    String getThreadId() {
        return id;
    }

    @Override
    public void run() {
        //TODO Implementar el proceso solicitado. De momento vamos a dejar un ejemplo de como
        //     llamar a los semaforos e invocar los metodos de la clase (A1 por ejemplo).
        try {
        	
        	if(this.id.equals("A")) {
        		this.ref.a1(this);
        		this.sems[0].v();
        		this.sems[1].p();
        		this.ref.a2(this);
        	}
        	else if(this.id.equals("B")) {
        		this.ref.b1(this);
        		this.sems[0].p();
        		this.ref.b2(this);
        		this.sems[1].v();

        	}
        	else {
        		throw new RuntimeException("Error");
        	}
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}