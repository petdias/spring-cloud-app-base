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

    public Campaign fallbackGet(Integer id) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar a campanha de id " + id);
    }

    public Campaign fallbackCreate(Campaign campaign) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para criar a seguinte campanha: " + campaign.getName());
    }

    public Campaign fallbackUpdate(Integer id, Campaign campaign) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para atualizar a seguinte campanha nome: " + campaign.getName() + " id: " + id) ;
    }

    public void fallbackDelete(Integer id) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para deletar a campanha de id" + id);
    }
}
