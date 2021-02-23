import co.edu.uniandes.infracom.taller4.rendezvous.RendezvousMethods;
import co.edu.uniandes.infracom.taller4.rendezvous.Rendezvous;
import co.edu.uniandes.infracom.taller4.rendezvous.RendezvousThread;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.LinkedList;

public class RendezvousTest {
    // Referencia al resultado
    private Rendezvous ren;

    // Configuracion de la prueba
    @BeforeEach
    public void configurarTest() throws InterruptedException {
        ren = new Rendezvous();
        String[] threads = new String[] {"A", "B"};
        for (int i = 0; i < threads.length; i++) {
            Thread myThread = new RendezvousThread(threads[i], ren);
            myThread.start();
        }

        // Esperamos algunos segundos a que JUnit ejecute este pedazo de código
        // para evitar errores de concurrencia del Test
        Thread.sleep(2000);
    }

    // Condicion sobre B2
    @RepeatedTest(5)
    @DisplayName("¿B2 se ejecuta despues que A1 y antes que A2?")
    public void condicionB2() {
        // Resultados
        LinkedList<RendezvousMethods> traza = ren.getEvents();
        int posicionB2 = traza.indexOf(RendezvousMethods.B2);
        int posicionA1 = traza.indexOf(RendezvousMethods.A1);
        int posicionA2 = traza.indexOf(RendezvousMethods.A2);

        System.out.println(String.format("A1: %d  -  B2: %d  -  A2: %d", posicionA1, posicionB2, posicionA2));

        assertEquals(true, posicionA1 < posicionB2, "A1 no se ha ejecutado antes que B2");
        assertEquals(true, posicionA2 > posicionB2, "A2 no se ha ejecutado despues que B2");
    }
}