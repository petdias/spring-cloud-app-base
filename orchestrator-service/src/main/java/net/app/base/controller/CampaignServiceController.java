package net.app.base.controller;

import net.app.base.projection.Campaign;
import net.app.base.hystrix.HystrixWrappedCampaignServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
@Api
public class CampaignServiceController {

    @Autowired
    private HystrixWrappedCampaignServiceClient campaignServiceClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/campaigns", method = RequestMethod.GET)
    public List<Campaign> findCurrentCampaigns() {
        return campaignServiceClient.findCurrentCampaigns();
    }

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.GET)
    public Campaign get(@PathVariable("id") Integer id) {
        return campaignServiceClient.get(id);
    }

    @RequestMapping(value = "/campaigns", method = RequestMethod.POST)
    public Campaign create(@RequestBody @Valid Campaign campaign) {
        return campaignServiceClient.create(campaign);
    }

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.PUT)
    public Campaign update(@PathVariable("id") Integer id, @RequestBody @Valid Campaign campaign) {
        return campaignServiceClient.update(id, campaign);
    }

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        campaignServiceClient.delete(id);
    }
}
