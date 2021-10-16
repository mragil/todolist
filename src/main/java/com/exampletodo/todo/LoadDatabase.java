package com.exampletodo.todo;

import com.exampletodo.todo.todolist.Todo;
import com.exampletodo.todo.todolist.TodoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TodoRepository repository) {

        return args -> {
            log.info("Preloading " + repository
                    .save(new Todo("Learn Spring Boot", "look for documentation, article and video about it", false)));
            log.info("Preloading " + repository.save(new Todo("Buy New TWS", "look for 1More or Lipertek", false)));
        };
    }

}
