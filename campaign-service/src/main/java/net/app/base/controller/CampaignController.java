package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.model.Campaign;
import net.app.base.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api
@RefreshScope
@RestController
public class CampaignController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private CampaignService campaignService;

    @RequestMapping(value = "/campaigns", method = RequestMethod.GET)
    public List<Campaign> findCurrentCampaigns() {
        return campaignService.findCurrentCampaigns();
    }

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.GET)
    public Campaign get(@PathVariable("id") Integer id) {
        return campaignService.get(id);
    }

    @RequestMapping(value = "/campaigns", method = RequestMethod.POST)
    public Campaign create(@RequestBody @Valid Campaign campaign) {
        return campaignService.create(campaign);
    }

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.PUT)
    public Campaign update(@PathVariable("id") Integer id, @RequestBody @Valid Campaign campaign) {
        if (campaign.getId() == null || !campaign.getId().equals(id)) {
            return null;
        }

        return campaignService.update(campaign);
    }

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        campaignService.delete(id);
    }
}
