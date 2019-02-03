package user;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import user.entity.User;
import user.repository.UserRepository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@SpringBootApplication
    public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }

    @Bean
    ApplicationRunner init(UserRepository repository){
        return args ->{
          User user1 = new User(1L,"account1","Mary",20,new BigDecimal(180.00));
          User user2 = new User(2L,"account2","Lauren",26,new BigDecimal(30.00));
          User user3 = new User(3L,"account3","Peter",23,new BigDecimal(210.00));
            Stream.of(user1,user2,user3).forEach(repository::save);
        };
    }
}
