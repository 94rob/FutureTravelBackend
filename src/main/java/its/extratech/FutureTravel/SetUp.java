package its.extratech.FutureTravel;


import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class SetUp {

    @Autowired
    private RecordServiceImpl recordService;

    public void fetchIstatApi(){
        try {
            File myObj = new File("./src/main/resources/tmp/file.txt");
            Scanner myReader = new Scanner(myObj);
            String data = null;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println("Aggiorno il database con i dati Istat successivi alla data: " + data);
            }
            myReader.close();
            recordService.fetch(data);

            try {
                FileWriter myWriter = new FileWriter("./src/main/resources/tmp/file.txt");

                // Per evitare duplicati, metto il mese successivo all'ultimo registrato
                String dateString = this.stringToNextMonth(this.recordService.getLastDate());
                myWriter.write(dateString);
                myWriter.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void updatePredictionModels(){

    }

    public String stringToNextMonth(String date){
        StringBuilder sb = new StringBuilder();
        sb.append(date.charAt(date.length() - 2));
        sb.append(date.charAt(date.length() - 1));
        String month = sb.toString();

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
}
