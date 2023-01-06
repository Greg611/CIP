package com.cip.moviedatabase.Service;

import com.cip.moviedatabase.Model.*;
import com.cip.moviedatabase.XMLHandler.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class Service {
    public static Boolean newAdmin(Map<String,String> request){
        UUID adminId = UUID.fromString(request.get("adminId"));
        String name = request.get("name");
        String pass = request.get("password");
        LocalDate dob = LocalDate.parse(request.get("dob"));
        String email = request.get("email");
        Admin admin = AdminXML.readAdmin(adminId);
        Admin newAdmin = new Admin(name,pass,dob,email);
        Boolean response = admin.adminCreateAdmin(newAdmin);
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
        Tags modifyTag = new Tags(tagId,name);
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

    public static Boolean newMovie(Map<String, String> request) {
        UUID adminId = UUID.fromString(request.get("adminId"));
        String title = request.get("title");
        Float imdb = Float.parseFloat(request.get("imdb"));
        Integer duration = Integer.parseInt(request.get("duration"));
        LocalDate release = LocalDate.parse(request.get("releaseDate"));

        Admin admin = AdminXML.readAdmin(adminId);
        Movie newMovie = new Movie(title, imdb, duration, release,);
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
        Admin admin = AdminXML.readAdmin(adminId);
        Movie modifiedMovie = new Movie(movieId, title, imdb, duration, release,);
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
}
/*
    @PostMapping
    @RequestMapping("newAdmin")
    public Boolean postNewAdmin(@RequestBody Map<String,String> request){
        Boolean response = service.newAdmin(request);
        return response;
    }
*/