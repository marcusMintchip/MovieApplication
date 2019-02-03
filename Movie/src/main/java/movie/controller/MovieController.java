package movie.controller;

import com.marcus.springcloud.MovieApplication.util.WebClientUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import movie.entity.Movie;
import movie.entity.User;
import movie.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {


//    @Autowired
//    RestTemplate restTemplate;

//    @GetMapping("/users/{id}")
//    public Mono<User> findById(@PathVariable Long id){
//        User user = this.restTemplate.getForObject("http://user/users/{id}",User.class,id);
//        return Mono.just(user);
////        Object[] objs= {};
////        return WebClientUtil.get(String.format("http://localhost:8080/users/%s",id),objs,User.class);
//    }

    @Autowired
    private UserFeignClient feignClient;
    @GetMapping("/users/{id}")
    @HystrixCommand(fallbackMethod = "defaultUser")
    public User findById(@PathVariable("id") Long id){
        return this.feignClient.findById(id);
    }

    //必须要返回相同的类型,通过加上异常参数可以打印出来
    public User defaultUser(@PathVariable("id") Long id,Throwable throwable){
        log.error("进入回退方法",throwable);
        //return new User(0L,"account0","Lucas",10,new BigDecimal(0));
        return new User();
    }
}
