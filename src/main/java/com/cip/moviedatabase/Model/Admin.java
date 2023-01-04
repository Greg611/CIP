package com.cip.moviedatabase.Model;

import com.cip.moviedatabase.XMLHandler.AdminXML;
import com.cip.moviedatabase.XMLHandler.MoviesXML;
import com.cip.moviedatabase.XMLHandler.UsersXML;

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

    public void deleteUser(User deletedUser){
        UsersXML.deleteUser(deletedUser);
    }

    public void modifyUser(User modifiedUser){
        UsersXML.modifyUser(modifiedUser);
    }

    public LinkedList<User> getAllUsers(){
        return UsersXML.readAllUsers();
    }

    public User getUser(UUID userId) {
        return UsersXML.readUser(userId);
    }

    public void createAdmin(Admin newAdmin){
        AdminXML.saveAdmin(newAdmin);
    }

    public void deleteAdmin(Admin deletedAdmin){
        AdminXML.deleteAdmin(deletedAdmin);
    }

    public void modifyAdmin(Admin modifiedAdmin){
        AdminXML.modifyAdmin(modifiedAdmin);
    }

    public void createMovie(Movie newMovie){
        MoviesXML.saveMovie(newMovie);
    }

    public void deleteMovie(Movie deletedMovie){
        MoviesXML.deleteMovie(deletedMovie);
    }

    public void modifyMovie(Movie modifiedMovie){ MoviesXML.modifyMovie(modifiedMovie); }
}
