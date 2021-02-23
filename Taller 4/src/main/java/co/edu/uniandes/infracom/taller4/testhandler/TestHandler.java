package co.edu.uniandes.infracom.taller4.testhandler;

import java.util.LinkedList;

// Construiremos una clase abstracta para instanciar el control de la lista para el manejo
// de los eventos asi como tener un tipo de referencia para las pruebas.
public abstract class TestHandler<E extends Enum<E>> implements TestInterface<E> {

    // Lista de eventos
    protected LinkedList<E> executionOrder;

    // Monitor para registrar el evento
    private Object eventMonitor;

    // Constructor
    public TestHandler() {
        this.executionOrder = new LinkedList<>();
        eventMonitor = new Object();
    }

    // Agregar un evento
    public void addEvents(E newEvent) {
        synchronized (this.eventMonitor) {
            executionOrder.add(newEvent);
        }
    }

    // Recuperar la lista de eventos
    public LinkedList<E> getEvents() {
        synchronized (this.eventMonitor) {
            return executionOrder;
        }
    }

    // Imprimir lista de eventos para depurar
    public String toString() {
        String events = "";
        for (int i = 0; i < executionOrder.size(); i++) {
            E event = executionOrder.get(i);
            events += String.format("Orden: %d - Evento: %s \n", i + 1, event.name());
        }
        return events;
    }
}
