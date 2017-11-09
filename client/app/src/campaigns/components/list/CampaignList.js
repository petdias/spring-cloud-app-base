import CampaignListController from './CampaignListController'

export default {
  name : 'campaignList',
  config : {
    bindings    : {  campaigns: '<', selected : '<', showDetails : '&onSelected' },
    templateUrl : 'src/campaigns/components/list/CampaignList.html',
      controller       : [ '$mdDialog', '$log', '$resource', CampaignListController ]
  }
};