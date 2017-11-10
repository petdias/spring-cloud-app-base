
function CampaignsDataService($resource) {
  return $resource('/app/campaigns/:id', null,
          {'get':   {method:'GET'},
          'save':   {method:'POST'},
          'query':  {method:'GET', isArray:true},
          'update': {method:'PUT'},
          'delete': {method:'DELETE'}});
}
export default ['$resource', CampaignsDataService];

