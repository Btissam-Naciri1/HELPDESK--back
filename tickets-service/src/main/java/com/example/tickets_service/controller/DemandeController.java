package com.example.tickets_service.controller;

import com.example.tickets_service.dto.AppUserDTO;
import com.example.tickets_service.model.Demande;
import com.example.tickets_service.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/demande")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    // Fetch Users for dropdown (search optional)
    @GetMapping("/users")
    public ResponseEntity<List<AppUserDTO>> getUsers(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(demandeService.getUsers(search));
    }

    //  Create a new Demande with all fields
    @PostMapping("/create")
    public ResponseEntity<?> createDemande(@RequestBody Demande demande) {
        try {
            // Set creation date
            demande.setDateCreation(LocalDateTime.now());

            // Save demande
            Demande savedDemande = demandeService.createDemande(demande);

            return ResponseEntity.ok(
                    "Demande created successfully with ID: " + savedDemande.getId()
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    //  Fetch all demandes
    @GetMapping("/all")
    public ResponseEntity<List<Demande>> getAllDemandes() {
        return ResponseEntity.ok(demandeService.getAllDemandes());
    }

    //  Fetch a specific demande by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getDemandeById(@PathVariable Long id) {
        Optional<Demande> demande = demandeService.getDemandeById(id);
        return demande.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  Update an existing demande
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDemande(@PathVariable Long id, @RequestBody Demande updatedDemande) {
        try {
            Demande demande = demandeService.updateDemande(id, updatedDemande);
            return ResponseEntity.ok("Demande updated successfully: " + demande.getId());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Delete a demande
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDemande(@PathVariable Long id) {
        boolean isDeleted = demandeService.deleteDemande(id);
        if (isDeleted) {
            return ResponseEntity.ok("Demande deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Demande not found");
        }
    }

    @GetMapping("/{id}/workflow")
    public ResponseEntity<?> getWorkflowStatus(@PathVariable Long id) {
        Optional<Demande> demandeOpt = demandeService.getDemandeById(id);

        if (demandeOpt.isPresent()) {
            Demande demande = demandeOpt.get();

            // ✅ Define workflow progress
            List<String> workflowSteps = List.of(
                    "Consigner",
                    "Approuver",
                    "Exécuter",
                    "Accepter",
                    "Examiner",
                    "Clôturer",
                    "Abandonner"
            );

            // ✅ Find current step index
            int currentStepIndex = workflowSteps.indexOf(demande.getEtatWorkflow());

            return ResponseEntity.ok(Map.of(
                    "currentStep", demande.getEtatWorkflow(),
                    "workflowSteps", workflowSteps,
                    "currentStepIndex", currentStepIndex
            ));
        }

        return ResponseEntity.notFound().build();
    }

}
