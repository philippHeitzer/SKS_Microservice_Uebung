package at.technikumwien.blogservice.Service;

import at.technikumwien.blogservice.Model.Attraction;
import at.technikumwien.blogservice.Model.Author;
import at.technikumwien.blogservice.Model.Blog;
import at.technikumwien.blogservice.Repository.AttractionRepository;
import at.technikumwien.blogservice.Repository.AuthorRepository;
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

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private AuthorRepository authorRepository;


    public List<Blog> retrieveAllBlogs() {
       return blogRepository.findAll();
    }

    public ResponseEntity<?> createNewBlog(Blog blog) {


        blog.setId(null);

        blog.setAuthor(this.checkAuthor(blog.getAuthor()));
        blog.setAttraction(this.checkAttraction(blog.getAttraction()));


        blog = blogRepository.save(blog);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).retrieve(blog.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    private Attraction checkAttraction(Attraction attraction) {
        if(attractionRepository.existsByName(attraction.getName()))
        {
            return attractionRepository.findByName(attraction.getName());
        }
        else
        {
            attraction.setId(null);
            return attractionRepository.save(attraction);
        }
    }

    private Author checkAuthor(Author author) {

        if(authorRepository.existsByFirstnameAndLastname(author.getFirstname(),author.getLastname()))
        {
            return authorRepository.findByFirstnameAndLastname(author.getFirstname(), author.getLastname());
        }
        else
        {
            author.setId(null);
            return authorRepository.save(author);
        }

    }

    public Blog retrieve(long id) {
        log.info("retrieve() >> id=" + id);

        return blogRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }
}
