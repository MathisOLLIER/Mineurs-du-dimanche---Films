package com.ipiecole.films;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class Films {

    private static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FilmsData> getFilms(String donneesFilms) {
        List<FilmsData> filmsData = new ArrayList<>();
        Document filmDataInUrlFormat = convertStringToXMLDocument(donneesFilms);


        try {
            filmDataInUrlFormat.getDocumentElement().normalize();

            NodeList nList = filmDataInUrlFormat.getElementsByTagName("item");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String titre = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String description = eElement.getElementsByTagName("description").item(0).getTextContent();

                    FilmsData filmData = new FilmsData("", description, titre);
                    filmsData.add(filmData);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return filmsData;
    }

    public List<FilmsData> getFilmsRss() throws IOException {
        return getFilms(this.getPageContents("http://rss.allocine.fr/ac/cine/prochainement?format=xml"));
    }

    public String getPageContents(String address) throws IOException {
        BufferedReader br = null;
        StringJoiner lines = new StringJoiner(System.lineSeparator());

        try {
            URL url = new URL(address);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return lines.toString();

    }
}