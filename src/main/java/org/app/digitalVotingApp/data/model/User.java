package org.app.digitalVotingApp.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.app.digitalVotingApp.data.enums.Role;
import org.app.digitalVotingApp.data.enums.Status;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "user_profile")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "public_id",nullable = false,unique = true)
    private String userId;
   @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "nin",nullable = false,unique = true)
    private String nin;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="email",nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
     @Column(name="role",nullable = false)
    private Role role;
     @Column(name="profile_created_at",nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
   private Status status;

}
