package its.extratech.FutureTravel.importData;


import its.extratech.FutureTravel.entities.Record;
import its.extratech.FutureTravel.servicies.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;


public class XMLIstatReader {

    public static ArrayList<String> province;
    public static ArrayList<String> residenzeClienti;
    public static ArrayList<String> indicatori;
    public static ArrayList<String> tipologieEsercizi;

    static {
        province = new ArrayList<>();
        province.add("ITF31");
        province.add("ITF32");
        province.add("ITF33");
        province.add("ITF34");
        province.add("ITF35");

        residenzeClienti = new ArrayList<>();
        residenzeClienti.add("IT");
        residenzeClienti.add("WRL_X_ITA");

        indicatori = new ArrayList<>();
        indicatori.add("NI");
        indicatori.add("AR");

        tipologieEsercizi = new ArrayList<>();
        tipologieEsercizi.add("HOTELLIKE");
        tipologieEsercizi.add("OTHER");
    }


    public List<Record> fetch() {

        Document documentPresenze;
        Document documentArrivi;
        List<Series> arriviSeriesList;
        List<Series> presenzeSeriesList;

        List<Record> finalRecordList = new ArrayList<>();
        int i = 0;
        for (String provincia : province) {
            for (String tipologiaEsercizio : tipologieEsercizi){
                for (String residenzaClienti : residenzeClienti){
                    documentPresenze = fetchIstatApi(residenzaClienti,provincia,"NI", tipologiaEsercizio, "");
                    documentArrivi = fetchIstatApi(residenzaClienti,provincia,"AR", tipologiaEsercizio, "");
                    i += 2;

                    arriviSeriesList = getSeries(documentArrivi);
                    System.out.println(arriviSeriesList.get(0).toString());
                    presenzeSeriesList = getSeries(documentPresenze);
                    System.out.println(presenzeSeriesList.get(0).toString());

                    List<Record> arriviRecordList = new ArrayList<>();
                    List<Record> presenzeRecordList = new ArrayList<>();

                    for(Series s : arriviSeriesList){
                        arriviRecordList = s.toRecordList();
                        System.out.println("Record arrivi fetchati: " + arriviRecordList.size());
                    }

                    for(Series s : presenzeSeriesList){
                        presenzeRecordList = s.toRecordList();
                        System.out.println("Record presenze fetchati:" + presenzeRecordList.size());
                    }

                    for(Record recordArrivi : arriviRecordList){
                        boolean flag = false;
                        Iterator<Record> recordIterator = presenzeRecordList.listIterator();

                        while((!flag) && (recordIterator.hasNext())){
                            Record r = recordIterator.next();
                            System.out.println(r.getTime() + "\t" + recordArrivi.getTime());
                            if(Objects.equals(r.getTime(), recordArrivi.getTime())){
                                System.out.println("Sono uguali");
                                recordArrivi.setPresenze(r.getPresenze());
                                flag = true;
                                finalRecordList.add(recordArrivi);
                            }
                        }
                    }


                    System.out.println("N° record final list finora: " + finalRecordList.size());
                }
            }
        }
        System.out.println("Finito di fetchare\nN° di record: " + finalRecordList.size());
        return finalRecordList;

    }

    public Document fetchIstatApi(String paeseResidenzaClienti, String Itter107, String dati, String tipoEsercizio, String queryString) {
        String url = "http://sdmx.istat.it/SDMXWS/rest/data/122_54/";
        url += "M" + "."; // si riferisce alla frequenza, in questo caso M sta per mensile - il punto serve a distinguere i campi
        url += "551_553" + "."; // codice ATECO_2007 che identifica le strutture ricettive
        url += "N" + "."; // adjustment
        url += paeseResidenzaClienti + "."; // accetta come valori WORLD, ALL, IT, WRL_X_ITA (estero)
        url += Itter107 + "."; // codice territorio
        url += "TOT."; // classe dimensionale per numero di camere
        url += tipoEsercizio + "."; // tipo esercizio ALL, OTHER, HOTELLIKE
        url += dati + "."; // AR arrivi, NI partenze
        url += ".."; // grado urbanizzazione, località costiera
        url += "/?" + queryString;

        Document doc = null;

        try {
            // set HttpClient
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            // set HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/vnd.sdmx.genericdata+xml")
                    .GET()
                    .build();

            // DEBUG
            System.out.println(url);

            // execute
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // mi appoggio a un file per creare un oggetto Document dalla stringa che ho ricevuto

            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/tmp/file.xml"));
            writer.write(response.body());
            writer.close();

            try{
                File xmlFile = new File("src/main/resources/tmp/file.xml");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(xmlFile);
            } catch (ParserConfigurationException e){
                System.out.println("ParserConfigurationException: " + e);
            } catch (IOException e){
                System.out.println("IOException: " + e);
            } catch (SAXException e){
                System.out.println("SAXException: " + e);
            }

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return doc;
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
