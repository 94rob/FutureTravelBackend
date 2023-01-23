package its.extratech.FutureTravel;

import its.extratech.FutureTravel.servicies.implementations.FintechServiceImpl;
import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


public class SetUp {

    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private FintechServiceImpl fintechService;

    public boolean updateDataFromIstat() throws IOException {

        String lastdate = this.recordService.getLastDateRegisteredInIstatData();
        if(lastdate.isBlank()){
            lastdate = "2000-01";
        }

        System.out.println("Aggiorno il database con i dati Istat successivi alla data: " + lastdate);
        return recordService.fetch(considerNextMonth(lastdate));
    }

    public void updatePredictionModels() throws IOException {

        // Elimino le vecchie previsioni
        this.fintechService.deleteOldPrevisionRecords('P');

        // Eseguo il file python
        File file = new File("./src/main/resources/fintech/testing.py");
        ProcessBuilder processBuilder = new ProcessBuilder("py", file.getAbsolutePath());
        processBuilder.redirectErrorStream(true);

        System.out.println("Aggiorno i modelli previsionali");
        Process process = processBuilder.start();
        System.out.println(this.readProcessOutput(process.getInputStream()));
    }

    public String considerNextMonth(String date){
        String month = String.valueOf(date.charAt(date.length() - 2)) +
                date.charAt(date.length() - 1);

        int monthInt = Integer.parseInt(month);

        if(monthInt < 12){
            String newMonth = String.valueOf(monthInt + 1);
            if(newMonth.length() == 1){
                newMonth = "0" + newMonth;
            }
            date = date.substring(0, date.length() -2) + newMonth;

        } else {
            int year =  Integer.parseInt(date.substring(0, 4));
            year++;
            date = year + "-01";
        }

        return date;
    }

    private List<String> readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }



}
