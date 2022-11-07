package com.hackbright.MyBraryApp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackbright.MyBraryApp.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity //tells Spring that this class is being mapped to a data source
@Table(name = "users") //setting the table the objects will be mapped to
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    //onetomany is half the relationship with the read/to read lists within Hibernate
    //json managed reference to handle half of mitigating infinite recursion when we deliver the
    //resource as JSON (JS object notation) through our RESTful API endpoint
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Book> bookSet = new HashSet<>();

    //making sets that act as containers for our lists
    //chose set because each item within a set is unique
    //prevents 2 copies of the list object from being added

    //constructor that accepts the associated DTO as an argument
    // - contains conditional logic for preventing null pointer exceptions
    public User(UserDto userDto) {
        if (userDto.getUsername() != null) {
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        }
    }


}
