package co.edu.uniandes.infracom.taller4.rendezvous;
import co.edu.uniandes.infracom.taller4.testhandler.*;

public class Rendezvous extends TestHandler<RendezvousMethods> implements RendezvousInterface<RendezvousMethods> {
    // Semaforos
    private Semaforo[] semaphores;

    // Constructor
    public Rendezvous() {
        super();
        this.semaphores = new Semaforo[] {new Semaforo(0), new Semaforo(0)};
    }

    // Referencia a los semaforos
    @Override
    public Semaforo[] getSemaphoresRef() {
        return this.semaphores;
    }

    @Override
    public void a1(RendezvousThread thread) {
        //TODO Completar el metodo A1
        this.addEvents(RendezvousMethods.A1);
        System.out.println(String.format(
                "Thread ID: %s " + "Ejecutando el metodo A1",
                thread.getThreadId()
        ));

    }

    @Override
    public void a2(RendezvousThread thread) {
        //TODO: Ejemplo de como se debe implementar
        //TODO: Recuerde agregar la enumeracion de su evento a la lista de eventos siempre
        this.addEvents(RendezvousMethods.A2);

        //TODO: Imprimir por consola el resultado de este paso, la variable compartida
        //      es la lista de registro de eventos (no se preocupe por su implementacion, ya esta)
        System.out.println(String.format(
                "Thread ID: %s " + "Ejecutando el metodo A2",
                thread.getThreadId()
        ));

        // Debido a que este metodo es el que se debe ejecutar de ultimo
        // Vamos a imprimir la traza que generó el programa en su ejecucion
        // Deje este pedazo al final del ultimo metodo que se debe ejecutar
        System.out.println("---- Resultado ----");
        System.out.println(super.toString());
    }

    @Override
    public void b1(RendezvousThread thread) {
        this.addEvents(RendezvousMethods.B1);
        
        System.out.println(String.format(
                "Thread ID: %s " + "Ejecutando el metodo B1",
                thread.getThreadId()
        ));
    }

    @Override
    public void b2(RendezvousThread thread) {
        this.addEvents(RendezvousMethods.B2);
        System.out.println(String.format(
                "Thread ID: %s " + "Ejecutando el metodo B2",
                thread.getThreadId()
        ));
    }

    // Main
    public static void main(String[] args) {
        //TODO Ejemplo de como lanzar el programa
        Rendezvous ren = new Rendezvous();
        String[] threads = new String[] {"A", "B"};
        for (int i = 0; i < threads.length; i++) {
            new RendezvousThread(threads[i], ren).start();
        }
    }
}
