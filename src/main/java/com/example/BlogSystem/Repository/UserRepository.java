package com.example.BlogSystem.Repository;

import com.example.BlogSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

        User findUserById(Integer id);
        //-
        //(1) this later used to return a profile
        User findUserByUsername(String username);
        //(2)
        User findUserByUsernameAndPassword(String username,String password);

        //6)
        @Query("select u from User u where u.registerDate = CURRENT_DATE ")
        List<User> getNewUsersOfToday();
}
