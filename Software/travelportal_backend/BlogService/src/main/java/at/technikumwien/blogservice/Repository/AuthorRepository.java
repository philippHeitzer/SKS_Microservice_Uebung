package at.technikumwien.blogservice.Repository;

import at.technikumwien.blogservice.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
