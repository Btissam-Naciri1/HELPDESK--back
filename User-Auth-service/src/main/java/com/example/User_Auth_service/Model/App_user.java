package com.example.User_Auth_service.Model;

import com.example.User_Auth_service.enums.Type_utilisateur;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data

public class App_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomcomplet;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Type_utilisateur typeUtilisateur;

    public App_user( String email,String nomcomplet, String password, Type_utilisateur typeUtilisateur) {
        this.email = email;
        this.nomcomplet=nomcomplet;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public App_user() {
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type_utilisateur getTypeUtilisateur() {  // ✅ Ensure this method exists
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(Type_utilisateur typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }
    // ✅ Explicit Constructor (Fixes the error)
    public App_user(String email, String password, Type_utilisateur typeUtilisateur) {
        this.email = email;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
    }
}