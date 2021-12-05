package com.dians.lab.dianslab.web.controller;

import com.dians.lab.dianslab.model.Book;
import com.dians.lab.dianslab.model.Library;
import com.dians.lab.dianslab.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/libraries")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String getLibrariesPage(Model model){
        List<Library> libraries = this.libraryService.findAllLibraries();
        model.addAttribute("libraries", libraries);
        return "Libraries";
    }

    @GetMapping("/{id}")
    public String getSelectedBook(@PathVariable String id, Model model){
        Library library = libraryService.findLibraryById(id);
        model.addAttribute("library", library);
        return "SelectedLibrary";
    }
}
