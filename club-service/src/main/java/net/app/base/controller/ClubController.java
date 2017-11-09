package net.app.base.controller;

import io.swagger.annotations.Api;
import net.app.base.model.Club;
import net.app.base.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api
@RefreshScope
@RestController
public class ClubController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private ClubRepository clubRepository;

    @RequestMapping(value = "/clubs", method = RequestMethod.GET)
    public List<Club> findClubs() {
        return clubRepository.findAll();
    }
}
