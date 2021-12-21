package at.technikumwien.rewardservice.Repository;

import at.technikumwien.rewardservice.Model.Author;
import at.technikumwien.rewardservice.Model.BlogCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInitializer {
    @Autowired
    private BlogCallsRepository blogCallsRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady()
    {
        List<Author> authors= authorRepository.saveAll(List.of(
                new Author("Philipp", "Heitzer"),
                new Author("Christian","Pfeffer")
                )
        );


        Author author = authors.get(0);

        if(blogCallsRepository.count() == 0) {
            blogCallsRepository.saveAll(List.of(
                    new BlogCalls((long) 1,0,author)
            ));
        }
    }
}
