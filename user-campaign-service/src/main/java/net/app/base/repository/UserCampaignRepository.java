package net.app.base.repository;

import net.app.base.model.UserCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCampaignRepository extends JpaRepository<UserCampaign, Integer> {

    List<UserCampaign> findByUserId(Integer userId);
}
