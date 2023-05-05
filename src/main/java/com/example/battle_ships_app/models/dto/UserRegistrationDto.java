package com.example.battle_ships_app.models.dto;
import com.example.battle_ships_app.validations.FieldMatch;
import com.example.battle_ships_app.validations.UniqueUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match"
)
public class UserRegistrationDto {

    @NotBlank(message = "Username should not be empty.")
    @Size(min = 3, max = 10, message = "Length must be between 3 and 10 symbols")
    private String username;

    @NotBlank(message = "Full name should not be empty.")
    @Size(min = 5, max = 20, message = "Length must be between 5 and 20 symbols")
    private String fullName;

    @NotBlank(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
    @UniqueUserName(message = "User email should be unique.")
    private String email;

    @NotBlank(message = "Password should not be empty.")
    @Size(min = 3, message = "Length must be at least 3 symbols")
    private String password;

    @NotBlank
    @Size(min = 3)
    private String confirmPassword;

    public UserRegistrationDto() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
