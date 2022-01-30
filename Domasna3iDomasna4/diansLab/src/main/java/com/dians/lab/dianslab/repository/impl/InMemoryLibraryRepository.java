package com.dians.lab.dianslab.repository.impl;


import com.dians.lab.dianslab.bootstrap.DataHolder;
import com.dians.lab.dianslab.model.Book;
import com.dians.lab.dianslab.model.Library;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryLibraryRepository {

    public List<Library> findAll() {
        return DataHolder.libraryList;
    }

    public Library findLibraryById(String id){
        return DataHolder.libraryList.stream().filter(r->r.getId().equals(id)).findFirst().orElseThrow();
    }
}
