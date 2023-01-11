package its.extratech.FutureTravel.python;


import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import static org.junit.Assert.assertEquals;
import java.io.StringWriter;


public class RunPython {

    String nome = "Fabrizio";

    public void Hello() {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);

            pyInterp.exec("print('Hello Baeldung Readers!!')");
            assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", output.toString()
                    .trim());
        }
    }

    public void HelloPiuNome() {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);

            pyInterp.exec("a=str(input(' '))\n" +
                    "b=str('hello word')\n" +
                    "print(a,b)");
            assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", output.toString()
                    .trim());
        }
    }

    public void Somma() {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec("x = 10+10");
            PyObject x = pyInterp.get("x");
            assertEquals(String.valueOf(20), x.asInt(), "x: ");
        }
    }

}
