package at.technikumwien.statsservice.Service;

import at.technikumwien.statsservice.Model.Attraction;
import at.technikumwien.statsservice.Model.BlogIncoming;
import at.technikumwien.statsservice.Repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    @Autowired
    private AttractionRepository attractionRepository;

    public List<Attraction> retrieveRequestedListSize(int requestedListSize) {

        int listSize = (int) attractionRepository.count();

        if(requestedListSize < listSize )
        {
            return attractionRepository.findAllByOrderByCountThisMonthDesc().subList(0,(requestedListSize));
        }

        return attractionRepository.findAllByOrderByCountThisMonthDesc().subList(0,( listSize));

    }

    @StreamListener(Sink.INPUT)
    public void handleReaderEvent(BlogIncoming blogIncoming) {

    }

}
