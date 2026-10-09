package com.library.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="library_users")
public class LibraryUser {
    @Id private UUID id;
    @Column(nullable=false, length=140) private String name;
    @Column(nullable=false, unique=true, length=180) private String email;
    @Enumerated(EnumType.STRING) @Column(name="user_type", nullable=false, length=20) private UserType userType;
    @Column(name="created_at", nullable=false, updatable=false) private Instant createdAt;
    protected LibraryUser() {}
    public LibraryUser(String name, String email, UserType userType) {
        this.id=UUID.randomUUID(); this.name=name.trim(); this.email=email.trim().toLowerCase(); this.userType=userType; this.createdAt=Instant.now();
    }
    public void update(String name, String email, UserType userType){this.name=name.trim();this.email=email.trim().toLowerCase();this.userType=userType;}
    public UUID getId(){return id;} public String getName(){return name;} public String getEmail(){return email;} public UserType getUserType(){return userType;} public Instant getCreatedAt(){return createdAt;}
}
