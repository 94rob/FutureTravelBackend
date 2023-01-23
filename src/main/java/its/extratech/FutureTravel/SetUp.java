package its.extratech.FutureTravel;

import its.extratech.FutureTravel.servicies.implementations.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class SetUp {

    @Autowired
    private RecordServiceImpl recordService;

    public void updateDataFromIstat() throws IOException {

        String lastdate = this.recordService.getLastDate();
        if(lastdate.isBlank()){
            lastdate = "2000-01";
        }

        System.out.println("Aggiorno il database con i dati Istat successivi alla data: " + lastdate);
        recordService.fetch(considerNextMonth(lastdate));
    }

    public void updatePredictionModels(){

    }

    public String considerNextMonth(String date){
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
