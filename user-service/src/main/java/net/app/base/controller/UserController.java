package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.model.User;
import net.app.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api
@RefreshScope
@RestController
public class UserController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Integer id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable("id") Integer id, @RequestBody @Valid User user) {
        if (user.getId() == null || !user.getId().equals(id)) {
            return null;
        }

        return userService.update(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }
}
