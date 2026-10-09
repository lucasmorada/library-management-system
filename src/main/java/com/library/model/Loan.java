package com.library.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="loans", indexes={@Index(name="idx_loans_user", columnList="user_id")})
public class Loan {
    @Id private UUID id;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="book_id", nullable=false) private Book book;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="user_id", nullable=false) private LibraryUser user;
    @Column(name="loaned_at", nullable=false) private Instant loanedAt;
    @Column(name="due_at", nullable=false) private Instant dueAt;
    @Column(name="returned_at") private Instant returnedAt;
    protected Loan() {}
    public Loan(Book book, LibraryUser user, Instant loanedAt, Instant dueAt){this.id=UUID.randomUUID();this.book=book;this.user=user;this.loanedAt=loanedAt;this.dueAt=dueAt;}
    public void markReturned(Instant at){if(returnedAt!=null) throw new IllegalStateException("Loan has already been returned.");returnedAt=at;}
    public UUID getId(){return id;} public Book getBook(){return book;} public LibraryUser getUser(){return user;}
    public Instant getLoanedAt(){return loanedAt;} public Instant getDueAt(){return dueAt;} public Instant getReturnedAt(){return returnedAt;}
    public boolean isActive(){return returnedAt==null;}
}
