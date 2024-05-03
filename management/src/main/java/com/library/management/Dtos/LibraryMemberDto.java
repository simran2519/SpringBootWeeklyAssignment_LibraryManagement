package com.library.management.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibraryMemberDto
{
    @NotEmpty(message = "Username cannot be empty")
    @Size(min=3,max = 20,message = "min characters are 3 and maximum characters can be upto 20")
    @Pattern(regexp = "^\\S+(?:\\s\\S+)?$", message = "Username can contain only one space")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?=.*[A-Z])(?=.*[a-z]).*$", message = "Invalid Password !")
    private String password;

    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Role can only contain letters")
    private String role;

    //For Emails----------------------------------------------------
//    @NotEmpty(message = "Email cannot be empty")
//    @Email("Incorrect email")
    //       -----------------------OR--------------------
//    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email!")
//    //           -----------------OR----------------
//    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$" , message = "Invalid Email!")
//    private String email;

}