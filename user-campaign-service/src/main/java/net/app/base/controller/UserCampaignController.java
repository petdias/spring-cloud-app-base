package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.model.UserCampaign;
import net.app.base.repository.UserCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api
@RefreshScope
@RestController
public class UserCampaignController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private UserCampaignRepository userCampaignRepository;

    @RequestMapping(value = "/users-campaigns", method = RequestMethod.POST)
    public UserCampaign create(@RequestBody @Valid UserCampaign userCampaign) {
        return userCampaignRepository.save(userCampaign);
    }

    @RequestMapping(value = "/users-campaigns/{userId}", method = RequestMethod.GET)
    public List<UserCampaign> findBy(@PathVariable("userId") Integer userId) {
        return userCampaignRepository.findByUserId(userId);
    }

}
