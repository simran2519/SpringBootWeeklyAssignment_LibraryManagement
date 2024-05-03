package com.library.management.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.Dtos.BookDto;
import com.library.management.Entities.Book;
import com.library.management.Repositories.BookRepository;
import com.library.management.ServicesInterfaces.BookServiceInter;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class BookService implements BookServiceInter {
    private BookRepository bookRepository;
    private ObjectMapper objectMapper;

    public BookService(BookRepository bookRepository, ObjectMapper objectMapper)
    {
        this.bookRepository=bookRepository;
        this.objectMapper=objectMapper;
    }

    @Override
    public BookDto addBook(BookDto bookDto)
    {
        Book newBook=  objectMapper.convertValue(bookDto, Book.class);
        bookRepository.save(newBook);
        return objectMapper.convertValue(newBook,BookDto.class);
    }

    @Override
    public BookDto searchBook(long bookId)
    {
        Book bookToSearch= bookRepository.findById(bookId).get();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper.convertValue(bookToSearch,BookDto.class);
    }

    @Override
    public BookDto deleteBook(long bookId) {
        Book bookToDelete= bookRepository.findById(bookId).get();
         bookRepository.deleteById(bookId);
        return objectMapper.convertValue(bookToDelete,BookDto.class);
    }

    @Override
    public BookDto updateBook(BookDto bookDto, long bookId) {
        Book bookToMatch = bookRepository.findById(bookId).get();
        Book bookToUpdate = objectMapper.convertValue(bookDto, Book.class);
//        if(bookToMatch.getId()==bookToUpdate.getId())
//        {
            bookToUpdate.setId(bookId); //We set by some other value also I am setting it with same id as before
            bookRepository.save(bookToUpdate);
            return objectMapper.convertValue(bookToMatch, BookDto.class);
//        }
//        else {
//            return null;
//        }
    }
    @Override
    public List<BookDto> addAllBooks(List<BookDto> bookDtoList) {
        List<Book> bookList= Arrays.asList(objectMapper.convertValue(bookDtoList,Book[].class));
        bookRepository.saveAll(bookList);
        return Arrays.asList(objectMapper.convertValue(bookList,BookDto[].class));
    }

    @Override
    public List<BookDto> findAllBooks() {
        List<Book> bookList= bookRepository.findAll();
        return Arrays.asList(objectMapper.convertValue(bookList,BookDto[].class));
    }

}
