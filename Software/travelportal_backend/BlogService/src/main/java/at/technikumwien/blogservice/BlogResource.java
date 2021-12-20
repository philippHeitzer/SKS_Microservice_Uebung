package at.technikumwien.blogservice;

import at.technikumwien.blogservice.Model.Blog;
import at.technikumwien.blogservice.Service.BlogService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/travelportal/blog", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Log
public class BlogResource {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> retrieveAll(){
        return blogService.retrieveAllBlogs();
    }

    @PostMapping
    public ResponseEntity<?> createNew(@RequestBody Blog blog) {

        return blogService.createNewBlog(blog);
    }
}
