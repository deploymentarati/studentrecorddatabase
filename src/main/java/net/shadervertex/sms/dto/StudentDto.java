package net.shadervertex.sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class StudentDto {
    
    private Long id; // Student ID

    @NotEmpty(message = "First name should not be empty")
    private String firstName; // Student's first name

    @NotEmpty(message = "Last name should not be empty")
    private String lastName; // Student's last name

    @Email(message = "Email should be in email format")
    private String email; // Student's email address
    
    // Constructors
    
    public StudentDto() {
        // Default constructor
    }
    
    public StudentDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    // Getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
