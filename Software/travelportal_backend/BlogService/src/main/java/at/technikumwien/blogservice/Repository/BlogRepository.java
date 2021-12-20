package at.technikumwien.blogservice.Repository;

import at.technikumwien.blogservice.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
