package com.dians.lab.dianslab.service.impl;

import com.dians.lab.dianslab.model.User;
import com.dians.lab.dianslab.model.exceptions.InvalidArgumentsException;
import com.dians.lab.dianslab.model.exceptions.InvalidUserCredentialsException;
import com.dians.lab.dianslab.model.exceptions.UserAlreadyExistsException;
import com.dians.lab.dianslab.model.exceptions.UserNotFoundException;
import com.dians.lab.dianslab.repository.jpa.UserRepository;
import com.dians.lab.dianslab.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String name, String surname, String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();

        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
            throw new UserAlreadyExistsException(username);

        User user = new User(name, surname, username, password);
        return userRepository.save(user);
    }

    @Override
    public String getIdLibrary(String username_id) {
        return  this.userRepository.findByUsername(username_id).get().getId_library();
    }


}
