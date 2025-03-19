package com.example.tickets_service.repository;

import com.example.tickets_service.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
