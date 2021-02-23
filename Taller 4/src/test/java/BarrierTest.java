import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.LinkedList;
import co.edu.uniandes.infracom.taller4.barrier.*;

public class BarrierTest {
    // Resultados
    private Barrier bar;

    // Configuracion de la prueba
    @BeforeEach
    public void configurarTest() throws InterruptedException {
        bar = new Barrier();
        String[] threads = new String[] {"A", "B", "C"};
        for (int i = 0; i < threads.length; i++) {
            Thread myThread = new BarrierThread(threads[i], bar);
            myThread.start();
        }

        // Esperamos algunos segundos a que JUnit ejecute este pedazo de código
        // para evitar errores de concurrencia del Test
        Thread.sleep(2000);
    }

    // Verificar orden
    @RepeatedTest(5)
    @DisplayName("¿El metodo C() se ejecuta estrictamente despues de A() y B()?")
    public void condicionC() {
        // Resultados
        LinkedList<BarrierMethods> traza = bar.getEvents();

        int posicionA = traza.indexOf(BarrierMethods.A);
        int posicionB = traza.indexOf(BarrierMethods.B);
        int posicionC = traza.indexOf(BarrierMethods.C);

        // Aserciones
        assertEquals(true, posicionA < posicionC, "El metodo A no se ejecuta antes que el metodo C");
        assertEquals(true, posicionB < posicionC, "El metodo B no se ejecuta antes que el metodo C");
    }
}
