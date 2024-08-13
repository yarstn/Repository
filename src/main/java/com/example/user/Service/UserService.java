package com.example.user.Service;

import com.example.user.ApiException.ApiException;
import com.example.user.Model.User;
import com.example.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void add(User user) {
        userRepository.save(user);
    }
    public void update(Integer id,User user) {
        User user1 = userRepository.findUserById(id);
        if(user1 == null) {
            throw  new ApiException("no user found");
        }
        user.setName(user1.getName());
        user.setEmail(user1.getEmail());
        user.setPassword(user1.getPassword());
        user.setAge(user1.getAge());
        user.setRole(user1.getRole());
        userRepository.save(user);
    }
    public void delete(Integer id) {
        User user = userRepository.findUserById(id);
        if(user == null) {
            throw  new ApiException("no user found");
        }
        userRepository.delete(user);
    }
    public User getUserByEmail(String email) {
User user = userRepository.findUserByEmail(email);
if (user == null) {
    throw  new ApiException("no user found");
}
return user;
    }
    public List<User> getUsersByRole(String role) {
        List<User> users = userRepository.findAllByRole(role);
        if (users == null) {
            throw  new ApiException("no user found");
        }
        return users;
    }
    public User getUserByUserNameAndPassword(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user == null) {
            throw  new ApiException("no user found");
        }
        return user;
    }
    public List<User> getUserByAge(int age) {
        List<User> user = userRepository.findUserByAge(age);
        if (user == null) {
            throw  new ApiException("no user found");
        }
        return user;
    }
}
