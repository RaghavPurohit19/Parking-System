package com.example.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String role;

    // Getters and setters
}
