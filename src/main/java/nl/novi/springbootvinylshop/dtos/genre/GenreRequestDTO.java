package nl.novi.springbootvinylshop.dtos.genre;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenreRequestDTO(

        @NotBlank(message = "Naam mag niet leeg zijn")
        @Size(min=2, max=100, message = "Naam moet minimaal 2 karakters en maximaal 100 karakters bevatten" )
        String name,

        @Size(max= 255, message = "Description mag niet meer als 255 karakters bevatten")
        String description
) {

}
