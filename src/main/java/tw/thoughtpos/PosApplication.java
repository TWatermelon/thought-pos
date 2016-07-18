package tw.thoughtpos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PosApplication {
    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }
}
