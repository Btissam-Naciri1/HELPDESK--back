package com.example.User_Auth_service.DTO;

import com.example.User_Auth_service.enums.Type_utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private Type_utilisateur typeUtilisateur;
    private String adresse;
    private String telephone;
    private String nomcomplet;
    private List<String> groups; // Only applicable for Admin N2

    // ✅ Constructor
    public UserDto( String email, String nomcomplet, Type_utilisateur typeUtilisateur) {
        this.email = email;
        this.nomcomplet = nomcomplet;
        this.typeUtilisateur = typeUtilisateur;
    }



    // ✅ Getters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getNomcomplet() { return nomcomplet; }
    public Type_utilisateur getTypeUtilisateur() { return typeUtilisateur; }
}
