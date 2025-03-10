package com.example.User_Auth_service.Repository;

import com.example.User_Auth_service.Model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Groupe_Repository extends JpaRepository<Groupe, Long> {
    List<Groupe> findByNomIn(List<String> noms);
}
