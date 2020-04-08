package tn.isims.secservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.isims.secservice.entities.AppRole;
import tn.isims.secservice.service.AccountService;

import java.util.stream.Stream;

@SpringBootApplication
public class SecserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(SecserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
            accountService.save(new AppRole(null,"USER"));
            accountService.save(new AppRole(null,"ADMIN"));

            Stream.of("user1","user2","user3","admin").forEach(s -> {
               accountService.saveUser(s,"1234","1234");
            });

            accountService.addRoleToUser("admin","ADMIN");
        };

    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
