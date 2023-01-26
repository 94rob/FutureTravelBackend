package its.extratech.FutureTravel.fetchIstatData;


import its.extratech.FutureTravel.entities.*;
import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.fetchIstatData.istatXmlObjects.Obs;
import its.extratech.FutureTravel.fetchIstatData.istatXmlObjects.Series;
import its.extratech.FutureTravel.fetchIstatData.istatXmlObjects.SeriesKey;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
    public static ArrayList<String> indicatori;
    public static ArrayList<String> tipologieEsercizi;

    static {
        province = new ArrayList<>();
        province.add("ITF3");
        province.add("ITF31");
        province.add("ITF32");
        province.add("ITF33");
        province.add("ITF34");
        province.add("ITF35");

        residenzeClientiMensili = new ArrayList<>();
        residenzeClientiMensili.add("IT");
        residenzeClientiMensili.add("WRL_X_ITA");

        indicatori = new ArrayList<>();
        indicatori.add("NI");
        indicatori.add("AR");

        tipologieEsercizi = new ArrayList<>();
        tipologieEsercizi.add("HOTELLIKE");
        tipologieEsercizi.add("OTHER");
    }

    public List<Record> fetch(String date) throws IOException {

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

                    String responsePresenze = this.sendHttpRequest(
                            createUrlForIstatApi(residenzaClienti,provincia,"NI", tipologiaEsercizio, "startPeriod=" + date));
                    String responseArrivi = this.sendHttpRequest(
                            createUrlForIstatApi(residenzaClienti,provincia,"AR", tipologiaEsercizio, "startPeriod=" + date));


                    if((responsePresenze.length() < 29) || (responseArrivi.length() < 29)){
                        System.out.println("Nessun nuovo record");
                        break;
                    }

                    // Trasformo la stringa in un Document per poter spacchettare i dati
                    documentPresenze = this.fromStringToXMLDocument(responsePresenze);
                    documentArrivi = this.fromStringToXMLDocument(responseArrivi);

                    if((documentPresenze == null) || (documentArrivi == null)){
                        System.out.println("Il documento xml è vuoto");
                        throw new NullPointerException();
                    }

                    // trasformo i Document in Series, oggetti che ricalcano il modo in cui è costruito
                    // l'XML che l'API Istat mi ha restituito
                    arriviSeries = getSeriesFromDocument(documentArrivi).get(0);
                    presenzeSeries = getSeriesFromDocument(documentPresenze).get(0);

                    // Trasformo le Series in oggetti Record così poi li potrò salvare sul DB,
                    // ma prima devo unire i record che hanno lo stesso contesto (ore ho due record, uno per
                    // le presenze e uno per gli arrivi, ne voglio uno solo con i due campi popolati)
                    tempRecordList = this.mixRecordListWithSameContesto(this.fromSeriesToRecordList(arriviSeries), this.fromSeriesToRecordList(presenzeSeries));

                    // Aggiungo i Record di questo ciclo al listone che poi restituirò
                    finalRecordList.addAll(tempRecordList);

                    System.out.println("N° record final list finora: " + finalRecordList.size());
                }
            }
        }
        System.out.println("Finito di fetchare\nN° di record: " + finalRecordList.size());

        // pulisco il file xml usato come appoggio
        this.cleanFile();

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
        url += "M" + "."; // si riferisce alla frequenza (M -> mensile)
        url += "551_553" + "."; // codice ATECO_2007, identifica le strutture ricettive
        url += "N" + "."; // adjustment
        url += paeseResidenzaClienti + "."; // WORLD, ALL, IT, WRL_X_ITA
        url += Itter107 + "."; // codice territorio
        url += "TOT."; // classe dimensionale per numero di camere
        url += tipoEsercizio + "."; // ALL, OTHER, HOTELLIKE
        url += dati + "."; // AR ,NI
        url += ".."; // grado urbanizzazione, località costiera
        url += "/?" + queryString;

        return url;
    }

    public String sendHttpRequest(String url) {
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

        // DEBUG - Feedback
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

    public List<Series> getSeriesFromDocument(Document doc){
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

                    // Setto la SeriesKey nella Series
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

                    // Setto la ObsList nella Series
                    s.setObsList(obsList);

                    // Aggiungo la Series alla lista
                    series.add(s);
                }
            }
        }
        return series;
    }

    public List<Record> fromSeriesToRecordList(Series series) {

        List<Record> list = new ArrayList<>();

        for (Obs o: series.getObsList()) {

            Record record = new Record();

            // nuovo contesto vuoto
            record.setContesto(new Contesto());
            record.getContesto().setTipoAlloggio(new TipoAlloggio());
            record.getContesto().setProvincia(new Provincia());
            record.getContesto().setResidenzaClienti(new ResidenzaClienti());

            // popolo il contesto
            record.getContesto().getTipoAlloggio().setId(series.getSeriesKey().getTipoAlloggio());
            record.getContesto().getProvincia().setId(series.getSeriesKey().getCodiceProvincia());
            record.getContesto().getResidenzaClienti().setId(series.getSeriesKey().getResidenzaClienti());

            // Setto gli altri parametri
            record.setTipoDato('S');
            record.setTime(o.getMese());

            if(Objects.equals(series.getSeriesKey().getIndicatore(), "NI")){
                record.setPresenze(Integer.parseInt(o.getValue()));
            } else {
                record.setArrivi(Integer.parseInt(o.getValue()));
            }

            list.add(record);
        }

        return list;
    }

    public Document fromStringToXMLDocument(String content) {

        Document doc = null;
        String path = "./src/main/resources/tmp/file.xml";

        try {
            this.writeFile(path, content);
            doc = this.fileToDocument(new File(path));

        } catch (ParserConfigurationException e) {
            System.out.println("Errore durante la scrittura del file xml: \nParserConfigurationException: " + e);
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file xml: \nIOException: " + e);
        } catch (SAXException e) {
            System.out.println("Errore durante la scrittura del file xml: \nSAXException: " + e);
        }

        return doc;
    }

    private void writeFile(String path, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(content);
        writer.close();
    }

    private Document fileToDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    public void cleanFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/tmp/file.xml"));
        writer.write("<xml></xml>");
        writer.close();
    }
}
