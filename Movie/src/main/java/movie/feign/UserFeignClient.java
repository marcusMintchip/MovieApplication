package movie.feign;

import feign.Logger;
import movie.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user",configuration = UserFeignConfig.class)
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") Long id);
}

class UserFeignConfig{
    @Bean
    public Logger.Level logger(){
        return Logger.Level.FULL;
    }
}

class UserFeinConfiguration{
    @Bean
    public Logger.Level logger(){
        return Logger.Level.HEADERS;
    }
}