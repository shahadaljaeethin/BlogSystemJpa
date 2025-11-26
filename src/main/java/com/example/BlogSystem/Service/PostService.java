package com.example.BlogSystem.Service;

import com.example.BlogSystem.Api.ApiResponse;
import com.example.BlogSystem.Model.Category;
import com.example.BlogSystem.Model.Post;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Repository.CategoryRepository;
import com.example.BlogSystem.Repository.PostRepository;
import com.example.BlogSystem.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    //-

    public int addPost(Post post){

        Category cate = categoryRepository.findCategoryById(post.getCategoryID());
        if(cate==null) return 1;

        User user = userRepository.findUserById(post.getUserID());
        if(user==null)
        return 2;
        post.setPublishDate(LocalDate.now());
        postRepository.save(post);
        return 3;

    }

    public List<Post> getAll(){return postRepository.findAll();}


    public String updPost(Integer id, Post readInfo){

        Category cate = categoryRepository.findCategoryById(readInfo.getCategoryID());
        if(cate==null) return "category invalid";

        Post post = postRepository.findPostById(id);
        if(post==null) return "post id not found";

        post.setTitle(readInfo.getTitle());
        post.setContent((readInfo.getContent()));
        post.setCategoryID(readInfo.getCategoryID());
        //it is not logical to update the user id since a post can't transfer to another account
        //also it is not logical to update publish date


        postRepository.save(post);
        return "updated";

    }

    public boolean deletePost(Integer id){

        Post p = postRepository.findPostById(id);
        if(p==null) return false;

        postRepository.delete(p);
        return true;

    }

    //3
    public List<Post> findPostsBetween(LocalDate start, LocalDate end){

        return postRepository.findPostsBetween(start,end);

    }

    //8)
    public List<Post> getByCategory(Integer id){
     Category category = categoryRepository.findCategoryById(id);
     if(category==null) return null;

    return postRepository.findPostByCategoryID(id);
    }



}
