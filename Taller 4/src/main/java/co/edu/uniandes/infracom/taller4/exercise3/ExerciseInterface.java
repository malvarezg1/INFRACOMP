package co.edu.uniandes.infracom.taller4.exercise3;
import co.edu.uniandes.infracom.taller4.testhandler.Semaforo;

public interface ExerciseInterface<E extends Enum<E>> {
    // Metodo A
    void A(ExerciseThread thread);

    // Metodo B
    void B(ExerciseThread thread);

    // Metodo C
    void C(ExerciseThread thread);

    // Metodo D
    void D(ExerciseThread thread);

    // Obtener los semaforos
    Semaforo[] getSemaphoresRef();
}
