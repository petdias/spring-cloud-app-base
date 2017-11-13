package net.app.base.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.app.base.feign.CampaignServiceClient;
import net.app.base.feign.UserCampaignServiceClient;
import net.app.base.feign.UserServiceClient;
import net.app.base.projection.Campaign;
import net.app.base.projection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@Service
public class HystrixWrappedUserServiceClient implements UserServiceClient {

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private UserCampaignServiceClient userCampaignServiceClient;

    @Autowired
    private CampaignServiceClient campaignServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackList")
    public List<User> findUsers() {
        return userServiceClient.findUsers();
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackGet")
    public User get(Integer id) {
        return userServiceClient.get(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackFindByEmail")
    public User findByEmail(String email) {
        return userServiceClient.findByEmail(email);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackCreate")
    public User create(User user) {
        return userServiceClient.create(user);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackUpdate")
    public User update(Integer id, User user) {
        return userServiceClient.update(id, user);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackDelete")
    public void delete(Integer id) {
        userServiceClient.delete(id);
    }

    public List<User> fallbackList() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar a lista de usuários");
    }

    public User fallbackGet(Integer id) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar o usuário de id " + id);
    }

    public User fallbackFindByEmail(String email) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar o usuário pelo email " + email);
    }

    public User fallbackCreate(User user) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para criar o seguinte usuário: " + user.getName());
    }

    public User fallbackUpdate(Integer id, User user) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para atualizar o seguinte usuário nome: " + user.getName() + " id: " + id) ;
    }

    public void fallbackDelete(Integer id) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para deletar a campanha de id" + id);
    }
}
