package nl.novi.springbootvinylshop.services;

import nl.novi.springbootvinylshop.dtos.publisher.PublisherRequestDTO;
import nl.novi.springbootvinylshop.dtos.publisher.PublisherResponseDTO;
import nl.novi.springbootvinylshop.entities.Genre;
import nl.novi.springbootvinylshop.entities.Publisher;
import nl.novi.springbootvinylshop.exceptions.ResourceNotFoundException;
import nl.novi.springbootvinylshop.mappers.PublisherDTOMapper;
import nl.novi.springbootvinylshop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherDTOMapper  publisherDTOMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherDTOMapper publisherDTOMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherDTOMapper = publisherDTOMapper;
    }

    public List<PublisherResponseDTO> findAllPublishers() {
        List<Publisher> existingPublisherList = this.publisherRepository.findAll();

        return publisherDTOMapper.mapToDto(existingPublisherList);
    }

    public PublisherResponseDTO findPublisherById(Long id) {
        Optional<Publisher> existingPublisher = this.publisherRepository.findById(id);

        Publisher publisher = existingPublisher.orElseThrow(
                ()->new ResourceNotFoundException("Publisher not found!"));

            return  publisherDTOMapper.mapToDto(publisher);
    }

    public PublisherResponseDTO createPublisher(PublisherRequestDTO publisherDTO) {
        Publisher publisher = publisherDTOMapper.mapToEntity(publisherDTO);
        Publisher newPublisher = this.publisherRepository.save(publisher);

        return publisherDTOMapper.mapToDto(newPublisher);
    }

    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO publisherInput){
        Optional<Publisher> existingPublisher = this.publisherRepository.findById(id);

            Publisher updatedPublisher = existingPublisher.orElseThrow(
                    ()->new ResourceNotFoundException("Publisher not found!"));

            updatedPublisher.setName(publisherInput.name());
            updatedPublisher.setAddress(publisherInput.address());
            updatedPublisher.setContactDetails(publisherInput.contactDetails());

            Publisher savedPublisher = this.publisherRepository.save(updatedPublisher);

            return publisherDTOMapper.mapToDto(savedPublisher);
    }

    public void deletePublisher(Long id) {
        Optional<Publisher> existingPublisher = this.publisherRepository.findById(id);

        existingPublisher.ifPresent(this.publisherRepository::delete);
        }
    }

