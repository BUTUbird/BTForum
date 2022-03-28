package org.butu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @author BUTU
 */
@SpringBootApplication
public class ForumApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ForumApp.class, args);
    }
}
