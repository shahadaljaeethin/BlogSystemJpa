package com.example.BlogSystem.Service;

import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
private final UserRepository userRepository;

public void addUser(User user){

    if(user.getRegisterDate()==null)
    user.setRegisterDate(LocalDate.now());

    userRepository.save(user);
}

public List<User> getAll(){return userRepository.findAll();}


public boolean updateUser(Integer id, User readInfo){

    User user = userRepository.findUserById(id);
    if(user==null) return false;

    user.setUsername(readInfo.getUsername());
    user.setEmail(readInfo.getEmail());
    user.setPassword(readInfo.getPassword());

    userRepository.save(user);
    return true;

}

public boolean deleteUser(Integer id){

    User user = userRepository.findUserById(id);
    if(user==null) return false;

    userRepository.delete(user);
    return true;

}


//(1)
public User getById(Integer id){
    return userRepository.findUserById(id);
}


}
