package at.technikumwien.blogservice;

import at.technikumwien.blogservice.Model.Blog;
import at.technikumwien.blogservice.Service.BlogService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/resources/blogs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Log
public class BlogResource {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> retrieveAll(){
        return blogService.retrieveAllBlogs();
    }

    @GetMapping("/{id}")
    public Object retrieve(@PathVariable Long id){
        Blog blog = blogService.retrieve(id);
        if(blog != null)
        {
            return blog;
        }
        System.out.println("Error!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createNew(@RequestBody Blog blog) {

        return blogService.createNewBlog(blog);
    }
}
