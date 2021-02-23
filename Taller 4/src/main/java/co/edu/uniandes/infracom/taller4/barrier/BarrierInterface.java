package co.edu.uniandes.infracom.taller4.barrier;

import co.edu.uniandes.infracom.taller4.testhandler.Semaforo;

interface BarrierInterface<E extends Enum<E>>{
    // Metodo A
    void A(BarrierThread thread);

    // Metodo B
    void B(BarrierThread thread);

    // Metodo C
    void C(BarrierThread thread);

    // Obtener los semaforos
    Semaforo[] getSemaphoresRef();
}
