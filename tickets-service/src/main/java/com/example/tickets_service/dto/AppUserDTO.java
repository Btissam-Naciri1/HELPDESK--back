package com.example.tickets_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // ✅ Ensures empty fields are ignored
public class AppUserDTO implements Serializable {

    @JsonProperty("nomcomplet")
    private String nomcomplet;

    @JsonProperty("email")
    private String email;

    @JsonProperty("typeUtilisateur")
    private String typeUtilisateur;

    // ✅ Explicitly add Getters (sometimes Lombok fails with Jackson)
    public String getNomcomplet() { return nomcomplet; }
    public String getEmail() { return email; }
    public String getTypeUtilisateur() { return typeUtilisateur; }
}
