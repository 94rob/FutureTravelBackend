package its.extratech.FutureTravel.pythonTest;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.python.core.PyException;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class PythonTest {

    String nome = "Fabrizio";

/*
    @Test
    public void Hallo() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("py", resolvePythonScriptPath("main.py"));
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        List<String> results = readProcessOutput(process.getInputStream());

        System.out.println(results);

        MatcherAssert.assertThat("Results should not be empty", results, is(not(empty())));
        //MatcherAssert.assertThat("Results should contain output of script: ", results, hasItem(containsString("Hello Baeldung Readers!!")));

        int exitCode = process.waitFor();
        assertEquals("No errors should be detected", 0, exitCode);
    }



    @Test
    public void JSON() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("py", resolvePythonScriptPath("Json.py"));
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        List<String> results = readProcessOutput(process.getInputStream());

        System.out.println(results);

        MatcherAssert.assertThat("Results should not be empty", results, is(not(empty())));
        //MatcherAssert.assertThat("Results should contain output of script: ", results, hasItem(containsString("Hello Baeldung Readers!!")));

        int exitCode = process.waitFor();
        assertEquals("No errors should be detected", 0, exitCode);
    }
*/



    @Test
    public void HelloII() {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);

            pyInterp.exec("print('Hello " + this.nome +"')");
            assertEquals("Should contain script output: ", "Hello Fabrizio", output.toString()
                    .trim());
        }
    }
/*
    @Test
    public void JSON() {
        Map<String, String> result;
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);

            pyInterp.exec("x =  '{\"Provincia\" : \"Caserta\",\"Tipo_alloggio\" : \"HOTELLIKE\",\"Residenza_clienti\" : \"IT\", \"Time\" : \"2008-01\", \"Presenze\" : \"12874\"}'\n" +
                    "\n" +
                    "print(x)\n");

            System.out.println(output);


            result = new ObjectMapper().readValue((output.toString()), HashMap.class);

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(result.toString());
    }*/


    //Prendere questo output.toString() e renderlo map(cercare come fare un array associativo)

    /*
    @Test
    public void HelloPiuNome() {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            StringWriter output = new StringWriter();
            pyInterp.setOut(output);

            pyInterp.exec("a=str(input(\" \"))\n" +
                    "b=str(\"hello word\")\n" +
                    "print(a,b)");
            assertEquals("Should contain script output: ", "Hello Baeldung Readers!!", output.toString()
                    .trim());
        }
    }



    @Test
    public void prova() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath("hello.py"));

        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        List<String> results = readProcessOutput(process.getInputStream());

        assertThat("Results should not be empty", results, is(not(empty())));
        assertThat("Results should contain output of script: ", results, hasItem(
                containsString("Hello Baeldung Readers!!")));

        int exitCode = process.waitFor();
        assertEquals("No errors should be detected", 0, exitCode);
    }
*/


    private List<String> readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/test/python/Modelli_predittivi_regionale_e_provincia/" + filename);
        return file.getAbsolutePath();
    }

}
