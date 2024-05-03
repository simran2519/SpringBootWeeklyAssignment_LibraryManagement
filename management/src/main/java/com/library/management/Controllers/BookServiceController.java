package com.library.management.Controllers;

import com.library.management.Services.BookAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookServiceController {
    @Autowired
    private BookAssignmentService bookAssignmentService;

    @PostMapping("/{bookId}/assign/{memberId}")
    public ResponseEntity<String> assignBookToMember(@PathVariable Long bookId, @PathVariable Long memberId) {
        String result = bookAssignmentService.assignBookToMember(bookId, memberId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{bookId}/return/{memberId}")
    public ResponseEntity<String> returnBook(@PathVariable long bookId,@PathVariable long memberId)
    {
        String result=bookAssignmentService.returnBook(bookId,memberId);
        return ResponseEntity.ok(result);
    }
}
