package at.technikumwien.blogservice.Service;

import at.technikumwien.blogservice.Model.Attraction;
import at.technikumwien.blogservice.Model.Author;
import at.technikumwien.blogservice.Model.Blog;
import at.technikumwien.blogservice.Repository.AttractionRepository;
import at.technikumwien.blogservice.Repository.AuthorRepository;
import at.technikumwien.blogservice.Repository.BlogRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
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

    @Autowired
    private Source source;


    public List<Blog> retrieveAllBlogs() {
       return blogRepository.findAll();
    }

    public ResponseEntity<?> createNewBlog(Blog blog) {

        if(!checkBlog(blog))
        {
            return ResponseEntity.badRequest().build();
        }

        blog.setId(null);

        blog.setAuthor(this.checkAuthor(blog.getAuthor()));
        blog.setAttraction(this.checkAttraction(blog.getAttraction()));


        blog = blogRepository.save(blog);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).retrieve(blog.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    private boolean checkBlog(Blog blog) {

        if(blog.getTitle()==null || blog.getTitle().equals(""))
        {
            return false;
        }
        if(((blog.getAuthor().getLastname() ==null) || (blog.getAuthor().getLastname().equals(""))) && ((blog.getAuthor().getFirstname()==null)|| blog.getAuthor().getFirstname().equals(""))) {
            return false;
        }

        if(blog.getAuthor().getLastname() == null || blog.getAuthor().getLastname().equals(""))
        {
            return false;
        }

        if(blog.getAttraction().getName() == null || blog.getAttraction().getName().equals(""))
        {
            return false;
        }

        return true;
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

        if(blogRepository.existsById(id))
        {

            Blog blog= blogRepository.findById(id)
                    .orElseThrow(
                            () -> new EmptyResultDataAccessException("can't find blog with id " + id, 1)
                    );

            String blogText = blog.getBlogText();
            blog.setBlogText(null);

            notifyServices(blog);

            blog.setBlogText(blogText);

            return blog;
        }
        else return null;

    }

    private void notifyServices(Blog blog) {

        Message<Blog> message = MessageBuilder
                .withPayload(blog)
                .build();

        System.out.println(source.output()
                .send(message));

    }

}
