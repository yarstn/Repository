package com.example.user.Repository;

import com.example.user.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);
User findUserByEmail(String email);
List<User> findAllByRole(String role);
User findUserByUsernameAndPassword(String username, String password);
@Query("select c from User c where c.age>=?1")
List<User> findUserByAge(int age);
}
