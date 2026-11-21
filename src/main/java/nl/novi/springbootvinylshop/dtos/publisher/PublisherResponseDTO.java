package nl.novi.springbootvinylshop.dtos.publisher;

public record PublisherResponseDTO(

        Long id,
        String name,
        String address,
        String contactDetails
) {

}
