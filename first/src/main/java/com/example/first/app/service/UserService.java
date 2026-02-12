package com.example.first.app.service;

import com.example.first.app.controller.UserController;
import com.example.first.app.exception.UserNotFoundException;
import com.example.first.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private Map<Integer, User> userDb = new HashMap<>();
//    Creating instance for logger for adding logs
    private final Logger logger = LoggerFactory.getLogger(this.toString());

    public User createUser(User user) {
        logger.info("CREATING USER....................INFO");
        logger.error("Checking for................... Error");
        logger.trace("Checking for ..................... Trace");
        logger.debug("User email added.........DEBUG " + user.getEmail());
        System.out.println(user.getEmail());
        userDb.putIfAbsent(user.getId(),user);


        return user;
    }

    public User updateUser(User user){
        if(!userDb.containsKey(user.getId())){
            logger.error("Error when finding user with id {} : ", user.getId());
//            return null;
            // Exception handeling
            throw new UserNotFoundException("User with ID " + user.getId() + " does not Exist");
        }
        userDb.put(user.getId(),user);
        return user;
    }

    public boolean deleteUser(int id) {
        if(!userDb.containsKey(id)){
            throw new UserNotFoundException("User with ID " + id + " does not Exist");
        }
        userDb.remove(id);
        return true;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userDb.values());
    }

    public User getUserById(int id) {
        return userDb.get(id);
    }

    public List<User> searchUser(String name, String email) {
//        System.out.println(name);
        return userDb.values().stream()
                .filter(u-> u.getName().equalsIgnoreCase(name))
                .filter(u-> u.getEmail().equalsIgnoreCase(email))
                .toList();
    }
}
