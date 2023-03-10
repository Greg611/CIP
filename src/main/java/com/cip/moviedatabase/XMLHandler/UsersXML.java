package com.cip.moviedatabase.XMLHandler;

import com.cip.moviedatabase.Model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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


public class UsersXML {
    public static LinkedList<User> readAllUsers() {
        Document doc = XMLFileBuilder.usersFileBuilder();
        LinkedList<User> listOfUsers = new LinkedList<>();
        NodeList userNodes = doc.getElementsByTagName("User");
        for (int i = 0; i < userNodes.getLength(); i++) {
            Node userNode = userNodes.item(i);

            if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                Element userElement = (Element) userNode;

                if(userElement.getAttribute("auth_level").equals("Admin")){

                }
                else {
                    UUID id = UUID.fromString(userElement.getElementsByTagName("Id").item(0).getTextContent());
                    String name = userElement.getElementsByTagName("Name").item(0).getTextContent();
                    String password = userElement.getElementsByTagName("Password").item(0).getTextContent();
                    LocalDate dob = LocalDate.parse(userElement.getElementsByTagName("DateOfBirth").item(0).getTextContent());
                    String email = userElement.getElementsByTagName("Email").item(0).getTextContent();

                    User user = new User(id, name, password, dob, email);
                    listOfUsers.add(user);
                }
            }
        }
        return listOfUsers;
    }

    public static User readUser(UUID searchedId) {
        Document doc = XMLFileBuilder.usersFileBuilder();
        User user = new User();
        NodeList userNodes = doc.getElementsByTagName("User");

        int i = 0;
        while (i < userNodes.getLength()) {
            Node userNode = userNodes.item(i);

            if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                Element userElement = (Element) userNode;

                UUID id = UUID.fromString(userElement.getElementsByTagName("Id").item(0).getTextContent());
                if(id.equals(searchedId)) {
                    String name = userElement.getElementsByTagName("Name").item(0).getTextContent();
                    String password = userElement.getElementsByTagName("Password").item(0).getTextContent();
                    LocalDate dob = LocalDate.parse(userElement.getElementsByTagName("DateOfBirth").item(0).getTextContent());
                    String email = userElement.getElementsByTagName("Email").item(0).getTextContent();
                    user = new User(id, name, password, dob, email);
                }
            }
            i++;
        }
        return user;
    }

    public static Boolean saveUser(User newUser){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);



            Element user = doc.createElement("User");

            Element id = doc.createElement("Id");
            id.appendChild(doc.createTextNode(UUID.randomUUID().toString()));
            user.appendChild(id);
            System.out.println("ID appended");

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(newUser.getName()));
            user.appendChild(name);
            System.out.println("Name appended");

            Element password = doc.createElement("Password");
            password.appendChild(doc.createTextNode(newUser.getPassword()));
            user.appendChild(password);
            System.out.println("Pass appended");

            Element dob = doc.createElement("DateOfBirth");
            dob.appendChild(doc.createTextNode(newUser.getDob().toString()));
            user.appendChild(dob);
            System.out.println("Dob appended");

            Element email = doc.createElement("Email");
            email.appendChild(doc.createTextNode(newUser.getEmail()));
            user.appendChild(email);
            System.out.println("Email appended");

            Element root = doc.getDocumentElement();

            root.appendChild(user);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);
            return true;
        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modifyUser(User modifiedUser){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            int i = 0;
            NodeList userNodes = doc.getElementsByTagName("User");

            while (i < userNodes.getLength()) {
                Node userNode = userNodes.item(i);

                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;

                    UUID id = UUID.fromString(userElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(modifiedUser.getId())) {
                        userElement.getElementsByTagName("Name").item(0).setTextContent(modifiedUser.getName());
                        userElement.getElementsByTagName("Password").item(0).setTextContent(modifiedUser.getPassword());
                        userElement.getElementsByTagName("DateOfBirth").item(0).setTextContent(modifiedUser.getDob().toString());
                        userElement.getElementsByTagName("Email").item(0).setTextContent(modifiedUser.getEmail());
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

    public static void deleteUser(User deletedUser){
        try{
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList userNodes = doc.getElementsByTagName("User");

            for (int i = 0; i < deletedUser.getCollections().size(); i++) {
                CollectionsXML.deleteCollection(deletedUser.getCollections().get(i));
            }

            int j=0;

            while (j < userNodes.getLength()) {
                Node userNode = userNodes.item(j);

                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;

                    UUID id = UUID.fromString(userElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(deletedUser.getId())) {
                        userElement.getParentNode().removeChild(userElement);
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
