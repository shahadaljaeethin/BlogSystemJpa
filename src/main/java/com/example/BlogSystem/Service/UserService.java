package com.example.BlogSystem.Service;

import com.example.BlogSystem.Model.Comment;
import com.example.BlogSystem.Model.Post;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Repository.CommentRepository;
import com.example.BlogSystem.Repository.PostRepository;
import com.example.BlogSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

import javax.swing.event.ListDataEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


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

    //delete all comments and posts which this user made:
    //1> comments
    List<Comment> comments = commentRepository.getUserComments(id);
    for(Comment c: comments) commentRepository.delete(c);

    //2> posts
    List<Post> posts = postRepository.getPostsofUser(id);
    for(Post p: posts) postRepository.delete(p);
    //=====================================================


    userRepository.delete(user);
    return true;

}


//(1) find account by username
public String getByUsername(String username){
    User user = userRepository.findUserByUsername(username);
    if(user==null) return "-1";

    String randomPostsOfThisUser = "username/ "+user.getUsername()+",  register since "+user.getRegisterDate();
    try {
        List<Post> last = postRepository.getPostsofUser(user.getId());
        System.out.println(last);
        Post lastPost = last.get(0);
        randomPostsOfThisUser+=",,   some Posts:   ";
        randomPostsOfThisUser+="title: "+lastPost.getTitle();
        randomPostsOfThisUser+=",   content:"+lastPost.getContent();
        }catch (ArrayIndexOutOfBoundsException e){randomPostsOfThisUser+="-  no post yet"; }
        catch (Exception e){randomPostsOfThisUser+="- no post yet";}

    return randomPostsOfThisUser;

}
//(2) log in
public User logIn(String username,String password){
    return  userRepository.findUserByUsernameAndPassword(username,password);
}

//(2) log in
public String logIn(String[] info){

        try {
            String username =  info[0];
            String password = info[1];
            //==
            User user = userRepository.findUserByUsernameAndPassword(username, password);
            if (user == null) return "Username or Password wrong";
            //-
            return "log in successfully";
        }catch (ArrayIndexOutOfBoundsException e){
            return "log-in info is missing";
        }
        catch (Exception e){return e.getMessage();}
    }

    public List<Post> checkFullPost(Integer id){
    User user = userRepository.findUserById(id);
    if(user==null) return null;

    return postRepository.getPostsofUser(id);
    }


    public ArrayList<String> suggestNewUsers(){
    //return list of usernames of today registered account (like insta)

        List<User> users = userRepository.getNewUsersOfToday();
        if(users.isEmpty()) return null;
        ArrayList<String> usernames = new ArrayList<>();
        for(User u:users) usernames.add(u.getUsername());

        return usernames;

    }




}
