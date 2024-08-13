package com.example.user.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 3, message = "Name must be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "username cannot be null or empty")
    @Size(min = 5, message = "username must be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password must not be empty")
    @Column(columnDefinition =("varchar(30) not null ") )
    private String password;

    @Email
    @Column(columnDefinition =("varchar(30) not null unique") )
    private String email;

    @NotEmpty(message = "role must not be empty")
    @Column(columnDefinition =("varchar(11) not null ") )
    @Pattern(regexp = "Admin|User", message = "role must be Admin or User ")
    @Check(constraints = "role IN('Admin','User')")
    private String role;

    @NotNull(message = "age must not be null")
    @Min(value = 15, message = "age must be more than 15")
    @Column(columnDefinition =("int not null ") )
    private int age;

}
