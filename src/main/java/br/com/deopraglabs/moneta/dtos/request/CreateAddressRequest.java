package br.com.deopraglabs.moneta.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
public class CreateAddressRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Street cannot be blank")
    private final String street;

    @NotBlank(message = "Number cannot be blank")
    private final String number;

    @NotBlank(message = "Neighborhood cannot be blank")
    private final String neighborhood;

    @NotBlank(message = "City cannot be blank")
    private final String city;

    @Size(min = 2, max = 2, message = "State must have 2 characters")
    @NotBlank(message = "State cannot be blank")
    private final String state;

    @NotBlank(message = "Postal code cannot be blank")
    private final String postalCode;

    @NotBlank(message = "Country cannot be blank")
    private final String country;

    private final String complement;
}
