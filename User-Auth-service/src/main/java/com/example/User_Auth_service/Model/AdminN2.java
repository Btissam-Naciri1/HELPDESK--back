package com.example.User_Auth_service.Model;

import com.example.User_Auth_service.enums.Role;
import com.example.User_Auth_service.enums.Type_utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class AdminN2 extends App_user {

    private String nom_complet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "group_admin_roles",  // ✅ Correct join table name
            joinColumns = @JoinColumn(name = "adminn2_id"),  // ✅ FK reference to AdminN2.id
            inverseJoinColumns = @JoinColumn(name = "group_id")  // ✅ FK reference to Groupe.id
    )
    private List<Groupe> groups;

    // ✅ Explicit Default Constructor (Required by Hibernate)
    public AdminN2() {
        super(); // ✅ Calls parent constructor (App_user)
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Groupe> getGroups() {
        return groups;
    }

    public void setGroups(List<Groupe> groups) {
        this.groups = groups;
    }

    public AdminN2(String email, String password, Role role, List<Groupe> groups) {
        super(email, password, Type_utilisateur.ADMIN_N2);
        this.role = role;
        this.groups = groups;
    }
}
