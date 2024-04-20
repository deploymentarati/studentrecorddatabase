package net.shadervertex.sms.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

    private Long id;
    
    @NotEmpty(message = "Username should not be empty")
    @Size(min=5,max=8,message="username must be minimum of 5 characters")
    private String username;
   
    
    @NotNull(message="password should not be null")
    @NotEmpty(message = "Password should not be empty")
   
    private String password;

    // Constructors, getters, and setters
    public UserDto() {
    }

    public UserDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}