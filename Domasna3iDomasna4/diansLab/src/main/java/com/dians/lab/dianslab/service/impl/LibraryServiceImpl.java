package com.dians.lab.dianslab.service.impl;

import com.dians.lab.dianslab.model.Library;
import com.dians.lab.dianslab.repository.impl.InMemoryLibraryRepository;
import com.dians.lab.dianslab.repository.jpa.LibraryRepository;
import com.dians.lab.dianslab.service.LibraryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Override
    public List<Library> findAllReadingRooms() {
        return libraryRepository.findAll().stream().filter(r-> r.isReadingRoom()).collect(Collectors.toList());
    }
    @Override
    public Library findLibraryById(String id) {
        return this.libraryRepository.findLibraryById(id);
    }


    public Integer getFreeSeats(String id) {

        if(this.libraryRepository.getById(id).isReadingRoom()) {
            return this.libraryRepository.getById(id).getCounter();
        }
        else
            return 0;

    }
    @Transactional
    @Override
    public void setFreeSeats(String id_library, int num) {
        Library l=findLibraryById(id_library);
        l.setCounter(num);
        this.libraryRepository.save(l);
    }


}
