package com.library.management.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LibraryMember")
public class LibraryMember
{
//    one to one with LIbrary
//    one to many with books and magazines
//    one to many with fine
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    @Column
    private String username;
    private String password;
    private String role;
    private int booksCheckedOut;
    final private int maxBooksAllowed=5;

    @OneToMany(mappedBy = "issuedTo", cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

}
