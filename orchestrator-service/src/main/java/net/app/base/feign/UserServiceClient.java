package net.app.base.feign;

import net.app.base.projection.Campaign;
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
    List<Campaign> findUsers();

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    Campaign get(@PathVariable("id") Integer id);

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    Campaign create(@RequestBody @Valid User user);

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    Campaign update(@PathVariable("id") Integer id, @RequestBody User user);

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Integer id);
}
