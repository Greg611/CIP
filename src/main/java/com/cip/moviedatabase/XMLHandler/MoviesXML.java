package com.cip.moviedatabase.XMLHandler;

import com.cip.moviedatabase.Model.CastMember;
import com.cip.moviedatabase.Model.Movie;
import com.cip.moviedatabase.Model.Tags;
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

public class MoviesXML {
    public static LinkedList<Movie> readAllMovies() {
        Document doc = XMLFileBuilder.moviesFileBuilder();
        LinkedList<Movie> listOfMovies = new LinkedList<>();
        NodeList movieNodes = doc.getElementsByTagName("Movie");
        for (int i = 0; i < movieNodes.getLength(); i++) {
            Node movieNode = movieNodes.item(i);

            if (movieNode.getNodeType() == Node.ELEMENT_NODE) {
                Element movieElement = (Element) movieNode;

                UUID id = UUID.fromString(movieElement.getElementsByTagName("Id").item(0).getTextContent());
                String title = movieElement.getElementsByTagName("Title").item(0).getTextContent();
                Float imdb = Float.parseFloat(movieElement.getElementsByTagName("IMDB").item(0).getTextContent());
                Integer duration = Integer.parseInt(movieElement.getElementsByTagName("Duration").item(0).getTextContent());
                LocalDate releaseDate = LocalDate.parse(movieElement.getElementsByTagName("ReleaseDate").item(0).getTextContent());
                LinkedList<CastMember> director = new LinkedList<>();
                for (int j= 0; j<movieElement.getElementsByTagName("Director").getLength();j++){
                    director.add(CastMemberXML.readCastMember(UUID.fromString(movieElement.getElementsByTagName("Director").item(j).getTextContent())));
                }
                LinkedList<CastMember> cast = new LinkedList<>();
                for (int j= 0; j<movieElement.getElementsByTagName("Cast").getLength();j++){
                    cast.add(CastMemberXML.readCastMember(UUID.fromString(movieElement.getElementsByTagName("Cast").item(j).getTextContent())));
                }
                LinkedList<Tags> tags = new LinkedList<>();
                for (int j= 0; j<movieElement.getElementsByTagName("Tag").getLength();j++){
                    tags.add(TagsXML.readTag(UUID.fromString(movieElement.getElementsByTagName("Tag").item(j).getTextContent())));
                }

                Movie movie = new Movie(id,title,imdb,duration,releaseDate,director,cast,tags);
                listOfMovies.add(movie);
            }
        }
        return listOfMovies;
    }

    public static Movie readMovie(UUID searchedId) {
        Document doc = XMLFileBuilder.moviesFileBuilder();
        Movie movie = new Movie();
        NodeList movieNodes = doc.getElementsByTagName("Movie");

        int i = 0;
        while (i < movieNodes.getLength()) {
            Node movieNode = movieNodes.item(i);

            if (movieNode.getNodeType() == Node.ELEMENT_NODE) {
                Element movieElement = (Element) movieNode;

                UUID id = UUID.fromString(movieElement.getElementsByTagName("Id").item(0).getTextContent());
                if(id.equals(searchedId)) {
                    String title = movieElement.getElementsByTagName("Title").item(0).getTextContent();
                    Float imdb = Float.parseFloat(movieElement.getElementsByTagName("IMDB").item(0).getTextContent());
                    Integer duration = Integer.parseInt(movieElement.getElementsByTagName("Duration").item(0).getTextContent());
                    LocalDate releaseDate = LocalDate.parse(movieElement.getElementsByTagName("ReleaseDate").item(0).getTextContent());
                    LinkedList<CastMember> director = new LinkedList<>();
                    for (int j= 0; j<movieElement.getElementsByTagName("Director").getLength();j++){
                        director.add(CastMemberXML.readCastMember(UUID.fromString(movieElement.getElementsByTagName("Director").item(j).getTextContent())));
                    }
                    LinkedList<CastMember> cast = new LinkedList<>();
                    for (int j= 0; j<movieElement.getElementsByTagName("Cast").getLength();j++){
                        cast.add(CastMemberXML.readCastMember(UUID.fromString(movieElement.getElementsByTagName("Cast").item(j).getTextContent())));
                    }
                    LinkedList<Tags> tags = new LinkedList<>();
                    for (int j= 0; j<movieElement.getElementsByTagName("Tag").getLength();j++){
                        tags.add(TagsXML.readTag(UUID.fromString(movieElement.getElementsByTagName("Tag").item(j).getTextContent())));
                    }

                    movie = new Movie(id,title,imdb,duration,releaseDate,director,cast,tags);
                }
            }
            i++;
        }
        return movie;
    }

    public static void saveMovie(Movie newMovie){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/MoviesData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            Element movie = doc.createElement("Movie");

            Element id = doc.createElement("Id");
            id.appendChild(doc.createTextNode(UUID.randomUUID().toString()));
            movie.appendChild(id);

            Element title = doc.createElement("Title");
            title.appendChild(doc.createTextNode(newMovie.getTitle()));
            movie.appendChild(title);

            Element imdb = doc.createElement("IMDB");
            imdb.appendChild(doc.createTextNode(newMovie.getImdb().toString()));
            movie.appendChild(imdb);

            Element duration = doc.createElement("Duration");
            duration.appendChild(doc.createTextNode(newMovie.getDuration().toString()));
            movie.appendChild(duration);

            Element releaseDate = doc.createElement("ReleaseDate");
            releaseDate.appendChild(doc.createTextNode(newMovie.getReleaseDate().toString()));
            movie.appendChild(releaseDate);


            for (int i=0; i<newMovie.getDirectors().size();i++){
                Element directors = doc.createElement("Director");
                directors.appendChild(doc.createTextNode(newMovie.getDirectors().get(i).getId().toString()));
                movie.appendChild(directors);
            }



            for (int i=0; i < newMovie.getCast().size();i++){
                Element cast = doc.createElement("Cast");
                cast.appendChild(doc.createTextNode(newMovie.getCast().get(i).getId().toString()));
                movie.appendChild(cast);
            }


            for (int i=0; i < newMovie.getTags().size();i++){
                Element tags = doc.createElement("Tag");
                tags.appendChild(doc.createTextNode(newMovie.getTags().get(i).getId().toString()));
                movie.appendChild(tags);
            }


            root.appendChild(movie);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modifyMovie(Movie modifiedMovie){
        try {
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/MoviesData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            int i = 0;
            NodeList movieNodes = doc.getElementsByTagName("User");

            while (i < movieNodes.getLength()) {
                Node movieNode = movieNodes.item(i);

                if (movieNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element movieElement = (Element) movieNode;

                    UUID id = UUID.fromString(movieElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(modifiedMovie.getId())) {
                        movieElement.getElementsByTagName("Title").item(0).setTextContent(modifiedMovie.getTitle());
                        movieElement.getElementsByTagName("IMDB").item(0).setTextContent(modifiedMovie.getImdb().toString());
                        movieElement.getElementsByTagName("Duration").item(0).setTextContent(modifiedMovie.getDuration().toString());
                        movieElement.getElementsByTagName("ReleaseDate").item(0).setTextContent(modifiedMovie.getReleaseDate().toString());
                        movieElement.getElementsByTagName("Director").item(0).setTextContent("");
                        movieElement.getElementsByTagName("Cast").item(0).setTextContent("");
                        movieElement.getElementsByTagName("Tag").item(0).setTextContent("");
                        for (int j=0; j<modifiedMovie.getDirectors().size();j++){
                            movieElement.getElementsByTagName("Director").item(0).appendChild(doc.createTextNode(modifiedMovie.getDirectors().get(i).getId().toString()));
                        }
                        for (int j=0; j < modifiedMovie.getCast().size();j++){
                            movieElement.getElementsByTagName("Cast").item(0).appendChild(doc.createTextNode(modifiedMovie.getCast().get(i).getId().toString()));
                        }
                        for (int j=0; j < modifiedMovie.getTags().size();j++){
                            movieElement.getElementsByTagName("Tag").item(0).appendChild(doc.createTextNode(modifiedMovie.getTags().get(i).getId().toString()));
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

    public static void deleteMovie(Movie deletedMovie){
        try{
            File file = new File("src/main/java/com/cip/moviedatabase/XMLHandler/MoviesData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList movieNodes = doc.getElementsByTagName("Movie");

            for (int i = 0; i < CollectionsXML.readAllCollection().size(); i++) {
                for (int j = 0; j < CollectionsXML.readAllCollection().get(i).getMovies().size(); j++) {
                    if (CollectionsXML.readAllCollection().get(i).getMovies().get(j).equals(deletedMovie)){
                        CollectionsXML.readAllCollection().get(i).getMovies().remove(deletedMovie);
                        CollectionsXML.modifyCollection(CollectionsXML.readAllCollection().get(i));
                    }
                }
            }

            int j=0;

            while (j < movieNodes.getLength()) {
                Node movieNode = movieNodes.item(j);

                if (movieNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element movieElement = (Element) movieNode;

                    UUID id = UUID.fromString(movieElement.getElementsByTagName("Id").item(0).getTextContent());
                    if (id.equals(deletedMovie.getId())) {
                        movieElement.getParentNode().removeChild(movieElement);
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
