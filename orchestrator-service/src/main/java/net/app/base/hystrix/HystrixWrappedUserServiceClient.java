package net.app.base.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.app.base.feign.CampaignServiceClient;
import net.app.base.feign.UserServiceClient;
import net.app.base.projection.Campaign;
import net.app.base.projection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@Service
public class HystrixWrappedUserServiceClient implements UserServiceClient {

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackList")
    public List<Campaign> findUsers() {
        return userServiceClient.findUsers();
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackGet")
    public Campaign get(Integer id) {
        return userServiceClient.get(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackCreate")
    public Campaign create(User user) {
        return userServiceClient.create(user);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackUpdate")
    public Campaign update(Integer id, User user) {
        return userServiceClient.update(id, user);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackDelete")
    public void delete(Integer id) {
        userServiceClient.delete(id);
    }

    public List<Campaign> fallbackList() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar a lista de usuários");
    }

    public Campaign fallbackGet(Integer id) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para recuperar o usuário de id " + id);
    }

    public Campaign fallbackCreate(User user) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para criar o seguinte usuário: " + user.getName());
    }

    public Campaign fallbackUpdate(Integer id, User user) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para atualizar o seguinte usuário nome: " + user.getName() + " id: " + id) ;
    }

    public void fallbackDelete(Integer id) {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Falha ao acessar o serviço para deletar a campanha de id" + id);
    }
}
