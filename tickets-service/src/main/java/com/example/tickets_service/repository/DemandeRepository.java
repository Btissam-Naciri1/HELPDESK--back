package com.example.tickets_service.repository;

import com.example.tickets_service.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {

    Demande findById(long id);
    // ✅ Find all demandes by etatWorkflow (used for auto-closing validation)
    List<Demande> findByEtatWorkflow(String etatWorkflow);

    // ✅ Custom Query: Find demandes where validation is pending
    List<Demande> findByEtatWorkflowAndNotificationSent(String etatWorkflow, boolean notificationSent);
}
