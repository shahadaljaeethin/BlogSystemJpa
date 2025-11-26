package com.example.BlogSystem.Controller;

import com.example.BlogSystem.Api.ApiResponse;
import com.example.BlogSystem.Model.Category;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog system/category")
@RequiredArgsConstructor
public class CategoryController {
private final CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<?> addCate(@RequestBody @Valid Category cate, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        categoryService.addCategory(cate);
        return ResponseEntity.status(200).body(new ApiResponse("category added"));

    }
    @GetMapping("/get")
    public ResponseEntity<?> getAll(){return ResponseEntity.status(200).body(categoryService.getAll());}

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@RequestBody @Valid Category cate, Errors errors){

        if(errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if(categoryService.updateCate(id,cate))
            return ResponseEntity.status(200).body(new ApiResponse("Category is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("no id with #"+id));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCate(@PathVariable Integer id){
        if(categoryService.deleteCate(id))
            return ResponseEntity.status(200).body(new ApiResponse("category is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("no id with #"+id));
    }


//(7)
@GetMapping("/get most")
public ResponseEntity<?> getMostUsedCate(){
        if(categoryService.getMostUsedCategory().equals("-1")) return ResponseEntity.status(400).body(new ApiResponse("there is no most used category yet"));
     return ResponseEntity.status(200).body(new ApiResponse("most category used is "+categoryService.getMostUsedCategory()));

}


}
