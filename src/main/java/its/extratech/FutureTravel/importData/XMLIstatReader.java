package its.extratech.FutureTravel.importData;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLIstatReader {

    public static Map<String, String> articleMapOne;
    static {
        articleMapOne = new HashMap<>();
        articleMapOne.put("Caserta", "ITF31");
        articleMapOne.put("Benevento", "ITF32");
        articleMapOne.put("Napoli", "ITF33");
        articleMapOne.put("Avellino", "ITF34");
        articleMapOne.put("Salerno", "ITF35");
    }

    public static void main(String[] args) {
        Document doc = fetchIstatApi("IT", "ITF3", "NI", "ALL", "");

        try{
            List<Series> series = getSeries(doc);

            System.out.println(series.get(0).toString());

        } catch(NullPointerException e){
            e.printStackTrace();
        }

    }

    public static Document fetchIstatApi(String paeseResidenzaClienti, String Itter107, String dati, String tipoEsercizio, String queryString) {
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

            // execute
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // prove

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


    public static List<Series> getSeries(Document doc){
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
                    sk.setTerritorio(valuesElementList.get(4).getAttribute("value")); // ITTER107
                    sk.setTipoDato(valuesElementList.get(7).getAttribute("value")); // INDS (AR o NI)
                    sk.setTipoAlloggio(valuesElementList.get(6).getAttribute("value")); // TIPO_ESERCIZIO
                    sk.setProvenienzaClienti(valuesElementList.get(3).getAttribute("value")); // RES_CLIENTI

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
                            String value = ((Element) valueNode).getAttribute("value");
                            obs.setValue(value);

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
