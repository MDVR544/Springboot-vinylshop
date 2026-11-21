package nl.novi.springbootvinylshop.repository;

import nl.novi.springbootvinylshop.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
