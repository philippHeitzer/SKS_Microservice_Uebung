package at.technikumwien.rewardservice.Repository;

import at.technikumwien.rewardservice.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {


}
