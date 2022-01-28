package com.dians.lab.dianslab.service;

import com.dians.lab.dianslab.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> findAllBooks();
    Book findBookById(Long id);
    List<Book> findSimilar(String text);
    List<Book> sortByMostRead();
    Book findMostReadBook();
    List<Book> fullTextSearch(String search);
    List<Book> findBooksInLibrary(String id);
    void deleteTheBookFromLibrary(Long id,String id_library);
    void save(Long id, String name, String author, String desc, Integer counter, Integer counter_read);
    void saveNewAddedBook(String id_library, String name, String author, String desc, String counter);

}
