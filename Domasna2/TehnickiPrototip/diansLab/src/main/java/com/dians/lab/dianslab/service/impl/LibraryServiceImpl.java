package com.dians.lab.dianslab.service.impl;

import com.dians.lab.dianslab.model.Library;
import com.dians.lab.dianslab.repository.impl.InMemoryLibraryRepository;
import com.dians.lab.dianslab.repository.jpa.LibraryRepository;
import com.dians.lab.dianslab.service.LibraryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public List<Library> findAllLibraries() {
        return libraryRepository.findAll().stream().filter(r-> !r.isReadingRoom()).collect(Collectors.toList());
    }
    public List<Library> findAllReadingRooms() {
        return libraryRepository.findAll().stream().filter(r-> r.isReadingRoom()).collect(Collectors.toList());
    }

    public Library findLibraryById(String id) {
        return this.libraryRepository.findLibraryById(id);
    }

}
