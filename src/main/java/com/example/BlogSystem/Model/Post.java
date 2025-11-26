package com.example.BlogSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "enter title for this blog")
    @Size(min=8,max=25,message = "title should be between 8-25")
    @Column(columnDefinition = "varchar(25) not null")
    private String title;

    @NotEmpty(message = "enter title for this blog")
    @Size(min=16,max=255,message = "content should be between 16-255 character")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;


    @PastOrPresent(message = "invalid date")
    private LocalDate publishDate;

    @NotEmpty(message = "please log in to comment")
    private Integer userID;
    @NotEmpty(message = "choose category for this blog")
    private Integer categoryID;

}
