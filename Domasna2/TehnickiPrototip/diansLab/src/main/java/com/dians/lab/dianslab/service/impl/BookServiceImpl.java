package com.dians.lab.dianslab.service.impl;

import com.dians.lab.dianslab.model.Book;
import com.dians.lab.dianslab.repository.impl.InMemoryBookRepository;
import com.dians.lab.dianslab.repository.jpa.BookRepository;
import com.dians.lab.dianslab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return this.bookRepository.findById(id).orElseThrow();
    }

}
