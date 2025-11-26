package com.example.BlogSystem.Controller;


import com.example.BlogSystem.Api.ApiResponse;
import com.example.BlogSystem.Model.Comment;
import com.example.BlogSystem.Model.Post;
import com.example.BlogSystem.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog system/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @GetMapping("/get")
    public ResponseEntity<?> getAll(){return ResponseEntity.status(200).body(commentService.getAll());}


    @PostMapping("/add")
    public ResponseEntity<?> makeComment(@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        String message = commentService.addComment(comment);
        if(commentService.addComment(comment).equals("Added"))
            return ResponseEntity.status(200).body(new ApiResponse("comment added"));
            return ResponseEntity.status(400).body(new ApiResponse(message));

    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if(commentService.updateComment(id,comment))
            return ResponseEntity.status(200).body(new ApiResponse("comment is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("comment not found"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeComment(@PathVariable Integer id){
        if(commentService.deleteComment(id))
            return ResponseEntity.status(200).body(new ApiResponse("comment is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("comment not found"));
    }


    //5.1
    @DeleteMapping("/user comments/remove all/{id}")
    public ResponseEntity<?> deleteMyComments(@PathVariable Integer id){
    String message = commentService.deleteMyComments(id);
    if(message.equals("Deleted all"))
        return ResponseEntity.status(200).body(new ApiResponse("your comments got deleted"));
    return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    //5.2
    @GetMapping("/user comments/{id}")
    public ResponseEntity<?> getAllmyComments(@PathVariable Integer id){
    if(commentService.getMyComment(id)==null) return ResponseEntity.status(400).body(new ApiResponse("user not found"));

    return ResponseEntity.status(200).body(commentService.getMyComment(id));

    }


}
