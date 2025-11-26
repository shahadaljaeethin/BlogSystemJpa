package com.example.BlogSystem.Controller;

import com.example.BlogSystem.Api.ApiResponse;
import com.example.BlogSystem.Model.Post;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog system/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if(postService.addPost(post)==3)
        return ResponseEntity.status(200).body(new ApiResponse("post added"));
        if(postService.addPost(post)==1)
        return ResponseEntity.status(400).body(new ApiResponse("category not found for this post"));
        return ResponseEntity.status(400).body(new ApiResponse("log in to post")); //AKA userId not found

    }
           @GetMapping("/get")
            public ResponseEntity<?> getAll(){return ResponseEntity.status(200).body(postService.getAll());}



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors){

        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        String message= postService.updPost(id,post);
        if(message.equals("updated"))
            return ResponseEntity.status(200).body(new ApiResponse("post is updated"));
        return ResponseEntity.status(400).body(new ApiResponse(message));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
        if(postService.deletePost(id))
            return ResponseEntity.status(200).body(new ApiResponse("post is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("post not found"));
    }
    @GetMapping("/between/{s}/{e}")
    public ResponseEntity<?> getPostsBetween(@PathVariable LocalDate s,@PathVariable LocalDate e){

        List<Post> posts = postService.findPostsBetween(s,e);
        if(posts.isEmpty()) return ResponseEntity.status(400).body(new ApiResponse("no posts between these two dates"));
        return ResponseEntity.status(200).body(posts);


    }

    //8
    @GetMapping("by category/{cateID}")
    public ResponseEntity<?> getByCategory(@PathVariable Integer cateID){
    List<Post> posts = postService.getByCategory(cateID);
    if(posts==null) return ResponseEntity.status(400).body(new ApiResponse("Invalid category"));

    if(posts.isEmpty()) return ResponseEntity.status(400).body(new ApiResponse("no posts with this category yet"));
    return ResponseEntity.status(200).body(posts);
    }

}
