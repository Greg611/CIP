package com.cip.moviedatabase.Service;

import com.cip.moviedatabase.Model.*;
import com.cip.moviedatabase.Model.Collection;
import com.cip.moviedatabase.XMLHandler.*;

import java.time.LocalDate;
import java.util.*;

public class Service {
    public static List<Admin> listAllAdmins(Map<String,String> request){
        UUID adminId = UUID.fromString(request.get("adminId"));
        Admin admin = AdminXML.readAdmin(adminId);
        List<Admin> response = admin.adminGetAllAdmins();
        return response;
    }

    public static Admin getAdmin(Map<String,String> request){
        UUID adminId = UUID.fromString(request.get("adminId"));
        Admin admin = AdminXML.readAdmin(adminId);
        return admin;
    }

    public static Boolean newAdmin(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        String name = request.get("name");
        String pass = request.get("password");
        LocalDate dob = LocalDate.parse(request.get("dob"));
        String email = request.get("email");
        Admin admin = AdminXML.readAdmin(adminId);
        Admin newAdmin = new Admin(name, pass, dob, email);
        Boolean response = admin.adminCreateAdmin(newAdmin);
        return response;
    }
    public static Boolean modifyAdmin(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID userId = UUID.fromString(request.get("userId"));
        String name = request.get("name");
        String pass = request.get("password");
        LocalDate dob = LocalDate.parse(request.get("dob"));
        String email = request.get("email");
        Admin admin = AdminXML.readAdmin(adminId);
        User modifiedAdmin = new Admin(userId, name, pass, dob, email);
        Boolean response = admin.adminModifyUser(modifiedAdmin);
        return response;
    }

    public static Boolean deleteAdmin(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID userId = UUID.fromString(request.get("userId"));

        Admin admin = AdminXML.readAdmin(adminId);
        User deletedAdmin = AdminXML.readAdmin(userId);
        Boolean response = admin.adminDeleteUser(deletedAdmin);
        return response;
    }

    public static List<User> listAllUsers(Map<String,String> request){
        UUID adminId = UUID.fromString(request.get("adminId"));
        Admin admin = AdminXML.readAdmin(adminId);
        List<User> resposnse = admin.adminGetAllUsers();
        return resposnse;
    }

    public static User getUser(Map<String,String> request){
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID userId = UUID.fromString(request.get("userId"));
        Admin admin = AdminXML.readAdmin(adminId);
        User resposnse = admin.adminGetUser(userId);
        return resposnse;
    }

    public static Boolean newUser(Map<String,String> request){
        String name = request.get("name");
        String pass = request.get("password");
        LocalDate dob = LocalDate.parse(request.get("dob"));
        String email = request.get("email");
        User newUser = new User(name,pass,dob,email);
        Boolean response = UsersXML.saveUser(newUser);
        return response;
    }

    public static Boolean modifyUser(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID userId = UUID.fromString(request.get("userId"));
        String name = request.get("name");
        String pass = request.get("password");
        LocalDate dob = LocalDate.parse(request.get("dob"));
        String email = request.get("email");
        Admin admin = AdminXML.readAdmin(adminId);
        User modifiedUser = new User(userId, name, pass, dob, email);
        Boolean response = admin.adminModifyUser(modifiedUser);
        return response;
    }

    public static Boolean deleteUser(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID userId = UUID.fromString(request.get("userId"));

        Admin admin = AdminXML.readAdmin(adminId);
        User deletedUser = UsersXML.readUser(userId);
        Boolean response = admin.adminDeleteUser(deletedUser);
        return response;
    }

    public static LinkedList<CastMember> listCastMembers(Map<String,String> request){
        UUID adminId = UUID.fromString(request.get("adminId"));
        Admin admin = AdminXML.readAdmin(adminId);
        LinkedList<CastMember> response = admin.adminReadAllCastMember();
        return response;
    }

    public static Boolean newCastMember(Map<String, String> request) {
        UUID adminID = UUID.fromString(request.get("adminId"));
        String name = request.get("name");
        String alias = request.get("alias");
        LocalDate dob = LocalDate.parse(request.get("dob"));
        Admin admin = AdminXML.readAdmin(adminID);
        CastMember newCastMember = new CastMember(name, alias, dob);
        Boolean response = admin.adminCreateCastMember(newCastMember);
        return response;
    }

    public static Boolean modifyCastMember(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID castMemberId = UUID.fromString(request.get("castMemberId"));
        String name = request.get("name");
        String alias = request.get("alias");
        LocalDate dob = LocalDate.parse(request.get("dob"));
        Admin admin = AdminXML.readAdmin(adminId);
        CastMember modifiedCastMember = new CastMember(castMemberId, name, alias, dob);
        Boolean response = admin.adminModifyCastMember(modifiedCastMember);
        return response;
    }

    public static Boolean deleteCastMember(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID castMemberId = UUID.fromString(request.get("deletedCastmember"));
        CastMember deletedCastMember = CastMemberXML.readCastMember(castMemberId);
        Admin admin = AdminXML.readAdmin(adminId);
        Boolean response = admin.adminDeleteCastMember(deletedCastMember);
        return response;
    }

    public static LinkedList<Tags> listTags(Map<String,String> request){
        UUID adminId = UUID.fromString(request.get("adminId"));
        Admin admin = AdminXML.readAdmin(adminId);
        LinkedList<Tags> response = admin.adminReadAllTags();
        return response;
    }

    public static Boolean newTag(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        String name = request.get("name");
        Admin admin = AdminXML.readAdmin(adminId);
        Tags newTag = new Tags(name);
        Boolean response = admin.adminCreateTag(newTag);
        return response;
    }

    public static Boolean modifyTag(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID tagId = UUID.fromString(request.get("tagId"));
        String name = request.get("name");
        Admin admin = AdminXML.readAdmin(adminId);
        Tags modifyTag = new Tags(tagId, name);
        Boolean response = admin.adminModifyTag(modifyTag);
        return response;
    }

    public static Boolean deleteTag(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID tagId = UUID.fromString(request.get("tagId"));
        Admin admin = AdminXML.readAdmin(adminId);
        Tags newTag = TagsXML.readTag(tagId);
        Boolean response = admin.adminDeleteTag(newTag);
        return response;
    }

    public static Movie getMovie(Map<String,String> request){
        UUID movieId = UUID.fromString(request.get("movieId"));
        Movie response = MoviesXML.readMovie(movieId);
        return response;
    }

    public static Boolean newMovie(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        String title = request.get("title");
        Float imdb = Float.parseFloat(request.get("imdb"));
        Integer duration = Integer.parseInt(request.get("duration"));
        LocalDate release = LocalDate.parse(request.get("releaseDate"));
        String directorString = request.get("directors");
        LinkedList<CastMember> directors = getCastMemberListFromString(directorString);
        String castMemberString = request.get("castMembers");
        LinkedList<CastMember> castMembers = getCastMemberListFromString(castMemberString);
        String tagString = request.get("tags");
        LinkedList<Tags> tags = getTagListFromString(tagString);
        Admin admin = AdminXML.readAdmin(adminId);
        Movie newMovie = new Movie(title, imdb, duration, release, directors, castMembers, tags);
        Boolean response = admin.adminCreateMovie(newMovie);
        return response;
    }

    public static Boolean modifyMovie(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID movieId = UUID.fromString(request.get("movieId"));
        String title = request.get("title");
        Float imdb = Float.parseFloat(request.get("imdb"));
        Integer duration = Integer.parseInt(request.get("duration"));
        LocalDate release = LocalDate.parse(request.get("releaseDate"));
        String directorString = request.get("directors");
        LinkedList<CastMember> directors = getCastMemberListFromString(directorString);
        String castMemberString = request.get("castMembers");
        LinkedList<CastMember> castMembers = getCastMemberListFromString(castMemberString);
        String tagString = request.get("tags");
        LinkedList<Tags> tags = getTagListFromString(tagString);
        Admin admin = AdminXML.readAdmin(adminId);
        Movie modifiedMovie = new Movie(movieId, title, imdb, duration, release, directors, castMembers, tags);
        Boolean response = admin.adminModifyMovie(modifiedMovie);
        return response;
    }

    public static Boolean deleteMovie(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        UUID movieId = UUID.fromString(request.get("movieId"));
        Admin admin = AdminXML.readAdmin(adminId);
        Movie deletedMovie = MoviesXML.readMovie(movieId);
        Boolean response = admin.adminDeleteMovie(deletedMovie);
        return response;
    }

    public static LinkedList<Collection> listCollections(Map<String,String> request){
        UUID userId = UUID.fromString(request.get("userId"));
        User user = UsersXML.readUser(userId);
        LinkedList<Collection> response = user.getCollections();
        return response;
    }

    public static Collection getCollection(Map<String,String> request) {
        UUID userId = UUID.fromString(request.get("userId"));
        UUID collecionId = UUID.fromString(request.get("collectioId"));
        User user = UsersXML.readUser(userId);
        Collection response = user.userGetCollection(collecionId);
        return response;
    }

    public static Boolean newCollection(Map<String,String> request){
        UUID userId = UUID.fromString(request.get("userId"));
        UUID collectionId = UUID.fromString(request.get("collectionId"));
        String name = request.get("name");
        User user = UsersXML.readUser(userId);
        Boolean response = user.userCreateCollection(name);
        return response;
    }

    public static Boolean modifyCollectionName(Map<String,String> request){
        UUID userId = UUID.fromString(request.get("userId"));
        UUID collectionId = UUID.fromString(request.get("collectionId"));
        String newName = request.get("name");
        User user = UsersXML.readUser(userId);
        Boolean response = user.userChangeCollectionName(collectionId,newName);
        return response;
    }

    public static Boolean addMovieToCollection(Map<String,String> request){
        UUID userId = UUID.fromString(request.get("userId"));
        UUID collectionId = UUID.fromString(request.get("collectioId"));
        UUID movieId = UUID.fromString(request.get("movieId"));
        User user = UsersXML.readUser(userId);
        Boolean response = user.userAddMovieToCollection(collectionId,movieId);
        return response;
    }

    public static Boolean removeMovieFromCollection(Map<String,String> request){
        UUID userId = UUID.fromString(request.get("userId"));
        UUID collectionId = UUID.fromString(request.get("collectioId"));
        UUID movieId = UUID.fromString(request.get("movieId"));
        User user = UsersXML.readUser(userId);
        Boolean response = user.userDeleteMovieFromCollection(collectionId,movieId);
        return response;
    }

    public static Boolean deleteCollection(Map<String,String> request){
        UUID userId = UUID.fromString(request.get("userId"));
        UUID collectionId = UUID.fromString(request.get("collectionId"));
        User user = UsersXML.readUser(userId);
        Boolean response = user.userDeleteCollection(collectionId);
        return response;
    }




    private static LinkedList<CastMember> getCastMemberListFromString(String string) {
        LinkedList<CastMember> result = new LinkedList<>();
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<Integer> tokens = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ';') {
                tokens.add(i);
            }
        }
        tokens.add(string.length());
        for (int i = 0; i < tokens.size(); i++) {
            if (i == 0) {
                ids.add(string.substring(0, tokens.get(i)));
            } else {
                ids.add(string.substring((tokens.get(i - 1) + 1), (tokens.get(i))));
            }
        }
        for(int i=0;i< ids.size();i++){
            CastMember castMember = CastMemberXML.readCastMember(UUID.fromString(ids.get(i)));
            result.add(castMember);
        }
        return result;
    }

    private static LinkedList<Tags> getTagListFromString(String string){
        LinkedList<Tags> result = new LinkedList<>();
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<Integer> tokens = new ArrayList<>();
        for(int i=0;i<string.length();i++){
            if(string.charAt(i) == ';'){
                tokens.add(i);
            }
        }
        tokens.add(string.length());
        for(int i=0;i<tokens.size();i++){
            if(i==0){
                ids.add(string.substring(0,tokens.get(i)));
            }
            else{
                ids.add(string.substring((tokens.get(i-1)+1),(tokens.get(i))));
            }
        }
        for(int i=0;i<ids.size();i++){
            Tags tag = TagsXML.readTag(UUID.fromString(ids.get(i)));
            result.add(tag);
        }
        return result;
    }
}
/*
    @PostMapping
    @RequestMapping("newAdmin")
    public Boolean postNewAdmin(@RequestBody Map<String,String> request){
        Boolean response = service.newAdmin(request);
        return response;
    }
*/