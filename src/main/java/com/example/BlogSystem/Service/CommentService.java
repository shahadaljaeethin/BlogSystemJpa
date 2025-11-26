package com.example.BlogSystem.Service;

import com.example.BlogSystem.Model.Category;
import com.example.BlogSystem.Model.Comment;
import com.example.BlogSystem.Model.Post;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Repository.CommentRepository;
import com.example.BlogSystem.Repository.PostRepository;
import com.example.BlogSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
//=================================================

    public String addComment(Comment comment){

        Post post = postRepository.findPostById(comment.getPostID());
        if(post==null) return "Post not found";

        User user = userRepository.findUserById(comment.getUserID());
        if(user==null) return "log in to comment on this post";

        comment.setCommentDate(LocalDate.now());
        commentRepository.save(comment);
        return "Added";

    }

    public List<Comment> getAll(){return commentRepository.findAll();}


    public boolean updateComment(Integer id, Comment readInfo){
        Comment comment = commentRepository.findCommentById(id);
        if(comment==null) return false;
        //it is not logical to check for post/user IDs since a comment won't transfer.
        comment.setContent(readInfo.getContent());
        commentRepository.save(comment);
        return true;
    }

    public boolean deleteComment(Integer id){

        Comment comment = commentRepository.findCommentById(id);
        if(comment==null) return false;

        commentRepository.delete(comment);
        return true;

    }

    //all of a user
    public String deleteMyComments(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null) return "user not found";
        List<Comment> comments = commentRepository.getUserComments(id);
        if(comments.isEmpty()) return "you don't have any comment";

        for(Comment c: comments) commentRepository.delete(c);
        return "Deleted all";
    }

    public List<Comment> getMyComment(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null) return null;

        return commentRepository.getUserComments(id);
    }

}
