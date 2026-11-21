package nl.novi.springbootvinylshop.dtos.publisher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublisherRequestDTO(

        @NotBlank(message = "Naam mag niet leeg zijn")
        @Size(max= 50, message = "Naam mag niet meer als 50 karakters bevatten")
        String name,

        String address,

        String contactDetails
) {
}
