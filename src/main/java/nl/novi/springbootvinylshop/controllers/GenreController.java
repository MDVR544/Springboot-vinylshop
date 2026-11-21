package nl.novi.springbootvinylshop.controllers;


import nl.novi.springbootvinylshop.entities.Genre;
import nl.novi.springbootvinylshop.helpers.UrlHelper;
import nl.novi.springbootvinylshop.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;
    private final UrlHelper urlHelper;

    public GenreController(GenreService genreService, UrlHelper urlHelper) {
        this.genreService = genreService;
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getOneGenre(@PathVariable Long id){
        return ResponseEntity.ok(genreService.findGenreById(id));
    }
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres(){
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre){
        genreService.createGenre(genre);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(genre.getId())).body(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genreInput){
        return ResponseEntity.ok(genreService.updateGenre(id, genreInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable long id){
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
