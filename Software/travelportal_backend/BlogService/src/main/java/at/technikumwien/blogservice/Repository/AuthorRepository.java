package at.technikumwien.blogservice.Repository;

import at.technikumwien.blogservice.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByFirstnameAndLastname(String firstname,String lastname);

    Author findByFirstnameAndLastname(String firstname, String lastname);
}
