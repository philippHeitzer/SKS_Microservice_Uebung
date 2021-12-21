package at.technikumwien.rewardservice.Service;

import at.technikumwien.rewardservice.Model.Blog;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Service
@Log
public class RewardsService {

    @StreamListener(Sink.INPUT)
    public void handleReaderEvent(Blog blog) {
        log.info("handleReaderEvent() >> recievedBlog=" + blog);

    }
}
