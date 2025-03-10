package com.example.User_Auth_service.Model;

import com.example.User_Auth_service.enums.Role;
import com.example.User_Auth_service.enums.Type_utilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends App_user {
    private String adresse;
    private String telephone;


    public User(String email, String password, String adresse, String telephone) {
        super(email, password, Type_utilisateur.CLIENT);  // Remove null argument
        this.adresse = adresse;
        this.telephone = telephone;
    }
}
