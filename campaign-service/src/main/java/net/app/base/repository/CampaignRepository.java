package net.app.base.repository;

import net.app.base.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    @Query("select c from Campaign c where c.endDate >= CURRENT_DATE")
    List<Campaign> findCurrentCampaigns();

    @Query("select c from Campaign c where c.endDate >= CURRENT_DATE and c.team = ?1")
    List<Campaign> findByTeam(Integer team);
}
