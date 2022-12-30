package com.cip.moviedatabase.XMLHandler;

import com.cip.moviedatabase.Model.Collection;
import com.cip.moviedatabase.Model.Movie;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CollectionsXML {
    public static List<Collection> readAllCollection() {
        Document doc = XMLFileBuilder.collectionsFileBuilder();
        List<Collection> listOfCollections = new LinkedList<>();
        NodeList collectionNodes = doc.getElementsByTagName("Collection");
        for (int i = 0; i < collectionNodes.getLength(); i++) {
            Node collectionsNode = collectionNodes.item(i);

            if (collectionsNode.getNodeType() == Node.ELEMENT_NODE) {
                Element collectionElement = (Element) collectionsNode;

                UUID id = UUID.fromString(collectionElement.getElementsByTagName("Id").item(0).getTextContent());
                String name = collectionElement.getElementsByTagName("Name").item(0).getTextContent();
                UUID userId = UUID.fromString(collectionElement.getElementsByTagName("UserId").item(0).getTextContent());
                LinkedList<Movie> movies = new LinkedList<>();
                for (int j=0; i<collectionElement.getElementsByTagName("Movies").getLength();j++){
                    movies.add(MoviesXML.readMovie(UUID.fromString(collectionElement.getElementsByTagName("Movies").item(j).getTextContent())));
                }

                Collection collection = new Collection(id, name, userId, movies);
                listOfCollections.add(collection);
            }
        }
        return listOfCollections;
    }

    public static Collection readCollection(UUID searchedId, UUID user) {
        Document doc = XMLFileBuilder.collectionsFileBuilder();
        Collection collection = new Collection();
        NodeList collectionNodes = doc.getElementsByTagName("Collection");

        int i = 0;
        while (i < collectionNodes.getLength()) {
            Node collectionNode = collectionNodes.item(i);

            if (collectionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element collectionElement = (Element) collectionNode;

                UUID id = UUID.fromString(collectionElement.getElementsByTagName("Id").item(0).getTextContent());
                UUID userId = UUID.fromString(collectionElement.getElementsByTagName("UserId").item(0).getTextContent());
                if(id.equals(searchedId) && userId.equals(user)) {
                    String name = collectionElement.getElementsByTagName("Name").item(0).getTextContent();
                    LinkedList<Movie> movies = new LinkedList<>();
                    for (int j=0; i<collectionElement.getElementsByTagName("Movies").getLength();j++){
                        movies.add(MoviesXML.readMovie(UUID.fromString(collectionElement.getElementsByTagName("Movies").item(j).getTextContent())));
                    }
                    collection = new Collection(id, name, movies);
                }
            }
            i++;
        }
        return collection;
    }

    public static LinkedList<Collection> readUserAllCollection(UUID searchedId){
        Document doc = XMLFileBuilder.collectionsFileBuilder();
        LinkedList<Collection> listOfCollections = new LinkedList<>();
        Collection collection = new Collection();
        NodeList collectionNodes = doc.getElementsByTagName("Collection");
        for (int i = 0; i < collectionNodes.getLength(); i++) {
            Node collectionsNode = collectionNodes.item(i);

            if (collectionsNode.getNodeType() == Node.ELEMENT_NODE) {
                Element collectionElement = (Element) collectionsNode;

                UUID userId = UUID.fromString(collectionElement.getElementsByTagName("UserId").item(0).getTextContent());
                if(userId.equals(searchedId)) {
                    UUID id = UUID.fromString(collectionElement.getElementsByTagName("Id").item(0).getTextContent());
                    String name = collectionElement.getElementsByTagName("Name").item(0).getTextContent();
                    LinkedList<Movie> movies = new LinkedList<>();
                    for (int j=0; i<collectionElement.getElementsByTagName("Movies").getLength();j++){
                        movies.add(MoviesXML.readMovie(UUID.fromString(collectionElement.getElementsByTagName("Movies").item(j).getTextContent())));
                    }
                    collection = new Collection(id, name, movies);
                }
                listOfCollections.add(collection);
            }
        }
        return listOfCollections;
    }

    public static void saveCollection(Collection newCollection){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/CollectionsData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element tag = doc.createElement("Collection");

            Element id = doc.createElement("Id");
            id.appendChild(doc.createTextNode(UUID.randomUUID().toString()));
            tag.appendChild(id);

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(newCollection.getName()));
            tag.appendChild(name);

            Element userId = doc.createElement("UserId");
            userId.appendChild(doc.createTextNode(newCollection.getUserId().toString()));
            tag.appendChild(userId);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
    public static void modifyCollection(Collection modifiedCollection) {
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/CollectionsData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            int i = 0;
            NodeList collectionNodes = doc.getElementsByTagName("User");

            while (i < collectionNodes.getLength()) {
                Node collectionNode = collectionNodes.item(i);

                if (collectionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element collectionElement = (Element) collectionNode;

                    UUID id = UUID.fromString(collectionElement.getElementsByTagName("Id").item(0).getTextContent());
                    UUID userId=UUID.fromString(collectionElement.getElementsByTagName("UserId").item(0).getTextContent());
                    if (id.equals(modifiedCollection.getId()) && userId.equals(modifiedCollection.getUserId())) {
                        collectionElement.getElementsByTagName("Name").item(0).setTextContent(modifiedCollection.getName());
                        collectionElement.getElementsByTagName("Movie").item(0).setTextContent(null);
                        for (int j = 0; j < modifiedCollection.getMovies().size(); j++) {
                            collectionElement.getElementsByTagName("Movie").item(0).appendChild(doc.createTextNode(modifiedCollection.getMovies().get(i).getId().toString()));
                        }
                    }
                }
                i++;
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch (SAXException | TransformerException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteCollection(Collection deletedCollection){
        try{
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/CollectionsData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList collectionNodes = doc.getElementsByTagName("Collection");

            UsersXML.readUser(deletedCollection.getUserId()).getCollections().remove(deletedCollection);
            UsersXML.modifyUser(UsersXML.readUser(deletedCollection.getUserId()));

            int j=0;

            while (j < collectionNodes.getLength()) {
                Node collectionNode = collectionNodes.item(j);

                if (collectionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element collectionElement = (Element) collectionNode;

                    UUID id = UUID.fromString(collectionElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(deletedCollection.getId())) {
                        collectionElement.getParentNode().removeChild(collectionElement);
                    }
                }
                j++;
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch (SAXException | TransformerException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
