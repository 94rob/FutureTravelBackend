package its.extratech.FutureTravel.python;


import org.hamcrest.MatcherAssert;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


public class RunPython {

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

    private List<String> readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/test/python/" + filename);
        return file.getAbsolutePath();
    }
}
