package com.dians.lab.dianslab.service;

import com.dians.lab.dianslab.model.Library;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {
    List<Library> findAllLibraries();
    List<Library> findAllReadingRooms();
    Library findLibraryById(String id);
}
