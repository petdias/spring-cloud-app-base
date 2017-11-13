package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.model.User;
import net.app.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api
@RefreshScope
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Integer id) {
        return userService.get(id);
    }

    @RequestMapping(value = "/check/{email}", method = RequestMethod.GET)
    public User findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable("id") Integer id, @RequestBody @Valid User user) {
        if (user.getId() == null || !user.getId().equals(id)) {
            return null;
        }

        return userService.update(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }
}
