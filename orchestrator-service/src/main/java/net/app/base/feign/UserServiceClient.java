package net.app.base.feign;

import net.app.base.projection.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    List<User> findUsers();

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    User get(@PathVariable("id") Integer id);

    @RequestMapping(value = "/users/check/{email}", method = RequestMethod.GET)
    User findByEmail(@PathVariable("email") String email);

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    User create(@RequestBody @Valid User user);

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    User update(@PathVariable("id") Integer id, @RequestBody User user);

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Integer id);
}
