package com.library.management.ServicesInterfaces;

import com.library.management.Dtos.BookDto;

import java.util.List;

public interface BookServiceInter
{
    public BookDto addBook(BookDto bookDto);
    public BookDto searchBook(long bookId);
    public BookDto deleteBook(long bookId);
    public BookDto updateBook(BookDto bookDto,long bookId);
    public List<BookDto> addAllBooks(List<BookDto> bookDtoList);
    public List<BookDto> findAllBooks();
}
