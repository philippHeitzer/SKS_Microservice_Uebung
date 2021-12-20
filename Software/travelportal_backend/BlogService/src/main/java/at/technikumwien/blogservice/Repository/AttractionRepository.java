package at.technikumwien.blogservice.Repository;

import at.technikumwien.blogservice.Model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    boolean existsByName(String name);

    Attraction findByName(String name);
}
