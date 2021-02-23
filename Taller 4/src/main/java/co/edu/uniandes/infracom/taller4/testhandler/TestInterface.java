package co.edu.uniandes.infracom.taller4.testhandler;
import java.util.LinkedList;

// Especificamos que es una clase generica que debe ser un Enum
public interface TestInterface<E extends Enum<E>> {

    // Agregar un evento para simbolizar la ejecución de un metodo
    void addEvents(E newEvent);

    // Recuperar la lista de metodos ejecutados en orden
    LinkedList<E> getEvents();
}
