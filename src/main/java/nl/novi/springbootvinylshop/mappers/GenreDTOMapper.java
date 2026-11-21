package nl.novi.springbootvinylshop.mappers;


import nl.novi.springbootvinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.springbootvinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.springbootvinylshop.entities.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GenreDTOMapper implements DTOMapper<GenreResponseDTO, GenreRequestDTO, Genre> {

    @Override
    public  GenreResponseDTO mapToDto(Genre genre) {

        return new GenreResponseDTO(
                genre.getId(),
                genre.getName(),
                genre.getDescription());
    }

    @Override
    public List<GenreResponseDTO> mapToDto(List<Genre> genres) {
                return genres
                        .stream()
                        .map(this::mapToDto)
                        .toList();
    }

    @Override
    public Genre mapToEntity(GenreRequestDTO genreRequestDTO) {
        Genre g = new Genre();

        g.setName(genreRequestDTO.name());
        g.setDescription(genreRequestDTO.description());

        return g;
    }
}
