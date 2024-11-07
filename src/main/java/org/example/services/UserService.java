package org.example.services;

import org.example.helpers.AppHelper;
import org.example.model.User;
import org.example.repository.Repository;

import java.util.List;

public class UserService implements Service<User>{
    private final AppHelper<User> userAppHelper;
    private final Repository<User> userRepository;

    public UserService(AppHelper<User> userAppHelper, Repository<User> userRepository) {
        this.userAppHelper = userAppHelper;
        this.userRepository = userRepository;
    }
    @Override
    public boolean add(){
        User user = userAppHelper.create();
        List<User> users = userRepository.load();
        if(user == null) {
            return false;
        }
        userRepository.save(user);
        return true;

    }

    @Override
    public boolean print() {
        return userAppHelper.printList(userRepository.load());
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public Repository<User> getRepository() {
        return userRepository;
    }
}