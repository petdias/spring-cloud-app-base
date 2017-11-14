package net.app.base.mocks;

import net.app.base.model.Campaign;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectsMocks {

    public static Campaign getCampaign() {
        Campaign campaign = new Campaign();
        campaign.setId(1);
        campaign.setName("Campanha 1");
        campaign.setTeam(1);
        try {
            campaign.setStartDate(formatDate("01/10/2017"));
            campaign.setEndDate(formatDate("03/10/2017"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campaign;
    }

    public static Campaign getCampaign2() {
        Campaign campaign = new Campaign();
        campaign.setId(2);
        campaign.setName("Campanha 2");
        campaign.setTeam(1);
        try {
            campaign.setStartDate(formatDate("01/10/2017"));
            campaign.setEndDate(formatDate("02/10/2017"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campaign;
    }

    public static Campaign getCampaign3() {
        Campaign campaign = new Campaign();
        campaign.setId(3);
        campaign.setName("Campanha 3");
        campaign.setTeam(1);
        try {
            campaign.setStartDate(formatDate("01/10/2017"));
            campaign.setEndDate(formatDate("03/10/2017"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campaign;
    }

    public static Campaign getCampaign4() {
        Campaign campaign = new Campaign();
        campaign.setId(1);
        campaign.setName("Campanha 1");
        campaign.setTeam(1);
        try {
            campaign.setStartDate(formatDate("01/10/2017"));
            campaign.setEndDate(formatDate("04/10/2017"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campaign;
    }

    public static Campaign getCampaign5() {
        Campaign campaign = new Campaign();
        campaign.setId(1);
        campaign.setName("Campanha 1");
        campaign.setTeam(1);
        try {
            campaign.setStartDate(formatDate("01/10/2017"));
            campaign.setEndDate(formatDate("05/10/2017"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campaign;
    }

    public static Campaign getCampaign6() {
        Campaign campaign = new Campaign();
        campaign.setId(2);
        campaign.setName("Campanha 2");
        campaign.setTeam(1);
        try {
            campaign.setStartDate(formatDate("01/10/2017"));
            campaign.setEndDate(formatDate("04/10/2017"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campaign;
    }

    public static List<Campaign> getCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();

        campaigns.add(getCampaign2());
        campaigns.add(getCampaign());

        return campaigns;
    }

    public static Date formatDate(String data) throws Exception {
        if (data == null || data.equals(""))
            return null;
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (Date) formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
}
