package com.library.management.Services;

import com.library.management.Entities.Book;
import com.library.management.Entities.LibraryMember;
import com.library.management.Exceptions.ResourceNotFoundException;
import com.library.management.Repositories.BookRepository;
import com.library.management.Repositories.LibraryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookAssignmentService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryMemberRepository libraryMemberRepository;

    public String assignBookToMember(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        LibraryMember member = libraryMemberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));

        if (!book.isAvailable()) {
            return "Book is not available for assignment.";
        }

        if (member.getBooksCheckedOut() >= member.getMaxBooksAllowed()) {
            return "Member has reached the maximum allowed books checked out.";
        }

        book.setAvailable(false);
        book.setIssuedTo(member);
        member.getBookList().add(book);
        member.setBooksCheckedOut(member.getBooksCheckedOut() + 1);

        bookRepository.save(book);
        libraryMemberRepository.save(member);

        return "Book assigned successfully to the member.";
    }
    public String returnBook(long bookId, long memberId)
    {
        Book book= bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book not found with id: "+ bookId));
        LibraryMember libraryMember= libraryMemberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException("Member is not found with this id : "+ memberId));
        book.setAvailable(true);
        book.setIssuedTo(null);
        libraryMember.getBookList().remove(book);
        libraryMember.setBooksCheckedOut(libraryMember.getBooksCheckedOut()-1);
        bookRepository.save(book);
        libraryMemberRepository.save(libraryMember);
        return "Book is returned successfully";
    }
}