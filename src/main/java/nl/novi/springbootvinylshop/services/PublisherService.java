package nl.novi.springbootvinylshop.services;

import nl.novi.springbootvinylshop.entities.Publisher;
import nl.novi.springbootvinylshop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAllPublishers() {
        return this.publisherRepository.findAll();
    }
    public Publisher findPublisherById(Long id) {
        Optional<Publisher> existingPublisher = this.publisherRepository.findById(id);
        if (existingPublisher.isPresent()){
            return  existingPublisher.get();
        } else {
            return null;
        }
    }

    public Publisher createPublisher(Publisher publisher) {
        return this.publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Long id, Publisher publisherInput){
        Publisher publisher = findPublisherById(id);

        if(publisher != null) {

            publisher.setName(publisherInput.getName());
            publisher.setAddress(publisherInput.getAddress());
            publisher.setContactDetails(publisherInput.getContactDetails());

            return this.publisherRepository.save(publisher);
        } else {
            return null;
        }
    }

    public void deletePublisher(Long id) {
        Publisher publisher = findPublisherById(id);

        if (publisher != null) {
            this.publisherRepository.delete(publisher);
        }
    }
}
