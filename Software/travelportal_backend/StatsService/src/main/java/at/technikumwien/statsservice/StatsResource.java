package at.technikumwien.statsservice;

import at.technikumwien.statsservice.Model.Attraction;
import at.technikumwien.statsservice.Service.StatsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/resources/stats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Log
public class StatsResource {

    @Autowired
    private StatsService statsService;



    @GetMapping("/{requestedListSize}")
    public List<Attraction> retrieveRequestedListSize(@PathVariable int requestedListSize)
    {
        return statsService.retrieveRequestedListSize(requestedListSize);
    }

}
