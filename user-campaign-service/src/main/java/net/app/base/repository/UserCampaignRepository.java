package net.app.base.repository;

import net.app.base.model.UserCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserCampaignRepository extends JpaRepository<UserCampaign, Integer> {

    List<UserCampaign> findByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("delete from UserCampaign u where u.userId = ?1")
    void deleteByUserId(Integer userId);
}
