package at.technikumwien.blogservice.Repository;

import at.technikumwien.blogservice.Model.Attraction;
import at.technikumwien.blogservice.Model.Author;
import at.technikumwien.blogservice.Model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInitializer {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired AuthorRepository authorRepository;

    @Autowired AttractionRepository attractionRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady()
    {
        List<Author> authors= authorRepository.saveAll(List.of(
                new Author("Philipp", "Heitzer"),
                new Author("Christian","Pfeffer")
                )
        );

        List<Attraction> attractions= attractionRepository.saveAll(List.of(
                new Attraction("Wiener Riesenrad","Riesenrad in Wien")
        ));

        Author author = authors.get(0);
        Attraction attraction= attractions.get(0);

        if(blogRepository.count() == 0) {
            blogRepository.saveAll(List.of(
                    new Blog("Neues vom Wiener Riesenrad", "Das altehrwürdige Riesenrad ist Wiener Wahrzeichen und gleichzeitig das weltweit bekannte Symbol für den Wurstelprater. Seine Silhouette ist weithin sichtbar und zieht jedes Jahr Zehntausende Besucher aus aller Herren Länder an.\n" +
                            "\n" +
                            "Das Riesenrad feierte 2017 bereits sein 120-Jahr-Jubiläum! Es wurde im Jahre 1896 von den Engländern Basset und Hitchins geplant und anlässlich des 50. Thronjubiläums von Kaiser Franz Joseph I., errichtet. Bereits ein Jahr später fand die Einweihung dieser Wiener Attraktion statt. Zu dieser Zeit war das Riesenrad die größte Anlage dieser Art und galt mit seinen 30 Gondeln als modern und wegweisend.\n" +
                            "\n" +
                            "Während des Krieges wurde das Riesenrad im Jahr 1944 durch Feuer und Bomben beinahe vollständig zerstört. Doch schon im Jahr darauf zählte es gemeinsam mit dem Wiener Stephansdom und der Wiener Staatsoper zu den ersten Objekten, die wieder hergestellt wurden. 1947 wurde es mit 15 Waggons wieder in Betrieb genommen.\n" +
                            "\n" +
                            "Besucher der Donaumetropole kommen um eine Rundfahrt mit dem Wiener Riesenrad kaum herum. Für einzigartige Feiern hoch über der Stadt, Jubiläen oder ein romantisches Dinner zu zweit bieten sich die zwei Luxusgondeln „Jubiläums-“ und „Kaiserwaggon“ an.",
                            author,
                            attraction)
            ));
        }
    }
}
