package com.library.management.Controllers;

import com.library.management.Dtos.BookDto;
import com.library.management.Services.BookService;
import com.library.management.Utils.MyResponseGenerator;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/book")
public class BookController
{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BookController.class);
    BookService bookService;
    BookController(BookService bookService)
    {
        this.bookService=bookService;
    }
    @GetMapping("hii")
    public String hii()
    {
        return "Hello";
    }
    @GetMapping("/hmm")
    public String hmm()
    {
        return "hmmmmmm";
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@RequestBody BookDto bookDto)
    {
        BookDto addedBookDto= bookService.addBook(bookDto);
        if(addedBookDto!=null)
        {
            return MyResponseGenerator.generateResponse(HttpStatus.CREATED,true,"Book is Created",addedBookDto);
        }
        else {
            return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Something went wrong", addedBookDto);
        }
    }
    @GetMapping("/findBook/{bookId}")
    public ResponseEntity<Object> findBook(@PathVariable long bookId)
    {
        BookDto booktoFind =bookService.searchBook(bookId);
        if(booktoFind!=null)
        {
            return MyResponseGenerator.generateResponse(HttpStatus.OK,true,"Book is found", booktoFind);
        }
        else {
            return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Book is not founc",booktoFind);
        }
    }
    @PutMapping("/update/{bookId}")
    public ResponseEntity<Object> update(@RequestBody BookDto bookDto,@PathVariable long bookId)
    {
        BookDto booktoUpdate = bookService.updateBook(bookDto,bookId);
        String author= booktoUpdate.getAuthor();
        if(booktoUpdate!=null)
        {
            return MyResponseGenerator.generateResponse(HttpStatus.OK,true,author,booktoUpdate);
        }
        else {
            return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"long id",booktoUpdate);
        }
    }
    @PostMapping("/addAll")
    public List<BookDto> addAll(@RequestBody List<BookDto> bookDtoList)
    {
       return bookService.addAllBooks(bookDtoList);
    }
    @DeleteMapping("/delete{bookId}/")
    public ResponseEntity<Object> deleteBook(@PathVariable long bookId)
    {
        BookDto bookDto= bookService.deleteBook(bookId);
        if(bookDto!=null)
        {
            return MyResponseGenerator.generateResponse(HttpStatus.OK,true,"Book is Deleted Successfully",bookDto);
        }
        else {
            return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Book is not Deleted Successfully",bookDto);
        }
    }
    @GetMapping("/findAll")
    public List<BookDto> findAll()
    {
        return bookService.findAllBooks();
    }
}
