package nl.novi.springbootvinylshop.services;

import nl.novi.springbootvinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.springbootvinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.springbootvinylshop.entities.Genre;
import nl.novi.springbootvinylshop.exceptions.ResourceNotFoundException;
import nl.novi.springbootvinylshop.mappers.GenreDTOMapper;
import nl.novi.springbootvinylshop.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreDTOMapper genreDTOMapper;

    public GenreService(GenreRepository genreRepository, GenreDTOMapper genreDTOMapper) {
        this.genreRepository = genreRepository;
        this.genreDTOMapper = genreDTOMapper;
    }

    public List<GenreResponseDTO> findAllGenres() {
        List<Genre> existingGenreList = this.genreRepository.findAll();
        System.out.println(genreRepository.findAll().size());
        return genreDTOMapper.mapToDto(existingGenreList);
    }

    public GenreResponseDTO findGenreById(Long id) {
        Optional<Genre> existingGenre = this.genreRepository.findById(id);

        Genre genre = existingGenre.orElseThrow(
                ()->new ResourceNotFoundException("Genre not found!"));

            return  genreDTOMapper.mapToDto(genre);
        }


    public GenreResponseDTO createGenre(GenreRequestDTO genreDTO) {
        Genre genre = genreDTOMapper.mapToEntity(genreDTO);
        Genre newGenre = this.genreRepository.save(genre);

        return genreDTOMapper.mapToDto(newGenre);
    }

    public GenreResponseDTO updateGenre(Long id, GenreRequestDTO genreInput){
        Optional<Genre> existingGenre = this.genreRepository.findById(id);

        Genre updatedGenre = existingGenre.orElseThrow(
                ()->new ResourceNotFoundException("Genre not found!"));

            updatedGenre.setName(genreInput.name());
            updatedGenre.setDescription(genreInput.description());

            Genre savedGenre = this.genreRepository.save(updatedGenre);

            return genreDTOMapper.mapToDto(savedGenre);
        }


    public void deleteGenre(Long id) {
        Optional<Genre> existingGenre = this.genreRepository.findById(id);

        existingGenre.ifPresent(this.genreRepository::delete);
    }
}

