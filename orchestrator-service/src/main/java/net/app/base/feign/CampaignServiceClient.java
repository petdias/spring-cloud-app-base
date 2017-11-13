package net.app.base.feign;

import net.app.base.projection.Campaign;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "campaign-service")
public interface CampaignServiceClient {

    @RequestMapping(value = "/campaigns", method = RequestMethod.GET)
    List<Campaign> findCurrentCampaigns();

    @RequestMapping(value = "/campaigns/team/{id}", method = RequestMethod.GET)
    List<Campaign> findBy(@PathVariable("id") Integer id);

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.GET)
    Campaign get(@PathVariable("id") Integer id);

    @RequestMapping(value = "/campaigns", method = RequestMethod.POST)
    Campaign create(@RequestBody @Valid Campaign campaign);

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.PUT)
    Campaign update(@PathVariable("id") Integer id, @RequestBody @Valid Campaign campaign);

    @RequestMapping(value = "/campaigns/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Integer id);
}
