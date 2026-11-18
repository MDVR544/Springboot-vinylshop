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
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher){
        publisherService.createPublisher(publisher);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(publisher.getId())).body(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisherInput){
        return ResponseEntity.ok(publisherService.updatePublisher(id, publisherInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable long id){
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
