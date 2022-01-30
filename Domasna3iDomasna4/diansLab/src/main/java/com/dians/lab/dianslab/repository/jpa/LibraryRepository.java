package com.dians.lab.dianslab.repository.jpa;

import com.dians.lab.dianslab.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, String> {
    Library findLibraryById(String id);
}
