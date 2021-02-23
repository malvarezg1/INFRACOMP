package co.edu.uniandes.infracom.taller4.rendezvous;

import co.edu.uniandes.infracom.taller4.testhandler.Semaforo;

// Metodos que se deben ejecutar
interface RendezvousInterface<E extends Enum<E>> {

    // Implementacion para A1
    void a1(RendezvousThread thread);

    // Implementacion para A2
    void a2(RendezvousThread thread);

    // Implementacion para B1
    void b1(RendezvousThread thread);

    // Implementacion para B2
    void b2(RendezvousThread thread);

    // Obtener los semaforos
    Semaforo[] getSemaphoresRef();
}
