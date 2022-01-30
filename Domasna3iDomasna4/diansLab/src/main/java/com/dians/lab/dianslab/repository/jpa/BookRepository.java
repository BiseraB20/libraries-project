package com.dians.lab.dianslab.repository.jpa;

import com.dians.lab.dianslab.model.Book;
import com.dians.lab.dianslab.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /*Optional<Book> findById(Long id);*/
    List<Book> findByLibraries(Library library);
    void  deleteByLibraries(Library library);
    void deleteBookByLibraries(Library library);
    Book findByName(String name);

    List<Book> findByAuthorNameContainingIgnoreCaseOrNameContainingIgnoreCase(String search, String search2);


}
