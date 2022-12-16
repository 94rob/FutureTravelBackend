package its.extratech.FutureTravel.importData;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Document doc = instantiateXMLFile("DataGenericFormatted.xml");
        List<Series> series = getSeries(doc);
        System.out.println(series.size());

        System.out.println(series.get(1).toString());

    }

    public static Document instantiateXMLFile(String fileName){
        Document doc = null;

        try{
            File xmlFile = new File(fileName);
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
        return doc;
    }

    public static List<Series> getSeries(Document doc){
        NodeList seriesNodeList = doc.getElementsByTagName("Series");
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
                Node seriesKeyNode = seriesElement.getElementsByTagName("SeriesKey").item(0);
                if(seriesKeyNode.getNodeType() == Node.ELEMENT_NODE){
                    Element seriesKeyElement = (Element) seriesKeyNode;

                    // Dalla SeriesKey tiro fuori una lista di Value che sono quelli che mi servono
                    NodeList valuesNodeList = seriesKeyElement.getElementsByTagName("Value");
                    List<Element> valuesElementList = new ArrayList<>();

                    // trasformo la NodeList dei Value in una lista di Element per poter prendere gli attributi
                    for (int j=0; j< valuesNodeList.getLength();j++){
                        if(valuesNodeList.item(j).getNodeType() == Node.ELEMENT_NODE){
                            valuesElementList.add((Element) valuesNodeList.item(j));
                        }
                    }

                    // popolo la SeriesKey con i valori presi dagli attributi dei Value
                    sk.setTerritorio(valuesElementList.get(0).getAttribute("value"));
                    sk.setTipoDato(valuesElementList.get(1).getAttribute("value"));
                    sk.setTipoAlloggio(valuesElementList.get(3).getAttribute("value"));
                    sk.setProvenienzaClienti(valuesElementList.get(5).getAttribute("value"));

                    s.setSeriesKey(sk);

                    // Ora recupero tutte le Obs
                    List<Obs> obsList = new ArrayList<>();

                    NodeList obsNodeList = seriesElement.getElementsByTagName("Obs");

                    for(int k=0; k< obsNodeList.getLength(); k++){
                        Obs obs = new Obs();

                        Node currentObsNode = obsNodeList.item(k);
                        if(currentObsNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element currentObsElement = (Element) currentObsNode;

                            // Setto il periodo
                            obs.setMese(currentObsElement.getElementsByTagName("Time").item(0).getTextContent());

                            // Setto il valore
                            Element obsValueElement = (Element) currentObsElement.getElementsByTagName("ObsValue").item(0);
                            obs.setValue(obsValueElement.getAttribute("value"));

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

    public static void insertSerie(Series s){

    }
}
