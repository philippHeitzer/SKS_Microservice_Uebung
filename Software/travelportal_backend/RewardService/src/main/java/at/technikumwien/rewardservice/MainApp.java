package at.technikumwien.rewardservice;

import at.technikumwien.rewardservice.Service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableBinding(Sink.class)
@EnableScheduling
public class MainApp {

    @Autowired
    private RewardsService rewardsService;

    public static void main(String[] args) {

        SpringApplication.run(MainApp.class, args);
    }

    @Scheduled(fixedDelay = 100000,initialDelay = 100000) //@Scheduled (cron = 0 15 10? * 6l ") - last friday in month 10:15 am
    public void run() {
        System.out.println();

        try {
            rewardsService.rewardAuthors();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
