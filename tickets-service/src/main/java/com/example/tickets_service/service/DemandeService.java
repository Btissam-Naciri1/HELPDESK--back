package com.example.tickets_service.service;

import com.example.tickets_service.dto.AppUserDTO;
import com.example.tickets_service.Client.UserClient;
import com.example.tickets_service.model.Demande;
import com.example.tickets_service.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private UserClient userClient; // Feign Client for User Service

    // ✅ Fetch Users from User Service
    public List<AppUserDTO> getUsers(String search) {
        return userClient.getUsers(search);
    }

    // ✅ Save new demande
    public Demande createDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    // ✅ Get all demandes
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    // ✅ Get demande by ID
    public Optional<Demande> getDemandeById(Long id) {
        return demandeRepository.findById(id);
    }

    // ✅ Update demande
    public Demande updateDemande(Long id, Demande updatedDemande) {
        return demandeRepository.findById(id).map(existingDemande -> {
            // Update fields only if provided in the request
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

    // ✅ Delete demande
    public boolean deleteDemande(Long id) {
        if (demandeRepository.existsById(id)) {
            demandeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
