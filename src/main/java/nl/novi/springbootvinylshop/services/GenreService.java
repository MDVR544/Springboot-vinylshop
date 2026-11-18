package nl.novi.springbootvinylshop.services;

import nl.novi.springbootvinylshop.entities.Genre;
import nl.novi.springbootvinylshop.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAllGenres() {
        return this.genreRepository.findAll();
    }
    public Genre findGenreById(Long id) {
        Optional<Genre> existingGenre = this.genreRepository.findById(id);
        if (existingGenre.isPresent()){
            return  existingGenre.get();
        } else {
            return null;
        }
    }

    public Genre createGenre(Genre genre) {
        return this.genreRepository.save(genre);
    }

    public Genre updateGenre(Long id, Genre genreInput){
        Genre genre = findGenreById(id);

        if(genre != null) {

            genre.setName(genreInput.getName());
            genre.setDescription(genreInput.getDescription());

            return this.genreRepository.save(genre);
        } else {
            return null;
        }
    }

    public void deleteGenre(Long id) {
        Genre genre = findGenreById(id);

        if (genre != null) {
            this.genreRepository.delete(genre);
        }
    }
}
