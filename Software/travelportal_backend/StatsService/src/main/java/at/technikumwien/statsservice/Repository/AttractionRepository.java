package at.technikumwien.statsservice.Repository;

import at.technikumwien.statsservice.Model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

   List<Attraction> findAllByOrderByCountThisMonthDesc();
}
