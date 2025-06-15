package br.com.deopraglabs.moneta.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class CreateUserRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Username cannot be blank")
    private final String username;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email cannot be blank")
    private final String email;

    @Min(value = 11, message = "CPF must have 11 digits")
    @NotBlank(message = "CPF cannot be blank")
    private final String cpf;

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @Pattern(
        regexp="^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
        message="Password must have at least 1 letter, number and special character"
    )
    @Min(value = 8, message = "Password must have at least 8 characters")
    @NotBlank(message = "Password cannot be blank")
    private final String password;

    @Min(value = 10, message = "Phone must have 10 digits")
    @NotBlank(message = "Phone cannot be blank")
    private final String phone;

    @Past(message = "Birth date must be in the past")
    @NotBlank(message = "Birth date cannot be blank")
    private final LocalDate birthDate;

    @NotNull(message = "Address cannot be null")
    private final CreateAddressRequest address;

    @NotEmpty(message = "Permissions cannot be empty")
    private final List<String> permissions;
}
