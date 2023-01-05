package com.cip.moviedatabase.XMLHandler;

import com.cip.moviedatabase.Model.CastMember;
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
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CastMemberXML {
    public static LinkedList<CastMember> readAllCastMembers() {
        Document doc = XMLFileBuilder.castMemberFileBuilder();
        LinkedList<CastMember> listOfCastMember = new LinkedList<>();
        NodeList castMemberNodes = doc.getElementsByTagName("CastMember");
        for (int i = 0; i < castMemberNodes.getLength(); i++) {
            Node castMemberNode = castMemberNodes.item(i);

            if (castMemberNode.getNodeType() == Node.ELEMENT_NODE) {
                Element castMemberElement = (Element) castMemberNode;

                UUID id = UUID.fromString(castMemberElement.getElementsByTagName("Id").item(0).getTextContent());
                String name = castMemberElement.getElementsByTagName("Name").item(0).getTextContent();
                LocalDate dob = LocalDate.parse(castMemberElement.getElementsByTagName("DateOfBirth").item(0).getTextContent());
                String alias = castMemberElement.getElementsByTagName("Alias").item(0).getTextContent();

                CastMember castMember = new CastMember(id, name, alias, dob);
                listOfCastMember.add(castMember);
            }
        }
        return listOfCastMember;
    }

    public static CastMember readCastMember(UUID searchedId) {
        Document doc = XMLFileBuilder.castMemberFileBuilder();
        CastMember castMember = new CastMember();
        NodeList castMemberNodes = doc.getElementsByTagName("CastMember");
        int i = 0;
        while (i < castMemberNodes.getLength()) {
            Node castMemberNode = castMemberNodes.item(i);

            if (castMemberNode.getNodeType() == Node.ELEMENT_NODE) {
                Element castMemberElement = (Element) castMemberNode;

                UUID id = UUID.fromString(castMemberElement.getElementsByTagName("Id").item(0).getTextContent());
                if(id.equals(searchedId)) {
                    String name = castMemberElement.getElementsByTagName("Name").item(0).getTextContent();
                    LocalDate dob = LocalDate.parse(castMemberElement.getElementsByTagName("DateOfBirth").item(0).getTextContent());
                    String alias = castMemberElement.getElementsByTagName("Alias").item(0).getTextContent();
                    castMember = new CastMember(id, name, alias, dob);
                }
            }
            i++;
        }
        return castMember;
    }

    public static void saveCastMember(CastMember castMember){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/CastMemberData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element castMemberElement = doc.createElement("CastMember");

            Element id = doc.createElement("Id");
            id.appendChild(doc.createTextNode(UUID.randomUUID().toString()));
            castMemberElement.appendChild(id);

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(castMember.getName()));
            castMemberElement.appendChild(name);

            Element alias = doc.createElement("Alias");
            alias.appendChild(doc.createTextNode(castMember.getAlias()));
            castMemberElement.appendChild(alias);

            Element dob = doc.createElement("DateOfBirth");
            dob.appendChild(doc.createTextNode(castMember.getDob().toString()));
            castMemberElement.appendChild(dob);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modifyCastMember(CastMember modifiedCastMember){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/CastMemberData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            int i = 0;
            NodeList castMemberNodes = doc.getElementsByTagName("CastMember");

            while (i < castMemberNodes.getLength()) {
                Node castMemberNode = castMemberNodes.item(i);

                if (castMemberNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element castMemberElement = (Element) castMemberNode;

                    UUID id = UUID.fromString(castMemberElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(modifiedCastMember.getId())) {
                        castMemberElement.getElementsByTagName("Name").item(0).setTextContent(modifiedCastMember.getName());
                        castMemberElement.getElementsByTagName("Alias").item(0).setTextContent(modifiedCastMember.getAlias());
                        castMemberElement.getElementsByTagName("DateOfBirth").item(0).setTextContent(modifiedCastMember.getDob().toString());}
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

    public static void deleteCastMember(CastMember deletedCastMember){
        try{
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/CastMemberData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList castMemberNodes = doc.getElementsByTagName("CastMember");

            for (int i = 0; i < MoviesXML.readAllMovies().size(); i++) {

                for (int j = 0; j < MoviesXML.readAllMovies().get(i).getDirectors().size(); j++) {
                    MoviesXML.readAllMovies().get(i).getDirectors().get(j);
                    if (MoviesXML.readAllMovies().get(i).getDirectors().get(j).equals(deletedCastMember)){
                        MoviesXML.readAllMovies().get(i).getDirectors().remove(deletedCastMember);
                    }
                }
                for (int j = 0; j < MoviesXML.readAllMovies().get(i).getCast().size(); j++) {
                    MoviesXML.readAllMovies().get(i).getCast().get(j);
                    if (MoviesXML.readAllMovies().get(i).getCast().get(j).equals(deletedCastMember)){
                        MoviesXML.readAllMovies().get(i).getCast().remove(deletedCastMember);
                    }
                }
                MoviesXML.modifyMovie(MoviesXML.readAllMovies().get(i));
            }
            int j=0;

            while (j < castMemberNodes.getLength()) {
                Node castMemberNode = castMemberNodes.item(j);

                if (castMemberNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element castMemberElement = (Element) castMemberNode;

                    UUID id = UUID.fromString(castMemberElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(deletedCastMember.getId())) {
                        castMemberElement.getParentNode().removeChild(castMemberElement);
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

