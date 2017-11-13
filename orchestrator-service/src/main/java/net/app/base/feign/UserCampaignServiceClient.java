package net.app.base.feign;

import net.app.base.projection.UserCampaign;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "user-campaign-service")
public interface UserCampaignServiceClient {

    @RequestMapping(value = "/users-campaigns", method = RequestMethod.POST)
    UserCampaign create(@RequestBody @Valid UserCampaign userCampaign);

    @RequestMapping(value = "/users-campaigns/{userId}", method = RequestMethod.GET)
    List<UserCampaign> findBy(@PathVariable("userId") Integer userId);

    @RequestMapping(value = "/users-campaigns/{userId}", method = RequestMethod.DELETE)
    void deleteByUser(@PathVariable("userId") Integer userId);
}
