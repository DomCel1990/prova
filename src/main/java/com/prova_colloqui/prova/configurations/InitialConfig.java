package com.prova_colloqui.prova.configurations;

import com.prova_colloqui.prova.entities.Autore;
import com.prova_colloqui.prova.repositories.AutoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialConfig {

   @Bean
    CommandLineRunner initialDB(AutoreRepository autoreRepository)  {
       return args -> {
           if (autoreRepository.count() == 0) {
               Autore autore = Autore.builder()
                       .name("Tolkien")
                       .build();

               autoreRepository.save(autore);
           }
       };
   }
}
