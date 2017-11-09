package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.hystrix.HystrixWrappedCampaignServiceClient;
import net.app.base.hystrix.HystrixWrappedClubServiceClient;
import net.app.base.projection.Campaign;
import net.app.base.projection.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
@Api
public class ClubServiceController {

    @Autowired
    private HystrixWrappedClubServiceClient hystrixWrappedClubServiceClient;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/clubs", method = RequestMethod.GET)
    public List<Club> findClubs() {
        return hystrixWrappedClubServiceClient.findClubs();
    }
}
