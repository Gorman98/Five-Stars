package ser210.quinnipiac.edu.finalproject;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Kyle on 5/1/2018.
 */


public class HandlerXml extends DefaultHandler{

    private String gameTitle;
    private String year;
    private int counter1 = 0;
    private String temp = "";

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        temp = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        temp = "";
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equalsIgnoreCase("GameTitle") && counter1 == 0){
            gameTitle = temp;
        } else if(qName.equalsIgnoreCase("ReleaseDate") && counter1 == 0){
            year = temp;
            counter1 ++;
        }
    }

    public String getTitle(){
        return gameTitle;
    }

    public String getYear(){
        return year;
    }
}
