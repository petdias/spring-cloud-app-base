package net.app.base.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.app.base.feign.ClubServiceClient;
import net.app.base.feign.UserCampaignServiceClient;
import net.app.base.projection.Club;
import net.app.base.projection.UserCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@Service
public class HystrixWrappedUserCampaignServiceClient implements UserCampaignServiceClient {

    @Autowired
    private UserCampaignServiceClient userCampaignServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "fallbacCreate")
    public UserCampaign create(UserCampaign userCampaign) {
        return userCampaignServiceClient.create(userCampaign);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbacFindBy")
    public List<UserCampaign> findBy(Integer userId) {
        return userCampaignServiceClient.findBy(userId);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbacDeleteBy")
    public void deleteByUser(Integer userId) {
        userCampaignServiceClient.deleteByUser(userId);
    }

    public UserCampaign fallbacCreate(UserCampaign userCampaign) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao criar a associação entre usuário e a campanha");
    }

    public List<UserCampaign> fallbacFindBy(Integer userId) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha recuperar as associações pelo id do usuário " + userId);
    }

    public void fallbacDeleteBy(Integer userId) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha remover as associações das campanhas de acordo com o usuário " + userId);
    }
}
