package com.dians.lab.dianslab.service.impl;

import com.dians.lab.dianslab.model.Book;
import com.dians.lab.dianslab.model.Library;
import com.dians.lab.dianslab.repository.jpa.BookRepository;
import com.dians.lab.dianslab.repository.jpa.LibraryRepository;
import com.dians.lab.dianslab.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    public BookServiceImpl(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return this.bookRepository.findById(id).orElseThrow();
    }


    @Override
    public Book findMostReadBook() {
        int max=-1;
        Book maxRead = new Book();
        List<Book> books = this.bookRepository.findAll();
        for(int i=0; i<books.size(); i++){
            if(books.get(i).getCounter_read() > max){
                max = books.get(i).getCounter_read();
                maxRead = books.get(i);
            }
        }
        return maxRead;
    }

    @Override
    public List<Book> sortByMostRead() {
        return this.bookRepository.findAll().stream().sorted(Comparator.comparing(Book::getCounter_read).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Book> findSimilar(String text) {
        return this.bookRepository.findAll().stream().filter(b -> b.getAuthorName().equals(text)).collect(Collectors.toList());
    }


    @Override
    public List<Book> findBooksInLibrary(String id) {
        Library library = libraryRepository.findById(id).orElseThrow(() -> new RuntimeException());
        //  List<Book> bookList=      this.bookRepository.findAll().stream().filter(b->b.getLibraries().contains(library)).collect(Collectors.toList());
        return this.bookRepository.findByLibraries(library);
    }

    @Override
    public void deleteTheBookFromLibrary(Long id, String id_library) {

        Library library = libraryRepository.findById(id_library).orElseThrow(() -> new RuntimeException());
        Book book =this.bookRepository.findById(id).orElseThrow();
        book.getLibraries().removeIf(i -> i.getId().equals(library.getId()));
        this.bookRepository.save(book);
        if(book.getLibraries().isEmpty()){
            bookRepository.deleteById(id);
        }


    }



    @Transactional
    @Override
    public void save(Long id,String name, String author, String desc, Integer counter, Integer counter_read) {
        List<Library> libraries=  this.bookRepository.findById(id).get().getLibraries();

        if(this.bookRepository.existsById(id))
        {
            this.bookRepository.deleteById(id);
            this.bookRepository.save(new Book(name, desc, counter, author, libraries, counter_read));

        }
        else
            this.bookRepository.save(new Book(name, desc, counter, author, libraries, 0));
    }
    @Override
    public  List<Book> fullTextSearch(String search){
        return this.bookRepository.findByAuthorNameContainingIgnoreCaseOrNameContainingIgnoreCase(search,search);
    }

    @Override
    public void saveNewAddedBook(String id_library,String name, String author, String desc, String counter){
        if(this.bookRepository.findByName(name)!=null){
            Book book = this.bookRepository.findByName(name);
            book.getLibraries().add(  this.libraryRepository.getById(id_library));
            this.bookRepository.save(book);
        }
        else{
            List<Library> l=new ArrayList<>();
            l.add(this.libraryRepository.getById(id_library));
            this.bookRepository.save(new Book(name, desc, Integer.parseInt(counter), author,l, 0));
        }

    }


}
