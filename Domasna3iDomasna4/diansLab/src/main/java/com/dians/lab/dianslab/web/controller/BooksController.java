package com.dians.lab.dianslab.web.controller;


import com.dians.lab.dianslab.model.Book;
import com.dians.lab.dianslab.service.BookService;
import com.dians.lab.dianslab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    private final UserService userService;



    public BooksController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String searchText,
                               Model model, HttpServletRequest req){

        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.findAllBooks();

        if(searchText!=null) {
            books = this.bookService.fullTextSearch(searchText);
        }
        model.addAttribute("books", books);
        model.addAttribute("mostReadBooks",this.bookService.sortByMostRead());
        if(req.getSession().getAttribute("user") == null){
            model.addAttribute("isLoggedIn", false);
            model.addAttribute("bodyContent", "Books");
            return "master-template";
        }
        model.addAttribute("isLoggedIn", true);
        model.addAttribute("bodyContent", "Books");

        return "master-template";
    }

    @GetMapping("/{id}")
    public String getSelectedBook(@PathVariable Long id, Model model, HttpServletRequest req){
        if(req.getSession().getAttribute("user") == null){
            model.addAttribute("isLoggedIn", false);

        }
        else{
            model.addAttribute("isLoggedIn", true);
        }


        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        List<Book> similarBooks = this.bookService.findSimilar(book.getAuthorName());
        model.addAttribute("similarBooks",similarBooks);
        return "SelectedBook";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBookFromLibrary(@PathVariable(required = false) Long id, HttpServletRequest req) {

        String userename= (String) req.getSession().getAttribute("username");
        String id_library=  this.userService.getIdLibrary( userename);
        this.bookService.deleteTheBookFromLibrary(id,id_library);
        return "redirect:/user";

    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,  Model model) {
        if (this.bookService.findBookById(id) != null) {
            Book book = this.bookService.findBookById(id);
            model.addAttribute("book",book);
            return "editbook";
        } else {
            return "redirect:/user";//kako query go isprakjame,ponatamu mozhe da go ispechatime
        }
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,@RequestParam String name,

                           @RequestParam String author,
                           @RequestParam String desc,
                           @RequestParam String counter,  Model model) {
        Integer cc = this.bookService.findBookById(id).getCounter();

        this.bookService.save(id, name, author, desc, Integer.parseInt(counter), cc);
        return "redirect:/user";
    }

    @PostMapping("/chitana/{id}")
    public String readBookCounterIncrement(@PathVariable Long id){
        this.bookService.findBookById(id).setCounter_read(this.bookService.findBookById(id).getCounter_read());
        Book book= this.bookService.findBookById(id);

        Integer cc =book.getCounter();
        Integer novC=book.getCounter_read()+1;
        this.bookService.save(book.getId(),book.getName(),book.getAuthorName(),book.getDescription(),cc,novC);
        return "redirect:/user";
    }



}
