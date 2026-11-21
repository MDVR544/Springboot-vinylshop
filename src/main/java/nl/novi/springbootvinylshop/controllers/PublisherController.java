package nl.novi.springbootvinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.springbootvinylshop.dtos.publisher.PublisherRequestDTO;
import nl.novi.springbootvinylshop.dtos.publisher.PublisherResponseDTO;
import nl.novi.springbootvinylshop.helpers.UrlHelper;
import nl.novi.springbootvinylshop.services.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;
    private final UrlHelper urlHelper;

    public PublisherController(PublisherService publisherService, UrlHelper urlHelper) {
        this.publisherService = publisherService;
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> getOnePublisher(@PathVariable Long id){
        return ResponseEntity.ok(publisherService.findPublisherById(id));
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublishers(){
        return ResponseEntity.ok(publisherService.findAllPublishers());
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(@RequestBody @Valid PublisherRequestDTO publisherInput){
        PublisherResponseDTO newPublisher = publisherService.createPublisher(publisherInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.id())).body(newPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> updatePublisher(@PathVariable Long id, @RequestBody @Valid PublisherRequestDTO publisherInput){
        PublisherResponseDTO updatedPublisher = publisherService.updatePublisher(id, publisherInput);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id){
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
