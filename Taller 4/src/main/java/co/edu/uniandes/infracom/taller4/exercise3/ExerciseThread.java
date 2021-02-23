package co.edu.uniandes.infracom.taller4.exercise3;
import co.edu.uniandes.infracom.taller4.testhandler.Semaforo;

public class ExerciseThread extends Thread {
    private String id;
    private Exercise ref;
    private Semaforo[] sems;

    // Constructor
    public ExerciseThread(String pId, Exercise pRef) {
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
        // TODO Implementar el proceso
        super.run();
    }
}
