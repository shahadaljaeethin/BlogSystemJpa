package com.example.BlogSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "content is missing")
    @Size(min=2, message = "comment minimum is two characters") //one emoji takes two character
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @PastOrPresent(message = "date invalid")
    private LocalDate commentDate;

    @NotNull(message = "log in to comment")
    private Integer userID;
    @NotNull(message = "choose post to comment")
    private Integer postID;

}
