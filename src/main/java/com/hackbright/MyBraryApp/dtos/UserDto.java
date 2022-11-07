package com.hackbright.MyBraryApp.dtos;


import com.hackbright.MyBraryApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Set<BookDto> bookDtoSet = new HashSet<>();

    public UserDto(User user) {
        //if the values are not null they will be saved to the corresponding variable (except user_id)
        if (user.getId() != null) {
            this.id = user.getId();
        }
        if(user.getUsername() != null) {
            this.username = getUsername();
        }
        if (user.getPassword() != null) {
            this.password = getPassword();
        }
    }
}
