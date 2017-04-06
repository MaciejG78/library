package pl.com.bottega.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableAsync
public class LibraryApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LibraryApplication.class, args);
    }
}

