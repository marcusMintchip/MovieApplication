package user.controller;

import com.netflix.client.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.ipc.netty.http.server.HttpServerRequest;
import user.entity.User;
import user.repository.UserRepository;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id, HttpRequest request){
        request.getHttpHeaders().containsHeader("user");
        return this.repository.findById(id).orElse(new User());
    }


}
