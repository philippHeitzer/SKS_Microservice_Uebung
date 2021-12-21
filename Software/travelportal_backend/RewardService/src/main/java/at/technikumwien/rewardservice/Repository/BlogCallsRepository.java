package at.technikumwien.rewardservice.Repository;

import at.technikumwien.rewardservice.Model.BlogCalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogCallsRepository extends JpaRepository<BlogCalls, Long> {

}
