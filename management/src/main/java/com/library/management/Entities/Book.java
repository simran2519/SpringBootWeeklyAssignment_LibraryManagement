package com.library.management.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;

    @ManyToOne(optional = true) //A book can exist without being issued to a member
    @JoinColumn(name="member_id")
    private LibraryMember issuedTo;
}
