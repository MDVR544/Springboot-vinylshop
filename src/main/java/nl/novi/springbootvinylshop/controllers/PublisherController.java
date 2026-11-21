package nl.novi.springbootvinylshop.controllers;

import nl.novi.springbootvinylshop.entities.Publisher;
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
    public ResponseEntity<Publisher> getOnePublisher(@PathVariable Long id){
        return ResponseEntity.ok(publisherService.findPublisherById(id));
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers(){
        return ResponseEntity.ok(publisherService.findAllPublishers());
    }

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisherInput){
        Publisher newPublisher = publisherService.createPublisher(publisherInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.getId())).body(newPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisherInput){
        Publisher newPublisher = publisherService.updatePublisher(id, publisherInput);
        return ResponseEntity.ok(newPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id){
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
