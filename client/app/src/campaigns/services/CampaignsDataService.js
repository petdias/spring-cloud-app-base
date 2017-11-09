
function CampaignsDataService($resource) {
  return $resource('/app/campaigns/');
}
export default ['$resource', CampaignsDataService];

