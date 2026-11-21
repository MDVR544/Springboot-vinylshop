package nl.novi.springbootvinylshop.mappers;

import nl.novi.springbootvinylshop.dtos.publisher.PublisherRequestDTO;
import nl.novi.springbootvinylshop.dtos.publisher.PublisherResponseDTO;
import nl.novi.springbootvinylshop.entities.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PublisherDTOMapper implements DTOMapper<PublisherResponseDTO, PublisherRequestDTO, Publisher>{

    @Override
    public PublisherResponseDTO mapToDto(Publisher publisher) {

        return new PublisherResponseDTO(
                publisher.getId(),
                publisher.getName(),
                publisher.getAddress(),
                publisher.getContactDetails());
    }

    @Override
    public List<PublisherResponseDTO> mapToDto(List<Publisher> publishers) {
        return publishers
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public Publisher mapToEntity(PublisherRequestDTO publisherRequestDTO) {
        Publisher p = new Publisher();

        p.setName(publisherRequestDTO.name());
        p.setAddress(publisherRequestDTO.address());
        p.setContactDetails(publisherRequestDTO.contactDetails());

        return p;
    }
}
