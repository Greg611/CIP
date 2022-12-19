package com.cip.moviedatabase.Model;

import java.time.LocalDate;

public class Admin extends User{
    public Admin(Integer id, String name, String password, LocalDate dob, String email) {
        super(id, name, password, dob, email);
    }

}
