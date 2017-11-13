package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.hystrix.HystrixWrappedClubServiceClient;
import net.app.base.hystrix.HystrixWrappedUserCampaignServiceClient;
import net.app.base.projection.Campaign;
import net.app.base.projection.User;
import net.app.base.projection.UserCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RefreshScope
@RestController
@RequestMapping(value = "users-campaigns")
public class UserCampaignController {

    @Autowired
    private HystrixWrappedUserCampaignServiceClient hystrixWrappedUserCampaignServiceClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public UserCampaign create(@RequestBody @Valid UserCampaign userCampaign) {
        return hystrixWrappedUserCampaignServiceClient.create(userCampaign);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<UserCampaign> findBy(@PathVariable("userId") Integer userId) {
        return hystrixWrappedUserCampaignServiceClient.findBy(userId);
    }

    @RequestMapping(value = "/users-campaigns/{userId}", method = RequestMethod.DELETE)
    public void deleteByUser(@PathVariable("userId") Integer userId) {
        hystrixWrappedUserCampaignServiceClient.deleteByUser(userId);
    }

    @RequestMapping(value = "/users-campaigns/association", method = RequestMethod.POST)
    public void connect(@RequestBody User user) {
        deleteByUser(user.getId());
        if (!ObjectUtils.isEmpty(user.getCampaigns())) {
            createAssociations(user);
        }
    }

    private void createAssociations(User user) {
        user.getCampaigns().forEach(c -> {
            if (c.getSelected()) {
                UserCampaign userCampaign = new UserCampaign();
                userCampaign.setCampaignId(c.id);
                userCampaign.setUserId(user.getId());

                create(userCampaign);
            }
        });
    }
}
