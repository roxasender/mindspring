package com.nexus.mindspring.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    private String passwordHash;

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
}
