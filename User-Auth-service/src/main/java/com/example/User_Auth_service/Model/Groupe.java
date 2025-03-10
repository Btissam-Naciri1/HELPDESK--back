package com.example.User_Auth_service.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Groupe {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nom; // Group name

         public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }
}
