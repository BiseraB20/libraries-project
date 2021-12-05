package com.dians.lab.dianslab.web.controller;


import com.dians.lab.dianslab.model.Library;
import com.dians.lab.dianslab.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/readingrooms")
public class ReadingRoomsController {
    private final LibraryService libraryService;

    public ReadingRoomsController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String getReadingRoomsPage(Model model){
        List<Library> readingRooms = this.libraryService.findAllReadingRooms();
        model.addAttribute("readingRooms", readingRooms);
        return "ReadingRooms";
    }

    @GetMapping("/{id}")
    public String getSelectedBook(@PathVariable String id, Model model){
        Library readingRoom = libraryService.findLibraryById(id);
        model.addAttribute("readingRoom", readingRoom);
        return "SelectedReadingRoom";
    }
}
