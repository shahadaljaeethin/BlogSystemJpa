package com.example.BlogSystem.Repository;

import com.example.BlogSystem.Model.Post;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostById(Integer id);
    //(3)
    @Query("select p from Post p where p.publishDate between ?1 and ?2")
    List<Post> findPostsBetween(LocalDate start, LocalDate end);

    //(5)
    @Query("select p from Post p where p.userID = ?1")
    List<Post> getPostsofUser(Integer userId);

    //(7)
    @Query("select p from Post p group by p.categoryID order by count(p) desc")
    Post getMostUsedCategory();
    //(8)
    List<Post> findPostByCategoryID(Integer id);
}
