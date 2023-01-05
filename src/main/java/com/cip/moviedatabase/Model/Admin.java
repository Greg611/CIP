package com.cip.moviedatabase.Model;

import com.cip.moviedatabase.XMLHandler.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;

public class Admin extends User{
    public Admin(UUID id, String name, String password, LocalDate dob, String email) {
        super(id, name, password, dob, email);
    }

    public Admin(String name, String password, LocalDate dob, String email) {
        super(name, password, dob, email);
    }

    public Admin() {
    }

    public Boolean adminDeleteUser(User deletedUser){
        UsersXML.deleteUser(deletedUser);
        return true;
    }

    public Boolean adminModifyUser(User modifiedUser){
        UsersXML.modifyUser(modifiedUser);
        return true;
    }

    public LinkedList<User> adminGetAllUsers(){
        return UsersXML.readAllUsers();
    }

    public User adminGetUser(UUID userId) {
        return UsersXML.readUser(userId);
    }

    public Boolean adminCreateAdmin(Admin newAdmin){
        AdminXML.saveAdmin(newAdmin);
        return true;
    }

    public Boolean adminDeleteAdmin(Admin deletedAdmin){
        AdminXML.deleteAdmin(deletedAdmin);
        return true;
    }

    public Boolean adminModifyAdmin(Admin modifiedAdmin){
        AdminXML.modifyAdmin(modifiedAdmin);
        return true;
    }

    public Boolean adminCreateMovie(Movie newMovie){
        MoviesXML.saveMovie(newMovie);
        return true;
    }

    public Boolean adminDeleteMovie(Movie deletedMovie){
        MoviesXML.deleteMovie(deletedMovie);
        return true;
    }

    public Boolean adminModifyMovie(Movie modifiedMovie){
        MoviesXML.modifyMovie(modifiedMovie);
        return true;
    }

    public LinkedList<Tags> adminReadAllTags(){
        return TagsXML.readAllTags();
    }

    public Boolean adminCreateTag(Tags newTag){
        TagsXML.saveTag(newTag);
        return true;
    }

    public Boolean adminDeleteTag(Tags deletedTag){
        TagsXML.deleteTag(deletedTag);
        return true;
    }

    public Boolean adminModifyTag(Tags modifiedTag){
        TagsXML.modifyTag(modifiedTag);
        return true;
    }

    public LinkedList<CastMember> adminReadAllCastMember(){
        return CastMemberXML.readAllCastMembers();
    }

    public Boolean adminCreateCastMember(CastMember newCastMember){
        CastMemberXML.saveCastMember(newCastMember);
        return true;
    }

    public Boolean adminDeleteCastMember(CastMember deletedCastMember){
        CastMemberXML.deleteCastMember(deletedCastMember);
        return true;
    }

    public Boolean adminModifyCastMember(CastMember modifiedCastMember){
        CastMemberXML.modifyCastMember(modifiedCastMember);
        return true;
    }


}
