package net.app.base.service;

import net.app.base.model.Campaign;
import net.app.base.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public List<Campaign> findCurrentCampaigns() {
        return campaignRepository.findCurrentCampaigns();
    }

    public List<Campaign> findTeam(Integer team) {
        return campaignRepository.findByTeam(team);
    }

    public Campaign get(Integer id) {
        return campaignRepository.getOne(id);
    }

    public Campaign create(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign update(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void delete(Integer id) {
        campaignRepository.delete(id);
    }
}
