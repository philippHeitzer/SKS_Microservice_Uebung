package at.technikumwien.statsservice.Service;

import at.technikumwien.statsservice.Model.Attraction;
import at.technikumwien.statsservice.Model.BlogIncoming;
import at.technikumwien.statsservice.Repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Long attractionId= blogIncoming.getAttraction().getId();

       if(attractionRepository.existsById(attractionId))
       {
           Optional<Attraction> attraction = attractionRepository.findById(attractionId);
           attraction.get().setCountThisMonth(attraction.get().getCountThisMonth()+1);
           attraction.get().setCountAllTime(attraction.get().getCountAllTime()+1);
           attractionRepository.save(attraction.get());
       }

    }

    public void resetMonthlyCount() {
        List<Attraction> attractionList= attractionRepository.findAll();
        System.out.println(attractionList);

        for(Attraction attraction : attractionList)
        {
            attraction.setCountThisMonth(0);
        }

        attractionRepository.saveAll(attractionList);
    }
}
