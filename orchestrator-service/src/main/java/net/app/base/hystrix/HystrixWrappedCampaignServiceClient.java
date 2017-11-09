package net.app.base.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import net.app.base.projection.Campaign;
import net.app.base.feign.CampaignServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;


@Service
public class HystrixWrappedCampaignServiceClient implements CampaignServiceClient {

    @Autowired
    private CampaignServiceClient campaignServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackList")
    public List<Campaign> findCurrentCampaigns() {
        return campaignServiceClient.findCurrentCampaigns();
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackGet")
    public Campaign get(Integer id) {
        return campaignServiceClient.get(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackCreate")
    public Campaign create(Campaign campaign) {
        return campaignServiceClient.create(campaign);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackUpdate")
    public Campaign update(Integer id, Campaign campaign) {
        return campaignServiceClient.update(id, campaign);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackDelete")
    public void delete(Integer id) {
        campaignServiceClient.delete(id);
    }

    public List<Campaign> fallbackList() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar a lista de campanhas");
    }

    public Campaign fallbackGet() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar a campanha");
    }

    public Campaign fallbackCreate() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para criar uma campanha");
    }

    public Campaign fallbackUpdate() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para atualizar a campanha");
    }

    public void fallbackDelete() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para deletar a campanha");
    }
}
