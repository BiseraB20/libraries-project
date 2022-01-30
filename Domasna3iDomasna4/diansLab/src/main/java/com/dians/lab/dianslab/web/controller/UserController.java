package com.dians.lab.dianslab.web.controller;


import com.dians.lab.dianslab.model.Book;
import com.dians.lab.dianslab.model.Library;
import com.dians.lab.dianslab.service.BookService;
import com.dians.lab.dianslab.service.LibraryService;
import com.dians.lab.dianslab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    private final LibraryService libraryService;

    public UserController(UserService userService, BookService bookService, LibraryService libraryService) {
        this.userService = userService;
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @GetMapping
    public String getUserInterface(HttpServletRequest req, Model model) {
        if(req.getSession().getAttribute("user") == null){
            model.addAttribute("isLoggedIn", false);
            return "userInterface";
        }
        String username_id = (String) req.getSession().getAttribute("username");
        String id_library = this.userService.getIdLibrary(username_id);
        //    User employee= new User((String) req.getSession().getAttribute("user"));
        int numFreeSeats = 0;
        if (this.libraryService.findLibraryById(id_library).isReadingRoom()) {
            numFreeSeats = this.libraryService.getFreeSeats(id_library);
            model.addAttribute("isReadingRoom", true);
        } else {
            model.addAttribute("isReadingRoom", false);
        }

        List<Book> bookList = this.bookService.findBooksInLibrary(id_library);
        model.addAttribute("numFreeSeats", numFreeSeats);
        model.addAttribute("bookList", bookList);


        model.addAttribute("isLoggedIn", true);

        return "userInterface";

    }
    @PostMapping("/numSeats")
    public String setNumFreeSeats(@RequestParam String numSeats,HttpServletRequest req){
        int num=Integer.parseInt(numSeats);
        String username_id = (String) req.getSession().getAttribute("username");
        String id_library = this.userService.getIdLibrary(username_id);
        this.libraryService.setFreeSeats(id_library,num);
        return "redirect:/user";

    }
    @PostMapping("/add-form")
    public String getAddBookForm(HttpServletRequest req, Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        return "addbook";


    }
    @PostMapping("/save-book")
    public String saveBook(@RequestParam String name,

                           @RequestParam String author,
                           @RequestParam String desc,
                           @RequestParam String counter,  Model model,HttpServletRequest req){
        String username_id = (String) req.getSession().getAttribute("username");
        String id_library = this.userService.getIdLibrary(username_id);
        this.bookService.saveNewAddedBook(id_library,name,author,desc,counter);
        return "redirect:/user";
    }


//    @GetMapping
//    public String changeCounter(@RequestParam(required = false) Long id, @RequestParam String counterText){
//        if(this.bookService.findBookById(id).isPresent()){
//            this.bookService.findBookById(id).get().setCounter(Integer.parseInt(counterText));
//        }
//        return "SelectedBook";
//    }


}
