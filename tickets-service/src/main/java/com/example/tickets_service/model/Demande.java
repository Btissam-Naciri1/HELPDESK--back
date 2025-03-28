package com.example.tickets_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String etat;  // "En cours", "Assigné", "Terminé", etc.
    private String titre;  // Title of the request.

    @Lob
    @Column(length = 10000)
    private String description;  // Request details

    private LocalDateTime dateCreation;  // Date and time of creation
    private LocalDateTime dateResolutionPrevue;  // Expected resolution date
    private String createdBy;  // User who created the request
    private String requestedFor;  // Person for whom the request is made
    private String requestedBy;  // Person who submitted the request
    private String emplacementLivraison;  // Delivery location
    private String methodeContact;  // Preferred contact method (Phone, Email, etc.)

    @Lob
    @Column(length = 10000)
    private String remarques;  // Additional remarks

    // Classification details
    private String priorite;  // Priority level (e.g., P1, P2, P3, P4)
    private String impact;  // Impact scope (Single user, Multiple users, etc.)
    private String urgence;  // Urgency level (e.g., Blocking work)
    private String categorie;  // Request category
    private String service;  // Service associated with the request
    private boolean serviceCritique;  // Is the service critical?
    private String etenduePublique;  // Scope (Private/Public)


    private String typeDemande;  // Type of request (IT Service Request, etc.)
    private String prioriteMetier;  // Business priority
    private String typeAction;  // Type of action required
    private String composantService;  // Service component affected
    private String actifTypeDemande;  // Type of asset requested

    // Assignment details
    private String groupeAffectation;  // Assignment group
    private String businessUnit;  // Business unit
    private String responsable;  // Responsible person for handling the request


    private boolean resolutionDistance;  // Was the issue resolved remotely?
    private String solution;  // Description of the solution applied
    private String codeAchevement;  // Closure code (if applicable)
    private LocalDateTime dateHeureCloture;  // Date/time of closure
    private boolean detecteParNexthink;  // Was it detected by Nexthink?
    private String etatWorkflow;

    private String userResponse; // "Oui", "Non", or NULL
    private LocalDateTime validationStartTime; // When validation started
    private boolean notificationSent; // To track if the user was notified

    public String getEtatWorkflow() {
        return etatWorkflow;
    }

    public Long getId() {
        return id;
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateResolutionPrevue() {
        return dateResolutionPrevue;
    }

    public void setDateResolutionPrevue(LocalDateTime dateResolutionPrevue) {
        this.dateResolutionPrevue = dateResolutionPrevue;
    }


    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getRequestedFor() {
        return requestedFor;
    }

    public void setRequestedFor(String requestedFor) {
        this.requestedFor = requestedFor;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getEmplacementLivraison() {
        return emplacementLivraison;
    }

    public void setEmplacementLivraison(String emplacementLivraison) {
        this.emplacementLivraison = emplacementLivraison;
    }

    public String getMethodeContact() {
        return methodeContact;
    }

    public void setMethodeContact(String methodeContact) {
        this.methodeContact = methodeContact;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getUrgence() {
        return urgence;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isServiceCritique() {
        return serviceCritique;
    }

    public void setServiceCritique(boolean serviceCritique) {
        this.serviceCritique = serviceCritique;
    }

    public String getEtenduePublique() {
        return etenduePublique;
    }

    public void setEtenduePublique(String etenduePublique) {
        this.etenduePublique = etenduePublique;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }

    public String getPrioriteMetier() {
        return prioriteMetier;
    }

    public void setPrioriteMetier(String prioriteMetier) {
        this.prioriteMetier = prioriteMetier;
    }

    public String getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(String typeAction) {
        this.typeAction = typeAction;
    }

    public String getComposantService() {
        return composantService;
    }

    public boolean isNotificationSent() {
        return notificationSent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setEtatWorkflow(String etatWorkflow) {
        this.etatWorkflow = etatWorkflow;
    }

    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }

    public LocalDateTime getValidationStartTime() {
        return validationStartTime;
    }

    public void setValidationStartTime(LocalDateTime validationStartTime) {
        this.validationStartTime = validationStartTime;
    }

    public void setNotificationSent(boolean notificationSent) {
        this.notificationSent = notificationSent;
    }

    public void setComposantService(String composantService) {
        this.composantService = composantService;
    }

    public String getActifTypeDemande() {
        return actifTypeDemande;
    }

    public void setActifTypeDemande(String actifTypeDemande) {
        this.actifTypeDemande = actifTypeDemande;
    }

    public String getGroupeAffectation() {
        return groupeAffectation;
    }

    public void setGroupeAffectation(String groupeAffectation) {
        this.groupeAffectation = groupeAffectation;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public boolean isResolutionDistance() {
        return resolutionDistance;
    }

    public void setResolutionDistance(boolean resolutionDistance) {
        this.resolutionDistance = resolutionDistance;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getCodeAchevement() {
        return codeAchevement;
    }

    public void setCodeAchevement(String codeAchevement) {
        this.codeAchevement = codeAchevement;
    }

    public LocalDateTime getDateHeureCloture() {
        return dateHeureCloture;
    }

    public void setDateHeureCloture(LocalDateTime dateHeureCloture) {
        this.dateHeureCloture = dateHeureCloture;
    }

    public boolean isDetecteParNexthink() {
        return detecteParNexthink;
    }

    public void setDetecteParNexthink(boolean detecteParNexthink) {
        this.detecteParNexthink = detecteParNexthink;
    }
}
