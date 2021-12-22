package at.technikumwien.statsservice;

import at.technikumwien.statsservice.Service.StatsService;
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
    StatsService statsService;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Scheduled(fixedDelay = 20000) //@Scheduled (cron="0 0 0 1 1/1 *") first day in month 0:00
    public void run() {
        System.out.println("RUN SCHEDULER");

        try {
            statsService.resetMonthlyCount();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
