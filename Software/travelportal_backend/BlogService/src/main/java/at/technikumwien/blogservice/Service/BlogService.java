package at.technikumwien.blogservice.Service;

import at.technikumwien.blogservice.Model.Blog;
import at.technikumwien.blogservice.Repository.BlogRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.URI;
import java.util.List;

@Service
@Log
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;


    public List<Blog> retrieveAllBlogs() {
       return blogRepository.findAll();
    }

    public ResponseEntity<?> createNewBlog(Blog blog) {


        blog.setId(null);

        blog = blogRepository.save(blog);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).retrieve(blog.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    public Blog retrieve(long id) {
        log.info("retrieve() >> id=" + id);

        return blogRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }
}
