package com.dians.lab.dianslab.service;

import com.dians.lab.dianslab.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> findAllBooks();
    Book findBookById(Long id);
}
