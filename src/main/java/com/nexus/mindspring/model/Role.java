package com.nexus.mindspring.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<UserModel> users = new HashSet<>();

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public void assignRoleToUser(UserModel user) {
        user.getRoles().add(this);
        this.getUsers().add(user);
    }

    public void removeRoleFromUser(UserModel user) {
        user.getRoles().remove(this);
        this.getUsers().remove(user);
    }

    public void removeAllUsersFromRole() {
        if (this.getUsers() != null) {
            List<UserModel> users = this.getUsers().stream().toList();
            users.forEach(this :: removeRoleFromUser);
        }
    }

    public String getRoleName() {
        return roleName != null ? roleName : "";
    }
}
