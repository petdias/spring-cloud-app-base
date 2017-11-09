package net.app.base.feign;

import net.app.base.projection.Club;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "club-service")
public interface ClubServiceClient {

    @RequestMapping(value = "/clubs", method = RequestMethod.GET)
    List<Club> findClubs();
}
