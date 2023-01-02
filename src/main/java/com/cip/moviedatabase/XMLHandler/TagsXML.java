package com.cip.moviedatabase.XMLHandler;

import com.cip.moviedatabase.Model.Tags;
import com.cip.moviedatabase.Model.User;
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

public class TagsXML {
    public static List<Tags> readAllTags() {
        Document doc = XMLFileBuilder.tagsFileBuilder();
        List<Tags> listOfTags = new LinkedList<>();
        NodeList tagsNodes = doc.getElementsByTagName("Tag");
        for (int i = 0; i < tagsNodes.getLength(); i++) {
            Node tagsNode = tagsNodes.item(i);

            if (tagsNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tagsElement = (Element) tagsNode;

                UUID id = UUID.fromString(tagsElement.getElementsByTagName("Id").item(0).getTextContent());
                String name = tagsElement.getElementsByTagName("Name").item(0).getTextContent();

                Tags tag = new Tags(id, name);
                listOfTags.add(tag);
            }
        }
        return listOfTags;
    }

    public static Tags readTag(UUID searchedId) {
        Document doc = XMLFileBuilder.tagsFileBuilder();
        Tags tag = new Tags();
        NodeList tagsNodes = doc.getElementsByTagName("Tag");
        int i = 0;
        while (i < tagsNodes.getLength()) {
            Node tagsNode = tagsNodes.item(i);

            if (tagsNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tagsElement = (Element) tagsNode;

                UUID id = UUID.fromString(tagsElement.getElementsByTagName("Id").item(0).getTextContent());
                if(id.equals(searchedId)) {
                    String name = tagsElement.getElementsByTagName("Name").item(0).getTextContent();
                    tag = new Tags(id, name);
                }
            }
            i++;
        }
        return tag;
    }

    public static void saveTag(Tags newTag){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/TagsData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element tag = doc.createElement("Tag");

            Element id = doc.createElement("Id");
            id.appendChild(doc.createTextNode(UUID.randomUUID().toString()));
            tag.appendChild(id);

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(newTag.getName()));
            tag.appendChild(name);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modifyUser(Tags modifiedTag){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/TagsData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            int i = 0;
            NodeList tagNodes = doc.getElementsByTagName("Tag");

            while (i < tagNodes.getLength()) {
                Node tagNode = tagNodes.item(i);

                if (tagNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tagElement = (Element) tagNode;

                    UUID id = UUID.fromString(tagElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(modifiedTag.getId())) {
                        tagElement.getElementsByTagName("Name").item(0).setTextContent(modifiedTag.getName());
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

    public static void deleteTag(Tags deletedTag){
        try{
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/TagsData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList tagNodes = doc.getElementsByTagName("Tag");

            for (int i = 0; i < MoviesXML.readAllMovies().size(); i++) {
                for (int j = 0; j < MoviesXML.readAllMovies().get(i).getTags().size(); j++) {
                    if (MoviesXML.readAllMovies().get(i).getTags().get(i).equals(deletedTag)){
                        MoviesXML.readAllMovies().get(i).getTags().remove(deletedTag);
                        MoviesXML.modifyMovie(MoviesXML.readAllMovies().get(i));
                    }
                }
            }

            int j=0;

            while (j < tagNodes.getLength()) {
                Node tagNode = tagNodes.item(j);

                if (tagNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tagElement = (Element) tagNode;

                    UUID id = UUID.fromString(tagElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(deletedTag.getId())) {
                        tagElement.getParentNode().removeChild(tagElement);
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
