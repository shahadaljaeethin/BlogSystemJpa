package com.example.BlogSystem.Controller;

import com.example.BlogSystem.Api.ApiResponse;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog system/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user register"));
    }
    @GetMapping("/get")
    public ResponseEntity<?> getAll(){return ResponseEntity.status(200).body(userService.getAll());}

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@RequestBody @Valid User user, Errors errors){

        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if(userService.updateUser(id,user))
            return ResponseEntity.status(200).body(new ApiResponse(user.getUsername()+" account is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("no id with "+id));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
      if(userService.deleteUser(id))
          return ResponseEntity.status(200).body(new ApiResponse(id+" id is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("no id with "+id));
    }




    //(1)
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        User user = userService.getById(id);
        if(user==null)  return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        return ResponseEntity.status(200).body(user);
    }

}
