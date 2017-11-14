package net.app.base.service;

import net.app.base.model.Campaign;
import net.app.base.repository.CampaignRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Calendar;
import java.util.Date;
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
        // valida se já existe alguma camanha dentro do período.
        periodValidation(campaign);

        // Salva a campanha.
        return campaignRepository.save(campaign);
    }

    public Campaign update(Campaign campaign) {

        // Valida se já existe alguma campanha com a mesma data fim.
        validEndDate(campaign);

        // Salva a modificação.
        return campaignRepository.save(campaign);
    }

    public void delete(Integer id) {
        campaignRepository.delete(id);
    }

    protected void periodValidation(Campaign campaign) {
        // Recupera todas as campanhas que estão dentro do período selecionado.
        List<Campaign> campaigns = campaignRepository.findByPeriod(campaign.getStartDate(), campaign.getEndDate());
        // Casa exista alguma campanha será necessário adicionar um dia na data fim das campanhas.
        if (!campaigns.isEmpty()) {
            // percorre a lista de campanhas adicionando 1 dia na data fim de cada campanha.
            campaigns.forEach(c -> {
                c.setEndDate(addOneDay(c.getEndDate()));
                // Caso a campanha possua a mesma data fim da campanha selecionada, será necessario add mais um dia.
                if (c.getEndDate().equals(campaign.getEndDate())) {
                    c.setEndDate(addOneDay(c.getEndDate()));
                }
                // atualiza as datas das campanhas.
                update(c);
            });
        }
    }

    protected void validEndDate(Campaign campaign) {
        // verifica se existe alguma campanha com a mesma data fim.
        Campaign c = campaignRepository.findByEndDate(campaign.getEndDate(), campaign.getId());
        if (!ObjectUtils.isEmpty(c)) {
            // Caso exista será necessario add mais um dia na data fim.
            campaign.setEndDate(addOneDay(campaign.getEndDate()));
        }
    }

    // Add 1 dia em uma data.
    private Date addOneDay(Date date) {
        DateTime dt = new DateTime(date);
        DateTime newEndDate = dt.plusDays(1);

        return newEndDate.toDate();
    }
}
