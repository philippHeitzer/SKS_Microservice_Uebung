package at.technikumwien.rewardservice.Service;

import at.technikumwien.rewardservice.Model.Author;
import at.technikumwien.rewardservice.Model.BlogCalls;
import at.technikumwien.rewardservice.Model.BlogIncoming;
import at.technikumwien.rewardservice.Repository.AuthorRepository;
import at.technikumwien.rewardservice.Repository.BlogCallsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class RewardsService {

    @Autowired
    BlogCallsRepository blogCallsRepository;

    @Autowired
    AuthorRepository authorRepository;

    @StreamListener(Sink.INPUT)
    public void handleReaderEvent(BlogIncoming blogIncoming) {
        log.info("handleReaderEvent() >> recievedBlog=" + blogIncoming);

        if(blogCallsRepository.existsById(blogIncoming.getId()))
        {
            validateAuthor(blogIncoming.getAuthor());
            Optional<BlogCalls> blogCalls = blogCallsRepository.findById(blogIncoming.getId());
            blogCalls.get().setCalls((blogCalls.get().getCalls()+1));
            System.out.println( "IFtrue: " +blogCalls.get());
            blogCallsRepository.save(blogCalls.get());
        }
        else{
            BlogCalls blogCalls = convertIncomingBlog(blogIncoming);
            System.out.println( "IF false: " +blogCalls);
            blogCallsRepository.save(blogCalls);
        }

    }

    private void validateAuthor(Author author) {
        if(!authorRepository.existsById(author.getId()))
        {
            System.out.println("new author");
            authorRepository.save(author);
        }
    }

    public BlogCalls convertIncomingBlog(BlogIncoming blogIncoming){
        return new BlogCalls(blogIncoming.getAuthor(),  0);
    }

    public void rewardAuthors() {

        HashMap<Long, Integer> authorRewardMap= createAuthorRewardMap();

        authorRewardMap=updateRewards(authorRewardMap);

        rewardAuthorsPrint(authorRewardMap);
    }

    private HashMap<Long, Integer> updateRewards(HashMap<Long, Integer> authorRewardMap) {
        List<BlogCalls> blogCallsList= blogCallsRepository.findAll();
        for (BlogCalls blogCalls : blogCallsList) {
            if(authorRewardMap.containsKey(blogCalls.getAuthor().getId()))
            {
                authorRewardMap.put(blogCalls.getAuthor().getId(),
                        (blogCalls.getCalls().intValue() + authorRewardMap.get(blogCalls.getAuthor().getId()).intValue()));

                updateCallsInDatabase(blogCalls);
            }
        }
        return authorRewardMap;
    }

    private void updateCallsInDatabase(BlogCalls blogCalls) {
        blogCalls.setCalls(0);
        blogCallsRepository.save(blogCalls);
    }


    public HashMap<Long, Integer> createAuthorRewardMap ()
    {
        List<Author> authorList = authorRepository.findAll();
        HashMap<Long, Integer> authorRewardMap= new HashMap<>();

        for(Author author : authorList)
        {
            authorRewardMap.put(author.getId(),0);
        }

        return authorRewardMap;
    }

    public void rewardAuthorsPrint( HashMap<Long, Integer> authorRewardMap)
    {
        for(Long key : authorRewardMap.keySet())
        {
            System.out.println("Author: " + key + " recieves: " +  (authorRewardMap.get(key)/100.00) + " €");
        }
    }

}
