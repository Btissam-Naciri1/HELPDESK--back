package com.example.tickets_service.service;

import com.example.tickets_service.dto.AppUserDTO;
import com.example.tickets_service.Client.UserClient;
import com.example.tickets_service.model.Demande;
import com.example.tickets_service.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private UserClient userClient;


    public List<AppUserDTO> getUsers(String search) {
        return userClient.getUsers(search);
    }


    public Demande createDemande(Demande demande) {
        return demandeRepository.save(demande);
    }


    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }


    public Optional<Demande> getDemandeById(Long id) {
        return demandeRepository.findById(id);
    }


    public Demande updateDemande(Long id, Demande updatedDemande) {
        return demandeRepository.findById(id).map(existingDemande -> {
            existingDemande.setTitre(updatedDemande.getTitre());
            existingDemande.setDescription(updatedDemande.getDescription());
            existingDemande.setEtat(updatedDemande.getEtat());
            existingDemande.setDateResolutionPrevue(updatedDemande.getDateResolutionPrevue());
            existingDemande.setPriorite(updatedDemande.getPriorite());
            existingDemande.setImpact(updatedDemande.getImpact());
            existingDemande.setUrgence(updatedDemande.getUrgence());
            existingDemande.setCategorie(updatedDemande.getCategorie());
            existingDemande.setService(updatedDemande.getService());
            existingDemande.setServiceCritique(updatedDemande.isServiceCritique());
            existingDemande.setEtenduePublique(updatedDemande.getEtenduePublique());
            existingDemande.setTypeDemande(updatedDemande.getTypeDemande());
            existingDemande.setPrioriteMetier(updatedDemande.getPrioriteMetier());
            existingDemande.setTypeAction(updatedDemande.getTypeAction());
            existingDemande.setComposantService(updatedDemande.getComposantService());
            existingDemande.setActifTypeDemande(updatedDemande.getActifTypeDemande());
            existingDemande.setGroupeAffectation(updatedDemande.getGroupeAffectation());
            existingDemande.setBusinessUnit(updatedDemande.getBusinessUnit());
            existingDemande.setResponsable(updatedDemande.getResponsable());
            existingDemande.setSolution(updatedDemande.getSolution());
            existingDemande.setCodeAchevement(updatedDemande.getCodeAchevement());
            existingDemande.setDateHeureCloture(updatedDemande.getDateHeureCloture());
            existingDemande.setDetecteParNexthink(updatedDemande.isDetecteParNexthink());

            return demandeRepository.save(existingDemande);
        }).orElseThrow(() -> new RuntimeException("Demande not found with ID: " + id));
    }


    public boolean deleteDemande(Long id) {
        if (demandeRepository.existsById(id)) {
            demandeRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public ResponseEntity<?> checkValidation(Long id) {
        Optional<Demande> demandeOpt = demandeRepository.findById(id);

        if (demandeOpt.isPresent()) {
            Demande demande = demandeOpt.get();

            if ("Validation en attente".equals(demande.getEtatWorkflow()) && demande.isNotificationSent()) {
                return ResponseEntity.ok(Map.of(
                        "notification", true,
                        "message", "Votre demande est terminée. Validez-vous la clôture ?",
                        "etatWorkflow", demande.getEtatWorkflow()
                ));
            }
        }
        return ResponseEntity.ok(Map.of("notification", false));
    }


    public ResponseEntity<?> updateUserResponse(Long id, String userResponse) {
        if (!"Oui".equalsIgnoreCase(userResponse) && !"Non".equalsIgnoreCase(userResponse)) {
            return ResponseEntity.badRequest().body("Invalid response. Use 'Oui' or 'Non'");
        }

        return demandeRepository.findById(id).map(demande -> {
            demande.setUserResponse(userResponse);

            if ("Oui".equalsIgnoreCase(userResponse)) {
                demande.setEtatWorkflow("Clôturer");
            } else {
                demande.setEtatWorkflow("Examiner");
            }

            demandeRepository.save(demande);

            return ResponseEntity.ok(Map.of(
                    "message", "User response updated successfully",
                    "newWorkflowState", demande.getEtatWorkflow()
            ));
        }).orElse(ResponseEntity.notFound().build());
    }



    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void autoCloseExpiredValidations() {
        List<Demande> demandes = demandeRepository.findByEtatWorkflow("Validation en attente");

        for (Demande demande : demandes) {
            if (demande.getValidationStartTime() != null &&
                    demande.getValidationStartTime().plusHours(24).isBefore(LocalDateTime.now())) {
                demande.setEtatWorkflow("Clôturer");
                demandeRepository.save(demande);
            }
        }
    }

    public ResponseEntity<?> getWorkflowStatus(Long id) {
        Optional<Demande> demandeOpt = demandeRepository.findById(id);

        if (demandeOpt.isPresent()) {
            Demande demande = demandeOpt.get();


            if ("Terminé".equals(demande.getEtatWorkflow())) {
                demande.setEtatWorkflow("Clôturer");
                demandeRepository.save(demande);
            }


            List<String> workflowSteps = List.of(
                    "Consigner",
                    "Approuver",
                    "Exécuter",
                    "Accepter",
                    "Examiner",
                    "Clôturer",
                    "Abandonner"
            );

            // ✅ Find the correct step index
            int currentStepIndex = workflowSteps.indexOf(demande.getEtatWorkflow());

            if (currentStepIndex == -1) {
                return ResponseEntity.badRequest().body(Map.of(
                        "error", "Invalid workflow state: " + demande.getEtatWorkflow(),
                        "currentStep", demande.getEtatWorkflow(),
                        "workflowSteps", workflowSteps
                ));
            }

            return ResponseEntity.ok(Map.of(
                    "currentStep", demande.getEtatWorkflow(),
                    "workflowSteps", workflowSteps,
                    "currentStepIndex", currentStepIndex
            ));
        }

        return ResponseEntity.notFound().build();
    }

}
