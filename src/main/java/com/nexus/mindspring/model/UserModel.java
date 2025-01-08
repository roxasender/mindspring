package com.nexus.mindspring.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @Size(max = 255) // store as url
    private String profilePicture;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_date", nullable = false)
    private Date joinDate = new Date();

    @Size(max = 50)
    private String languagePreference;

    @Column(name = "current_streak", nullable = false)
    private int currentStreak;

    public void incrementStreak() {
        this.currentStreak++;
    }

    public void resetStreak() {
        this.currentStreak = 0;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
    private Collection<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLessonProgresses> userLessonProgresses = new ArrayList<>();
}
