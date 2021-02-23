import static org.junit.jupiter.api.Assertions.*;
import co.edu.uniandes.infracom.taller4.exercise3.Exercise;
import co.edu.uniandes.infracom.taller4.exercise3.ExerciseMethods;
import co.edu.uniandes.infracom.taller4.exercise3.ExerciseThread;
import org.junit.jupiter.api.*;
import java.util.LinkedList;

public class Exercise3Test {
    // Resultados
    private Exercise ex;

    // Configuracion de la prueba
    @BeforeEach
    public void configurarTest() throws InterruptedException {
        ex = new Exercise();
        String[] threads = new String[] {"A", "B", "C", "D"};
        for (int i = 0; i < threads.length; i++) {
            Thread myThread = new ExerciseThread(threads[i], ex);
            myThread.start();
        }

        // Esperamos algunos segundos a que JUnit ejecute este pedazo de código
        // para evitar errores de concurrencia del Test
        Thread.sleep(2000);
    }

    //Condición sobre D
    @RepeatedTest(5)
    @DisplayName("¿El metodo D() se ejecuta despues de B() y antes que A() y C()?")
    public void condicionD() {
        // Resultados
        LinkedList<ExerciseMethods> traza = ex.getEvents();
        int posicionA = traza.indexOf(ExerciseMethods.A);
        int posicionB = traza.indexOf(ExerciseMethods.B);
        int posicionC = traza.indexOf(ExerciseMethods.C);
        int posicionD = traza.indexOf(ExerciseMethods.D);

        assertEquals(true, posicionB < posicionD, "El metodo B() no se ejecuta antes que el metodo D()");
        assertEquals(true, posicionA > posicionD, "El metodo A() no se ejecuta despues que el metodo D()");
        assertEquals(true, posicionC > posicionD, "El metodo C() no se ejecuta despues que el metodo D()");
    }

    //Condición sobre B
    @RepeatedTest(5)
    @DisplayName("¿El metodo B() se ejecuta antes que A(), D() y C()?")
    public void condicionB() {
        // Resultados
        LinkedList<ExerciseMethods> traza = ex.getEvents();
        int posicionA = traza.indexOf(ExerciseMethods.A);
        int posicionB = traza.indexOf(ExerciseMethods.B);
        int posicionC = traza.indexOf(ExerciseMethods.C);
        int posicionD = traza.indexOf(ExerciseMethods.D);

        assertEquals(true, posicionB < posicionA, "El metodo B() no se ejecuta antes que el metodo A()");
        assertEquals(true, posicionB < posicionC, "El metodo B() no se ejecuta antes que el metodo C()");
        assertEquals(true, posicionB < posicionD, "El metodo B() no se ejecuta antes que el metodo D()");
    }
}
