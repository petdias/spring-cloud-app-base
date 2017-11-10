
import CampaignsDataService from 'src/campaigns/services/CampaignsDataService';
import ClubsDataService from 'src/campaigns/services/ClubsDataService';

import CampaignList from 'src/campaigns/components/list/CampaignList';
import UserDetails from 'src/campaigns/components/details/CampaignDetails';

export default angular
  .module("campaigns", ['ngMaterial', 'ngResource'])

  .component(CampaignList.name, CampaignList.config)
  .component(UserDetails.name, UserDetails.config)

  .service("CampaignsDataService", CampaignsDataService)
  .service("ClubsDataService", ClubsDataService);
