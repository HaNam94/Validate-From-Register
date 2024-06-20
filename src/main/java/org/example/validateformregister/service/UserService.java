package org.example.validateformregister.service;



import org.example.validateformregister.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    void save(User el);

   void remove(int id);
}
