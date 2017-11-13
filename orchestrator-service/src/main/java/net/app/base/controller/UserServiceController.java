package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.hystrix.HystrixWrappedCampaignServiceClient;
import net.app.base.hystrix.HystrixWrappedUserCampaignServiceClient;
import net.app.base.hystrix.HystrixWrappedUserServiceClient;
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

@RefreshScope
@RestController
@Api
public class UserServiceController {

    @Autowired
    private HystrixWrappedUserServiceClient userServiceClient;

    @Autowired
    private HystrixWrappedCampaignServiceClient campaignServiceClient;

    @Autowired
    private HystrixWrappedUserCampaignServiceClient userCampaignServiceClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> findUsers() {
        return userServiceClient.findUsers();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Integer id) {
        User user = userServiceClient.get(id);
        user.setCampaigns(getCampaigns(user));
        return user;
    }

    @RequestMapping(value = "/users/check/{email}", method = RequestMethod.GET)
    public User findByEmail(@PathVariable("email") String email) {
        return userServiceClient.findByEmail(email);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody @Valid User user) {
        User u = findByEmail(user.getEmail());

        if (ObjectUtils.isEmpty(u)) {
            u = userServiceClient.create(user);
            u.setCampaigns(getCampaignByTeam(u.getTeam()));
        } else {
            u.setCampaigns(getCampaigns(user));
        }

        return u;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable("id") Integer id, @RequestBody @Valid User user) {
        return userServiceClient.update(id, user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        userServiceClient.delete(id);
    }

    private List<Campaign> getCampaignByTeam(Integer idTeam) {
        return  campaignServiceClient.findBy(idTeam);
    }

    private List<Campaign> getCampaigns(User user) {
        List<Campaign> campaigns = getCampaignByTeam(user.getTeam());
        List <UserCampaign> userCampaigns = userCampaignServiceClient.findBy(user.getId());
        if (userCampaigns.isEmpty()) {
            campaigns.forEach(c -> {
                userCampaigns.forEach(uc -> {
                    if (uc.getCampaignId().equals(c.id)) {
                        c.setSelected(Boolean.TRUE);
                    }
                });
            });
        }

        return campaigns;
    }
}
