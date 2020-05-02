package ogustaflor.com.github.logbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LogbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogbookApplication.class, args);
    }

}
