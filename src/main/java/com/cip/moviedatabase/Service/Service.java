package com.cip.moviedatabase.Service;

import com.cip.moviedatabase.Model.Admin;
import com.cip.moviedatabase.XMLHandler.AdminXML;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class Service {
    public Boolean newAdmin(Map<String,String> o){
        UUID adminID = UUID.fromString(o.get("adminId"));
        String name = o.get("name");
        String pass = o.get("password");
        LocalDate dob = LocalDate.parse(o.get("dob"));
        String email = o.get("email");
        Admin admin = AdminXML.readAdmin(adminID);
        Admin newAdmin = new Admin(name,pass,dob,email);
        Boolean result = admin.adminCreateAdmin(newAdmin);
        return true;
    }
}
