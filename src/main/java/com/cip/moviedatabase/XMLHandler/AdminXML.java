package com.cip.moviedatabase.XMLHandler;

import com.cip.moviedatabase.Model.Admin;
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
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AdminXML {
    public static List<Admin> readAllAdmin() {
        Document doc = XMLFileBuilder.usersFileBuilder();
        List<Admin> listOfAdmin = new LinkedList<>();
        NodeList adminNodes = doc.getElementsByTagName("Admin");
        for (int i = 0; i < adminNodes.getLength(); i++) {
            Node adminNode = adminNodes.item(i);

            if (adminNode.getNodeType() == Node.ELEMENT_NODE) {
                Element adminElement = (Element) adminNode;

                UUID id = UUID.fromString(adminElement.getElementsByTagName("Id").item(0).getTextContent());
                String name = adminElement.getElementsByTagName("Name").item(0).getTextContent();
                String password = adminElement.getElementsByTagName("Password").item(0).getTextContent();
                LocalDate dob = LocalDate.parse(adminElement.getElementsByTagName("DateOfBirth").item(0).getTextContent());
                String email = adminElement.getElementsByTagName("Email").item(0).getTextContent();

                Admin admin = new Admin(id, name, password, dob, email);
                listOfAdmin.add(admin);
            }
        }
        return listOfAdmin;
    }

    public static User readAdmin(UUID searchedId) {
        Document doc = XMLFileBuilder.usersFileBuilder();
        Admin admin = new Admin();
        NodeList adminNodes = doc.getElementsByTagName("Admin");

        int i = 0;
        while (i < adminNodes.getLength()) {
            Node adminNode = adminNodes.item(i);

            if (adminNode.getNodeType() == Node.ELEMENT_NODE) {
                Element adminElement = (Element) adminNode;

                UUID id = UUID.fromString(adminElement.getElementsByTagName("Id").item(0).getTextContent());
                if(id.equals(searchedId)) {
                    String name = adminElement.getElementsByTagName("Name").item(0).getTextContent();
                    String password = adminElement.getElementsByTagName("Password").item(0).getTextContent();
                    LocalDate dob = LocalDate.parse(adminElement.getElementsByTagName("DateOfBirth").item(0).getTextContent());
                    String email = adminElement.getElementsByTagName("Email").item(0).getTextContent();
                    admin = new Admin(id, name, password, dob, email);
                }
            }
            i++;
        }
        return admin;
    }

    public static void saveAdmin(Admin newAdmin){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element admin = doc.createElement("Admin");

            Element id = doc.createElement("Id");
            id.appendChild(doc.createTextNode(UUID.randomUUID().toString()));
            admin.appendChild(id);

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(newAdmin.getName()));
            admin.appendChild(name);

            Element password = doc.createElement("Password");
            password.appendChild(doc.createTextNode(newAdmin.getPassword()));
            admin.appendChild(password);

            Element dob = doc.createElement("DateOfBirth");
            dob.appendChild(doc.createTextNode(newAdmin.getDob().toString()));
            admin.appendChild(dob);

            Element email = doc.createElement("Email");
            email.appendChild(doc.createTextNode(newAdmin.getEmail()));
            admin.appendChild(email);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modifyAdmin(User modifiedAdmin){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            int i = 0;
            NodeList adminNodes = doc.getElementsByTagName("Admin");

            while (i < adminNodes.getLength()) {
                Node adminNode = adminNodes.item(i);

                if (adminNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element adminElement = (Element) adminNode;

                    UUID id = UUID.fromString(adminElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(modifiedAdmin.getId())) {
                        adminElement.getElementsByTagName("Name").item(0).setTextContent(modifiedAdmin.getName());
                        adminElement.getElementsByTagName("Password").item(0).setTextContent(modifiedAdmin.getPassword());
                        adminElement.getElementsByTagName("DateOfBirth").item(0).setTextContent(modifiedAdmin.getDob().toString());
                        adminElement.getElementsByTagName("Email").item(0).setTextContent(modifiedAdmin.getEmail());
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

    public static void deleteAdmin(User deletedAdmin){
        try{
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList adminNodes = doc.getElementsByTagName("User");

            //WIP
            //ha megvan a readCollection és a deleteCollection funkció, akkor visszatérni

            int j=0;

            while (j < adminNodes.getLength()) {
                Node adminNode = adminNodes.item(j);

                if (adminNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element adminElement = (Element) adminNode;

                    UUID id = UUID.fromString(adminElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(deletedAdmin.getId())) {
                        adminElement.getParentNode().removeChild(adminElement);
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
