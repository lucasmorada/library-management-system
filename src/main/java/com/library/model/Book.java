package com.library.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {
    @Id private UUID id;
    @Column(nullable=false, length=180) private String title;
    @Column(nullable=false, length=160) private String author;
    @Column(name="publication_year", nullable=false) private int publicationYear;
    @Column(length=20, unique=true) private String isbn;
    @Column(name="created_at", nullable=false, updatable=false) private Instant createdAt;
    protected Book() {}
    public Book(String title, String author, int publicationYear, String isbn) {
        this.id=UUID.randomUUID(); this.title=title.trim(); this.author=author.trim(); this.publicationYear=publicationYear;
        this.isbn=isbn == null || isbn.isBlank() ? null : isbn.trim(); this.createdAt=Instant.now();
    }
    public void update(String title, String author, int publicationYear, String isbn) {
        this.title=title.trim(); this.author=author.trim(); this.publicationYear=publicationYear;
        this.isbn=isbn == null || isbn.isBlank() ? null : isbn.trim();
    }
    public UUID getId(){return id;} public String getTitle(){return title;} public String getAuthor(){return author;}
    public int getPublicationYear(){return publicationYear;} public String getIsbn(){return isbn;} public Instant getCreatedAt(){return createdAt;}
}
