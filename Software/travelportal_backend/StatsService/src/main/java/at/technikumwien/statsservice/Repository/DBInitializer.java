package at.technikumwien.statsservice.Repository;

import at.technikumwien.statsservice.Model.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInitializer {


    @Autowired AttractionRepository attractionRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady()
    {
        if(attractionRepository.count() == 0) {
            attractionRepository.saveAll(List.of(
                    new Attraction("Wiener Riesenrad", 23,50),
                    new Attraction("Schloss Schönbrunn", 22,100),
                    new Attraction("Schloss Belvedere", 40,70),
                    new Attraction("Stadtpark Wien", 5,25)
            ));
        }
    }
}
