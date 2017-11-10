package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.hystrix.HystrixWrappedUserServiceClient;
import net.app.base.projection.Campaign;
import net.app.base.projection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
@Api
public class UserServiceController {

    @Autowired
    private HystrixWrappedUserServiceClient userServiceClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Campaign> findUsers() {
        return userServiceClient.findUsers();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Campaign get(@PathVariable("id") Integer id) {
        return userServiceClient.get(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Campaign create(@RequestBody @Valid User user) {
        return userServiceClient.create(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public Campaign update(@PathVariable("id") Integer id, @RequestBody @Valid User user) {
        return userServiceClient.update(id, user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        userServiceClient.delete(id);
    }
}
