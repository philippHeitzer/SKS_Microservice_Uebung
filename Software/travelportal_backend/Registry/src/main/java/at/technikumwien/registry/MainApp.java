package at.technikumwien.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


// see http://localhost:8761/
// see http://localhost:8761/eureka/apps
// see http://localhost:8761/eureka/apps/blogservice

@EnableEurekaServer
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

}
