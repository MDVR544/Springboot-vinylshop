package nl.novi.springbootvinylshop.controllers;


import jakarta.validation.Valid;
import nl.novi.springbootvinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.springbootvinylshop.dtos.genre.GenreResponseDTO;
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
    public ResponseEntity<GenreResponseDTO> getOneGenre( @PathVariable Long id){
        return ResponseEntity.ok(genreService.findGenreById(id));
    }


    @GetMapping
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres(){
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    @PostMapping
    public ResponseEntity<GenreResponseDTO> createGenre(@RequestBody @Valid GenreRequestDTO genreInput) {
        GenreResponseDTO newGenre = genreService.createGenre(genreInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newGenre.id())).body(newGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> updateGenre(@PathVariable Long id, @RequestBody @Valid GenreRequestDTO genreInput){
        GenreResponseDTO updatedGenre = genreService.updateGenre(id, genreInput);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id){
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
