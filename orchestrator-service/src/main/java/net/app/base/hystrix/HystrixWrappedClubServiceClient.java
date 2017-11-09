package net.app.base.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.app.base.feign.CampaignServiceClient;
import net.app.base.feign.ClubServiceClient;
import net.app.base.projection.Campaign;
import net.app.base.projection.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@Service
public class HystrixWrappedClubServiceClient implements ClubServiceClient {

    @Autowired
    private ClubServiceClient clubServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackList")
    public List<Club> findClubs() {
        return clubServiceClient.findClubs();
    }


    public List<Club> fallbackList() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar a lista de clubes");
    }
}
