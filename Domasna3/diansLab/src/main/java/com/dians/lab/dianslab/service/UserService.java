package com.dians.lab.dianslab.service;

import com.dians.lab.dianslab.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User login(String username, String password);
    User register(String name, String surname, String username, String password);
    String getIdLibrary(String username_id);

}
