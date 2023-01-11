package its.extratech.FutureTravel.importData;


import its.extratech.FutureTravel.entities.Record;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;


import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;


public class XMLIstatReader {

    public static ArrayList<String> province;
    public static ArrayList<String> residenzeClientiMensili;
    public static ArrayList<String> residenzeClientiAnnuali;
    public static ArrayList<String> indicatori;
    public static ArrayList<String> tipologieEsercizi;
    public static ImportDataUtils importDataUtils;

    static {
        province = new ArrayList<>();
        province.add("ITF31");
        province.add("ITF32");
        province.add("ITF33");
        province.add("ITF34");
        province.add("ITF35");

        residenzeClientiMensili = new ArrayList<>();
        residenzeClientiMensili.add("IT");
        residenzeClientiMensili.add("WRL_X_ITA");

        residenzeClientiAnnuali = new ArrayList<>();
        residenzeClientiAnnuali.add("AFR_OTH");
        residenzeClientiAnnuali.add("AFRMED");
        residenzeClientiAnnuali.add("AME_C_S_OTH");
        residenzeClientiAnnuali.add("AME_N_OTH");
        residenzeClientiAnnuali.add("AR");
        residenzeClientiAnnuali.add("ASI_OTH");
        residenzeClientiAnnuali.add("ASI_W_OTH");
        residenzeClientiAnnuali.add("AT");
        residenzeClientiAnnuali.add("AU");
        residenzeClientiAnnuali.add("BE");
        residenzeClientiAnnuali.add("BG");
        residenzeClientiAnnuali.add("BR");
        residenzeClientiAnnuali.add("CA");
        residenzeClientiAnnuali.add("CH_LI");
        residenzeClientiAnnuali.add("CN");
        residenzeClientiAnnuali.add("CY");
        residenzeClientiAnnuali.add("CZ");
        residenzeClientiAnnuali.add("DE");
        residenzeClientiAnnuali.add("DK");
        residenzeClientiAnnuali.add("EE");
        residenzeClientiAnnuali.add("EG");
        residenzeClientiAnnuali.add("ES");
        residenzeClientiAnnuali.add("EU");
        residenzeClientiAnnuali.add("EUR_NEU");
        residenzeClientiAnnuali.add("EUR_OTH");
        residenzeClientiAnnuali.add("FI");
        residenzeClientiAnnuali.add("FR");
        residenzeClientiAnnuali.add("GR");
        residenzeClientiAnnuali.add("HR");
        residenzeClientiAnnuali.add("HU");
        residenzeClientiAnnuali.add("IE");
        residenzeClientiAnnuali.add("IL");
        residenzeClientiAnnuali.add("IN");
        residenzeClientiAnnuali.add("IS");
        residenzeClientiAnnuali.add("IT");
        residenzeClientiAnnuali.add("JP");
        residenzeClientiAnnuali.add("KR");
        residenzeClientiAnnuali.add("LT");
        residenzeClientiAnnuali.add("LU");
        residenzeClientiAnnuali.add("LV");
        residenzeClientiAnnuali.add("MT");
        residenzeClientiAnnuali.add("MX");
        residenzeClientiAnnuali.add("NL");
        residenzeClientiAnnuali.add("NO");
        residenzeClientiAnnuali.add("NZ");
        residenzeClientiAnnuali.add("OCE_OTH");
        residenzeClientiAnnuali.add("OTH");
        residenzeClientiAnnuali.add("PL");
        residenzeClientiAnnuali.add("PT");
        residenzeClientiAnnuali.add("RO");
        residenzeClientiAnnuali.add("RU");
        residenzeClientiAnnuali.add("SE");
        residenzeClientiAnnuali.add("SI");
        residenzeClientiAnnuali.add("SK");
        residenzeClientiAnnuali.add("TR");
        residenzeClientiAnnuali.add("UK");
        residenzeClientiAnnuali.add("US");
        residenzeClientiAnnuali.add("VE");
        residenzeClientiAnnuali.add("WORLD");
        residenzeClientiAnnuali.add("WRL_X_EUR");
        residenzeClientiAnnuali.add("WRL_X_ITA");
        residenzeClientiAnnuali.add("ZA");


        indicatori = new ArrayList<>();
        indicatori.add("NI");
        indicatori.add("AR");

        tipologieEsercizi = new ArrayList<>();
        tipologieEsercizi.add("HOTELLIKE");
        tipologieEsercizi.add("OTHER");

        importDataUtils = new ImportDataUtils();
    }


    public List<Record> fetch() throws IOException {

        Document documentPresenze;
        Document documentArrivi;
        Series arriviSeries;
        Series presenzeSeries;

        List<Record> finalRecordList = new ArrayList<>();
        List<Record> tempRecordList;

        for (String provincia : province) {
            for (String tipologiaEsercizio : tipologieEsercizi){
                for (String residenzaClienti : residenzeClientiMensili){

                    // eseguo la chiamata all'API istat, con i valori di questo ciclo; ne eseguo una per gli ARRIVI e una per le PRESENZE
                    // costruisco un URL e lo passo al metodo che effettua la chiamata, questo mi restituisce una stringa
                    // che poi trasformo in un Document per poterlo spacchettare
                    documentPresenze = importDataUtils.
                                            fromStringToXMLDocument(
                                            fetchIstatApi(
                                                    createUrlForIstatApi(residenzaClienti,provincia,"NI", tipologiaEsercizio, "")));
                    documentArrivi = importDataUtils.
                                            fromStringToXMLDocument(
                                            fetchIstatApi(
                                                    createUrlForIstatApi(residenzaClienti,provincia,"AR", tipologiaEsercizio, "")));

                    // trasformo i Document in Series, oggetti che ricalcano il modo in cui è costruito
                    // l'XML che l'API Istat mi ha restituito
                    arriviSeries = getSeries(documentArrivi).get(0);
                    presenzeSeries = getSeries(documentPresenze).get(0);

                    // Trasformo le Series in oggetti Record così poi li potrò salvare sul DB,
                    // ma prima devo unire i record che hanno lo stesso contesto (ore ho due record, uno per
                    // le presenze e uno per gli arrivi, ne voglio uno solo con i due campi popolati)
                    tempRecordList = this.mixRecordListWithSameContesto(
                            importDataUtils.fromSeriesToRecordList(arriviSeries),
                            importDataUtils.fromSeriesToRecordList(presenzeSeries));

                    // Aggiungo i Record di questo ciclo al listone che poi restituirò
                    tempRecordList
                            .stream()
                            .map(finalRecordList::add);

                    System.out.println("N° record final list finora: " + finalRecordList.size());
                }
            }
        }
        System.out.println("Finito di fetchare\nN° di record: " + finalRecordList.size());
        return finalRecordList;

    }


    public List<Record> mixRecordListWithSameContesto(List<Record> arriviRecordList, List<Record> presenzeRecordList){
        List<Record> finalRecordList = new ArrayList<>();

        for(Record recordArrivi : arriviRecordList){

            boolean flag = false;
            Iterator<Record> recordIterator = presenzeRecordList.listIterator();

            while((!flag) && (recordIterator.hasNext())){
                Record r = recordIterator.next();

                if(Objects.equals(r.getTime(), recordArrivi.getTime())){
                    recordArrivi.setPresenze(r.getPresenze());
                    flag = true;
                    finalRecordList.add(recordArrivi);
                }
            }
            if(!flag){
                finalRecordList.add(recordArrivi);
            }
        }

        for(Record recordPresenze : presenzeRecordList){
            if(arriviRecordList.stream()
                    .noneMatch((item) -> Objects.equals(recordPresenze.getTime(), item.getTime()))){
                finalRecordList.add(recordPresenze);
            }
        }

        return finalRecordList;
    }

    public String createUrlForIstatApi(String paeseResidenzaClienti, String Itter107, String dati, String tipoEsercizio, String queryString){
        String url = "http://sdmx.istat.it/SDMXWS/rest/data/122_54/";
        url += "A" + "."; // si riferisce alla frequenza, in questo caso M sta per mensile - il punto serve a distinguere i campi
        url += "551_553" + "."; // codice ATECO_2007 che identifica le strutture ricettive
        url += "N" + "."; // adjustment
        url += paeseResidenzaClienti + "."; // accetta come valori WORLD, ALL, IT, WRL_X_ITA (estero)
        url += Itter107 + "."; // codice territorio
        url += "TOT."; // classe dimensionale per numero di camere
        url += tipoEsercizio + "."; // tipo esercizio ALL, OTHER, HOTELLIKE
        url += dati + "."; // AR arrivi, NI partenze
        url += ".."; // grado urbanizzazione, località costiera
        url += "/?" + queryString;

        return url;
    }

    public String fetchIstatApi(String url) {

            // setto l'HttpClient
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            // setto l'HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/vnd.sdmx.genericdata+xml")
                    .GET()
                    .build();

            // DEBUG
            System.out.println(url);

            // eseguo la richiesta HTTP
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.body();
    }

    public List<Series> getSeries(Document doc){
        NodeList seriesNodeList = doc.getElementsByTagName("generic:Series");

        List<Series> series = new ArrayList<>();

        // Ciclo tutte le series
        for(int i = 0; i < seriesNodeList.getLength(); i++){
            Series s = new Series();
            SeriesKey sk = new SeriesKey();

            Node currentSeriesNode = seriesNodeList.item(i);


            if(currentSeriesNode.getNodeType() == Node.ELEMENT_NODE)
            {

                // Casto la series corrente da Node a Element per poter andare a prendere i nodi dentro
                Element seriesElement = (Element) currentSeriesNode;

                // Recupero la SeriesKey relativa alla series corrente
                Node seriesKeyNode = seriesElement.getElementsByTagName("generic:SeriesKey").item(0);
                if(seriesKeyNode.getNodeType() == Node.ELEMENT_NODE){
                    Element seriesKeyElement = (Element) seriesKeyNode;

                    // Dalla SeriesKey tiro fuori una lista di Value che sono quelli che mi servono
                    NodeList valuesNodeList = seriesKeyElement.getElementsByTagName("generic:Value");

                    List<Element> valuesElementList = new ArrayList<>();

                    // trasformo la NodeList dei Value in una lista di Element per poter prendere gli attributi
                    for (int j=0; j< valuesNodeList.getLength();j++){
                        if(valuesNodeList.item(j).getNodeType() == Node.ELEMENT_NODE){
                            valuesElementList.add((Element) valuesNodeList.item(j));
                        }
                    }

                    // popolo la SeriesKey con i valori presi dagli attributi dei Value
                    sk.setFrequenza(valuesElementList.get(0).getAttribute("value")); // FREQ
                    sk.setCodiceProvincia(valuesElementList.get(4).getAttribute("value")); // ITTER107
                    sk.setIndicatore(valuesElementList.get(7).getAttribute("value")); // INDS (AR o NI)
                    sk.setTipoAlloggio(valuesElementList.get(6).getAttribute("value")); // TIPO_ESERCIZIO
                    sk.setResidenzaClienti(valuesElementList.get(3).getAttribute("value")); // RES_CLIENTI

                    s.setSeriesKey(sk);

                    // Ora recupero tutte le Obs
                    List<Obs> obsList = new ArrayList<>();

                    NodeList obsNodeList = seriesElement.getElementsByTagName("generic:Obs");

                    for(int k=0; k< obsNodeList.getLength(); k++){
                        Obs obs = new Obs();

                        Node currentObsNode = obsNodeList.item(k);
                        if(currentObsNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element currentObsElement = (Element) currentObsNode;

                            // Setto il periodo
                            Node timeNode = currentObsElement.getElementsByTagName("generic:ObsDimension").item(0);
                            String time = ((Element) timeNode).getAttribute("value");
                            obs.setMese(time);

                            // Setto il valore
                            Node valueNode = currentObsElement.getElementsByTagName("generic:ObsValue").item(0);
                            try{
                                String value = ((Element) valueNode).getAttribute("value");
                                obs.setValue(value);
                            } catch (NullPointerException e){
                                System.out.println("Valore assente");
                                continue;
                            }


                            obsList.add(obs);
                        }
                    }

                    s.setObsList(obsList);
                    series.add(s);

                }
            }

        }
        return series;
    }
}
