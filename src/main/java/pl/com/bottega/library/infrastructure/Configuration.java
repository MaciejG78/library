package pl.com.bottega.library.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pl.com.bottega.library.application.BookLendingProcess;
import pl.com.bottega.library.application.BookManagementProcess;
import pl.com.bottega.library.application.impl.StandardBookManagementProcess;
import pl.com.bottega.library.application.impl.StandardBookLendingProcess;
import pl.com.bottega.library.model.BookRepository;
import pl.com.bottega.library.model.NumberGenerator;

import java.util.concurrent.Executor;

/**
 * Created by maciek on 02.04.2017.
 */
@org.springframework.context.annotation.Configuration
@EnableAsync
public class Configuration extends AsyncConfigurerSupport {

    @Bean
    public BookLendingProcess bookLendingProcess(BookRepository bookRepository){
        return new StandardBookLendingProcess(bookRepository);
    }

    @Bean
    public BookManagementProcess bookManagementProcess(NumberGenerator numberGenerator, BookRepository bookRepository){
        return new StandardBookManagementProcess(numberGenerator, bookRepository);
        }

    @Bean
    public NumberGenerator numberGenerator(){
        return new NumberGenerator();
    }

    @Bean
    public BookRepository bookRepository() {
        return new JPABookRepository();
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Library-Async-Executor");
        executor.initialize();
        return executor;
    }

}
