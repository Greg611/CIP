package com.cip.moviedatabase.XMLHandler;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLFileBuilder {
    public static Document usersFileBuilder(){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/UsersData.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLFileBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Document moviesFileBuilder(){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/MoviesData.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLFileBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Document castMemberFileBuilder(){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/CastMemberData.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLFileBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Document tagsFileBuilder(){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/TagsData.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLFileBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Document collectionsFileBuilder(){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/CollectionsData.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLFileBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
