package com.example.BlogSystem.Repository;

import com.example.BlogSystem.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoryById(Integer id);

    Category findCategoryByName(String name);

}
