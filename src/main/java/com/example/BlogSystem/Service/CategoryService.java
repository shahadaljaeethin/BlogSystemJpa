package com.example.BlogSystem.Service;

import com.example.BlogSystem.Model.Category;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
private final CategoryRepository categoryRepository;
//-

    public void addCategory(Category cate){
        categoryRepository.save(cate);
    }

    public List<Category> getAll(){return categoryRepository.findAll();}


    public boolean updateCate(Integer id, Category readInfo){

        Category cate = categoryRepository.findCategoryById(id);
        if(cate==null) return false;

        cate.setName(readInfo.getName());


        categoryRepository.save(cate);
        return true;

    }

    public boolean deleteCate(Integer id){

        Category cate = categoryRepository.findCategoryById(id);
        if(cate==null) return false;

        categoryRepository.delete(cate);
        return true;

    }



}
