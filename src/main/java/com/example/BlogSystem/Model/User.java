package com.example.BlogSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Table(name = "users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "write username")
    @Size(min=3,max=20,message = "username should be 3-20 character")
    @Column(columnDefinition = "varchar(20) unique not null")
    private String username;

    @NotEmpty(message = "write username")
    @Size(min=8,max=25,message = "password should be 8-25 character")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-]).{8,25}$",message = "password must contain at least one uppercase & lowercase & one number + one special character")
    @Column(columnDefinition = "varchar(90) not null")
    private String password;

    @NotEmpty(message = "write email")
    @Email(message = "invalid email")
    private String email;

    @PastOrPresent(message = "invalid date")
    private LocalDate registerDate;

}
