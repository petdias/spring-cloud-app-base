package net.app.base.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.app.base.CampaignApplication;
import net.app.base.mocks.ObjectsMocks;
import net.app.base.model.Campaign;
import net.app.base.repository.CampaignRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CampaignApplication.class)
public class CampaignServiceTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CampaignService campaignService;

    @MockBean
    private CampaignRepository campaignRepository;


    @Test
    public void periodValidation_shouldModifyEndDate() throws Exception {
        Campaign campaign = ObjectsMocks.getCampaign3();

        given(campaignRepository.findByPeriod(campaign.getStartDate(), campaign.getEndDate())).willReturn(ObjectsMocks.getCampaigns());
        given(campaignRepository.findByEndDate(ObjectsMocks.getCampaign6().getEndDate(), ObjectsMocks.getCampaign6().getId())).willReturn(null);
        given(campaignRepository.findByEndDate(ObjectsMocks.getCampaign4().getEndDate(), ObjectsMocks.getCampaign4().getId())).willReturn(ObjectsMocks.getCampaign6());

       campaignService.periodValidation(campaign);

        // Verifico se a aplicação chamou o metodo de save 2 vezes. Para atualizar a Data fim da Campanha 1 e Data fim da Campanha 2.
        verify(campaignRepository, times(2)).save(any(Campaign.class));
    }

    @Test
    public void validEndDate_shouldAdd1DayEndDate() throws Exception {
        Campaign campaign = ObjectsMocks.getCampaign();

        given(campaignRepository.findByEndDate(campaign.getEndDate(), campaign.getId())).willReturn(ObjectsMocks.getCampaign3());

        campaignService.validEndDate(campaign);

        Assert.assertEquals(campaign.getEndDate(), ObjectsMocks.formatDate("04/10/2017"));
    }

    @Test
    public void validEndDate_shouldKeepEndDate() throws Exception {
        Campaign campaign = ObjectsMocks.getCampaign();

        given(campaignRepository.findByEndDate(campaign.getEndDate(), campaign.getId())).willReturn(null);

        campaignService.validEndDate(campaign);

        Assert.assertEquals(campaign.getEndDate(), ObjectsMocks.formatDate("03/10/2017"));
    }

    @Test
    public void update_shouldModifyAdd1dayEndDate() throws Exception {
        Campaign campaign = ObjectsMocks.getCampaign();

        given(campaignRepository.findByEndDate(campaign.getEndDate(), campaign.getId())).willReturn(ObjectsMocks.getCampaign3());

        campaignService.update(campaign);

        Assert.assertEquals(campaign.getEndDate(), ObjectsMocks.formatDate("04/10/2017"));
    }

    @Test
    public void create_shouldModifyEndDateAndCreateNewCampaign() throws Exception {
        Campaign campaign = ObjectsMocks.getCampaign3();

        given(campaignRepository.findByPeriod(campaign.getStartDate(), campaign.getEndDate())).willReturn(ObjectsMocks.getCampaigns());
        given(campaignRepository.findByEndDate(ObjectsMocks.getCampaign6().getEndDate(), ObjectsMocks.getCampaign6().getId())).willReturn(null);
        given(campaignRepository.findByEndDate(ObjectsMocks.getCampaign4().getEndDate(), ObjectsMocks.getCampaign4().getId())).willReturn(ObjectsMocks.getCampaign6());

        campaignService.create(campaign);

        // Verifico se a aplicação chamou o metodo de save 2 vezes. Para atualizar a Data fim da Campanha 1 e Data fim da Campanha 2.
        // e uma vez para criar a campanha nova
        verify(campaignRepository, times(3)).save(any(Campaign.class));
    }
}
