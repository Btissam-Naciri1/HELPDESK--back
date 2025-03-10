package com.example.User_Auth_service.Model;

import com.example.User_Auth_service.enums.Type_utilisateur;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class AdminN1 extends App_user {

    public AdminN1(String email, String password) {
        super(email, password, Type_utilisateur.ADMIN_N1);
    }
}
