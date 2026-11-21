package nl.novi.springbootvinylshop.repository;

import nl.novi.springbootvinylshop.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
