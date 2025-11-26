package com.example.BlogSystem.Repository;

import com.example.BlogSystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findCommentById(Integer id);
    //(4)
    @Query("select c from Comment c where c.userID = ?1")
    List<Comment> getUserComments(Integer userId);



}
